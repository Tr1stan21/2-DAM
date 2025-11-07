package cuentaVocales;

import java.io.*;

public class CuentaVocalesSecuencial {
    public static void main(String[] args) throws IOException {

        ProcessBuilder pb = new ProcessBuilder("java", "-jar", "./out/artifacts/T1_09_Multiprocesos_jar/CuentaLetra.jar");

        setLetterToProcess(pb, 'a');
        setLetterToProcess(pb, 'e');
        setLetterToProcess(pb, 'i');
        setLetterToProcess(pb, 'o');
        setLetterToProcess(pb, 'u');

    }

    public static void setLetterToProcess(ProcessBuilder builder, char letter) throws IOException {
        long start = System.nanoTime(); // inicio
        Process countLetter = builder.start();
        int count;
        try (
                BufferedWriter toChild = new BufferedWriter(new OutputStreamWriter(countLetter.getOutputStream()));
                BufferedReader fromChild = new BufferedReader(new InputStreamReader(countLetter.getInputStream()))
        ) {
            toChild.write(letter);
            toChild.newLine();
            toChild.flush();

            count = Integer.parseInt(fromChild.readLine());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long end = System.nanoTime(); // fin
        double seconds = (end - start) / 1_000_000_000.0;

        System.out.println("Se ha encontrado la letra "+ letter +" un toral de "+ count +" veces.");
        System.out.println("    - El tiempo total del programa fue: "+ seconds +"s.\n");

    }
}
