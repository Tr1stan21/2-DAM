package util;

import java.io.InputStream;
import java.util.Properties;

/**
 * Clase de utilidades para operaciones comunes de la aplicación.
 *
 * <p>Actualmente, proporciona métodos para cargar archivos
 * de propiedades desde el classpath.</p>
 */
public class Utils {

    /**
     * Constructor privado para evitar la instanciación de la clase.
     */
    private Utils() {
    }

    /**
     * Carga un archivo de propiedades desde el classpath.
     *
     * @param path ruta del archivo de propiedades
     * @return objeto {@link Properties} con las claves y valores cargados
     * @throws RuntimeException si ocurre un error al cargar el archivo
     */
    public static Properties load(String path) {

        Properties properties = new Properties();

        try (InputStream is = Utils.class.getResourceAsStream(path)) {
            properties.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Error cargando properties desde: " + path, e);
        }

        return properties;
    }

}
