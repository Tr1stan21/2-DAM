package syncObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Thread-safe container used to store pizzas.
 * Operations are synchronized to guarantee consistency under concurrent access.
 */
public class PizzaStore {
    private final List<String> pizzas = new ArrayList<>();

    /**
     * Adds a pizza to the list of pizzas.
     */
    public synchronized void add() {
        pizzas.add("Pizza");
    }

    /**
     * Tries to remove a pizza from the list if it is not empty.
     * @return wether the pizza has been removed.
     */
    public synchronized boolean tryToEatPizza() {
        if (pizzas.isEmpty()) {
            return false;
        }
        pizzas.removeLast();
        return true;
    }

    /**
     * Returns the current number of pizzas in the list.
     *
     * @return current number of pizzas.
     */
    public synchronized int getPizzaCount() {
        return pizzas.size();
    }

}
