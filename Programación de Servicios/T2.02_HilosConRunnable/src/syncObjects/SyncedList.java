package syncObjects;

import java.util.ArrayList;
import java.util.List;

public class SyncedList {
    private final List<Long> primeNumbers = new ArrayList<>();

    public synchronized void add(long primeNumber) {
        primeNumbers.add(primeNumber);
    }

    public synchronized List<Long> getPrimeNumbers() {
        return new ArrayList<>(primeNumbers);
    }

}
