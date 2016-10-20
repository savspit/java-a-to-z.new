package shestakov.dao;

import shestakov.models.Entity;
import shestakov.models.User;

import java.util.List;

/**
 * The interface User.
 */
public interface IUser extends IEntity<User> {
    /**
     * Gets by login.
     *
     * @param login the login
     * @return the by login
     */
    List<Entity> getByLogin(String login);

    /**
     * Is root boolean.
     *
     * @param login the login
     * @return the boolean
     */
    boolean isRoot(String login);
}
