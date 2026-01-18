package infrastructure.persistence;

import domain.model.VehicleImage;
import domain.repository.VehicleImageRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de VehicleImageRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateVehicleImageRepository extends AbstractHibernateRepository<VehicleImage>
        implements VehicleImageRepository {

    public HibernateVehicleImageRepository() {
        super(VehicleImage.class);
    }

    @Override
    public Optional<VehicleImage> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<VehicleImage> findAll() {
        return super.findAll();
    }
}
