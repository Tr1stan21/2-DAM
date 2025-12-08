package workers;

import utils.PieceCounter;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Assembler implements Runnable{

    private final String name;
    private final BlockingQueue<Integer> queue;
    private final Random random = new Random();

    /**
     * Creates a new assembler (consumer).
     *
     * @param name  display name for log output
     * @param queue shared blocking queue from which pieces are consumed
     */
    public Assembler(String name, BlockingQueue<Integer> queue) {
        this.name = name;
        this.queue = queue;
    }


    /**
     * Consumes exactly MAX_PIECES items.
     * Each call to take() blocks if no pieces are available.
     */
    @Override
    public void run() {
        for(int i = 0; i < PieceCounter.MAX_PIECES; i++) {
            System.out.println("CONSUME: "+ name +" esperando pieza...");
            try {
                queue.take();
                Thread.sleep((8 + random.nextInt(15 - 8 + 1)) * 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("CONSUME: "+ name +" he cogido una pieza. Ahora llevo "+ (i+1) +" piezas consumidas quedan "+ queue.size() +" en la cola compartida");
        }

    }
}
