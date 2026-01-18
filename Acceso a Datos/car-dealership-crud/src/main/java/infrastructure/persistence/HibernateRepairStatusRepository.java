package infrastructure.persistance;

import domain.model.RepairStatus;
import domain.repository.RepairStatusRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de RepairStatusRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateRepairStatusRepository extends AbstractHibernateRepository<RepairStatus>
        implements RepairStatusRepository {

    public HibernateRepairStatusRepository() {
        super(RepairStatus.class);
    }

    @Override
    public Optional<RepairStatus> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<RepairStatus> findAll() {
        return super.findAll();
    }
}
