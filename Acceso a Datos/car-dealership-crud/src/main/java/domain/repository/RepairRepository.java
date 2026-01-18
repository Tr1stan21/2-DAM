package domain.repository;

import domain.model.Repair;
import domain.repository.base.CrudRepository;

/**
 * Contrato de repositorio para la entidad Repair.
 * Proporciona operaciones CRUD completas.
 */
public interface RepairRepository extends CrudRepository<Repair, Integer> {
}
