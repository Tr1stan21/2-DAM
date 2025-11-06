package sumador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaestroSumadorApp {

    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("java", "-jar", "Sumador.jar");
        List<Process> processes = new ArrayList<>();
        List<Long> startTimes = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int start = 1;
        int end = 100;
        int total = 0;

        if (end - start <= 0) {
            System.out.println("no válido");
        }
        else if(end - start < 25 ) {
            launchProcesses(pb, processes, startTimes, start, end, 1);
        } else if(end - start < 100) {
            int batch = (end - start)/2;
            launchProcesses(pb, processes, startTimes, start, end, 2);
        }


        for(int i = 0; i< processes.size(); i++) {
            try (
                    BufferedReader fromChild = new BufferedReader(new InputStreamReader(processes.get(i).getInputStream()))
            ) {
                total = Integer.parseInt(fromChild.readLine());
                double seconds = (System.nanoTime() - startTimes.get(i)) / 1_000_000_000.0;
                System.out.println("Total: " + total);
                System.out.println("Segundos: " + seconds);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    private static void launchProcesses(ProcessBuilder pb, List<Process> processes, List<Long> startTimes, int start, int end, int numProcesses) throws IOException {
        for(int i = 0; i< numProcesses; i++) {
            launchProcess(pb, processes, startTimes, start, end);

        }
    }

    private static void launchProcess(ProcessBuilder pb, List<Process> processes, List<Long> startTimes, int start, int end) throws IOException {
        Process process = pb.start();
        processes.add(process);
        startTimes.add(System.nanoTime());

        try (
                BufferedWriter toChild = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        ) {
            toChild.write(String.valueOf(start));
            toChild.newLine();
            toChild.write(String.valueOf(end));
            toChild.newLine();
            toChild.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
