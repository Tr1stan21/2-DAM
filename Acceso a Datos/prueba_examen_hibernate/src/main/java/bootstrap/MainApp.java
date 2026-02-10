package bootstrap;

import domain.model.Deportista;
import domain.model.Entrenamiento;
import domain.model.EntrenamientoDeportista;
import infrastructure.persistence.HibernateDeportistaRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class MainApp {

    public static void main(String[] args) {

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            HibernateDeportistaRepository deportistaRepo = new HibernateDeportistaRepository(session);

            System.out.println("========= CRUD Deportista =========");

            // CREATE
            Deportista d = new Deportista("DNI9999999", "Juan", "Prueba");
            deportistaRepo.save(d);
            System.out.println("CREATE -> guardado: " + d.getDni() + " " + d.getNombre() + " " + d.getApellido());

            // READ (findById)
            Optional<Deportista> encontrado = deportistaRepo.findById("DNI9999999");
            System.out.println("READ (findById) -> existe? " + encontrado.isPresent());

            // READ (findAll)
            List<Deportista> todos = deportistaRepo.findAll();
            System.out.println("READ (findAll) -> total deportistas: " + todos.size());

            // UPDATE
            encontrado.ifPresent(dep -> {
                dep.setApellido("PruebaMod");
                Deportista updated = deportistaRepo.update(dep);
                System.out.println("UPDATE -> apellido nuevo: " + updated.getApellido());
            });

            // DELETE (deleteById)
            boolean deleted = deportistaRepo.deleteById("DNI9999999");
            System.out.println("DELETE (deleteById) -> borrado? " + deleted);

            // ============================================================
            // 2) Consultas HQL "puro" (métodos en el mismo main)
            // ============================================================
            System.out.println("\n========= HQL =========");

            getEntrenamientosDeportistas(session);
            getEntrenamientosSinDeportistas(session);

            getEntrenamientosConMasDeXDeportistas(session, 2);

        }
    }

    // ------------------------------------------------------------------
    // HQL #1
    // getEntrenamientosDeportistas:
    // Devuelve un listado de todos los entrenamientos y los deportistas inscritos
    // en cada uno, ordenado por el título del entrenamiento y por el apellido del deportista.
    // ------------------------------------------------------------------
    private static void getEntrenamientosDeportistas(Session session) {

        String hql =
                "select e, d " +
                        "from Entrenamiento e " +
                        "join e.deportistas ed " +
                        "join ed.deportista d " +
                        "order by e.titulo asc, d.apellido asc";

        List<Object[]> rows = session.createQuery(hql, Object[].class).getResultList();

        System.out.println("\n-- getEntrenamientosDeportistas (solo con deportistas) --");

        for (Object[] row : rows) {
            Entrenamiento e = (Entrenamiento) row[0];
            Deportista d = (Deportista) row[1];

            System.out.println(e + " | " + d);
        }
    }

    // ------------------------------------------------------------------
    // HQL #2
    // getEntrenamientosSinDeportistas:
    // Devuelve todos los entrenamientos que no tienen ningún deportista inscrito.
    // ------------------------------------------------------------------
    private static void getEntrenamientosSinDeportistas(Session session) {

        String hql =
                "from Entrenamiento e " +
                        "where e.deportistas is empty " +
                        "order by e.titulo asc";

        List<Entrenamiento> result = session.createQuery(hql, Entrenamiento.class).getResultList();

        System.out.println("\n-- getEntrenamientosSinDeportistas --");

        result.forEach(System.out::println);
    }

    // ------------------------------------------------------------------
    // HQL #3
    // getEntrenamientosConMasDeXDeportistas:
    // Devuelve entrenamientos donde hay más de X deportistas inscritos.
    // X parametrizable.
    // ------------------------------------------------------------------
    private static void getEntrenamientosConMasDeXDeportistas(Session session, int x) {

        String hql =
                "select e " +
                        "from Entrenamiento e " +
                        "join e.deportistas ed " +
                        "group by e " +
                        "having count(ed) > :x " +
                        "order by e.titulo asc";

        List<Entrenamiento> result = session.createQuery(hql, Entrenamiento.class)
                .setParameter("x", (long) x) // count() -> long
                .getResultList();

        System.out.println("\n-- getEntrenamientosConMasDeXDeportistas (x=" + x + ") --");
        if (result.isEmpty()) {
            System.out.println("(ninguno)");
            return;
        }

        result.forEach(System.out::println);
    }


    // ------------------------------------------------------------------
    // HQL #1
    // getEntrenamientosByTituloLike:
    // Devuelve entrenamientos cuyo título contiene el texto indicado (LIKE).
    // ------------------------------------------------------------------
    public static void getEntrenamientosByTituloLike(Session session, String texto) {

        String hql =
                "from Entrenamiento e " +
                        "where e.titulo like :txt " +
                        "order by e.titulo asc";

        List<Entrenamiento> result = session.createQuery(hql, Entrenamiento.class)
                .setParameter("txt", "%" + texto + "%")
                .getResultList();

        System.out.println("\n-- getEntrenamientosByTituloLike (\"" + texto + "\") --");
        result.forEach(System.out::println);
    }

    // ------------------------------------------------------------------
    // HQL #2
    // getEntrenamientoYDeportista:
    // Devuelve pares (Entrenamiento, Deportista) de las inscripciones existentes,
    // ordenado por título del entrenamiento y apellido del deportista.
    // ------------------------------------------------------------------
    public static void getEntrenamientoYDeportista(Session session) {

        String hql =
                "select e, d " +
                        "from Entrenamiento e " +
                        "join e.deportistas ed " +
                        "join ed.deportista d " +
                        "order by e.titulo asc, d.apellido asc";

        List<Object[]> rows = session.createQuery(hql, Object[].class).getResultList();

        System.out.println("\n-- getEntrenamientoYDeportista --");
        for (Object[] row : rows) {
            System.out.println(row[0] + " | " + row[1]); // toString() en ambos
        }
    }

    // ------------------------------------------------------------------
    // HQL #4
    // getEntrenamientosConMasDeXInscritos:
    // Devuelve entrenamientos en los que hay más de X deportistas inscritos.
    // ------------------------------------------------------------------
    public static void getEntrenamientosConMasDeXInscritos(Session session, int x) {

        String hql =
                "select e " +
                        "from Entrenamiento e " +
                        "join e.deportistas ed " +
                        "group by e " +
                        "having count(ed) > :x " +
                        "order by e.titulo asc";

        List<Entrenamiento> result = session.createQuery(hql, Entrenamiento.class)
                .setParameter("x", (long) x) // count() -> Long
                .getResultList();

        System.out.println("\n-- getEntrenamientosConMasDeXInscritos (x=" + x + ") --");
        result.forEach(System.out::println);
    }

    // ------------------------------------------------------------------
    // HQL #5
    // getEntrenamientoMasLargoTituloYDuracion:
    // Devuelve el título y duración (horas) del entrenamiento con mayor duración.
    // (Si hay empate, imprime uno de ellos por setMaxResults(1)).
    // ------------------------------------------------------------------
    public static void getEntrenamientoMasLargoTituloYDuracion(Session session) {

        String hql =
                "select e.titulo, e.duracionHoras " +
                        "from Entrenamiento e " +
                        "order by e.duracionHoras desc, e.titulo asc";

        Object[] row = session.createQuery(hql, Object[].class)
                .setMaxResults(1)
                .getSingleResult();

        System.out.println("\n-- getEntrenamientoMasLargoTituloYDuracion --");
        System.out.println("Titulo=" + row[0] + " | DuracionHoras=" + row[1]);
    }
}
