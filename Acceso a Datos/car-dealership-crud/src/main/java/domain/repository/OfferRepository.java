package domain.repository;

import domain.model.Offer;
import domain.repository.base.CrudRepository;

/**
 * Contrato de repositorio para la entidad Offer.
 * Proporciona operaciones CRUD completas.
 */
public interface OfferRepository extends CrudRepository<Offer, Integer> {
}
