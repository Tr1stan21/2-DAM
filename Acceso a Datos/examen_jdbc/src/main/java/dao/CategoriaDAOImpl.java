package dao;

import db.DBConnection;
import model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO {

    private static final String INSERT =
            "INSERT INTO categoria (nombre) VALUES (?)";

    private static final String UPDATE =
            "UPDATE categoria SET nombre = ? WHERE id_categoria = ?";

    private static final String DELETE =
            "DELETE FROM categoria WHERE id_categoria = ?";

    private static final String FIND_BY_ID =
            "SELECT id_categoria, nombre FROM categoria WHERE id_categoria = ?";

    private static final String FIND_ALL =
            "SELECT id_categoria, nombre FROM categoria ORDER BY id_categoria";

    @Override
    public int insert(Categoria c) throws Exception {
        int idGenerado = -1;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, c.getNombre());
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
    public boolean update(Categoria c) throws Exception {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE)) {

            stmt.setString(1, c.getNombre());
            stmt.setInt(2, c.getIdCategoria());

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
    public Categoria findById(int id) throws Exception {
        Categoria categoria = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_BY_ID)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categoria = new Categoria(
                            rs.getInt("id_categoria"),
                            rs.getString("nombre")
                    );
                }
            }
        }

        return categoria;
    }

    @Override
    public List<Categoria> findAll() throws Exception {
        List<Categoria> lista = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Categoria(
                        rs.getInt("id_categoria"),
                        rs.getString("nombre")
                ));
            }
        }

        return lista;
    }
}
