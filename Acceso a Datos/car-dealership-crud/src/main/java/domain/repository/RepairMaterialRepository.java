package domain.repository;

import domain.model.RepairMaterial;
import domain.model.RepairMaterialId;
import domain.repository.base.CrudRepository;

/**
 * Contrato de repositorio para la entidad RepairMaterial.
 * Proporciona operaciones CRUD completas.
 * Nota: Utiliza clave compuesta RepairMaterialId.
 */
public interface RepairMaterialRepository extends CrudRepository<RepairMaterial, RepairMaterialId> {
}
