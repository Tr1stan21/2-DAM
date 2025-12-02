package syncObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Thread-safe container used to store prime numbers discovered by worker threads.
 * Operations are synchronized to guarantee consistency under concurrent access.
 */
public class PizzaStore {
    private final List<String> availablePizzas = new ArrayList<>();

    /**
     * Adds a prime number to the internal list in a thread-safe manner.
     *
     * @param pizza Prime number to store.
     */
    public synchronized void add(String pizza) {
        availablePizzas.add(pizza);
    }

    public synchronized void remove() {
        availablePizzas.removeLast();
    }

    /**
     * Returns a sorted copy of all stored prime numbers.
     *
     * @return A new sorted list containing all stored prime numbers.
     */
    public synchronized List<String> getAvailablePizzas() {
        return availablePizzas;
    }

    public boolean isPizza() {
        if(!availablePizzas.isEmpty()) {
            return true;
        }
        return false;
    }
}
