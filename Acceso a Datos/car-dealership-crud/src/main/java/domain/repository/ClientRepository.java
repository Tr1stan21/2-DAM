package domain.repository;

import domain.model.Client;
import domain.repository.base.CrudRepository;

/**
 * Contrato de repositorio para la entidad Client.
 * Proporciona operaciones CRUD completas.
 */
public interface ClientRepository extends CrudRepository<Client, Integer> {
}
