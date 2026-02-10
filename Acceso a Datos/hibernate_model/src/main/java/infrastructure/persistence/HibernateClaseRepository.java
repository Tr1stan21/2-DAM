package infrastructure.persistence;

import domain.model.Clase;
import domain.repository.ClaseRepository;
import org.hibernate.Session;

public class HibernateClaseRepository extends HibernateBaseRepository<Clase, String>
        implements ClaseRepository {
    public HibernateClaseRepository(Session session) {
        super(session);
    }
}
