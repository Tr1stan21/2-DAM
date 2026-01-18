package infrastructure.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import infrastructure.persistence.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

/**
 * Clase abstracta base para implementaciones de repositorios con Hibernate.
 * Encapsula la lógica común de operaciones CRUD usando HQL y Hibernate Session.
 * <p>
 * Aplica el patrón Template Method para gestión de transacciones y sesiones.
 *
 * @param <T> Tipo de entidad gestionada por el repositorio
 */
public abstract class AbstractHibernateRepository<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractHibernateRepository.class);

    private final Class<T> entityClass;
    private final String entityName;

    /**
     * Constructor protegido que inicializa el tipo de entidad.
     *
     * @param entityClass Clase de la entidad gestionada
     */
    protected AbstractHibernateRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.entityName = entityClass.getSimpleName();
    }

    /**
     * Busca una entidad por su identificador.
     *
     * @param id Identificador de la entidad
     * @return Optional conteniendo la entidad si existe
     */
    public Optional<T> findById(Integer id) {
        logger.debug("Finding {} with id: {}", entityName, id);
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            T entity = session.find(entityClass, id);
            transaction.commit();
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error finding {} with id: {}", entityName, id, e);
            throw new RuntimeException("Error finding " + entityName + " by id", e);
        }
    }

    /**
     * Recupera todas las entidades del repositorio.
     *
     * @return Lista de todas las entidades
     */
    public List<T> findAll() {
        logger.debug("Finding all {}", entityName);
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM " + entityName;
            Query<T> query = session.createQuery(hql, entityClass);
            List<T> results = query.getResultList();
            transaction.commit();
            return results;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error finding all {}", entityName, e);
            throw new RuntimeException("Error finding all " + entityName, e);
        }
    }

    /**
     * Persiste una nueva entidad en el repositorio.
     *
     * @param entity Entidad a crear
     * @return Entidad persistida con ID generado
     */
    public T create(T entity) {
        logger.debug("Creating new {}", entityName);
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            logger.debug("{} created successfully with id: {}", entityName, entity);
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error creating {}", entityName, e);
            throw new RuntimeException("Error creating " + entityName, e);
        }
    }

    /**
     * Actualiza una entidad existente en el repositorio.
     *
     * @param entity Entidad con los datos actualizados
     * @return Entidad actualizada
     */
    public T update(T entity) {
        logger.debug("Updating {}", entityName);
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
            throw new RuntimeException("Error updating " + entityName, e);
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

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            T entity = session.find(entityClass, id);

            if (entity == null) {
                transaction.commit();
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
     * Elimina una entidad del repositorio.
     *
     * @param entity Entidad a eliminar
     */
    public void delete(T entity) {
        logger.debug("Deleting {} entity", entityName);
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
            logger.debug("{} deleted successfully", entityName);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error deleting {}", entityName, e);
            throw new RuntimeException("Error deleting " + entityName, e);
        }
    }
}
