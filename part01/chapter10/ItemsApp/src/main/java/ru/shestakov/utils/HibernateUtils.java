package ru.shestakov.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * The type Hibernate utils.
 */
public class HibernateUtils {
    private static SessionFactory sessionFactory;

    /**
     * Gets session factory.
     *
     * @return the session factory
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}