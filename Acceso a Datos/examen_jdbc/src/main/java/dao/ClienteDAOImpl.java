package dao;

import db.DBConnection;
import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    private static final String INSERT =
            "INSERT INTO Cliente (nombre, email) VALUES (?, ?)";

    private static final String UPDATE =
            "UPDATE Cliente SET nombre = ?, email = ? WHERE id_cliente = ?";

    private static final String DELETE =
            "DELETE FROM Cliente WHERE id_cliente = ?";

    private static final String FIND_BY_ID =
            "SELECT id_cliente, nombre, email FROM Cliente WHERE id_cliente = ?";

    private static final String FIND_ALL =
            "SELECT id_cliente, nombre, email FROM Cliente ORDER BY id_cliente";

    @Override
    public int insert(Cliente c) throws Exception {
        int idGenerado = -1;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, c.getNombre());
            stmt.setString(2, c.getEmail());

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
    public boolean update(Cliente c) throws Exception {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE)) {

            stmt.setString(1, c.getNombre());
            stmt.setString(2, c.getEmail());
            stmt.setInt(3, c.getIdCliente());

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
    public Cliente findById(int id) throws Exception {
        Cliente c = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_BY_ID)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    c = new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nombre"),
                            rs.getString("email")
                    );
                }
            }
        }

        return c;
    }

    @Override
    public List<Cliente> findAll() throws Exception {
        List<Cliente> lista = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("email")
                ));
            }
        }

        return lista;
    }
}
