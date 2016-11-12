package ru.shestakov.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * The type Hibernate utils.
 */
public class HibernateUtils {
    private static final SessionFactory instance = new Configuration().configure().buildSessionFactory();

    /**
     * Gets session factory.
     *
     * @return the session factory
     */
    public static SessionFactory getSessionFactory() {
        return instance;
    }

    /**
     * Close session factory.
     */
    public void closeSessionFactory() {
        instance.close();
    }
}