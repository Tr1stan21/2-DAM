package com.tradetune.app.infrastructure.persistance;

import com.tradetune.app.domain.repository.CommonRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementación genérica del repositorio con Hibernate 6.x/7.x
 * Usa métodos JPA estándar
 *
 * @param <T> Tipo de la entidad
 * @param <ID> Tipo de la clave primaria
 */
public abstract class CommonRepositoryImpl<T, ID> implements CommonRepository<T, ID> {

    private static final Logger logger = LoggerFactory.getLogger(CommonRepositoryImpl.class);

    /** Clase de la entidad (se obtiene automáticamente) */
    private final Class<T> entityClass;

    /** Sesión de Hibernate */
    protected Session session;

    private final String entityName;


    /**
     * Constructor que detecta automáticamente el tipo de entidad
     *
     * @param session Sesión de Hibernate
     */
    @SuppressWarnings("unchecked")
    protected CommonRepositoryImpl(Session session) {
        this.session = session;
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
        this.entityName = entityClass.getName();
    }

    /**
     * INSERT - Guarda una nueva entidad usando session.persist()
     */
    @Override
    public T save(T entity) {
        Objects.requireNonNull(entity, "Entity cannot be null");
        logger.debug("Creating new {}", entityName);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(entity);  // ← persist() en lugar de save()
            transaction.commit();
            logger.debug("{} created successfully with id: {}", entityName, entity);
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error creating {}", entityName, e);
            throw new RuntimeException("Error al guardar la entidad: " + e.getMessage(), e);
        }
    }

    /**
     * SELECT - Busca una entidad por ID usando session.find()
     */
    @Override
    public Optional<T> findById(ID id) {
        logger.debug("Finding {} with id: {}", entityName, id);

        try {
            T entity = session.find(entityClass, id);  // ← find() en lugar de get()
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            logger.error("Error finding {} with id: {}", entityName, id, e);
            throw new RuntimeException("Error al buscar entidad por ID: " + e.getMessage(), e);
        }
    }

    /**
     * SELECT ALL - Obtiene todas las entidades
     */
    @Override
    public List<T> findAll() {
        logger.debug("Finding all {}", entityName);

        try {
            String hql = "FROM " + entityName;
            Query<T> query = session.createQuery(hql, entityClass);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error finding all {}", entityName, e);
            throw new RuntimeException("Error al obtener todas las entidades: " + e.getMessage(), e);
        }
    }

    /**
     * UPDATE - Actualiza una entidad usando session.merge()
     */
    @Override
    public T update(T entity) {
        Objects.requireNonNull(entity, "Entity cannot be null");
        logger.debug("Updating {}", entityName);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            T updatedEntity = session.merge(entity);
            transaction.commit();
            logger.debug("{} updated successfully", entityName);
            return updatedEntity;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error updating {}", entityName, e);
            throw new RuntimeException("Error al actualizar la entidad: " + e.getMessage(), e);
        }
    }

    /**
     * DELETE - Elimina una entidad usando session.remove()
     */
    @Override
    public void delete(T entity) {
        Objects.requireNonNull(entity, "Entity cannot be null");
        logger.debug("Deleting {} entity", entityName);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.remove(entity);  // ← remove() en lugar de delete()
            transaction.commit();
            logger.debug("{} deleted successfully", entityName);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error deleting {}", entityName, e);
            throw new RuntimeException("Error al eliminar la entidad: " + e.getMessage(), e);
        }
    }

    /**
     * Elimina una entidad por su identificador.
     *
     * @param id Identificador de la entidad a eliminar
     * @return true si la entidad fue eliminada, false si no existía
     */
    public boolean deleteById(Integer id) {
        logger.debug("Deleting {} with id: {}", entityName, id);
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            T entity = session.find(entityClass, id);
            if (entity == null) {
                transaction.rollback();
                logger.debug("{} with id {} not found for deletion", entityName, id);
                return false;
            }
            session.remove(entity);
            transaction.commit();
            logger.debug("{} with id {} deleted successfully", entityName, id);
            return true;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error deleting {} with id: {}", entityName, id, e);
            throw new RuntimeException("Error deleting " + entityName + " by id", e);
        }
    }


    /**
     * Obtiene la clase de la entidad
     *
     * @return Clase de la entidad
     */
    protected Class<T> getEntityClass() {
        return entityClass;
    }
}