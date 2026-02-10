package infrastructure.persistence;

import domain.model.Aula;
import domain.repository.AulaRepository;
import org.hibernate.Session;

public class HibernateAulaRepository extends HibernateBaseRepository<Aula, Long>
        implements AulaRepository {
    public HibernateAulaRepository(Session session) {
        super(session);
    }
}
