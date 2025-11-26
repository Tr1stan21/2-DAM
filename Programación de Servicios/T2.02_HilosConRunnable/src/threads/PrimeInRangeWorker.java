package threads;

import syncObjects.SyncedList;

public class PrimeInRangeWorker implements Runnable {
    private final long start;
    private final long end;
    private final SyncedList syncedList;
    private long elapsedMillis;

    public PrimeInRangeWorker(long start, long end,SyncedList syncedList) {
        this.start = start;
        this.end = end;
        this.syncedList = syncedList;
    }

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
    public long getElapsedMillis() {
        return elapsedMillis;
    }
}
