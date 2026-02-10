package infrastructure.persistence;

import domain.model.Entrenador;
import domain.repository.EntrenadorRepository;
import org.hibernate.Session;

public class HibernateEntrenadorRepository extends HibernateBaseRepository<Entrenador, Long>
        implements EntrenadorRepository {
    protected HibernateEntrenadorRepository(Session session) {
        super(session);
    }
}