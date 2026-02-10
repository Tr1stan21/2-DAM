package bootstrap;

import domain.model.Alumno;
import domain.model.Clase;
import infrastructure.persistence.HibernateAlumnoRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class MainApp {

    public static void main(String[] args) {

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            HibernateAlumnoRepository alumnoRepo = new HibernateAlumnoRepository(session);

            System.out.println("========= CRUD Alumno =========");

            // CREATE
            Alumno a = new Alumno("NIF99999999X", "Juan", "Prueba");
            alumnoRepo.save(a);
            System.out.println("CREATE -> guardado: " + a.getNif() + " " + a.getNombre() + " " + a.getApellidos());

            // READ (findById)
            Optional<Alumno> encontrado = alumnoRepo.findById("NIF99999999X");
            System.out.println("READ (findById) -> existe? " + encontrado.isPresent());

            // READ (findAll)
            List<Alumno> todos = alumnoRepo.findAll();
            System.out.println("READ (findAll) -> total alumnos: " + todos.size());

            // UPDATE
            encontrado.ifPresent(al -> {
                al.setApellidos("PruebaMod");
                Alumno updated = alumnoRepo.update(al);
                System.out.println("UPDATE -> apellidos nuevos: " + updated.getApellidos());
            });

            // DELETE (deleteById)
            boolean deleted = alumnoRepo.deleteById("NIF99999999X");
            System.out.println("DELETE (deleteById) -> borrado? " + deleted);

            // ============================================================
            // 2) Consultas HQL "puro" (métodos en el mismo main)
            // ============================================================
            System.out.println("\n========= HQL =========");

            getClasesAlumnos(session);
            getClasesSinAlumnos(session);

            getClasesConMasDeXAlumnos(session, 2);

        }
    }

    // ------------------------------------------------------------------
    // HQL #1
    // getClasesAlumnos:
    // Devuelve un listado de todas las clases y los alumnos inscritos
    // en cada una, ordenado por el título de la clase y por los apellidos del alumno.
    // ------------------------------------------------------------------
    private static void getClasesAlumnos(Session session) {

        String hql =
                "select c, a " +
                        "from Clase c " +
                        "join c.alumnos ca " +
                        "join ca.alumno a " +
                        "order by c.titulo asc, a.apellidos asc";

        List<Object[]> rows = session.createQuery(hql, Object[].class).getResultList();

        System.out.println("\n-- getClasesAlumnos (solo con alumnos) --");

        for (Object[] row : rows) {
            Clase c = (Clase) row[0];
            Alumno a = (Alumno) row[1];

            System.out.println(c + " | " + a);
        }
    }

    // ------------------------------------------------------------------
    // HQL #2
    // getClasesSinAlumnos:
    // Devuelve todas las clases que no tienen ningún alumno inscrito.
    // ------------------------------------------------------------------
    private static void getClasesSinAlumnos(Session session) {

        String hql =
                "from Clase c " +
                        "where c.alumnos is empty " +
                        "order by c.titulo asc";

        List<Clase> result = session.createQuery(hql, Clase.class).getResultList();

        System.out.println("\n-- getClasesSinAlumnos --");

        result.forEach(System.out::println);
    }

    // ------------------------------------------------------------------
    // HQL #3
    // getClasesConMasDeXAlumnos:
    // Devuelve clases donde hay más de X alumnos inscritos.
    // X parametrizable.
    // ------------------------------------------------------------------
    private static void getClasesConMasDeXAlumnos(Session session, int x) {

        String hql =
                "select c " +
                        "from Clase c " +
                        "join c.alumnos ca " +
                        "group by c " +
                        "having count(ca) > :x " +
                        "order by c.titulo asc";

        List<Clase> result = session.createQuery(hql, Clase.class)
                .setParameter("x", (long) x) // count() -> long
                .getResultList();

        System.out.println("\n-- getClasesConMasDeXAlumnos (x=" + x + ") --");
        if (result.isEmpty()) {
            System.out.println("(ninguna)");
            return;
        }

        result.forEach(System.out::println);
    }


    // ------------------------------------------------------------------
    // HQL #1
    // getClasesByTituloLike:
    // Devuelve clases cuyo título contiene el texto indicado (LIKE).
    // ------------------------------------------------------------------
    public static void getClasesByTituloLike(Session session, String texto) {

        String hql =
                "from Clase c " +
                        "where c.titulo like :txt " +
                        "order by c.titulo asc";

        List<Clase> result = session.createQuery(hql, Clase.class)
                .setParameter("txt", "%" + texto + "%")
                .getResultList();

        System.out.println("\n-- getClasesByTituloLike (\"" + texto + "\") --");
        result.forEach(System.out::println);
    }

    // ------------------------------------------------------------------
    // HQL #2
    // getClaseYAlumno:
    // Devuelve pares (Clase, Alumno) de las inscripciones existentes,
    // ordenado por título de la clase y apellidos del alumno.
    // ------------------------------------------------------------------
    public static void getClaseYAlumno(Session session) {

        String hql =
                "select c, a " +
                        "from Clase c " +
                        "join c.alumnos ca " +
                        "join ca.alumno a " +
                        "order by c.titulo asc, a.apellidos asc";

        List<Object[]> rows = session.createQuery(hql, Object[].class).getResultList();

        System.out.println("\n-- getClaseYAlumno --");
        for (Object[] row : rows) {
            System.out.println(row[0] + " | " + row[1]); // toString() en ambos
        }
    }

    // ------------------------------------------------------------------
    // HQL #4
    // getClasesConMasDeXInscritos:
    // Devuelve clases en las que hay más de X alumnos inscritos.
    // ------------------------------------------------------------------
    public static void getClasesConMasDeXInscritos(Session session, int x) {

        String hql =
                "select c " +
                        "from Clase c " +
                        "join c.alumnos ca " +
                        "group by c " +
                        "having count(ca) > :x " +
                        "order by c.titulo asc";

        List<Clase> result = session.createQuery(hql, Clase.class)
                .setParameter("x", (long) x) // count() -> Long
                .getResultList();

        System.out.println("\n-- getClasesConMasDeXInscritos (x=" + x + ") --");
        result.forEach(System.out::println);
    }

    // ------------------------------------------------------------------
    // HQL #5
    // getClaseMasLargaTituloYDuracion:
    // Devuelve el título y duración (minutos) de la clase con mayor duración.
    // (Si hay empate, imprime una de ellas por setMaxResults(1)).
    // ------------------------------------------------------------------
    public static void getClaseMasLargaTituloYDuracion(Session session) {

        String hql =
                "select c.titulo, c.duracionMinutos " +
                        "from Clase c " +
                        "order by c.duracionMinutos desc, c.titulo asc";

        Object[] row = session.createQuery(hql, Object[].class)
                .setMaxResults(1)
                .getSingleResult();

        System.out.println("\n-- getClaseMasLargaTituloYDuracion --");
        System.out.println("Titulo=" + row[0] + " | DuracionMinutos=" + row[1]);
    }
}
