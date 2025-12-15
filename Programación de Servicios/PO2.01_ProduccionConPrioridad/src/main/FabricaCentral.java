package main;

import utils.ContadorPiezas;
import utils.Pieza;
import workers.Ensamblador;
import workers.ProductorEstandar;
import workers.ProductorUrgente;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class FabricaCentral {
    public static void main(String[] args) {

        /**
         * Verifica que el programa se inicie con 4 parametros.
         */
        if(args.length != 4) {
            System.out.println("Parámetros inválidos");
            return;
        }

        /**
         * Verifica que los parametros sean numeros enteros.
         */
        int productoresEstandar;
        int productoresUrgentes;
        int capacidad;
        int piezasPorProductor;
        try {
            productoresEstandar = Integer.parseInt(args[0]);
            productoresUrgentes = Integer.parseInt(args[1]);
            capacidad = Integer.parseInt(args[2]);
            piezasPorProductor = Integer.parseInt(args[3]);
        } catch (NumberFormatException e) {
            System.out.println("Error con el formato de los parámetros");
            throw new RuntimeException(e);
        }

        BlockingQueue<Pieza> queue = new PriorityBlockingQueue<>(capacidad);
        List<Thread> productoresEstandarThread = new ArrayList<>();
        List<Thread> productoresUrgentesThread = new ArrayList<>();
        ContadorPiezas contadorPiezas = new ContadorPiezas();

        /**
         * Inicia los productores estandar
         */
        for (int i = 0; i < productoresEstandar; i++) {
            Thread t = new Thread(new ProductorEstandar((i+1), contadorPiezas, queue, piezasPorProductor));
            productoresEstandarThread.add(t);
            t.start();
        }

        /**
         * Inicia los productores urgentes
         */
        for (int i = 0; i < productoresUrgentes; i++) {
            Thread t = new Thread(new ProductorUrgente((i+1), contadorPiezas, queue, piezasPorProductor));
            productoresUrgentesThread.add(t);
            t.start();
        }

        /**
         * Inicia el hilo ensamblador
         */
        Thread ensambladorThread = new Thread(new Ensamblador(queue, (productoresEstandar + productoresUrgentes) * piezasPorProductor));
        ensambladorThread.start();

        /**
         * Espera a que terminen los productores estandar
         */
        for (Thread t : productoresEstandarThread) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Problema al terminar alguno de los hilos de Productores Estandar.");
                throw new RuntimeException(e);
            }
        }

        /**
         * Espera a que terminen los productores urgentes
         */
        for (Thread t : productoresUrgentesThread) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Problema al terminar alguno de los hilos de Productores Urgentes.");
                throw new RuntimeException(e);
            }
        }

        /**
         * Espera a que termine el hilo ensamblador
         */
        try {
            ensambladorThread.join();
        } catch (InterruptedException e) {
            System.out.println("Problema al terminar el hilo Ensamblador.");
            throw new RuntimeException(e);
        }

    }
}
