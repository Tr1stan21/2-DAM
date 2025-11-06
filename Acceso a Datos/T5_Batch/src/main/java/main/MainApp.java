import connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) {


        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {
            c.setAutoCommit(false);
            try {
                c.commit();
                ps.setString(1, "Pepe");
                ps.setString(2, "Rodríguez");
                ps.addBatch();

                ps.setString(1, "Mario");
                ps.setString(2, "Botella");
                ps.addBatch();

                ps.setString(1, "Luis");
                ps.setString(2, "Botella");
                ps.addBatch();

                ps.setString(1, "Alba");
                ps.setString(2, "Botella");
                ps.addBatch();

                ps.setString(1, "Lucia");
                ps.setString(2, "Botella");
                ps.addBatch();

                ps.setString(1, "Antonio");
                ps.setString(2, "Botella");
                ps.addBatch();

                int[] failures = ps.executeBatch();
                for(int fail : failures) {
                    if(fail <= 0){
                        c.rollback();
                    }
                }
                ps.clearBatch();
            } catch (SQLException e) {
                c.rollback();
                throw new RuntimeException(e);
            }


            try {
                c.commit();
                ps.setString(1, "Pepe");
                ps.setString(2, "Botella");
                ps.addBatch();

                ps.setString(1, "Mario");
                ps.setString(2, "Sánchez");
                ps.addBatch();

                ps.setString(1, "Luis");
                ps.setString(2, "Rodríguez");
                ps.addBatch();

                ps.setString(1, "Alba");
                ps.setString(2, "Botella");
                ps.addBatch();

                ps.setString(1, "Lucia");
                ps.setString(2, "Sánchez");
                ps.addBatch();

                ps.setString(1, "Pepe");
                ps.setString(2, "Botella");
                ps.addBatch();

                int[] failures = ps.executeBatch();
                for(int fail : failures) {
                    if(fail <= 0){
                        c.rollback();
                    }
                }
                ps.clearBatch();
            } catch (SQLException e) {
                c.rollback();
                throw new RuntimeException(e);
            }


            try {
                c.commit();
                ps.setString(1, "Pepe");
                ps.setString(2, "Sánchez");
                ps.addBatch();

                ps.setString(1, "Mario");
                ps.setString(2, "Botella");
                ps.addBatch();

                ps.setString(1, "Luis");
                ps.setString(2, "Botella");
                ps.addBatch();

                ps.setString(1, "Alba");
                ps.setString(2, "Sánchez");
                ps.addBatch();

                ps.setString(1, "Lucia");
                ps.setString(2, "Rodríguez");
                ps.addBatch();

                ps.setString(1, "Miguel");
                ps.setString(2, "Botella");
                ps.addBatch();

                int[] failures = ps.executeBatch();
                for(int fail : failures) {
                    if(fail <= 0){
                        c.rollback();
                    }
                }
                ps.clearBatch();
            } catch (SQLException e) {
                c.rollback();
                throw new RuntimeException(e);
            }


            try {
                c.commit();
                ps.setString(1, "Pepe");
                ps.setString(2, "Botella");
                ps.addBatch();

                ps.setString(1, "Mario");
                ps.setString(2, "Rodríguez");
                ps.addBatch();

                ps.setString(1, "Luis");
                ps.setString(2, "Botella");
                ps.addBatch();

                ps.setString(1, "Alba");
                ps.setString(2, "Botella");
                ps.addBatch();

                ps.setString(1, "Lucia");
                ps.setString(2, "Sánchez");
                ps.addBatch();

                ps.setString(1, "Hugo");
                ps.setString(2, "Sánchez");
                ps.addBatch();

                int[] failures = ps.executeBatch();
                for(int fail : failures) {
                    if(fail <= 0){
                        c.rollback();
                    }
                }
                ps.clearBatch();
            } catch (SQLException e) {
                c.rollback();
                throw new RuntimeException(e);
            }
            c.commit();
            c.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
