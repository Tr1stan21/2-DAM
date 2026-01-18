package infrastructure.persistance;

import domain.model.Offer;
import domain.repository.OfferRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de OfferRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateOfferRepository extends AbstractHibernateRepository<Offer>
        implements OfferRepository {

    public HibernateOfferRepository() {
        super(Offer.class);
    }

    @Override
    public Optional<Offer> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<Offer> findAll() {
        return super.findAll();
    }

    @Override
    public Offer create(Offer entity) {
        return super.create(entity);
    }

    @Override
    public Offer update(Offer entity) {
        return super.update(entity);
    }

    @Override
    public boolean deleteById(Integer id) {
        return super.deleteById(id);
    }

    @Override
    public void delete(Offer entity) {
        super.delete(entity);
    }
}
