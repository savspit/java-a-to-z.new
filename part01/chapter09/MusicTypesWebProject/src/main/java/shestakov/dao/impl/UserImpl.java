package shestakov.dao.impl;

import shestakov.dao.IUser;
import shestakov.templates.Template;
import shestakov.templates.UserTemplate;
import shestakov.models.Entity;
import shestakov.models.User;
import shestakov.db.DataSource;

import java.util.List;

/**
 * The type User.
 */
public class UserImpl implements IUser {
    private static final String SQL_CREATE = "INSERT INTO users(login, name, roleId) VALUES (?, ?, (SELECT r.id FROM roles AS r WHERE r.name = ?))";
    private static final String SQL_UPDATE = "UPDATE users SET name=?, WHERE login=?";
    private static final String SQL_DELETE = "DELETE FROM users WHERE login=?";
    private static final String SQL_GET_BY_ID = "SELECT u.id, u.name, u.login, coalesce(r.name,'') as role FROM users AS u LEFT JOIN roles AS r ON u.roleId = r.id WHERE u.id = ?";
    private static final String SQL_GET_BY_LOGIN = "SELECT u.id, u.name, u.login, coalesce(r.name,'') as role FROM users AS u LEFT JOIN roles AS r ON u.roleId = r.id WHERE u.login = ?";
    private static final String SQL_GET_ALL = "SELECT u.id, u.name, u.login, r.name as role FROM users AS u LEFT JOIN roles AS r ON u.roleId = r.id";
    private static final DataSource instance = DataSource.getInstance();

    @Override
    public void create(User user) {
        Template userTemplate = new UserTemplate();
        userTemplate.execute(instance, SQL_CREATE, user.getName(), user.getLogin().trim(), user.getRole().getName().trim());
    }

    @Override
    public List<Entity> getById(int id) {
        Template userTemplate = new UserTemplate();
        return userTemplate.executeAndReturn(instance, SQL_GET_BY_ID, id);

    }

    @Override
    public void update(User user) {
        Template userTemplate = new UserTemplate();
        userTemplate.execute(instance, SQL_UPDATE, user.getName(), user.getLogin().trim());
    }

    @Override
    public void delete(User user) {
        Template userTemplate = new UserTemplate();
        userTemplate.execute(instance, SQL_DELETE, user.getLogin().trim());
    }

    @Override
    public List<Entity> getAll() {
        Template userTemplate = new UserTemplate();
        return userTemplate.executeAndReturn(instance, SQL_GET_ALL);
    }

    @Override
    public List<Entity> getByLogin(String login) {
        Template userTemplate = new UserTemplate();
        return userTemplate.executeAndReturn(instance, SQL_GET_BY_LOGIN, login.trim());
    }

    @Override
    public boolean isRoot(String login) {
        RoleImpl dbRole = new RoleImpl();
        List<Entity> users = dbRole.getByUserLogin(login);
        boolean result = false;
        if (users.size() != 0) {
            User user = (User) users.get(0);
            result = "root".equals(user.getName());
        }
        return result;
    }
}
