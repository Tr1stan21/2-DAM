package infrastructure.persistence;

import domain.model.Manager;
import domain.repository.ManagerRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de ManagerRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateManagerRepository extends AbstractHibernateRepository<Manager>
        implements ManagerRepository {

    public HibernateManagerRepository() {
        super(Manager.class);
    }

    @Override
    public Optional<Manager> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<Manager> findAll() {
        return super.findAll();
    }
}
