package worker;

import utils.MonteCarloCalculator;

import java.io.*;

public class WorkerProcess {
    public static void main(String[] args) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            String line;
            while ((line = in.readLine()) != null) {
                int drops = Integer.parseInt(line.trim());

                out.write(String.valueOf(MonteCarloCalculator.monteCarlo(drops)));
                out.newLine();
                out.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
