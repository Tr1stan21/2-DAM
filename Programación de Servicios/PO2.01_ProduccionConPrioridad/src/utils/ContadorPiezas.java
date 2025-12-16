package utils;

public class ContadorPiezas {

    private int piezasCreadas = 0;

    /**
     * Metodo para incrementar el numero de piezas creadas y devolver cuantas hay.
     *
     * @return devuelve el número de piezas creadas hasta el momento
     */
    public synchronized int addPieza() {
        piezasCreadas++;
        return piezasCreadas;
    }

}
