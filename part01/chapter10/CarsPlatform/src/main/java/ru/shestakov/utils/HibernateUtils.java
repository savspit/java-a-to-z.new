package ru.shestakov.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * The type Hibernate utils.
 */
public class HibernateUtils {
    /**
     * Instance field.
     */
    private static final SessionFactory INSTANCE = new Configuration().configure().buildSessionFactory();

    /**
     * Gets session factory.
     *
     * @return the session factory
     */
    public static SessionFactory getSessionFactory() {
        return INSTANCE;
    }

    /**
     * Close session factory.
     */
    public void closeSessionFactory() {
        INSTANCE.close();
    }
}