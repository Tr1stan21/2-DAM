package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String BBDD = "t3profesor_alumno";
    private static final String PARAMETROS = "?serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String CLAVE = "Jchadom2103.";

    /**
     * Establishes and returns a connection to the MySQL database.
     *
     * @return a Connection object if successful, or null if the connection fails
     */
    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL+BBDD+PARAMETROS, USUARIO, CLAVE);
            System.out.println("Conexion OK");
        } catch (SQLException e) {
            System.out.println("Error en la conexion");
            e.printStackTrace();
        }

        return connection;
    }
}
