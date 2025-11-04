package cuentaVocales;

import java.io.*;

public class CuentaLetra {
    public static void main(String[] args) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader("./resources/el_quijote.txt"))
        ) {
            String line;
            while((line = reader.readLine()) != null) {
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
