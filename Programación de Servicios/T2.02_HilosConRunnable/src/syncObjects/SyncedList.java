package syncObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Thread-safe container used to store prime numbers discovered by worker threads.
 * Operations are synchronized to guarantee consistency under concurrent access.
 */
public class SyncedList {
    private final List<Long> primeNumbers = new ArrayList<>();

    /**
     * Adds a prime number to the internal list in a thread-safe manner.
     *
     * @param primeNumber Prime number to store.
     */
    public synchronized void add(long primeNumber) {
        primeNumbers.add(primeNumber);
    }

    /**
     * Returns a sorted copy of all stored prime numbers.
     *
     * @return A new sorted list containing all stored prime numbers.
     */
    public synchronized List<Long> getPrimeNumbers() {
        List<Long> copy = new ArrayList<>(primeNumbers);
        Collections.sort(copy);
        return copy;
    }
}
