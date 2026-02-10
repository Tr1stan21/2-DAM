package infrastructure.persistence;

import domain.model.Alumno;
import domain.repository.AlumnoRepository;
import org.hibernate.Session;

public class HibernateAlumnoRepository extends HibernateBaseRepository<Alumno, String>
        implements AlumnoRepository {
    public HibernateAlumnoRepository(Session session) {
        super(session);
    }
}
