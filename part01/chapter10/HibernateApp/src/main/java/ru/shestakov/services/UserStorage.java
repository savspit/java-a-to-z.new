package ru.shestakov.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.shestakov.models.User;

import java.util.List;

public class UserStorage {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        User user = new User();

        //user.setLogin("test");

        /*user.setId(18);
        user.setLogin("Shestakov");
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        session.saveOrUpdate(user);*/

        /*user.setId(18);
        session.delete(user);
        session.delete("delete from users id=?", 18);*/

        user.setId(18);
        List<User> users = session.createQuery("from User").list();
        for (User curUser : users) {
            System.out.println(curUser.getLogin());
        }

        session.save(user);
        //System.out.println(session.createQuery("from User").list());
        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}
