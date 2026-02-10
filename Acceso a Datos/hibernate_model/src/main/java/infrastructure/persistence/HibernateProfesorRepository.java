package infrastructure.persistence;

import domain.model.Profesor;
import domain.repository.ProfesorRepository;
import org.hibernate.Session;

public class HibernateProfesorRepository extends HibernateBaseRepository<Profesor, Long>
        implements ProfesorRepository {
    public HibernateProfesorRepository(Session session) {
        super(session);
    }
}
