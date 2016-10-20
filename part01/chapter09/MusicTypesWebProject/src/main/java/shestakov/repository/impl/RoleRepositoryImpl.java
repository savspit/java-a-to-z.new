package shestakov.repository.impl;

import shestakov.db.DataSource;
import shestakov.templates.Template;
import shestakov.templates.UserTemplate;
import shestakov.models.Entity;
import shestakov.models.Role;
import shestakov.repository.IRoleRepository;

import java.util.List;

/**
 * The type Role repository.
 */
public class RoleRepositoryImpl implements IRoleRepository{
    private static final String SQL_GET_USERS = "SELECT u.id, u.login, u.name FROM users AS u JOIN roles AS r ON u.roleId = r.id AND r.name = ?";
    private static final DataSource instance = DataSource.getInstance();

    /**
     * Gets users.
     *
     * @param role the role
     * @return the users
     */
    public List<Entity> getUsers(Role role) {
        Template userTemplate = new UserTemplate();
        return userTemplate.executeAndReturn(instance, SQL_GET_USERS, role.getName().trim());
    }

}
