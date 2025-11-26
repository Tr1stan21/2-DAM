package dao;

import db.DBConnection;
import model.Cliente;
import model.LineaPedido;
import model.Pedido;
import model.Producto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImpl implements PedidoDAO {

    private static final String INSERT_PEDIDO =
            "INSERT INTO Pedido (fecha, id_cliente) VALUES (?, ?)";

    private static final String FIND_PEDIDO_BY_ID =
            "SELECT id_pedido, fecha, id_cliente FROM Pedido WHERE id_pedido = ?";

    private static final String FIND_ALL_PEDIDOS =
            "SELECT id_pedido, fecha, id_cliente FROM Pedido ORDER BY id_pedido";

    private static final String INSERT_LINEA =
            "INSERT INTO LineaPedido (id_pedido, id_producto, cantidad, precio_unitario) " +
            "VALUES (?, ?, ?, ?)";

    private static final String FIND_LINEAS_BY_PEDIDO =
            "SELECT lp.id_linea, lp.id_pedido, lp.id_producto, lp.cantidad, lp.precio_unitario, " +
            "       p.nombre AS nombre_producto, p.precio AS precio_producto " +
            "FROM LineaPedido lp " +
            "INNER JOIN Producto p ON lp.id_producto = p.id_producto " +
            "WHERE lp.id_pedido = ?";


    @Override
    public int insert(Pedido p) throws Exception {
        int idGenerado = -1;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_PEDIDO, Statement.RETURN_GENERATED_KEYS)) {

            LocalDate fecha = p.getFecha();
            stmt.setDate(1, java.sql.Date.valueOf(fecha));
            stmt.setInt(2, p.getIdCliente());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                }
            }
        }

        return idGenerado;
    }

    @Override
    public Pedido findById(int id) throws Exception {
        Pedido pedido = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_PEDIDO_BY_ID)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pedido = new Pedido(
                            rs.getInt("id_pedido"),
                            rs.getDate("fecha").toLocalDate(),
                            rs.getInt("id_cliente")
                    );
                }
            }
        }

        return pedido;
    }

    @Override
    public List<Pedido> findAll() throws Exception {
        List<Pedido> lista = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_ALL_PEDIDOS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Pedido(
                        rs.getInt("id_pedido"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("id_cliente")
                ));
            }
        }

        return lista;
    }


    /**
     * Devuelve las líneas de un pedido. Aquí hacemos un JOIN con Producto.
     * Si quieres usar también nombre/precio del producto, puedes:
     *  - añadir un campo Producto a LineaPedido, o
     *  - crear un DTO específico.
     */
    @Override
    public List<LineaPedido> findLineasByPedido(int idPedido) throws Exception {
        List<LineaPedido> lista = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_LINEAS_BY_PEDIDO)) {

            stmt.setInt(1, idPedido);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LineaPedido lp = new LineaPedido(
                            rs.getInt("id_linea"),
                            rs.getInt("id_pedido"),
                            rs.getInt("id_producto"),
                            rs.getInt("cantidad"),
                            rs.getDouble("precio_unitario")
                    );

                    // Si amplías LineaPedido con un Producto, aquí podrías hacer algo como:
                    // Producto prod = new Producto(
                    //         rs.getInt("id_producto"),
                    //         rs.getString("nombre_producto"),
                    //         rs.getDouble("precio_producto"),
                    //         0 // id_categoria no viene en la consulta
                    // );
                    // lp.setProducto(prod);

                    lista.add(lp);
                }
            }
        }

        return lista;
    }

    /**
     * Crea un pedido completo (cabecera + líneas) en una sola transacción.
     */
    @Override
    public void crearPedidoCompleto(Cliente c, List<LineaPedido> lineas) throws Exception {
        Connection conn = null;
        PreparedStatement stmtPedido = null;
        PreparedStatement stmtLinea = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // 1. Insertar pedido
            stmtPedido = conn.prepareStatement(INSERT_PEDIDO, Statement.RETURN_GENERATED_KEYS);
            LocalDate hoy = LocalDate.now();
            stmtPedido.setDate(1, java.sql.Date.valueOf(hoy));
            stmtPedido.setInt(2, c.getIdCliente());
            stmtPedido.executeUpdate();

            int idPedidoGenerado = -1;
            try (ResultSet rs = stmtPedido.getGeneratedKeys()) {
                if (rs.next()) {
                    idPedidoGenerado = rs.getInt(1);
                }
            }

            if (idPedidoGenerado == -1) {
                throw new SQLException("No se pudo obtener el id_pedido generado.");
            }

            // 2. Insertar líneas
            stmtLinea = conn.prepareStatement(INSERT_LINEA);

            for (LineaPedido lp : lineas) {
                stmtLinea.setInt(1, idPedidoGenerado);
                stmtLinea.setInt(2, lp.getIdProducto());
                stmtLinea.setInt(3, lp.getCantidad());
                stmtLinea.setDouble(4, lp.getPrecioUnitario());
                stmtLinea.addBatch();
            }

            stmtLinea.executeBatch();

            // 3. Commit
            conn.commit();

        } catch (Exception ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            throw ex;
        } finally {
            if (stmtLinea != null) {
                try { stmtLinea.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (stmtPedido != null) {
                try { stmtPedido.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
