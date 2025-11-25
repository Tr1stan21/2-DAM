package dao;

import connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ActorDao {

    /**
     * Inserta actores en la tabla Actor utilizando inserciones por lotes (batch).
     * Cada lote de tamaño batchSize se ejecuta y se confirma (commit) de forma independiente.
     *
     * @param actors    matriz [n][2] con first_name y last_name
     * @param batchSize número de actores por lote
     */
    public void insertActorsByBatches(String[][] actors, int batchSize) {
        if (actors == null || actors.length == 0) {
            return;
        }
        if (batchSize <= 0) {
            throw new IllegalArgumentException("batchSize debe ser mayor que 0");
        }

        String query = "INSERT INTO Actor(first_name, last_name, last_update) VALUES (?,?, NOW())";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {

            c.setAutoCommit(false);

            int position = insertFullBatches(actors, batchSize, ps, c);
            insertRemainingActors(actors, position, ps, c);

            c.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inserta todos los lotes completos de tamaño batchSize.
     * Devuelve el índice a partir del cual quedan actores sin agrupar en lote completo.
     */
    private int insertFullBatches(String[][] actors, int batchSize, PreparedStatement ps, Connection c) throws SQLException {
        int numFullBatches = actors.length / batchSize;

        for (int batchIndex = 0; batchIndex < numFullBatches; batchIndex++) {
            int fromIndex = batchIndex * batchSize;
            int toIndex = fromIndex + batchSize;

            addActorsToBatch(actors, fromIndex, toIndex, ps);
            executeAndCommitBatch(ps, c);
        }

        return numFullBatches * batchSize;
    }

    /**
     * Inserta el último grupo de actores que no forma un lote completo,
     * si es que quedan actores por insertar.
     */
    private void insertRemainingActors(String[][] actors, int startIndex, PreparedStatement ps, Connection c) throws SQLException {
        if (startIndex >= actors.length) {
            return;
        }

        addActorsToBatch(actors, startIndex, actors.length, ps);
        executeAndCommitBatch(ps, c);
    }

    /**
     * Añade al batch los actores comprendidos entre fromIndex (incluido)
     * y toIndex (excluido).
     */
    private void addActorsToBatch(String[][] actors, int fromIndex, int toIndex, PreparedStatement ps) throws SQLException {
        for (int i = fromIndex; i < toIndex; i++) {
            ps.setString(1, actors[i][0]); // first_name
            ps.setString(2, actors[i][1]); // last_name
            ps.addBatch();
        }
    }

    /**
     * Ejecuta el batch actual, comprueba errores y hace commit.
     * Si algún elemento del batch falla, hace rollback y lanza SQLException.
     */
    private void executeAndCommitBatch(PreparedStatement ps, Connection c) throws SQLException {
        int[] results = ps.executeBatch();
        for (int r : results) {
            if (r == Statement.EXECUTE_FAILED) {
                c.rollback();
                throw new SQLException("Error en ejecución del batch");
            }
        }
        ps.clearBatch();
        c.commit();
    }
}
