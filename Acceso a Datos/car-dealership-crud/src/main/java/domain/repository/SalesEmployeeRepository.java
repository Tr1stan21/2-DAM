package domain.repository;

import domain.model.SalesEmployee;
import domain.repository.base.FindRepository;

/**
 * Contrato de repositorio para la entidad SalesEmployee.
 * Proporciona únicamente operaciones de búsqueda.
 */
public interface SalesEmployeeRepository extends FindRepository<SalesEmployee, Integer> {
}
