package main;

import utils.PieceCounter;
import workers.Assembler;
import workers.PieceGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class MainApp {
    static void main() {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        List<Thread> threads = getThreads(queue);

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Todos los hilos han terminado.");

    }

    /**
     * Creates all producers and the consumer, starts them,
     * and returns the list of their Thread objects.
     *
     * @param queue the shared blocking queue
     * @return list of started threads
     */
    private static List<Thread> getThreads(ArrayBlockingQueue<Integer> queue) {
        PieceCounter counter = new PieceCounter();
        List<Thread> threads = new ArrayList<>();

        // Crear e iniciar 3 productores
        for (int i = 1; i <= 3; i++) {
            Thread t = new Thread(new PieceGenerator(queue, counter));
            t.start();
            threads.add(t);
        }

        // Crear e iniciar 1 consumidor
        Thread consumer = new Thread(new Assembler("E1", queue));
        consumer.start();
        threads.add(consumer);
        return threads;
    }
}
