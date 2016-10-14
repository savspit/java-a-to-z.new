package shestakov.dao;

import shestakov.models.Role;

/**
 * The interface Role.
 */
public interface IRole extends IEntity<Role> {
    /**
     * Gets by name.
     *
     * @param name the name
     * @return the by name
     */
    Role getByName(String name);

    /**
     * Gets by user login.
     *
     * @param login the login
     * @return the by user login
     */
    Role getByUserLogin(String login);
}
