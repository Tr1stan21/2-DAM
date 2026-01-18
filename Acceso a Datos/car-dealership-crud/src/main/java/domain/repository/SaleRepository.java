package domain.repository;

import domain.model.Sale;
import domain.repository.base.CreateRepository;

/**
 * Contrato de repositorio para la entidad Sale.
 * Proporciona únicamente operaciones de creación.
 */
public interface SaleRepository extends CreateRepository<Sale> {
}
