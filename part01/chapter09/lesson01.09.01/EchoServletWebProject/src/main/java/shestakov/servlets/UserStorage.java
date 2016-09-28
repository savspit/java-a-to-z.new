package shestakov.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserStorage {
    private static final Logger Log = LoggerFactory.getLogger(UserStorage.class);

    private static final UserStorage instance = new UserStorage();
    private List<User> users = new CopyOnWriteArrayList<User>();

    private UserStorage() {

    }

    public static UserStorage getInstance() {
        return instance;
    }

    public void add(User user) {
        this.users.add(user);
    }

    public List<User> getUsers() {
        return this.users;
    }
}
