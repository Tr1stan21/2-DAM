package infrastructure.persistence;

import domain.model.Dealership;
import domain.repository.DealershipRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de DealershipRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateDealershipRepository extends AbstractHibernateRepository<Dealership>
        implements DealershipRepository {

    public HibernateDealershipRepository() {
        super(Dealership.class);
    }

    @Override
    public Optional<Dealership> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<Dealership> findAll() {
        return super.findAll();
    }
}
