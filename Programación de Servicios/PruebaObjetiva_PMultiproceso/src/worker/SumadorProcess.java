package worker;

import java.io.*;

/**
 * Clase esclavo que recoje un límite superior, uno inferior, el tipo de número a sumar
 * y devuelve la suma correspondiente.
 */
public class SumadorProcess {
    public static void main(String[] args) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            long start = Integer.parseInt(in.readLine());
            long end = Integer.parseInt(in.readLine());
            String tipoNumero = in.readLine();
            long total = getTotal(tipoNumero, start, end);

            out.write(String.valueOf(total));
            out.newLine();
            out.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cálcula el total dentro de los párametros según el tipo de número
     *
     * @param tipoNumero tipod de número a sumar
     * @param start incio del intervalo
     * @param end fin del intervalo
     * @return Total de la suma entre el inicio y el final del tipo de número indicado.
     */
    private static long getTotal(String tipoNumero, long start, long end) {
        long total = 0;
        switch (tipoNumero) {
            case "todos":
                for (long i = start; i <= end; i++) {
                    total += i;
                }
                break;
            case "pares":
                for (long i = start; i <= end; i++) {
                    if(i % 2 == 0) {
                        total += i;
                    }
                }
                break;
            case "impares":
                for (long i = start; i <= end; i++) {
                    if (i % 2 != 0) {
                        total += i;
                    }
                }
                    break;
        }
        return total;
    }
}
