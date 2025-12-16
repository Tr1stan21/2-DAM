package workers;

import utils.Pieza;
import utils.Utils;

import java.util.concurrent.BlockingQueue;

public class Ensamblador implements Runnable{

    private final BlockingQueue<Pieza> queue;
    private final int totalDePiezas;

    /**
     * Construye un ensamblador que coge piezas de una cola compartida.
     *
     * @param queue la cola compartida entre productores y consumidores.
     * @param totalDePiezas total de piezas que se van a producir.
     */
    public Ensamblador(BlockingQueue<Pieza> queue, int totalDePiezas) {
        this.queue = queue;
        this.totalDePiezas = totalDePiezas;
    }

    /**
     * Coge tantas piezas como se produzcan con un tiempo de espera.
     */
    @Override
    public void run() {
        Pieza piezaEnsamblada;
        int piezasConsumidas = 0;
        System.out.println("CONSUME: ENSAMBLADOR esperando pieza...");
        while (piezasConsumidas <  totalDePiezas) {
            try {
                piezaEnsamblada = queue.take();
                piezasConsumidas++;
            } catch (InterruptedException e) {
                System.err.println("Error al coger pieza");
                throw new RuntimeException(e);
            }
            Utils.espera(8, 15);
            if (piezaEnsamblada.getPrioridad() == 1) System.out.println("CONSUME: ENSAMBLADOR ensambla [PIEZA B] (Alta prioridad). Total ensambladas: "+ (piezasConsumidas) +". Quedan "+queue.size()+" en cola.");
            if (piezaEnsamblada.getPrioridad() == 2) System.out.println("CONSUME: ENSAMBLADOR ensambla [PIEZA A] (Baja prioridad). Total ensambladas: "+ (piezasConsumidas) +". Quedan "+queue.size()+" en cola.");

        }

    }
}
