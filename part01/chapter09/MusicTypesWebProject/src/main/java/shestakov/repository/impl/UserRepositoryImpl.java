package shestakov.repository.impl;

import shestakov.dao.impl.UserImpl;
import shestakov.db.DataSource;
import shestakov.templates.*;
import shestakov.models.*;
import shestakov.repository.IUserRepository;

import java.util.List;

/**
 * The type User repository.
 */
public class UserRepositoryImpl implements IUserRepository{
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
     * Gets by login.
     *
     * @param login the login
     * @return the by login
     */
    public List<Entity> getByLogin(String login) {
        UserImpl userImpl = new UserImpl();
        return userImpl.getByLogin(login);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public List<Entity> getById(int id) {
        UserImpl userImpl = new UserImpl();
        return userImpl.getById(id);
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
