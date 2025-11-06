package sumador;

import java.io.*;

public class SumadorProcess {

    public static void main(String[] args) {

        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {

            int start = Integer.parseInt(in.readLine());
            int end = Integer.parseInt(in.readLine());

            int totalSum = 0;
            for (int i = start; i <= end; i++) {
                totalSum += i;
            }

            out.write(String.valueOf(totalSum));
            out.newLine();
            out.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
