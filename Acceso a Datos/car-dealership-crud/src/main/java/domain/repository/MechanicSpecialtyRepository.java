package domain.repository;

import domain.model.MechanicSpecialty;
import domain.repository.base.FindRepository;

/**
 * Contrato de repositorio para la entidad MechanicSpecialty.
 * Proporciona únicamente operaciones de búsqueda.
 */
public interface MechanicSpecialtyRepository extends FindRepository<MechanicSpecialty, Integer> {
}
