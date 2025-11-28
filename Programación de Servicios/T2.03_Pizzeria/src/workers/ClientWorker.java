package workers;

import syncObjects.PizzaStore;

/**
 * Worker that removes a pizza to a PizzaStore.
 */
public class ClientWorker implements Runnable{
    private final PizzaStore pizzaStore;
    private int pizzasLeftToEat = 5;

    /**
     * Constructs a worker that removes a pizza to the PizzaStore.
     *
     * @param pizzaStore  Thread-safe list used to store pizzas.
     */
    public ClientWorker(PizzaStore pizzaStore) {
        this.pizzaStore = pizzaStore;
    }

    /**
     * Removes a pizza from the PizzaStore
     */
    @Override
    public void run() {
        pizzaStore.remove();
        pizzasLeftToEat--;
    }


    public int getPizzasLeftToEat() {
        return pizzasLeftToEat;
    }
}
