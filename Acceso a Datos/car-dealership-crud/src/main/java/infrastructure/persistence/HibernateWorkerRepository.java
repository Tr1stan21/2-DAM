package infrastructure.persistence;

import domain.model.Worker;
import domain.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de WorkerRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateWorkerRepository extends AbstractHibernateRepository<Worker>
        implements WorkerRepository {

    public HibernateWorkerRepository() {
        super(Worker.class);
    }

    @Override
    public Optional<Worker> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<Worker> findAll() {
        return super.findAll();
    }

}
