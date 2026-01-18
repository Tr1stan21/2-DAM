package infrastructure.persistence;

import domain.model.VehicleCategory;
import domain.repository.VehicleCategoryRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de VehicleCategoryRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateVehicleCategoryRepository extends AbstractHibernateRepository<VehicleCategory>
        implements VehicleCategoryRepository {

    public HibernateVehicleCategoryRepository() {
        super(VehicleCategory.class);
    }

    @Override
    public Optional<VehicleCategory> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<VehicleCategory> findAll() {
        return super.findAll();
    }
}
