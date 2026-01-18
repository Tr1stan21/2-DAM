package infrastructure.persistence.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for managing Hibernate SessionFactory lifecycle.
 * Implements singleton pattern to ensure only one SessionFactory instance
 * exists.
 */
public class HibernateUtil {

    private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
    private static SessionFactory sessionFactory;

    private HibernateUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Builds and returns the singleton SessionFactory instance.
     * Thread-safe lazy initialization with double-checked locking.
     *
     * @return SessionFactory instance
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            synchronized (HibernateUtil.class) {
                if (sessionFactory == null) {
                    try {
                        logger.info("Initializing Hibernate SessionFactory...");
                        sessionFactory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .buildSessionFactory();
                        logger.info("SessionFactory initialized successfully");
                    } catch (Exception e) {
                        logger.error("Error initializing SessionFactory", e);
                        throw new ExceptionInInitializerError(e);
                    }
                }
            }
        }
        return sessionFactory;
    }

    /**
     * Closes the SessionFactory and releases all resources.
     * Should be called when application shuts down.
     */
    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            logger.info("Shutting down SessionFactory...");
            sessionFactory.close();
            logger.info("SessionFactory closed successfully");
        }
    }
}