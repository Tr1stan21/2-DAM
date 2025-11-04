package master;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MasterProcess {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();
        System.out.println("Inserta el número de procesos a realizar: ");
        int numWorkers = scanner.nextInt();
        System.out.println("Inserta el número de gotas: ");
        int dropsPerWorker = scanner.nextInt();

        for (int i = 0; i < numWorkers; i++) {
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", "./WorkerProcess.jar");
            try {
                processes.add(pb.start());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        double sumPi = getSumPi(processes, dropsPerWorker);

        System.out.println("La estimación de pi realizada con "+ numWorkers +" procesos y "+ dropsPerWorker +" gotas es de: "+ sumPi/numWorkers);

        scanner.close();
    }

    private static double getSumPi(List<Process> processes, int dropsPerWorker) {
        double sumPi = 0;
        for (Process process : processes) {
            long start = System.nanoTime(); // inicio
            try (
                    BufferedWriter toChild = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                    BufferedReader fromChild = new BufferedReader(new InputStreamReader(process.getInputStream()))
            ) {
                toChild.write(String.valueOf(dropsPerWorker));
                toChild.newLine();
                toChild.flush();

                sumPi += Double.parseDouble(fromChild.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            long end = System.nanoTime(); // fin
            double seconds = (end - start) / 1_000_000_000.0;
            System.out.println("Proceso hijo " + process.pid() + " tardó " + seconds + "s");
        }
        return sumPi;
    }
}
