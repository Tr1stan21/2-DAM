package infrastructure.persistence;

import domain.model.Deportista;
import domain.repository.DeportistaRepository;
import org.hibernate.Session;

public class HibernateDeportistaRepository extends HibernateBaseRepository<Deportista, String>
        implements DeportistaRepository {
    public HibernateDeportistaRepository(Session session) {
        super(session);
    }
}