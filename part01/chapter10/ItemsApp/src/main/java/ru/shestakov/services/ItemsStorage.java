package ru.shestakov.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.shestakov.models.Item;
import ru.shestakov.utils.HibernateUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Items storage.
 */
public class ItemsStorage {

    /**
     * Gets items by filter.
     *
     * @param showAll the show all
     * @return the items by filter
     */
    public List<Item> getItemsByFilter(String showAll) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Item> items = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            if ("true".equals(showAll)) {
                items = session.createQuery("from Item").list();
            } else {
                items = session.createQuery("from Item i where i.done = false").list();
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return items;
        }
    }

    /**
     * Sets new item.
     *
     * @param itemDesc the item desc
     */
    public void setNewItem(String itemDesc) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Item item = new Item();
            item.setDescription(itemDesc);
            item.setCreated_date(new Timestamp(System.currentTimeMillis()));
            session.saveOrUpdate(item);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
