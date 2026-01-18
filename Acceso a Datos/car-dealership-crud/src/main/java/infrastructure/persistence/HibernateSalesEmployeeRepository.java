package infrastructure.persistence;

import domain.model.SalesEmployee;
import domain.repository.SalesEmployeeRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de SalesEmployeeRepository usando Hibernate.
 * Hereda toda la lógica común de AbstractHibernateRepository.
 */
public class HibernateSalesEmployeeRepository extends AbstractHibernateRepository<SalesEmployee>
        implements SalesEmployeeRepository {

    public HibernateSalesEmployeeRepository() {
        super(SalesEmployee.class);
    }

    @Override
    public Optional<SalesEmployee> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<SalesEmployee> findAll() {
        return super.findAll();
    }
}
