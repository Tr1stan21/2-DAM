package threads;

import syncObjects.SyncedList;

/**
 * Worker that scans a numeric interval and identifies all prime numbers within it.
 */
public class PrimeInRangeWorker implements Runnable {
    private final long start;
    private final long end;
    private final SyncedList syncedList;
    private long elapsedMillis;

    /**
     * Constructs a worker that checks all numbers in the range
     * and stores any prime found into the provided synchronized list.
     *
     * @param start       Lower bound of the assigned range (inclusive).
     * @param end         Upper bound of the assigned range (inclusive).
     * @param syncedList  Thread-safe list used to store discovered primes.
     */
    public PrimeInRangeWorker(long start, long end,SyncedList syncedList) {
        this.start = start;
        this.end = end;
        this.syncedList = syncedList;
    }

    /**
     * Executes the prime-search process for the assigned interval and records
     * the elapsed execution time in milliseconds.
     */
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        for (long n = start; n <= end; n++) {
            if (isPrime(n)) {
                syncedList.add(n);
            }
        }
        long endTime = System.currentTimeMillis();
        elapsedMillis = endTime - startTime;
    }

    /**
     * Determines whether the given number is prime.
     *
     * @param number Number to test.
     * @return true if the number is prime; false otherwise.
     */
    public boolean isPrime(long number){
        if (number < 2) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;

        long limit = (long) Math.sqrt(number);
        for (long i = 3; i <= limit; i++) {
            if (number%i == 0) return false;
        }
        return true;
    }

    /**
     * Returns how many milliseconds this worker spent computing primes.
     *
     * @return Execution time in milliseconds.
     */
    public long getElapsedMillis() {
        return elapsedMillis;
    }
}
