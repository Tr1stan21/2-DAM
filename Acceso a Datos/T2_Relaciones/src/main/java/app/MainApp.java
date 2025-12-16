package app;

import org.hibernate.Session;
import util.HibernateUtil;

public class MainApp {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.getTransaction().commit();
        HibernateUtil.closeSession();

    }
}
