package domain.repository;

import domain.model.RepairStatus;
import domain.repository.base.FindRepository;

/**
 * Contrato de repositorio para la entidad RepairStatus.
 * Proporciona únicamente operaciones de búsqueda.
 */
public interface RepairStatusRepository extends FindRepository<RepairStatus, Integer> {
}
