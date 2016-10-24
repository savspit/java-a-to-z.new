package shestakov.dao.impl;

import shestakov.dao.IRole;
import shestakov.repository.IRoleRepository;
import shestakov.templates.RoleTemplate;
import shestakov.templates.Template;
import shestakov.models.Entity;
import shestakov.models.Role;
import shestakov.db.DataSource;
import shestakov.templates.UserTemplate;

import java.util.List;

/**
 * The type Role.
 */
public class RoleImpl implements IRole, IRoleRepository {
    private static final String SQL_CREATE = "INSERT INTO roles(name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE roles SET name=?, WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM roles WHERE id=?";
    private static final String SQL_GET_BY_ID = "SELECT r.id, r.name FROM roles AS r WHERE r.id = ?";
    private static final String SQL_GET_BY_NAME = "SELECT r.id, r.name FROM roles AS r WHERE r.name = ?";
    private static final String SQL_GET_BY_USER_LOGIN = "SELECT r.id, r.name FROM roles AS r JOIN users AS u ON u.roleId = r.id AND u.login = ?";
    private static final String SQL_GET_ALL = "SELECT r.id, r.name FROM roles AS r";
    private static final String SQL_GET_USERS = "SELECT u.id, u.login, u.name FROM users AS u JOIN roles AS r ON u.roleId = r.id AND r.name = ?";
    private static final DataSource instance = DataSource.getInstance();

    @Override
    public void create(Role role) {
        Template roleTemplate = new RoleTemplate();
        roleTemplate.execute(instance, SQL_CREATE, role.getName().trim());
    }

    @Override
    public List<Entity> getById(int id) {
        Template roleTemplate = new RoleTemplate();
        return roleTemplate.executeAndReturn(instance, SQL_GET_BY_ID, id);
    }

    @Override
    public List<Entity> getByName(String name) {
        Template roleTemplate = new RoleTemplate();
        return roleTemplate.executeAndReturn(instance, SQL_GET_BY_NAME, name.trim());
    }

    @Override
    public void update(Role role) {
        Template roleTemplate = new RoleTemplate();
        roleTemplate.execute(instance, SQL_UPDATE, role.getName(), role.getId());
    }

    @Override
    public void delete(Role role) {
        Template roleTemplate = new RoleTemplate();
        roleTemplate.execute(instance, SQL_DELETE, role.getId());
    }

    @Override
    public List<Entity> getAll() {
        Template roleTemplate = new RoleTemplate();
        return roleTemplate.executeAndReturn(instance, SQL_GET_ALL);
    }

    @Override
    public List<Entity> getByUserLogin(String usersLogin) {
        Template roleTemplate = new RoleTemplate();
        return roleTemplate.executeAndReturn(instance, SQL_GET_BY_USER_LOGIN, usersLogin.trim());
    }

    @Override
    public List<Entity> getUsers(Role role) {
        Template userTemplate = new UserTemplate();
        return userTemplate.executeAndReturn(instance, SQL_GET_USERS, role.getName().trim());
    }
}
