package shestakov.service;

import shestakov.models.*;

import java.util.List;

/**
 * The interface User service.
 */
public interface IUserService {
    /**
     * Gets role.
     *
     * @param user the user
     * @return the role
     */
    List<Entity> getRole(User user);

    /**
     * Gets address.
     *
     * @param user the user
     * @return the address
     */
    List<Entity> getAddress(User user);

    /**
     * Gets music types.
     *
     * @param user the user
     * @return the music types
     */
    List<Entity> getMusicTypes(User user);

    /**
     * Add user.
     *
     * @param user the user
     */
    void addUser(User user);

    /**
     * Add users address.
     *
     * @param user    the user
     * @param address the address
     */
    void addUsersAddress(User user, Address address);

    /**
     * Add users music type.
     *
     * @param user      the user
     * @param musicType the music type
     */
    void addUsersMusicType(User user, MusicType musicType);

    /**
     * Add users role.
     *
     * @param user the user
     * @param role the role
     */
    void addUsersRole(User user, Role role);

    /**
     * Gets by login.
     *
     * @param login the login
     * @return the by login
     */
    List<Entity> getByLogin(String login);

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    List<Entity> getById(int id);

    /**
     * Gets by name.
     *
     * @param name the name
     * @return the by name
     */
    List<Entity> getByName(String name);

    /**
     * Gets by role.
     *
     * @param role the role
     * @return the by role
     */
    List<Entity> getByRole(Role role);

    /**
     * Gets by music type.
     *
     * @param musicType the music type
     * @return the by music type
     */
    List<Entity> getByMusicType(MusicType musicType);
}
