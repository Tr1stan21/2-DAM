package domain.repository.base;

/**
 * Contrato base para operaciones de creación de entidades.
 * Define capacidad de persistir nuevas entidades.
 *
 * @param <T> Tipo de entidad
 */
public interface CreateRepository<T> {

    /**
     * Persiste una nueva entidad en el repositorio.
     *
     * @param entity Entidad a crear
     * @return Entidad persistida con ID generado
     */
    T create(T entity);
}
