package persistence;

import domain.model.Deportista;
import domain.model.Instalacion;
import domain.repository.DeportistaRepository;
import domain.repository.InstalacionRepository;
import org.hibernate.Session;

public class HibernateInstalacionRepository extends HibernateBaseRepository<Instalacion, Long>
        implements InstalacionRepository {
    public HibernateInstalacionRepository(Session session) {
        super(session);
    }
}

