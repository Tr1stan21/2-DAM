package infrastructure.persistence;

import domain.model.HeadMechanic;
import domain.repository.HeadMechanicRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de HeadMechanicRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateHeadMechanicRepository extends AbstractHibernateRepository<HeadMechanic>
        implements HeadMechanicRepository {

    public HibernateHeadMechanicRepository() {
        super(HeadMechanic.class);
    }

    @Override
    public Optional<HeadMechanic> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<HeadMechanic> findAll() {
        return super.findAll();
    }
}
