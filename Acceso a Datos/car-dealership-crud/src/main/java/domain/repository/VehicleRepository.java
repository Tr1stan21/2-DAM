package domain.repository;

import domain.model.Vehicle;
import domain.repository.base.CrudRepository;

/**
 * Contrato de repositorio para la entidad Vehicle.
 * Proporciona operaciones CRUD completas.
 */
public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
}
