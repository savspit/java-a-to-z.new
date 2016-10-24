package ru.shestakov.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.shestakov.models.Item;

import java.sql.Timestamp;
import java.util.List;

public class ItemsStorage {

    public List<Item> getItemsByFilter(String showAll) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        List<Item> items;
        if ("true".equals(showAll)) {
            items = session.createQuery("from Item").list();
        } else {
            items = session.createQuery("from Item i where i.done = false").list();
        }
        session.getTransaction().commit();
        return items;
    }

    public void setNewItem(String itemDesc) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        Item item = new Item();
        item.setDescription(itemDesc);
        item.setCreated_date(new Timestamp(System.currentTimeMillis()));
        session.saveOrUpdate(item);
        session.getTransaction().commit();
    }
}
