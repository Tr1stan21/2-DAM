package dao;

import model.Student;

import java.util.List;

public interface GenericDao<T, ID> {

    /**
     * Creates a new entity.
     *
     * @param entity entity to be created
     * @return the created entity
     */
    T create(T entity);

    /**
     * Retrieves an entity by its identifier.
     *
     * @param id identifier of the entity
     * @return the found entity or null if not found
     */
    T findById(ID id);

    /**
     * Retrieves all entities.
     *
     * @return list of all entities
     */
    List<T> findAll();

    /**
     * Updates an existing entity.
     *
     * @param entity entity to be updated
     * @return the updated entity
     */
    T update(T entity);

    /**
     * Deletes an entity by its identifier.
     *
     * @param id identifier of the entity to be deleted
     */
    void deleteById(ID id);
}
