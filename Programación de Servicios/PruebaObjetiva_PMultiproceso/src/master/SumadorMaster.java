package master;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase maestra que recoge los parámetros del usuario, valida y lanza el número de procesos correspondientes
 */
public class SumadorMaster {
    public static void main(String[] args) throws IOException {
        List<Process> processes = new ArrayList<>();
        List<Long> startTimes = new ArrayList<>();

        int start;
        int end;
        String tipoNumero;
        int numOfProcesses;

        //Válidación de parámetros
        try {
            if (args.length == 4) {
                start = Integer.parseInt(args[0]);
                end = Integer.parseInt(args[1]);
                tipoNumero = args[2];
                numOfProcesses = Integer.parseInt(args[3]);
            }
            else {
                System.out.println("Error: cantidad de parámetros añadidos inválida");
                return;
            }

            int difference = end-start+1;
            int cantidadPares = 0;
            int cantidadImpares = 0;
            for (int i = start; i <= end; i++){
                if((i % 2) == 0) {
                    cantidadPares++;
                } else {
                    cantidadImpares++;
                }
            }

            if(start < 0 || end <= 0 || numOfProcesses <= 0) {
                System.out.println("Error: los números enteros negativos no son válidos");
            } else if(end < start) {
                System.out.println("Error: el parámetros de fin debe ser mayor que el inicio");
            } else if ((tipoNumero.equals("todos") && difference < numOfProcesses) || (tipoNumero.equals("pares") && cantidadPares < numOfProcesses) || (tipoNumero.equals("impares") && cantidadImpares < numOfProcesses)) {
                System.out.println("Error: más núcleos que intervalos a procesar");
            } else if (!(tipoNumero.equals("todos" )|| tipoNumero.equals("pares") || tipoNumero.equals("impares"))) {
                System.out.println("Error: tipoNumero debe ser 'pares', 'impares' o 'todos'");
            }
            else {
                System.out.println("Datos correctos, ejecutando programa...");
                launchProcessesInBatches(start, end, tipoNumero, numOfProcesses, processes, startTimes);
                readProcesses(processes, startTimes);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error en el tipo de los parámetros añadidos");
        }
    }

    /**
     * Recoge los resultados de los procesos e imprime los resultados por pantalla
     *
     * @param processes Lista de los procesos lanzados
     * @param startTimes Lista con el tiempo de inicio de cada proceso
     */
    private static void readProcesses(List<Process> processes, List<Long> startTimes) {
        long total = 0;
        double totalTime = 0;
        for(int i = 0; i < processes.size(); i++) {
            try (
                    BufferedReader fromChild = new BufferedReader(new InputStreamReader(processes.get(i).getInputStream()))
            ) {
                long partialSum = Long.parseLong(fromChild.readLine());
                total += partialSum;
                double seconds = (System.nanoTime() - startTimes.get(i)) /1_000_000_000.0;
                totalTime += seconds;
                System.out.println("    - Suma parcial del proceso "+ (i+1) +": "+ partialSum +". Tiempo: "+ seconds +"s");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Tiempo total del programa: "+ totalTime +" segundos");
        System.out.println("Suma Total: "+ total);
    }

    /**
     * Lanza un proceso enviando los parámetros correspondientes
     *
     * @param start Inicio del intervalo
     * @param end Final del intervalo
     * @param tipoNumero Tipo de número a sumar
     * @param processes Lista de procesos lanzados
     * @param startTimes Lista con el tiempo de inicio de cada proceso
     */
    public static void launchProcess(int start, int end, String tipoNumero, List<Process> processes, List<Long> startTimes) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("java", "-jar", "SlaveProcess.jar");
        Process process = pb.start();
        processes.add(process);
        startTimes.add(System.nanoTime());
        try (BufferedWriter toChild = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
            toChild.write(String.valueOf(start));
            toChild.newLine();
            toChild.write(String.valueOf(end));
            toChild.newLine();
            toChild.write(tipoNumero);
            toChild.newLine();
            toChild.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Lanza tantos procesos como indique el usuario,
     * repartiendo los intervalos equitativamente
     *
     * @param start Inicio del intervalo
     * @param end Final del intervalo
     * @param tipoNumero Tipo de número a sumar
     * @param numOfProcesses Número de procesos a lanzar
     * @param processes Lista de procesos lanzados
     * @param startTimes Lista con el tiempo de inicio de cada proceso
     */
    private static void launchProcessesInBatches(int start, int end, String tipoNumero, int numOfProcesses, List<Process> processes, List<Long> startTimes) throws IOException {
        int difference = end - start + 1;
        int batchSize = difference / numOfProcesses;
        int finalEnd = start + difference - 1;

        for(int i = 0; i < numOfProcesses; i++) {
            if (i == numOfProcesses - 1) {
                end = finalEnd;
            } else {
                end = start + batchSize - 1;
            }
            launchProcess(start, end, tipoNumero, processes, startTimes);
            start = end + 1;
        }
    }

}
