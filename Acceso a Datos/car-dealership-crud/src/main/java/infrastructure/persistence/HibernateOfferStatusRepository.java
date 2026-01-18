package infrastructure.persistence;

import domain.model.OfferStatus;
import domain.repository.OfferStatusRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de OfferStatusRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateOfferStatusRepository extends AbstractHibernateRepository<OfferStatus>
        implements OfferStatusRepository {

    public HibernateOfferStatusRepository() {
        super(OfferStatus.class);
    }

    @Override
    public Optional<OfferStatus> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<OfferStatus> findAll() {
        return super.findAll();
    }
}
