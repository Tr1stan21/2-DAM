package domain.repository;

import domain.model.Dealership;
import domain.repository.base.FindRepository;

/**
 * Contrato de repositorio para la entidad Dealership.
 * Proporciona únicamente operaciones de búsqueda.
 */
public interface DealershipRepository extends FindRepository<Dealership, Integer> {
}
