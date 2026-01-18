package infrastructure.persistance;

import domain.model.MechanicSpecialty;
import domain.repository.MechanicSpecialtyRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de MechanicSpecialtyRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateMechanicSpecialtyRepository extends AbstractHibernateRepository<MechanicSpecialty>
        implements MechanicSpecialtyRepository {

    public HibernateMechanicSpecialtyRepository() {
        super(MechanicSpecialty.class);
    }

    @Override
    public Optional<MechanicSpecialty> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<MechanicSpecialty> findAll() {
        return super.findAll();
    }
}
