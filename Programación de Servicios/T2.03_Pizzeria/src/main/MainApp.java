package main;

import syncObjects.PizzaStore;
import workers.ClientWorker;
import workers.PizzaMakerWorker;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    private static final PizzaStore pizzaStore = new PizzaStore();

    static void main(String[] args) throws InterruptedException {

        int numPizzaMakers;
        int numClients;

        if (args.length != 2) {
            numPizzaMakers = 2;
            numClients = 5;
        } else {
            try {
                numPizzaMakers = Integer.parseInt(args[0]);
                numClients = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }

        List<Thread> pizzaMakerThreads = new ArrayList<>();
        List<Thread> clientThreads = new ArrayList<>();

        // Crear pizzeros
        for (int i = 0; i < numPizzaMakers; i++) {
            Thread t = new Thread(new PizzaMakerWorker(pizzaStore, i+1), "Pizzero-" + i+1);
            pizzaMakerThreads.add(t);
            t.start();
        }

        // Crear clientes
        for (int i = 0; i < numClients; i++) {
            Thread t = new Thread(new ClientWorker(pizzaStore, i + 1), "Cliente-" + (i + 1));
            clientThreads.add(t);
            t.start();
        }

        for (Thread t : clientThreads) {
            t.join();
        }
        System.out.println("Todos los clientes están satisfechos");

        for (Thread t : pizzaMakerThreads) {
            t.interrupt();
        }

        System.out.println("Los pizzeros se van a casa");
    }
}
