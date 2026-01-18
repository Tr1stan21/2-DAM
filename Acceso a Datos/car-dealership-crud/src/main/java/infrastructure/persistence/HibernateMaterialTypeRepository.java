package infrastructure.persistence;

import domain.model.MaterialType;
import domain.repository.MaterialTypeRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de MaterialTypeRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateMaterialTypeRepository extends AbstractHibernateRepository<MaterialType>
        implements MaterialTypeRepository {

    public HibernateMaterialTypeRepository() {
        super(MaterialType.class);
    }

    @Override
    public Optional<MaterialType> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<MaterialType> findAll() {
        return super.findAll();
    }

}
