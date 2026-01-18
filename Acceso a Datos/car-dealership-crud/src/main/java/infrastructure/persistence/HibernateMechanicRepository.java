package infrastructure.persistence;

import domain.model.Mechanic;
import domain.repository.MechanicRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de MechanicRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateMechanicRepository extends AbstractHibernateRepository<Mechanic>
        implements MechanicRepository {

    public HibernateMechanicRepository() {
        super(Mechanic.class);
    }

    @Override
    public Optional<Mechanic> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<Mechanic> findAll() {
        return super.findAll();
    }
}
