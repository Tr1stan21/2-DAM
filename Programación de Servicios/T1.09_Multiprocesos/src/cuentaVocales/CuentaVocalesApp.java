package cuentaVocales;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaVocalesApp {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", "./CuentaLetra.jar");
            try {
                processes.add(pb.start());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for(Process process : processes) {
            long start = System.nanoTime(); // inicio
            try (
                    BufferedWriter toChild = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                    BufferedReader fromChild = new BufferedReader(new InputStreamReader(process.getInputStream()))
            ) {

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            long end = System.nanoTime(); // fin
            double seconds = (end - start) / 1_000_000_000.0;

        }
    }
}
