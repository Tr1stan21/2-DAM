package workers;

import utils.ContadorPiezas;
import utils.Pieza;
import utils.Utils;

import java.util.concurrent.BlockingQueue;

public class ProductorEstandar implements Runnable{

    private final int id;
    private final ContadorPiezas contadorPiezas;
    private final BlockingQueue<Pieza> queue;
    private final int piezasPorProductor;

    /**
     * Construye un ProductorUrgente que crea piezas de baja prioridad y las añade a la cola.
     *
     * @param id id del productor.
     * @param contadorPiezas contador globar de piezas fabricadas.
     * @param queue cola compartida de piezas.
     * @param piezasPorProductor numero de piezas a fabricar.
     */
    public ProductorEstandar(int id, ContadorPiezas contadorPiezas, BlockingQueue<Pieza> queue, int piezasPorProductor) {
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
        System.out.println("PRODUCE: [PEA-" + id + "] crea PIEZA A número "  + (contadorPiezas.addPieza()) + " (Prioridad baja).");
        Utils.espera(6, 10);
        try {
            queue.put(new Pieza(2));
        } catch (InterruptedException e) {
            System.err.println("Productor-[PEA-" + id + "] problema al insertar una pieza en la cola compartida.");
            throw new RuntimeException(e);
        }
    }

}
