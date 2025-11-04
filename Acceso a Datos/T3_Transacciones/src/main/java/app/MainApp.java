package app;

import connection.DatabaseConnection;
import util.CsvFileReader;

import java.sql.*;

public class MainApp {
    public static void main(String[] args) {
        CsvFileReader reader = new CsvFileReader();
        int idProfesorActual = 0;

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            for (String[] lines : reader.getLines()) {
                if (lines[0].equals("Profesor")) {
                    connection.commit();
                    String query = """
                        INSERT INTO profesor (
                            nif_p,
                            nombre,
                            especialidad,
                            telefono
                        )
                        VALUES (?, ?, ?, ?)
                        """;
                    try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

                        ps.setString(1, lines[1]);
                        ps.setString(2, lines[2]);
                        ps.setString(3, lines[3]);
                        ps.setString(4, lines[4]);
                        ps.executeUpdate();

                        try (ResultSet rs = ps.getGeneratedKeys()) {
                            if (rs.next()) {
                                idProfesorActual = rs.getInt(1);
                            }
                        }
                    } catch (SQLException e) {
                        connection.rollback();
                        throw new RuntimeException(e);
                    }
                }
                else if (lines[0].equals("Asignatura")) {
                    String query = """
                        INSERT INTO asignatura (
                            codAsignatura,
                            nombre,
                            idProfesor
                        )
                        VALUES (?, ?, ?)
                        """;
                    try (PreparedStatement ps = connection.prepareStatement(query)) {
                        ps.setString(1, lines[1]);
                        ps.setString(2, lines[2]);
                        ps.setInt(3, idProfesorActual);
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        connection.rollback();
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
