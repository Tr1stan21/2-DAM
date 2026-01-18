package infrastructure.persistence;

import domain.model.Client;
import domain.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de ClientRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateClientRepository extends AbstractHibernateRepository<Client>
        implements ClientRepository {

    public HibernateClientRepository() {
        super(Client.class);
    }

    @Override
    public Optional<Client> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<Client> findAll() {
        return super.findAll();
    }

    @Override
    public Client create(Client entity) {
        return super.create(entity);
    }

    @Override
    public Client update(Client entity) {
        return super.update(entity);
    }

    @Override
    public boolean deleteById(Integer id) {
        return super.deleteById(id);
    }

    @Override
    public void delete(Client entity) {
        super.delete(entity);
    }
}
