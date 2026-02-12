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

            System.out.println("--- CRUD DEPORTISTA ---\n");

            Deportista deportista = new Deportista("12345678P", "Usain", "Bolt");
            System.out.println("Deportista guardado: " + deportistaRepo.save(deportista));

            System.out.println("Deportista por id: "+ deportistaRepo.findById("12345678P"));

            System.out.println("Todos los deportistas: ");
            for(Deportista d : deportistaRepo.findAll()) {
                System.out.println(d);
            }

            deportista.setNombre("Jose");
            deportista.setApellido("Pérez");
            System.out.println("Deportista actualizado: "+ deportistaRepo.update(deportista));

            System.out.println("Deportista eliminado correctamente: "+ (deportistaRepo.deleteById("12345678P")? "true" : "false"));


            System.out.println("\n\n--- CONSULTAS ---");

            System.out.println("\nEntrenamientos con deportistas: ");
            for(Object[] obj : getEntrenamientosDeportistas(session)) {
                for (Object o : obj) {
                    System.out.println(o);
                }
            }

            System.out.println("\nEntrenamientos sin deportistas: ");
            for(Entrenamiento e : getEntrenamientosSinDeportistas(session)) {
                System.out.println(e);
            }

            System.out.println("\nEntrenamientos con más de X deportistas: ");
            for(Entrenamiento e : getEntrenamientosConMasDeXDeportistas(session, 1)) {
                System.out.println(e);
            }

        }
    }

    private static List<Object[]> getEntrenamientosDeportistas(Session session){
        String hql = "select e, d from Entrenamiento e join e.deportistas ds join ds.deportista d order by e.titulo asc, d.apellido asc";

         return session.createQuery(hql, Object[].class).getResultList();
    }

    private static List<Entrenamiento> getEntrenamientosSinDeportistas (Session session) {
        String hql = "from Entrenamiento e where e.deportistas is empty";

        return session.createQuery(hql, Entrenamiento.class).getResultList();
    }

    private static List<Entrenamiento> getEntrenamientosConMasDeXDeportistas (Session session, int numDeportistas) {
        String hql = "from Entrenamiento e join e.deportistas d group by e having count(d) > :numDeportistas";

        return session.createQuery(hql, Entrenamiento.class).setParameter("numDeportistas", numDeportistas).getResultList();

    }

//    private static List<Object[]> getEntrenamientoMasPopularConDetalles (Session session) {
//        String hql = "from Entrenamiento e join e.deportistas d group by e where count(d) > ";
//
//        return session.createQuery(hql, Object[].class).getResultList();
//    }


}
