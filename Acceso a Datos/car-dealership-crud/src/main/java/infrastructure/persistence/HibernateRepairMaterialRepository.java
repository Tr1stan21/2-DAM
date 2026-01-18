package infrastructure.persistence;

import domain.model.RepairMaterial;
import domain.model.RepairMaterialId;
import domain.repository.RepairMaterialRepository;
import infrastructure.persistence.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de RepairMaterialRepository usando Hibernate.
 * Maneja entidades con clave compuesta RepairMaterialId.
 * No puede extender AbstractHibernateRepository debido a la clave compuesta.
 */
public class HibernateRepairMaterialRepository implements RepairMaterialRepository {

    private static final Logger logger = LoggerFactory.getLogger(HibernateRepairMaterialRepository.class);
    private static final String ENTITY_NAME = "RepairMaterial";

    @Override
    public Optional<RepairMaterial> findById(RepairMaterialId id) {
        logger.debug("Finding {} with id: {}", ENTITY_NAME, id);
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            RepairMaterial entity = session.find(RepairMaterial.class, id);
            transaction.commit();
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error finding {} with id: {}", ENTITY_NAME, id, e);
            throw new RuntimeException("Error finding " + ENTITY_NAME + " by id", e);
        }
    }

    @Override
    public List<RepairMaterial> findAll() {
        logger.debug("Finding all {}", ENTITY_NAME);
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM " + ENTITY_NAME;
            Query<RepairMaterial> query = session.createQuery(hql, RepairMaterial.class);
            List<RepairMaterial> results = query.getResultList();
            transaction.commit();
            return results;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error finding all {}", ENTITY_NAME, e);
            throw new RuntimeException("Error finding all " + ENTITY_NAME, e);
        }
    }

    @Override
    public RepairMaterial create(RepairMaterial entity) {
        logger.debug("Creating new {}", ENTITY_NAME);
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            logger.debug("{} created successfully", ENTITY_NAME);
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error creating {}", ENTITY_NAME, e);
            throw new RuntimeException("Error creating " + ENTITY_NAME, e);
        }
    }

    @Override
    public RepairMaterial update(RepairMaterial entity) {
        logger.debug("Updating {}", ENTITY_NAME);
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            RepairMaterial updatedEntity = session.merge(entity);
            transaction.commit();
            logger.debug("{} updated successfully", ENTITY_NAME);
            return updatedEntity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error updating {}", ENTITY_NAME, e);
            throw new RuntimeException("Error updating " + ENTITY_NAME, e);
        }
    }

    @Override
    public boolean deleteById(RepairMaterialId id) {
        logger.debug("Deleting {} with id: {}", ENTITY_NAME, id);
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            RepairMaterial entity = session.find(RepairMaterial.class, id);

            if (entity == null) {
                transaction.commit();
                logger.debug("{} with id {} not found for deletion", ENTITY_NAME, id);
                return false;
            }

            session.remove(entity);
            transaction.commit();
            logger.debug("{} with id {} deleted successfully", ENTITY_NAME, id);
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error deleting {} with id: {}", ENTITY_NAME, id, e);
            throw new RuntimeException("Error deleting " + ENTITY_NAME + " by id", e);
        }
    }

    @Override
    public void delete(RepairMaterial entity) {
        logger.debug("Deleting {} entity", ENTITY_NAME);
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
            logger.debug("{} deleted successfully", ENTITY_NAME);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error deleting {}", ENTITY_NAME, e);
            throw new RuntimeException("Error deleting " + ENTITY_NAME, e);
        }
    }
}
