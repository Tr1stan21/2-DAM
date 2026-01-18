package domain.repository;

import domain.model.VehicleStatus;
import domain.repository.base.FindRepository;

/**
 * Contrato de repositorio para la entidad VehicleStatus.
 * Proporciona únicamente operaciones de búsqueda.
 */
public interface VehicleStatusRepository extends FindRepository<VehicleStatus, Integer> {
}
