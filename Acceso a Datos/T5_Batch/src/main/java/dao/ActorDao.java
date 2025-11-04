package dao;

import connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActorDao {

    public void addActorsBatch(String[][] actors) {
        String query = "INSERT INTO Actor VALUES (?,?, NOW())";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {

            for (String[] actor : actors) {
                ps.setString(1, actor[0]);
                ps.setString(2, actor[1]);
                ps.addBatch();
            }
            ps.executeBatch();

            ps.addBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
            c.rollback();
        }
    }
}
