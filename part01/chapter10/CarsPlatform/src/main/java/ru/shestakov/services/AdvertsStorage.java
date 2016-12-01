package ru.shestakov.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shestakov.models.Advert;
import ru.shestakov.models.User;
import ru.shestakov.models.Transmission;
import ru.shestakov.models.Engine;
import ru.shestakov.models.Gearbox;
import ru.shestakov.models.Image;
import ru.shestakov.models.Car;
import ru.shestakov.models.Filter;
import ru.shestakov.utils.HibernateUtils;
import ru.shestakov.utils.LiquibaseUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Adverts storage.
 */
public class AdvertsStorage {
    /**
     * logger field.
     */
    private static final Logger LOG = LoggerFactory.getLogger(LiquibaseUtils.class);

    /**
     * Gets adverts.
     *
     * @param login the login
     * @return the adverts
     */
    public List<Advert> getAdverts(String login) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Advert> adverts = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            String sql = prepareAdvertsQuery(login);
            Query query = session.createQuery(sql);
            query.setParameter("login", login);
            adverts = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
            return adverts;
        }
    }

    /**
     * Prepare adverts query string.
     *
     * @param login the login
     * @return the string
     */
    public String prepareAdvertsQuery(String login) {
        String sql = "select a.id as id, a.description as description, c.name as car, a.sold as sold, a.user.name as user, (u.login = :login) as isMyAdvert from Advert as a left join a.car as c left join a.user as u";
        List<Filter> filters = getFiltersByLogin(login);
        int filtersSize = filters.size();
        if (filtersSize == 0) {
            return sql;
        }
        sql = String.format("%s %s ", sql, "where");
        for (int i = 0; i < filtersSize; i++) {
            Filter filter = filters.get(i);
            sql = String.format("%s %s %s %s %s ", sql, getFieldName(filter.getField()), filter.getOperation(), filter.getValue(), i + 1 == filtersSize || filtersSize == 1 ? "" : "or");
        }
        return sql;
    }

    /**
     * Gets field name.
     *
     * @param fieldName the field name
     * @return the field name
     */
    public String getFieldName(String fieldName) {
        String result = "";
        if ("sold".equals(fieldName)) {
            result = String.format("a.%s", fieldName);
        } else if ("transmission".equals(fieldName) || "engine".equals(fieldName) || "gearbox".equals(fieldName)) {
            result = String.format("c.%s", fieldName);
        }
        return result;
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
            LOG.error(e.getMessage(), e);
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
    public User getUserByLogin(String login) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        User user = new User();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User where login =:login");
            query.setParameter("login", login);
            query.setFirstResult(0);
            query.setMaxResults(1);
            user = (User) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
            return user;
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
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    /**
     * Update engine.
     *
     * @param engine the engine
     */
    public void updateEngine(Engine engine) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(engine);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    /**
     * Update gearbox.
     *
     * @param gearbox the gearbox
     */
    public void updateGearbox(Gearbox gearbox) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(gearbox);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    /**
     * Update transmission.
     *
     * @param transmission the transmission
     */
    public void updateTransmission(Transmission transmission) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(transmission);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    /**
     * Update user.
     *
     * @param user the user
     */
    public void updateUser(User user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
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
     * Save filter.
     *
     * @param login  the login
     * @param filter the filter
     */
    public void saveFilter(String login, Filter filter) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(filter);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    /**
     * Remove filter.
     *
     * @param filter the filter
     */
    public void removeFilter(Filter filter) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(filter);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    /**
     * Gets filters.
     *
     * @param filter the filter
     * @return the filters
     */
    public List<Filter> getFilters(Filter filter) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Filter> filters = new ArrayList();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Filter as f where f.field = :field and f.operation = :operation and f.value = :value and f.user.id = :userId");
            query.setParameter("field", filter.getField());
            query.setParameter("operation", filter.getOperation());
            query.setParameter("value", filter.getValue());
            query.setParameter("userId", filter.getUser().getId());
            filters = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
            return filters;
        }
    }

    /**
     * Gets filters by login.
     *
     * @param login the login
     * @return the filters by login
     */
    public List<Filter> getFiltersByLogin(String login) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Filter> filters = new ArrayList();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Filter as f where f.user.login = :login");
            query.setParameter("login", login);
            filters = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
            return filters;
        }
    }
}
