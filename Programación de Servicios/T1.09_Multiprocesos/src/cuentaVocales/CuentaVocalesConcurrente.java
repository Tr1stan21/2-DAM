package cuentaVocales;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaVocalesConcurrente {
    public static void main(String[] args) throws IOException {

        ProcessBuilder pb = new ProcessBuilder("java", "-jar", "./out/artifacts/T1_09_Multiprocesos_jar/CuentaLetra.jar");
        List<Process> processes = new ArrayList<>();
        List<Long> startTimes = new ArrayList<>();

        char[] lettersToCount = {'a', 'e', 'i', 'o', 'u'};

        for (char c : lettersToCount) {
            Process process = pb.start();
            processes.add(process);
            startTimes.add(System.nanoTime());
            try (
                    BufferedWriter toChild = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))
            ) {
                toChild.write(c);
                toChild.newLine();
                toChild.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int count;
        for(int i = 0; i< processes.size(); i++) {
            try (
                    BufferedReader fromChild = new BufferedReader(new InputStreamReader(processes.get(i).getInputStream()))
            ) {
                count = Integer.parseInt(fromChild.readLine());
                double seconds = (System.nanoTime() - startTimes.get(i)) /1_000_000_000.0;
                System.out.println("Se ha encontrado la letra "+ lettersToCount[i] +" un total de "+ count +" veces.");
                System.out.println("    - El tiempo total del programa fue: "+ seconds +"s.\n");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}