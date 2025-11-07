package sumador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaestroSumadorApp {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("java", "-jar", "./out/artifacts/T1_09_Multiprocesos_jar2/Sumador.jar");
        List<Process> processes = new ArrayList<>();
        List<Long> startTimes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Star: ");
        int start = Integer.parseInt(scanner.next());
        System.out.println("End: ");
        int end = Integer.parseInt(scanner.next());
        int difference = end - start + 1;

        if(difference <= 0){
            System.out.println("no valido");
        } else if (difference < 25) {
            System.out.println("Número de procesos lanzados: 1");
            launchProcess(pb, processes, start, end, startTimes);
        } else if (difference < 75) {
            int numOfProcesses = 2;
            System.out.println("Número de procesos lanzados: "+ numOfProcesses);
            launchProcessesInBatches(difference, numOfProcesses, start, pb, processes, startTimes);
        }
        else {
            int numOfProcesses = Runtime.getRuntime().availableProcessors();
            System.out.println("Número de procesos lanzados: "+ numOfProcesses);
            launchProcessesInBatches(difference, numOfProcesses, start, pb, processes, startTimes);
        }

        readProcesses(processes, startTimes);

    }

    private static void readProcesses(List<Process> processes, List<Long> startTimes) {
        int total = 0;
        double end = 0;
        for(int i = 0; i < processes.size(); i++) {
            try (
                    BufferedReader fromChild = new BufferedReader(new InputStreamReader(processes.get(i).getInputStream()))
            ) {
                total += Integer.parseInt(fromChild.readLine());
                end = System.nanoTime();
                double seconds = (end - startTimes.get(i)) /1_000_000_000.0;
                System.out.println("    - Tiempo total del proceso "+ (i+1) +": "+ seconds +"s");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Tiempo total del programa: "+ (end - startTimes.getFirst()));
        System.out.println("Resultado: "+ total);
    }

    private static void launchProcessesInBatches(int difference, int numOfProcesses, int start, ProcessBuilder pb, List<Process> processes, List<Long> startTimes) throws IOException {
        int end;
        int batchSize = difference / numOfProcesses;
        int finalEnd = start + difference - 1;

        for(int i = 0; i < numOfProcesses; i++) {
            if (i == numOfProcesses - 1) {
                end = finalEnd;
            } else {
                end = start + batchSize - 1;
            }
            launchProcess(pb, processes, start, end, startTimes);
            start = end + 1;
        }
    }

    public static void launchProcess(ProcessBuilder pb, List<Process> processes, int start, int end, List<Long> startTimes) throws IOException {
        Process process = pb.start();
        processes.add(process);
        startTimes.add(System.nanoTime());
        try (BufferedWriter toChild = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
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