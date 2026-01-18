package domain.repository;

import domain.model.OfferStatus;
import domain.repository.base.FindRepository;

/**
 * Contrato de repositorio para la entidad OfferStatus.
 * Proporciona únicamente operaciones de búsqueda.
 */
public interface OfferStatusRepository extends FindRepository<OfferStatus, Integer> {
}
