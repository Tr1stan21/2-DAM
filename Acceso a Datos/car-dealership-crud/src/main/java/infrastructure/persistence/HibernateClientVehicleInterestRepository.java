package infrastructure.persistence;

import domain.model.ClientVehicleInterest;
import domain.repository.ClientVehicleInterestRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de ClientVehicleInterestRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateClientVehicleInterestRepository extends AbstractHibernateRepository<ClientVehicleInterest>
        implements ClientVehicleInterestRepository {

    public HibernateClientVehicleInterestRepository() {
        super(ClientVehicleInterest.class);
    }

    @Override
    public Optional<ClientVehicleInterest> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<ClientVehicleInterest> findAll() {
        return super.findAll();
    }

    @Override
    public ClientVehicleInterest create(ClientVehicleInterest entity) {
        return super.create(entity);
    }

    @Override
    public ClientVehicleInterest update(ClientVehicleInterest entity) {
        return super.update(entity);
    }

    @Override
    public boolean deleteById(Integer id) {
        return super.deleteById(id);
    }

    @Override
    public void delete(ClientVehicleInterest entity) {
        super.delete(entity);
    }
}
