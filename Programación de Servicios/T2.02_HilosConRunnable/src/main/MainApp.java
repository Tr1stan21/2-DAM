package main;

import syncObjects.SyncedList;
import threads.PrimeInRangeWorker;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    private static final SyncedList syncedList = new SyncedList();
    private static final List<PrimeInRangeWorker> workers = new ArrayList<>();

    static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Error, debe insertar únicamente dos parámetros");
            return;
        }
        long start;
        long end;
        try {
            start = Long.parseLong(args[0]);
            end = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Error, ambos parámetros deben ser enteros positivos válidos");
            return;
        }
        if(start < 0 || end <= 0) {
            System.out.println("Error, ambos números deben ser positivos");
            return;
        }
        if(start >= end) {
            System.out.println("Error, el límite inferior es más grande que el superior");
            return;
        }

        long totalStart = System.currentTimeMillis();

        List<Thread> threads = launchThreadsInBatches(start, end, Runtime.getRuntime().availableProcessors());

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("La ejecución fue interrumpida mientras se esperaban los hilos.");
                return;
            }
        }

        long totalEnd = System.currentTimeMillis();
        System.out.println("Número de primos: " +syncedList.getPrimeNumbers().size());
        for (long prime : syncedList.getPrimeNumbers()){
            System.out.print(prime+";");
        }
        System.out.println();
        System.out.println("Tiempo total: " + (totalEnd-totalStart) + " ms");
        for (int i = 0; i < workers.size(); i++) {
            System.out.println(threads.get(i).getName() + ": " + workers.get(i).getElapsedMillis() + " ms");
        }

    }

    /**
     * Splits the given numeric interval into sub-ranges and launches one
     * PrimeInRangeWorker per segment. Each worker runs in an independent thread.
     *
     * @param start         Lower bound of the full range (inclusive).
     * @param end           Upper bound of the full range (inclusive).
     * @param numOfThreads  Number of threads to spawn.
     * @return A list containing the Thread objects that were started.
     */
    private static List<Thread> launchThreadsInBatches(long start, long end, int numOfThreads) {
        List<Thread> threads = new ArrayList<>();
        long rangeSize = end - start + 1;
        long baseSize = rangeSize / numOfThreads;
        long rest = rangeSize % numOfThreads;

        for(int i = 0; i < numOfThreads; i++) {
            if (baseSize == 0 && rest == 0) {
                break;
            }
            if(rest > 0) {
                end = start + baseSize;
                rest--;
            } else {
                end = start + baseSize - 1;
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
