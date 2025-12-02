package main;

import syncObjects.PizzaStore;
import workers.ClientWorker;
import workers.PizzaMakerWorker;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    private static final PizzaStore pizzaStore = new PizzaStore();
    private static final List<PizzaMakerWorker> pizzaMakers = new ArrayList<>();
    private static final List<ClientWorker> clientWorkers = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        int numPizzaMakers = 2;
        int numClients = 5;

        List<PizzaMakerWorker> pizzaMakers = new ArrayList<>();
        List<Thread> pizzaMakerThreads = new ArrayList<>();

        List<ClientWorker> clientWorkers = new ArrayList<>();
        List<Thread> clientThreads = new ArrayList<>();

        // Crear pizzeros
        for (int i = 0; i < numPizzaMakers; i++) {
            PizzaMakerWorker worker = new PizzaMakerWorker(pizzaStore);
            pizzaMakers.add(worker);
            Thread t = new Thread(worker, "Pizzero-" + i);
            pizzaMakerThreads.add(t);
            System.out.println("Inicio del pizzero " + i);
            t.start();
        }

        // Crear clientes
        for (int i = 0; i < numClients; i++) {
            ClientWorker worker = new ClientWorker(pizzaStore, i + 1);
            clientWorkers.add(worker);
            Thread t = new Thread(worker, "Cliente-" + (i + 1));
            clientThreads.add(t);
            System.out.println("Inicio del cliente " + (i + 1));
            t.start();
        }

        for (Thread t : clientThreads) {
            t.join();
        }
        System.out.println("Todos los clientes están satisfechos");

        for (Thread t : pizzaMakerThreads) {
            t.interrupt();
        }

        for (Thread t : pizzaMakerThreads) {
            t.interrupt();
        }

        System.out.println("Los pizzeros se van a casa");
    }
}
