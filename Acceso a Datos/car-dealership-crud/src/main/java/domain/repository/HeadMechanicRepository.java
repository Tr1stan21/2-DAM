package domain.repository;

import domain.model.HeadMechanic;
import domain.repository.base.FindRepository;

/**
 * Contrato de repositorio para la entidad HeadMechanic.
 * Proporciona únicamente operaciones de búsqueda.
 */
public interface HeadMechanicRepository extends FindRepository<HeadMechanic, Integer> {
}
