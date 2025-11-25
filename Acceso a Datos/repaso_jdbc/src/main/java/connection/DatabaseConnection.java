package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class responsible for establishing and providing a connection
 * to the configured MySQL database.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE = "clase";
    private static final String PARAMETERS = "?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Jchadom2103.";

    /**
     * Establishes and returns a connection to the MySQL database.
     *
     * @return a Connection object if successful
     * @throws RuntimeException if the connection attempt fails
     */
    public static Connection getConnection() {
        Connection connection;

        try {
            connection = DriverManager.getConnection(URL + DATABASE + PARAMETERS, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
