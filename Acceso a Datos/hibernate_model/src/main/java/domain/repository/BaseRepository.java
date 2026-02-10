package domain.repository;

import java.util.List;
import java.util.Optional;

/**
 * Generic repository with basic CRUD operations.
 * Provides standard persistence operations for domain entities.
 *
 * @param <T>  the entity type
 * @param <ID> the primary key type
 */
public interface BaseRepository<T, ID> {

    /**
     * Persists a new entity in the database.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    T save(T entity);

    /**
     * Finds an entity by its identifier.
     *
     * @param id the entity identifier
     * @return an Optional containing the entity if found, empty otherwise
     */
    Optional<T> findById(ID id);

    /**
     * Retrieves all entities from the repository.
     *
     * @return list containing all entities
     */
    List<T> findAll();

    /**
     * Updates an existing entity.
     *
     * @param entity the entity to update
     * @return the updated entity
     */
    T update(T entity);

    /**
     * Deletes an entity from the database.
     *
     * @param entity the entity to delete
     */
    void delete(T entity);

    /**
     * Deletes an entity by its identifier.
     *
     * @param id the identifier of the entity to delete
     * @return true if the entity was deleted, false if it didn't exist
     */
    boolean deleteById(ID id);
}