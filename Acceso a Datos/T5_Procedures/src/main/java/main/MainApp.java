package main;

import connection.DatabaseConnection;

import java.sql.*;

public class MainApp {

    static void main() {
        try(Connection c = DatabaseConnection.getConnection()) {
            CallableStatement cs = c.prepareCall("{ call iva_nota(?) }");
            cs.setDouble(1, 21.00);
            cs.execute();
            System.out.println("Procedimiento ejecutado correctamente.");

            String query = "SELECT producto, iva, notas FROM productos";
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(
                        rs.getString("producto") + " ; " +
                                rs.getDouble("iva") + " ; " +
                                rs.getString("notas")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
