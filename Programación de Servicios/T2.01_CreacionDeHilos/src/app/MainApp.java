package app;

import threads.PrimeInRangeThread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainApp {
    public static List<Long> allPrimesInRange = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        if(args.length != 3) return;

        long start = Long.parseLong(args[0]);
        long end = Long.parseLong(args[1]);
        int numOfThreads = Integer.parseInt(    args[2]);

        if (start > end || numOfThreads <= 0) {
            System.out.println("Parámetros inválidos");
            return;
        }

        long totalStart = System.currentTimeMillis();
        List<PrimeInRangeThread> threads = launchThreadsInBatches(start, end, numOfThreads);

        for (PrimeInRangeThread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        long totalEnd = System.currentTimeMillis();

        System.out.println("Número de primos: " +allPrimesInRange.size());
        for (long prime : allPrimesInRange){
            System.out.print(prime+";");
        }
        System.out.println();
        System.out.println("Tiempo total: " + (totalEnd-totalStart) + " ms");
        for (PrimeInRangeThread t : threads) {
            System.out.println(t.getName() + ": " + t.getElapsedMillis() + " ms");
        }

    }

    private static List<PrimeInRangeThread> launchThreadsInBatches(long start, long end, int numOfThreads) {
        List<PrimeInRangeThread> threads = new ArrayList<>();

        long rangeSize = end - start + 1;
        long batchSize = rangeSize / numOfThreads;
        long finalEnd = start + rangeSize - 1;

        for(int i = 0; i < numOfThreads; i++) {
            if (i == numOfThreads - 1) {
                end = finalEnd;
            } else {
                end = start + batchSize - 1;
            }
            PrimeInRangeThread thread = new PrimeInRangeThread(start, end, allPrimesInRange);
            thread.start();
            threads.add(thread);

            start = end + 1;
        }

        return threads;
    }

}
