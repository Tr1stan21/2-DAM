package utils;

public class PieceCounter {
    private int totalPieces = 0;
    public static final int MAX_PIECES = 30;

    /**
     * Increments the global piece counter.
     *
     * @return the new global count.
     */
    public synchronized int addPiece() {
        totalPieces++;
        return totalPieces;
    }

}
