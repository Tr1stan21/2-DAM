package main;

import syncObjects.PizzaStore;
import workers.ClientWorker;
import workers.PizzaMakerWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainApp {
    private static final PizzaStore pizzaStore = new PizzaStore();
    private static final List<PizzaMakerWorker> pizzaMakers = new ArrayList<>();
    private static final List<ClientWorker> clientWorkers = new ArrayList<>();
    private static final Random random = new Random();

    public static void main(String[] args) {
        int clientsSatisfied = 0;
        int numPizzaMakers = 2;
        int numClients = 5;

        for(int i = 0; i < numPizzaMakers; i++){
            pizzaMakers.add(new PizzaMakerWorker(pizzaStore));
            System.out.println("Inicio del pizzero "+i);

        }
        for(int i = 0; i < numClients; i++){
            clientWorkers.add(new ClientWorker(pizzaStore));
            System.out.println("Inicio del cliente "+i);
        }

        while(clientsSatisfied != 5){
            for(int i = 0; i < pizzaMakers.size(); i++){
                Thread t = new Thread(pizzaMakers.get(i));
                System.out.println("Pizzero "+ i +" comienza a hacer una pizza.");
                try {
                    Thread.sleep((5 + random.nextInt(10 - 5 + 1)) * 1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                t.start();
            }

            for(int i = 0; i < clientWorkers.size(); i++){
                Thread t = new Thread(clientWorkers.get(i));
                System.out.println("Cliente");
                if (pizzaStore.isPizza()) {
                    t.start();
                    System.out.println("El cliente "+ i +" se come una pizza, le faltan "+ clientWorkers.get(i).getPizzasLeftToEat()+ " pizzas por comer");
                    try {
                        System.out.println("El cliente "+ i +" comienza a dar un paseo");
                        Thread.sleep((20 + random.nextInt(30 - 20 + 1)) * 1000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    System.out.println("El cliente "+ i +" no se pudo comer una pizza");
                    try {
                        System.out.println("El cliente "+ i +" comienza a dar un paseo");
                        Thread.sleep((10 + random.nextInt(15 - 10 + 1)) * 1000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                if(clientWorkers.get(i).getPizzasLeftToEat() == 0){
                    System.out.println("El cliente "+ i +" está satisfecho y se va a su casa");
                    clientWorkers.remove(i);
                    clientsSatisfied--;
                }
            }

        }
        System.out.println("Los pizzeros se van a casa");
    }
}
