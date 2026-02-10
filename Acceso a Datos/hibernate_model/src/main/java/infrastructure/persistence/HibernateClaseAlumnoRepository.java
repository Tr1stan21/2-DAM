package infrastructure.persistence;

import domain.model.ClaseAlumno;
import domain.model.ClaseAlumnoId;
import domain.repository.ClaseAlumnoRepository;
import org.hibernate.Session;

public class HibernateClaseAlumnoRepository extends HibernateBaseRepository<ClaseAlumno, ClaseAlumnoId>
        implements ClaseAlumnoRepository {
    protected HibernateClaseAlumnoRepository(Session session) {
        super(session);
    }
}
