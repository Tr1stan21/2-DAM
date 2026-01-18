package domain.repository.base;

/**
 * Contrato base para operaciones de eliminación de entidades.
 * Define capacidades de borrado por ID o entidad.
 *
 * @param <T>  Tipo de entidad
 * @param <ID> Tipo del identificador
 */
public interface DeleteRepository<T, ID> {

    /**
     * Elimina una entidad por su identificador.
     *
     * @param id Identificador de la entidad a eliminar
     * @return true si la entidad fue eliminada, false si no existía
     */
    boolean deleteById(ID id);

    /**
     * Elimina una entidad del repositorio.
     *
     * @param entity Entidad a eliminar
     */
    void delete(T entity);
}
