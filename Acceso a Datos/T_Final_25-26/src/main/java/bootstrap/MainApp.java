package bootstrap;

import domain.model.Deportista;
import domain.model.Entrenamiento;
import persistence.HibernateDeportistaRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            HibernateDeportistaRepository deportistaRepo = new HibernateDeportistaRepository(session);

            System.out.println("CRUD Deportista\n");

            Deportista deportista = new Deportista("12345678P", "Usain", "Bolt");
            System.out.println("Deportista guardado: " + deportistaRepo.save(deportista));

        }
    }

    private static List<Object[]> getEntrenamientosDeportistas(Session session){
        String hql = "select e, d from Entrenamiento e join e.deportistas ds join ds.deportista d order by e.titulo asc, d.apellido asc";

         return session.createQuery(hql, Object[].class).getResultList();
    }

    private static List<Entrenamiento> getEntrenamientosSinDeportistas (Session session) {
        String hql = "from Entrenamiento e left join e.deportistas d where d is empty";

        return session.createQuery(hql, Entrenamiento.class).getResultList();
    }

    private static List<Entrenamiento> getEntrenamientosConMasDeXDeportistas (Session session, int numDeportistas) {
        String hql = "from Entrenamiento e join e.deportistas d group by e having count (d) > :numDeportistas";

    }

}
