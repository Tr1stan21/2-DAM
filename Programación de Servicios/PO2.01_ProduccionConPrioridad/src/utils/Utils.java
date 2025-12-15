package utils;

import java.util.Random;

public class Utils {

    /**
     *
     * Metodo que implementa una espera en segundos entre los dos parametros que se pasan
     *
     * @param minimo minimo tiempo de espera
     * @param maximo maximo tiempo de espera
     */
    public static void espera(int minimo, int maximo) {
        Random r = new Random();

        int tiempo = minimo * 1000 + r.nextInt((maximo-minimo)*1000);
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
