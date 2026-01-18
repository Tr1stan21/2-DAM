package domain.repository.base;

/**
 * Contrato base para repositorios con operaciones CRUD completas.
 * Agrupa todas las capacidades básicas de persistencia.
 * <p>
 * Combina las interfaces base para proporcionar funcionalidad completa
 * de creación, lectura, actualización y eliminación de entidades.
 *
 * @param <T>  Tipo de entidad
 * @param <ID> Tipo del identificador
 */
public interface CrudRepository<T, ID> extends
        FindRepository<T, ID>,
        CreateRepository<T>,
        UpdateRepository<T>,
        DeleteRepository<T, ID> {
}
