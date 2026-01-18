package infrastructure.persistence;

import domain.model.VehicleStatus;
import domain.repository.VehicleStatusRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de VehicleStatusRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateVehicleStatusRepository extends AbstractHibernateRepository<VehicleStatus>
        implements VehicleStatusRepository {

    public HibernateVehicleStatusRepository() {
        super(VehicleStatus.class);
    }

    @Override
    public Optional<VehicleStatus> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<VehicleStatus> findAll() {
        return super.findAll();
    }
}
