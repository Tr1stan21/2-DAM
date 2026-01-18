package domain.repository.base;

import java.util.List;
import java.util.Optional;

/**
 * Contrato base para operaciones de búsqueda y lectura de entidades.
 * Define capacidades de consulta sin modificación de estado.
 *
 * @param <T>  Tipo de entidad
 * @param <ID> Tipo del identificador
 */
public interface FindRepository<T, ID> {

    /**
     * Busca una entidad por su identificador.
     *
     * @param id Identificador de la entidad
     * @return Optional conteniendo la entidad si existe
     */
    Optional<T> findById(ID id);

    /**
     * Recupera todas las entidades del repositorio.
     *
     * @return Lista de todas las entidades
     */
    List<T> findAll();
}
