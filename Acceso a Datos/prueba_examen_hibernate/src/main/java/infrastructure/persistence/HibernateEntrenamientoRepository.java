package infrastructure.persistence;

import domain.model.Entrenamiento;
import domain.repository.EntrenamientoRepository;
import org.hibernate.Session;

public class HibernateEntrenamientoRepository extends HibernateBaseRepository<Entrenamiento, String>
        implements EntrenamientoRepository {
    protected HibernateEntrenamientoRepository(Session session) {
        super(session);
    }
}