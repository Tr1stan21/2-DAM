package utils;

import java.io.InputStream;
import java.util.Properties;

public class Utils {

    private Utils() {
    }

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
