package domain.repository;

import domain.model.PaymentMethod;
import domain.repository.base.FindRepository;

/**
 * Contrato de repositorio para la entidad PaymentMethod.
 * Proporciona únicamente operaciones de búsqueda.
 */
public interface PaymentMethodRepository extends FindRepository<PaymentMethod, Integer> {
}
