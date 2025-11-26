package main;

import syncObjects.SyncedList;
import threads.PrimeInRangeWorker;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    private static final SyncedList syncedList = new SyncedList();
    private static final List<PrimeInRangeWorker> workers = new ArrayList<>();
    public static final List<Long> primeNumbers = syncedList.getPrimeNumbers();

    public static void main(String[] args) {
        if(args.length != 2) return;

        long start = Long.parseLong(args[0]);
        long end = Long.parseLong(args[1]);
        long totalStart = System.currentTimeMillis();


        List<Thread> threads = launchThreadsInBatches(start, end, Runtime.getRuntime().availableProcessors());

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        long totalEnd = System.currentTimeMillis();

        System.out.println("Número de primos: " +primeNumbers.size());
        for (long prime : primeNumbers){
            System.out.print(prime+";");
        }
        System.out.println();
        System.out.println("Tiempo total: " + (totalEnd-totalStart) + " ms");
        for (int i = 0; i < workers.size(); i++) {
            System.out.println(threads.get(i).getName() + ": " + workers.get(i).getElapsedMillis() + " ms");
        }

    }

    private static List<Thread> launchThreadsInBatches(long start, long end, int numOfThreads) {
        List<Thread> threads = new ArrayList<>();
        long rangeSize = end - start + 1;
        long batchSize = rangeSize / numOfThreads;
        long finalEnd = start + rangeSize - 1;

        for(int i = 0; i < numOfThreads; i++) {
            if (i == numOfThreads - 1) {
                end = finalEnd;
            } else {
                end = start + batchSize - 1;
            }
            workers.add(new PrimeInRangeWorker(start, end, syncedList));
            Thread t = new Thread(workers.get(i));
            t.start();
            threads.add(t);

            start = end + 1;
        }
        return threads;
    }

}
