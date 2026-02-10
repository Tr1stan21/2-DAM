package infrastructure.persistence;

import domain.model.EntrenamientoDeportista;
import domain.model.EntrenamientoDeportistaId;
import domain.repository.EntrenamientoDeportistaRepository;
import org.hibernate.Session;

public class HibernateEntrenamientoDeportistaRepository extends HibernateBaseRepository<EntrenamientoDeportista, EntrenamientoDeportistaId>
        implements EntrenamientoDeportistaRepository {
    protected HibernateEntrenamientoDeportistaRepository(Session session) {
        super(session);
    }
}