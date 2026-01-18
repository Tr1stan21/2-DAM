package domain.repository;

import domain.model.Mechanic;
import domain.repository.base.FindRepository;

/**
 * Contrato de repositorio para la entidad Mechanic.
 * Proporciona únicamente operaciones de búsqueda.
 */
public interface MechanicRepository extends FindRepository<Mechanic, Integer> {
}
