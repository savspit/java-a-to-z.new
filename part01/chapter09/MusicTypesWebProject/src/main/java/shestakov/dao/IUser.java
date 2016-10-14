package shestakov.dao;

import shestakov.models.User;

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
    User getByLogin(String login);

    /**
     * Is root boolean.
     *
     * @param login the login
     * @return the boolean
     */
    boolean isRoot(String login);
}
