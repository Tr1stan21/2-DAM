package sumador;

import java.io.*;

public class SumadorProcess {
    static void main() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int start = Integer.parseInt(in.readLine());
            int end = Integer.parseInt(in.readLine());
            int total = 0;

            for (int i = start; i <= end; i++) {
                total += i;
            }

            out.write(String.valueOf(total));
            out.newLine();
            out.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
