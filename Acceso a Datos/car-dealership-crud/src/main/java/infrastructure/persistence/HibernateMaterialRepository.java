package infrastructure.persistance;

import domain.model.Material;
import domain.repository.MaterialRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de MaterialRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateMaterialRepository extends AbstractHibernateRepository<Material>
        implements MaterialRepository {

    public HibernateMaterialRepository() {
        super(Material.class);
    }

    @Override
    public Optional<Material> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<Material> findAll() {
        return super.findAll();
    }

    @Override
    public Material create(Material entity) {
        return super.create(entity);
    }

    @Override
    public Material update(Material entity) {
        return super.update(entity);
    }

    @Override
    public boolean deleteById(Integer id) {
        return super.deleteById(id);
    }

    @Override
    public void delete(Material entity) {
        super.delete(entity);
    }
}
