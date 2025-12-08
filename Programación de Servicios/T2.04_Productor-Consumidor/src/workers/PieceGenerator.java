package workers;

import utils.PieceCounter;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class PieceGenerator implements Runnable{

    private static int idCounter = 1;
    private final int id;
    private final BlockingQueue<Integer> queue;
    private final PieceCounter counter;
    private final Random random = new Random();

    /**
     * Creates a new producer.
     *
     * @param queue   shared blocking queue where produced pieces are inserted
     * @param counter global counter of pieces
     */
    public PieceGenerator(BlockingQueue<Integer> queue, PieceCounter counter) {
        id = idCounter++;
        this.queue = queue;
        this.counter = counter;
    }

    /**
     * Produces up to 10 pieces, sleeping between 5 and 10 seconds between each one.
     * Stops earlier if PieceCounter reaches MAX_PIECES.
     */
    @Override
    public void run() {
        int piecesCreated = 1;
        while(piecesCreated <= 10) {
            int globalCount = counter.addPiece();
            if (globalCount == PieceCounter.MAX_PIECES) {
                break;
            }

            System.out.println("PRODUCE: P"+ id +" fabricando su "+ piecesCreated +"ª pieza.");
            try {
                Thread.sleep((5 + random.nextInt(10 - 5 + 1)) * 1000L);
                queue.put(globalCount);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("PRODUCE: "+ id +" añade la pieza, ahora hay "+ queue.size() +" piezas en la cola compartida.");
            piecesCreated++;
        }
    }
}

