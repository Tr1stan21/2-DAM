package dao;

import db.DBConnection;
import model.LineaPedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LineaPedidoDAOImpl implements LineaPedidoDAO {

    private static final String INSERT =
            "INSERT INTO LineaPedido (id_pedido, id_producto, cantidad, precio_unitario) " +
            "VALUES (?, ?, ?, ?)";

    private static final String FIND_BY_PEDIDO =
            "SELECT id_linea, id_pedido, id_producto, cantidad, precio_unitario " +
            "FROM lineapedido WHERE id_pedido = ?";

    @Override
    public int insert(LineaPedido lp) throws Exception {
        int idGenerado = -1;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, lp.getIdPedido());
            stmt.setInt(2, lp.getIdProducto());
            stmt.setInt(3, lp.getCantidad());
            stmt.setDouble(4, lp.getPrecioUnitario());

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
    public List<LineaPedido> findByPedido(int idPedido) throws Exception {
        List<LineaPedido> lista = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_BY_PEDIDO)) {

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
                    lista.add(lp);
                }
            }
        }

        return lista;
    }
}
