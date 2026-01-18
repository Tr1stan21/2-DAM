package domain.repository;

import domain.model.MaterialType;
import domain.repository.base.FindRepository;

/**
 * Contrato de repositorio para la entidad MaterialType.
 * Proporciona operaciones de búsqueda y consulta de tipos disponibles.
 */
public interface MaterialTypeRepository extends FindRepository<MaterialType, Integer> {
}
