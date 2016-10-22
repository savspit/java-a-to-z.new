package shestakov.repository;

import shestakov.models.Entity;
import shestakov.models.Role;

import java.util.List;

/**
 * The interface Role repository.
 */
public interface IRoleRepository extends IRepository{
    List<Entity> getUsers(Role role);
}
