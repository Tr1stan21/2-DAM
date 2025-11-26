package dao;

import db.DBConnection;
import model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    private static final String INSERT =
            "INSERT INTO Producto (nombre, precio, id_categoria) VALUES (?, ?, ?)";

    private static final String UPDATE =
            "UPDATE Producto SET nombre = ?, precio = ?, id_categoria = ? WHERE id_producto = ?";

    private static final String DELETE =
            "DELETE FROM Producto WHERE id_producto = ?";

    private static final String FIND_BY_ID =
            "SELECT id_producto, nombre, precio, id_categoria FROM Producto WHERE id_producto = ?";

    private static final String FIND_ALL =
            "SELECT id_producto, nombre, precio, id_categoria FROM Producto ORDER BY id_producto";
    
    

    @Override
    public int insert(Producto p) throws Exception {
        int idGenerado = -1;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, p.getNombre());
            stmt.setDouble(2, p.getPrecio());
            stmt.setInt(3, p.getIdCategoria());

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
    public boolean update(Producto p) throws Exception {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE)) {

            stmt.setString(1, p.getNombre());
            stmt.setDouble(2, p.getPrecio());
            stmt.setInt(3, p.getIdCategoria());
            stmt.setInt(4, p.getIdProducto());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Producto findById(int id) throws Exception {
        Producto p = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_BY_ID)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p = new Producto(
                            rs.getInt("id_producto"),
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getInt("id_categoria")
                    );
                }
            }
        }

        return p;
    }

    @Override
    public List<Producto> findAll() throws Exception {
        List<Producto> lista = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("id_categoria")
                ));
            }
        }

        return lista;
    }
    
    /**
     * Importación masiva de productos desde una lista (simula CSV) usando batch + transacción.
     */
    @Override
    public void importarProductos(List<Producto> productos) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement(INSERT);

            for (Producto p : productos) {
                stmt.setString(1, p.getNombre());
                stmt.setDouble(2, p.getPrecio());
                stmt.setInt(3, p.getIdCategoria());
                stmt.addBatch();
            }

            stmt.executeBatch();
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
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
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
