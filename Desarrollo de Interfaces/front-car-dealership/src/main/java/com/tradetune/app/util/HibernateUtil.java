package com.tradetune.app.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase utilitaria para gestionar la sesión de Hibernate
 * Lee la configuración desde hibernate.cfg.xml
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    /**
     * Construye el SessionFactory a partir de hibernate.cfg.xml
     */
    private static SessionFactory buildSessionFactory() {
        try {
            // Lee hibernate.cfg.xml automáticamente
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Error al crear SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Obtiene el SessionFactory (Singleton)
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }

    /**
     * Abre y devuelve una nueva sesión
     */
    public static Session openSession() {
        return getSessionFactory().openSession();
    }

    /**
     * Cierra el SessionFactory
     */
    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}