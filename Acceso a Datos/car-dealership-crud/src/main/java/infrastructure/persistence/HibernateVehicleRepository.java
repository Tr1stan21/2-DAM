package infrastructure.persistence;

import domain.model.Vehicle;
import domain.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de VehicleRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateVehicleRepository extends AbstractHibernateRepository<Vehicle>
        implements VehicleRepository {

    public HibernateVehicleRepository() {
        super(Vehicle.class);
    }

    @Override
    public Optional<Vehicle> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<Vehicle> findAll() {
        return super.findAll();
    }

    @Override
    public Vehicle create(Vehicle entity) {
        return super.create(entity);
    }

    @Override
    public Vehicle update(Vehicle entity) {
        return super.update(entity);
    }

    @Override
    public boolean deleteById(Integer id) {
        return super.deleteById(id);
    }

    @Override
    public void delete(Vehicle entity) {
        super.delete(entity);
    }
}
