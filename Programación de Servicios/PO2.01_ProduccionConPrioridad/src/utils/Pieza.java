package utils;

public class Pieza implements Comparable<Pieza>{

    int prioridad;

    /**
     * Construye una pieza.
     *
     * @param prioridad prioridad de la pieza
     */
    public Pieza(int prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * Compara la prioridad de this.pieza con la de otra pieza.
     *
     * @param other Otra pieza.
     *
     * @return Pieza con prioridad.
     */
    @Override
    public int compareTo(Pieza other) {
        return Integer.compare(this.prioridad, other.prioridad);
    }

    public int getPrioridad() {
        return prioridad;
    }
}
