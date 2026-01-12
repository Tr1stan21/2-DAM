package app;

import model.Empleado;
import model.Restaurante;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        // Select simple
        List<Empleado> empleados = session.createQuery("from Empleado", Empleado.class)
                .getResultList();
        empleados.forEach(System.out::println);

        //select con join por where
        Query<Restaurante> query2 = session.createQuery("from Restaurante where localidad.codLocalidad = 1", Restaurante.class);
        List<Restaurante> restaurantes = query2.getResultList();

        for (Restaurante restaurante : restaurantes) {
            System.out.println(restaurante.getNombre());
        }

        session.getTransaction().commit();
        HibernateUtil.closeSession();
    }
}
