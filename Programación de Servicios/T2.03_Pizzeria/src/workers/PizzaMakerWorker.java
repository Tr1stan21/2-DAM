package workers;

import syncObjects.PizzaStore;

import java.util.Random;

/**
 * Constructs a worker that adds a pizza to a PizzaStore.
 *
 * @param pizzaStore Thread-safe list used to store pizzas.
 */

public record PizzaMakerWorker(PizzaStore pizzaStore, int id) implements Runnable {

    /**
     * Waits for a random number between 5 and 10 seconds and adds a pizza to a PizzaStore
     */
    @Override
    public void run() {
        Random random = new Random();
        System.out.println("Inicio del pizzero " + id);
        while (true) {
            try {
                System.out.println("El pizzero "+ id +" comienza a preparar una pizza");
                Thread.sleep((5 + random.nextInt(10 - 5 + 1)) * 1000L);
            } catch (InterruptedException e) {
                return;
            }
            pizzaStore.add();
            System.out.println("El pizzero "+ id +" ha terminado la pizza, hay " + pizzaStore.getPizzaCount() + " en la bandeja.");
        }
    }


}
