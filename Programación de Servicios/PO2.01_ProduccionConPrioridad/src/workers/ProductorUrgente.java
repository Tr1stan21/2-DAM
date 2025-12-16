package workers;

import utils.ContadorPiezas;
import utils.Pieza;
import utils.Utils;

import java.util.concurrent.BlockingQueue;

public class ProductorUrgente implements Runnable{

    private final int id;
    private final ContadorPiezas contadorPiezas;
    private final BlockingQueue<Pieza> queue;
    private final int piezasPorProductor;

    /**
     * Construye un ProductorUrgente que crea piezas de alta prioridad y las añade a la cola.
     *
     * @param id id del productor.
     * @param contadorPiezas contador globar de piezas fabricadas.
     * @param queue cola compartida de piezas.
     * @param piezasPorProductor numero de piezas a fabricar.
     */
    public ProductorUrgente(int id, ContadorPiezas contadorPiezas, BlockingQueue<Pieza> queue, int piezasPorProductor) {
        this.id = id;
        this.contadorPiezas = contadorPiezas;
        this.queue = queue;
        this.piezasPorProductor = piezasPorProductor;
    }

    /**
     * Metodo que fabrica piezasPorProductor piezas
     */
    @Override
    public void run() {
        for (int i = 0; i < piezasPorProductor; i++) {
            fabricaPieza();
        }
    }

    /**
     * Fabrica una pieza de alta prioridad con un tiempo de espera y la añade a la cola.
     */
    private void fabricaPieza() {
        System.out.println("PRODUCE: [PEB-" + id + "] crea PIEZA B número "  + (contadorPiezas.addPieza()) + " (Prioridad alta).");
        Utils.espera(3, 5);
        try {
            queue.put(new Pieza(1));
        } catch (InterruptedException e) {
            System.err.println("Productor-[PEB-" + id + "] problema al insertar una pieza en la cola compartida.");
            throw new RuntimeException(e);
        }
    }

}
