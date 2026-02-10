package infrastructure.persistence;

import domain.model.Instalacion;
import domain.repository.InstalacionRepository;
import org.hibernate.Session;

public class HibernateInstalacionRepository extends HibernateBaseRepository<Instalacion, Long>
        implements InstalacionRepository {
    protected HibernateInstalacionRepository(Session session) {
        super(session);
    }
}