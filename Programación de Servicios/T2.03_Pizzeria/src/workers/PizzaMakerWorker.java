package workers;

import syncObjects.PizzaStore;

/**
 * Constructs a worker that adds a pizza to a PizzaStore.
 *
 * @param pizzaStore Thread-safe list used to store pizzas.
 */
public record PizzaMakerWorker(PizzaStore pizzaStore) implements Runnable {

    /**
     * Adds a pizza to a PizzaStore
     */
    @Override
    public void run() {
        pizzaStore.add("Pizza");
        System.out.println("Pizza terminada, hay "+ pizzaStore.getAvailablePizzas().size() +" en la bandeja.");
    }

}
