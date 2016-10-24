package shestakov.dao.impl;

import shestakov.dao.IUser;
import shestakov.models.*;
import shestakov.repository.IUserRepository;
import shestakov.templates.*;
import shestakov.db.DataSource;

import java.util.List;

/**
 * The type User.
 */
public class UserImpl implements IUser, IUserRepository {
    private static final String SQL_CREATE = "INSERT INTO users(login, name, roleId) VALUES (?, ?, (SELECT r.id FROM roles AS r WHERE r.name = ?))";
    private static final String SQL_UPDATE = "UPDATE users SET name=?, WHERE login=?";
    private static final String SQL_DELETE = "DELETE FROM users WHERE login=?";
    private static final String SQL_GET_BY_ID = "SELECT u.id, u.name, u.login, coalesce(r.name,'') as role FROM users AS u LEFT JOIN roles AS r ON u.roleId = r.id WHERE u.id = ?";
    private static final String SQL_GET_BY_LOGIN = "SELECT u.id, u.name, u.login, coalesce(r.name,'') as role FROM users AS u LEFT JOIN roles AS r ON u.roleId = r.id WHERE u.login = ?";
    private static final String SQL_GET_ALL = "SELECT u.id, u.name, u.login, r.name as role FROM users AS u LEFT JOIN roles AS r ON u.roleId = r.id";
    private static final String SQL_GET_ROLE = "SELECT r.id, r.name FROM roles AS r JOIN users AS u ON r.id = u.roleId AND u.login = ?";
    private static final String SQL_GET_ADDRESS = "SELECT a.id, a.text FROM addresses AS a JOIN users AS u ON a.id = u.addressId AND u.login = ?";
    private static final String SQL_GET_MUSICTYPES = "SELECT mt.id, mt.name FROM musicTypes AS mt JOIN usersAndMusicTypes AS umt ON mt.id = umt.musicTypeId AND umt.userId = (SELECT id FROM users WHERE login = ?)";
    private static final String SQL_ADD_ADDRESS_TO_USER = "UPDATE users SET addressId = (SELECT id FROM addresses WHERE text = ?) WHERE login = ?";
    private static final String SQL_ADD_MUSICTYPE_TO_USER = "UPDATE usersAndMusicTypes SET userId = (SELECT id FROM users WHERE login = ?) WHERE musicTypeId = (SELECT id FROM musicTypes WHERE name = ?)";
    private static final String SQL_ADD_ROLE_TO_USER = "UPDATE users SET roleId = (SELECT id FROM roles WHERE name = ?) WHERE login = ?";
    private static final String SQL_GET_BY_NAME = "SELECT u.id, u.name, u.login, coalesce(r.name,'') as role FROM users AS u LEFT JOIN roles AS r ON u.roleId = r.id WHERE u.name = ?";
    private static final String SQL_GET_BY_ROLE = "SELECT u.id, u.name, u.login, coalesce(r.name,'') as role FROM users AS u LEFT JOIN roles AS r ON u.roleId = r.id AND r.name = ?";
    private static final String SQL_GET_BY_MUSICTYPE = "SELECT u.id, u.name, u.login, coalesce(r.name,'') as role FROM users AS u LEFT JOIN roles AS r ON u.roleId = r.id JOIN usersAndMusicTypes AS umt ON u.id = umt.usersId JOIN musicTypes AS mt ON mt.id = umt.musicTypeId AND mt.name = ?";
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
    public boolean isAdmin(String login) {
        RoleImpl dbRole = new RoleImpl();
        List<Entity> roles = dbRole.getByUserLogin(login);
        boolean result = false;
        if (roles.size() != 0) {
            Role role = (Role) roles.get(0);
            result = "ADMIN".equals(role.getName().toUpperCase());
        }
        return result;
    }

    /**
     * Gets role.
     *
     * @param user the user
     * @return the role
     */
    public List<Entity> getRole(User user) {
        Template roleTemplate = new RoleTemplate();
        return roleTemplate.executeAndReturn(instance, SQL_GET_ROLE, user.getLogin().trim());
    }

    /**
     * Gets address.
     *
     * @param user the user
     * @return the address
     */
    public List<Entity> getAddress(User user) {
        Template addressTemplate = new AddressTemplate();
        return addressTemplate.executeAndReturn(instance, SQL_GET_ADDRESS, user.getLogin().trim());
    }

    /**
     * Gets music types.
     *
     * @param user the user
     * @return the music types
     */
    public List<Entity> getMusicTypes(User user) {
        Template musicTypeTemplate = new MusicTypeTemplate();
        return musicTypeTemplate.executeAndReturn(instance, SQL_GET_MUSICTYPES, user.getLogin().trim());
    }

    /**
     * Add user.
     *
     * @param user the user
     */
    public void addUser(User user) {
        UserImpl userImpl = new UserImpl();
        userImpl.create(user);
    }

    /**
     * Add users address.
     *
     * @param user    the user
     * @param address the address
     */
    public void addUsersAddress(User user, Address address) {
        Template userTemplate = new UserTemplate();
        userTemplate.execute(instance, SQL_ADD_ADDRESS_TO_USER, address.getText(), user.getLogin().trim());
    }

    /**
     * Add users music type.
     *
     * @param user      the user
     * @param musicType the music type
     */
    public void addUsersMusicType(User user, MusicType musicType) {
        Template userTemplate = new UserTemplate();
        userTemplate.execute(instance, SQL_ADD_MUSICTYPE_TO_USER, user.getLogin().trim(), musicType.getName());
    }

    /**
     * Add users role.
     *
     * @param user the user
     * @param role the role
     */
    public void addUsersRole(User user, Role role) {
        Template userTemplate = new UserTemplate();
        userTemplate.execute(instance, SQL_ADD_ROLE_TO_USER, role.getName(), user.getLogin().trim());
    }

    /**
     * Gets by name.
     *
     * @param name the name
     * @return the by name
     */
    public List<Entity> getByName(String name) {
        Template userTemplate = new UserTemplate();
        return userTemplate.executeAndReturn(instance, SQL_GET_BY_NAME, name.trim());
    }

    /**
     * Gets by role.
     *
     * @param role the role
     * @return the by role
     */
    public List<Entity> getByRole(Role role) {
        Template userTemplate = new UserTemplate();
        return userTemplate.executeAndReturn(instance, SQL_GET_BY_ROLE, role.getName().trim());
    }

    /**
     * Gets by music type.
     *
     * @param musicType the music type
     * @return the by music type
     */
    public List<Entity> getByMusicType(MusicType musicType) {
        Template musicTypeTemplate = new MusicTypeTemplate();
        return musicTypeTemplate.executeAndReturn(instance, SQL_GET_BY_MUSICTYPE, musicType.getName().trim());
    }
}
