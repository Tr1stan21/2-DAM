package domain.repository;

import domain.model.Worker;
import domain.repository.base.FindRepository;

/**
 * Contrato de repositorio para la entidad Worker.
 * Proporciona operaciones de búsqueda y actualización.
 */
public interface WorkerRepository extends FindRepository<Worker, Integer> {
}
