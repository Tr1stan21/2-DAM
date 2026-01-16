package domain.repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio base con operaciones comunes de lectura.
 * Las operaciones de escritura (save, update, delete) deben declararse
 * explícitamente en cada repositorio específico según necesidades del dominio.
 *
 * @param <T> Tipo de la entidad
 * @param <ID> Tipo del identificador
 */
public interface BaseRepository<T, ID> {

    /**
     * Busca una entidad por su identificador.
     * @param id Identificador de la entidad
     * @return Optional con la entidad si existe
     */
    Optional<T> findById(ID id);

    /**
     * Obtiene todas las entidades.
     * @return Lista de todas las entidades
     */
    List<T> findAll();

}
