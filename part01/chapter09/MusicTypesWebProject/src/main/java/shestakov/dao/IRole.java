package shestakov.dao;

import shestakov.models.Entity;
import shestakov.models.Role;

import java.util.List;

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
    List<Entity> getByName(String name);

    /**
     * Gets by user login.
     *
     * @param login the login
     * @return the by user login
     */
    List<Entity> getByUserLogin(String login);
}
