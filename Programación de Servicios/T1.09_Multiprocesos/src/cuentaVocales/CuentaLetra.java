package cuentaVocales;

import java.io.*;

public class CuentaLetra {
    public static void main(String[] args) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader("./resources/el_quijote.txt"));
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int counter = 0;
            int character;
            String line;

            while ((line = in.readLine()) != null) {
                char charToCount = line.charAt(0);
                while ((character = reader.read()) != -1) {
                    if (character == Character.toLowerCase(charToCount) || character == Character.toUpperCase(charToCount)) { //Los carácteres se convierten a unicode automáticamente
                        counter++;
                    }
                }

                out.write(String.valueOf(counter));
                out.newLine();
                out.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
