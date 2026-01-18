package infrastructure.persistance;

import domain.model.Sale;
import domain.repository.SaleRepository;

/**
 * Implementación de SaleRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateSaleRepository extends AbstractHibernateRepository<Sale>
        implements SaleRepository {

    public HibernateSaleRepository() {
        super(Sale.class);
    }

    @Override
    public Sale create(Sale entity) {
        return super.create(entity);
    }
}
