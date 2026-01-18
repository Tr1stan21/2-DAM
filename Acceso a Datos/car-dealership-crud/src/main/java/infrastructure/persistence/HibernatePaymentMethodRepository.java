package infrastructure.persistance;

import domain.model.PaymentMethod;
import domain.repository.PaymentMethodRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de PaymentMethodRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernatePaymentMethodRepository extends AbstractHibernateRepository<PaymentMethod>
        implements PaymentMethodRepository {

    public HibernatePaymentMethodRepository() {
        super(PaymentMethod.class);
    }

    @Override
    public Optional<PaymentMethod> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<PaymentMethod> findAll() {
        return super.findAll();
    }
}
