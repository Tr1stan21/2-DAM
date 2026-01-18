package domain.repository;

import domain.model.Manager;
import domain.repository.base.FindRepository;

/**
 * Contrato de repositorio para la entidad Manager.
 * Proporciona únicamente operaciones de búsqueda.
 */
public interface ManagerRepository extends FindRepository<Manager, Integer> {
}
