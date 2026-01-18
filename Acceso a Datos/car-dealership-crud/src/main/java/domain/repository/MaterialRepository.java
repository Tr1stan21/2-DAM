package domain.repository;

import domain.model.Material;
import domain.repository.base.CrudRepository;

/**
 * Contrato de repositorio para la entidad Material.
 * Proporciona operaciones CRUD completas.
 */
public interface MaterialRepository extends CrudRepository<Material, Integer> {
}
