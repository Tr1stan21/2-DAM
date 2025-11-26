package threads;

import utils.PrimeUtils;

import java.util.List;

public class PrimeInRangeWorker extends Thread {
    private final long start;
    private final long end;
    private final List<Long> allPrimesInRange;
    private long elapsedMillis;

    public PrimeInRangeWorker(long start, long end, List<Long> allPrimeNumbers) {
        this.start = start;
        this.end = end;
        this.allPrimesInRange = allPrimeNumbers;
    }

    @Override
    public void run() {
        long t0 = System.nanoTime();

        PrimeUtils utils = new PrimeUtils();
        List<Long> primeNumbers = utils.primeNumbersInRange(start, end);
        allPrimesInRange.addAll(primeNumbers);

        long t1 = System.nanoTime();
        elapsedMillis = (t1 - t0) / 1_000_000; // ms
    }

    public long getElapsedMillis() {
        return elapsedMillis;
    }
}
