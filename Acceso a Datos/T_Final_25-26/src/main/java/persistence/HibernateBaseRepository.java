package persistence;

import domain.repository.BaseRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Generic repository implementation using Hibernate 6.x/7.x.
 * Uses standard JPA methods for persistence operations.
 *
 * @param <T>  the entity type
 * @param <ID> the primary key type
 */
public abstract class HibernateBaseRepository<T, ID> implements BaseRepository<T, ID> {


    private final Class<T> entityClass;
    private final String entityName;

    protected Session session;

    /**
     * Constructs a repository and automatically detects the entity type using
     * reflection.
     *
     * @param session the Hibernate session to use for database operations
     */
    @SuppressWarnings("unchecked")
    protected HibernateBaseRepository(Session session) {
        this.session = session;
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
        this.entityName = entityClass.getName();
    }

    public T save(T entity) {
        Objects.requireNonNull(entity, "Entity cannot be null");

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException("Error creating " + entityName + ": " + e.getMessage(), e);
        }
    }

    public Optional<T> findById(ID id) {

        try {
            T entity = session.find(entityClass, id);
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            throw new RuntimeException("Error finding entity by ID: " + e.getMessage(), e);
        }
    }

    public List<T> findAll() {

        try {
            String hql = "FROM " + entityName;
            Query<T> query = session.createQuery(hql, entityClass);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all entities: " + e.getMessage(), e);
        }
    }

    public T update(T entity) {
        Objects.requireNonNull(entity, "Entity cannot be null");

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            T updatedEntity = session.merge(entity);
            transaction.commit();
            return updatedEntity;

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException("Error updating entity: " + e.getMessage(), e);
        }
    }

    public void delete(T entity) {
        Objects.requireNonNull(entity, "Entity cannot be null");

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException("Error deleting entity: " + e.getMessage(), e);
        }
    }

    public boolean deleteById(ID id) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            T entity = session.find(entityClass, id);
            if (entity == null) {
                transaction.rollback();
                return false;
            }
            session.remove(entity);
            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException("Error deleting " + entityName + " by id", e);
        }
    }

}