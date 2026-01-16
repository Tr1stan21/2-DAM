package domain.repository;

import domain.model.Vehicle;
import domain.model.VehicleStatus;

import java.util.List;

/**
 * Repositorio para gestión de vehículos.
 * Extiende BaseRepository para operaciones comunes de lectura.
 * Solo incluye update para cambios de estado (no se crean vehículos desde la aplicación).
 */
public interface VehicleRepository extends BaseRepository<Vehicle, Integer> {

    /**
     * Actualiza un vehículo existente (principalmente para cambios de estado).
     * @param vehicle Vehículo a actualizar
     */
    void update(Vehicle vehicle);

    List<Vehicle> search(VehicleFilter filter);

}

