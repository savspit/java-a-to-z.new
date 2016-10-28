package ru.shestakov.services;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.shestakov.models.*;
import ru.shestakov.utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class AdvertsStorage {

    public List<Advert> getAdverts() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Advert> adverts = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            adverts = session.createQuery("from Advert").list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return adverts;
        }
    }

    public void setNewAdvert(String advertDesc) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Advert advert = new Advert();
            advert.setDescription(advertDesc);
            session.saveOrUpdate(advert);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<User> getUserByLogin(String login) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        List<User> users = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User where login =:login");
            query.setParameter("login", login);
            users = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return users;
        }
    }

    public List<Transmission> getTransmissions() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Transmission> transmissions = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            transmissions = session.createQuery("from Transmission").list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return transmissions;
        }
    }

    public List<Engine> getEngines() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Engine> engines = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            engines = session.createQuery("from Engine").list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return engines;
        }
    }

    public List<Gearbox> getGearboxes() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Gearbox> gearboxes = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            gearboxes = session.createQuery("from Gearbox").list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return gearboxes;
        }
    }
}
