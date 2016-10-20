package shestakov.service;

import shestakov.models.Entity;
import shestakov.models.Role;

import java.util.List;

/**
 * The interface Role service.
 */
public interface IRoleService {
    /**
     * Gets users.
     *
     * @param role the role
     * @return the users
     */
    List<Entity> getUsers(Role role);
}
