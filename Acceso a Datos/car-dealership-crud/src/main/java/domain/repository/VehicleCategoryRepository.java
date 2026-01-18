package domain.repository;

import domain.model.VehicleCategory;
import domain.repository.base.FindRepository;

/**
 * Contrato de repositorio para la entidad VehicleCategory.
 * Proporciona únicamente operaciones de búsqueda.
 */
public interface VehicleCategoryRepository extends FindRepository<VehicleCategory, Integer> {
}
