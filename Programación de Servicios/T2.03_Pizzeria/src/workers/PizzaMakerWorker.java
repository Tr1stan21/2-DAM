package workers;

import syncObjects.PizzaStore;

import java.util.Random;


public class PizzaMakerWorker implements Runnable {

    private boolean isWorking = true;
    private final PizzaStore pizzaStore;

    /**
     * Constructs a worker that adds a pizza to a PizzaStore.
     *
     * @param pizzaStore Thread-safe list used to store pizzas.
     */
    public PizzaMakerWorker(PizzaStore pizzaStore) {
        this.pizzaStore = pizzaStore;

    }

    public void setIsWorking(boolean working) {
        isWorking = working;
    }

    /**
     * Waits for a random number between 5 and 10 seconds and adds a pizza to a PizzaStore
     */
    @Override
    public void run() {
        Random random = new Random();
        while (isWorking) {
            try {
                Thread.sleep((5 + random.nextInt(10 - 5 + 1)) * 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            pizzaStore.add("Pizza");
            System.out.println("Pizza terminada, hay "+ pizzaStore.getAvailablePizzas().size() +" en la bandeja.");
        }
    }


}
