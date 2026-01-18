package domain.repository.base;

/**
 * Contrato base para operaciones de actualización de entidades.
 * Define capacidad de modificar entidades existentes.
 *
 * @param <T> Tipo de entidad
 */
public interface UpdateRepository<T> {

    /**
     * Actualiza una entidad existente en el repositorio.
     *
     * @param entity Entidad con los datos actualizados
     * @return Entidad actualizada
     */
    T update(T entity);
}
