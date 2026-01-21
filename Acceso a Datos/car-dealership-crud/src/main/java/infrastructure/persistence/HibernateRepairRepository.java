package infrastructure.persistence;

import domain.model.Repair;
import domain.repository.RepairRepository;
import infrastructure.persistence.AbstractHibernateRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de RepairRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateRepairRepository extends AbstractHibernateRepository<Repair>
        implements RepairRepository {

    public HibernateRepairRepository() {
        super(Repair.class);
    }

    @Override
    public Optional<Repair> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<Repair> findAll() {
        return super.findAll();
    }

    @Override
    public Repair create(Repair entity) {
        return super.create(entity);
    }

    @Override
    public Repair update(Repair entity) {
        return super.update(entity);
    }

    @Override
    public boolean deleteById(Integer id) {
        return super.deleteById(id);
    }

    @Override
    public void delete(Repair entity) {
        super.delete(entity);
    }
}
