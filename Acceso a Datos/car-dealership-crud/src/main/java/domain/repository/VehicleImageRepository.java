package domain.repository;

import domain.model.VehicleImage;
import domain.repository.base.FindRepository;

/**
 * Contrato de repositorio para la entidad VehicleImage.
 * Proporciona únicamente operaciones de búsqueda.
 */
public interface VehicleImageRepository extends FindRepository<VehicleImage, Integer> {
}
