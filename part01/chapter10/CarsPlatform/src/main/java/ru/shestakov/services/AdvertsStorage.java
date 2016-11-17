package ru.shestakov.services;

import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shestakov.models.*;
import ru.shestakov.models.Filter;
import ru.shestakov.utils.HibernateUtils;
import ru.shestakov.utils.LiquibaseUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Adverts storage.
 */
public class AdvertsStorage {
    private static final Logger Log = LoggerFactory.getLogger(LiquibaseUtils.class);

    /**
     * Gets adverts.
     *
     * @param login  the login
     * @param filter the filter
     * @return the adverts
     */
    public List<Advert> getAdverts(String login, Filter filter) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Advert> adverts = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            String sql = getPreparedQuery(filter);
            Query query = session.createQuery(sql);
            query.setParameter("login", login);
            adverts = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
            return adverts;
        }
    }

    /**
     * Sets new advert.
     *
     * @param advertDesc the advert desc
     */
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
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    /**
     * Gets user by login.
     *
     * @param login the login
     * @return the user by login
     */
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
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
            return users;
        }
    }

    /**
     * Gets transmissions.
     *
     * @return the transmissions
     */
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
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
            return transmissions;
        }
    }

    /**
     * Gets engines.
     *
     * @return the engines
     */
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
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
            return engines;
        }
    }

    /**
     * Gets gearboxes.
     *
     * @return the gearboxes
     */
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
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
            return gearboxes;
        }
    }

    /**
     * Gets images by advert id.
     *
     * @param advertId the advert id
     * @return the images by advert id
     */
    public List<String> getImagesByAdvertId(int advertId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        List<String> images = new ArrayList();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select i.path from Image as i where i.advert.id = :advertId");
            query.setParameter("advertId", advertId);
            images = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
            return images;
        }
    }

    /**
     * Update image.
     *
     * @param image the image
     */
    public void updateImage(Image image) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(image);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    /**
     * Update advert.
     *
     * @param advert the advert
     */
    public void updateAdvert(Advert advert) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(advert);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    /**
     * Update car.
     *
     * @param car the car
     */
    public void updateCar(Car car) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(car);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    /**
     * Gets transmission by advert id.
     *
     * @param advertId the advert id
     * @return the transmission by advert id
     */
    public Transmission getTransmissionByAdvertId(int advertId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        Transmission transmission = new Transmission();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select t as sel from Advert as a inner join a.car.transmission as t where a.id = :advertId");
            query.setParameter("advertId", advertId);
            transmission = (Transmission) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
            return transmission;
        }
    }

    /**
     * Gets transmission by name.
     *
     * @param name the name
     * @return the transmission by name
     */
    public Transmission getTransmissionByName(String name) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        Transmission transmission = new Transmission();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Transmission as t where t.name = :name");
            query.setParameter("name", name);
            transmission = (Transmission) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
            return transmission;
        }
    }

    /**
     * Gets description by advert id.
     *
     * @param advertId the advert id
     * @return the description by advert id
     */
    public String getDescriptionByAdvertId(int advertId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        String description = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select a.description from Advert as a where a.id = :advertId");
            query.setParameter("advertId", advertId);
            description = (String) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
            return description;
        }
    }

    /**
     * Gets engine by advert id.
     *
     * @param advertId the advert id
     * @return the engine by advert id
     */
    public Engine getEngineByAdvertId(int advertId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        Engine engine = new Engine();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select e as sel from Advert as a inner join a.car.engine as e where a.id = :advertId");
            query.setParameter("advertId", advertId);
            engine = (Engine) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
            return engine;
        }
    }

    /**
     * Gets engine by name.
     *
     * @param name the name
     * @return the engine by name
     */
    public Engine getEngineByName(String name) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        Engine engine = new Engine();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Engine as e where e.name = :name");
            query.setParameter("name", name);
            engine = (Engine) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
            return engine;
        }
    }

    /**
     * Gets gearbox by advert id.
     *
     * @param advertId the advert id
     * @return the gearbox by advert id
     */
    public Gearbox getGearboxByAdvertId(int advertId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        Gearbox gearbox = new Gearbox();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select g as sel from Advert as a inner join a.car.gearbox as g where a.id = :advertId");
            query.setParameter("advertId", advertId);
            gearbox = (Gearbox) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
            return gearbox;
        }
    }

    /**
     * Gets gearbox by name.
     *
     * @param name the name
     * @return the gearbox by name
     */
    public Gearbox getGearboxByName(String name) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        Gearbox gearbox = new Gearbox();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Gearbox as g where g.name = :name");
            query.setParameter("name", name);
            gearbox = (Gearbox) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
            return gearbox;
        }
    }

    /**
     * Gets car by advert id.
     *
     * @param advertId the advert id
     * @return the car by advert id
     */
    public Car getCarByAdvertId(int advertId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        Car car = new Car();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select c as sel from Advert as a inner join a.car as c where a.id = :advertId");
            query.setParameter("advertId", advertId);
            car = (Car) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
            return car;
        }
    }

    /**
     * Gets advert by id.
     *
     * @param advertId the advert id
     * @return the advert by id
     */
    public Advert getAdvertById(int advertId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        Advert advert = new Advert();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Advert as a where a.id = :advertId");
            query.setParameter("advertId", advertId);
            advert = (Advert) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
            return advert;
        }
    }

    /**
     * Gets advert by description.
     *
     * @param description the description
     * @return the advert by description
     */
    public Advert getAdvertByDescription(String description) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        Advert advert = new Advert();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Advert as a where a.description = :description");
            query.setParameter("description", description);
            advert = (Advert) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
            return advert;
        }
    }

    /**
     * Gets car by name.
     *
     * @param name the name
     * @return the car by name
     */
    public Car getCarByName(String name) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        Car car = new Car();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Car as c where c.name = :name");
            query.setParameter("name", name);
            car = (Car) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            Log.error(e.getMessage(), e);
        } finally {
            session.close();
            return car;
        }
    }

    /**
     * Is admin boolean.
     *
     * @param login the login
     * @return the boolean
     */
    public boolean isAdmin(String login) {
        return "admin".equals(login);
    }

    /**
     * Str join string.
     *
     * @param aArr the a arr
     * @param sSep the s sep
     * @return the string
     */
    public static String strJoin(String[] aArr, String sSep) {
        StringBuilder sbStr = new StringBuilder();
        for (int i = 0, il = aArr.length; i < il; i++) {
            if (i > 0) { sbStr.append(sSep); }
            if (("true".equals(aArr[i])) || ("false".equals(aArr[i]))) {
                sbStr.append(aArr[i]);
            } else {
                sbStr.append(String.format("'%s'", aArr[i]));
            }
        }
        return sbStr.toString();
    }

    /**
     * Gets prepared query.
     *
     * @param filter the filter
     * @return the prepared query
     */
    public String getPreparedQuery(Filter filter) {
        String sql = "select a.id as id, a.description as description, c.name as car, a.status as status, a.user.name as user, (u.login = :login) as isMyAdvert from Advert as a left join a.car as c left join a.user as u";
        if (filter.getSold() != null || filter.getTransmission() != null || filter.getEngine() != null || filter.getGearbox() != null) {
            sql = String.format("%s where %s %s %s %s",
                    sql,
                    getPreparedQueryOfParameter(filter.getSold(), "sold", false),
                    getPreparedQueryOfParameter(filter.getTransmission(), "transmission", filter.getSold() != null),
                    getPreparedQueryOfParameter(filter.getEngine(), "engine", filter.getSold() != null || filter.getTransmission() != null),
                    getPreparedQueryOfParameter(filter.getGearbox(), "gearbox", filter.getSold() != null || filter.getTransmission() != null || filter.getEngine() != null)
            );
        }
        return sql;
    }

    /**
     * Gets prepared query of parameter.
     *
     * @param parameter the parameter
     * @param name      the name
     * @param join      the join
     * @return the prepared query of parameter
     */
    public String getPreparedQueryOfParameter(String[] parameter, String name, boolean join) {
        if (parameter == null) { return ""; }
        String result = join ? " and " : "";
        if ("sold".equals(name)) {
            result += " a.status IN(" + strJoin(parameter, ",") + ")";
        } else if ("transmission".equals(name)) {
            result += " c.transmission.name IN(" + strJoin(parameter, ",") + ")";
        } else if ("engine".equals(name)) {
            result += " c.engine.name IN(" + strJoin(parameter, ",") + ")";
        } else if ("gearbox".equals(name)) {
            result += " c.gearbox.name IN(" + strJoin(parameter, ",") + ")";
        }
        return result;
    }
}
