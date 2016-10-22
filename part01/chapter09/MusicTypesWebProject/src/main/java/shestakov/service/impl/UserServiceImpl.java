package shestakov.service.impl;

import shestakov.dao.impl.UserImpl;
import shestakov.models.*;
import shestakov.service.IUserService;

import java.util.List;

/**
 * The type User service.
 */
public class UserServiceImpl implements IUserService {
    private UserImpl userRepository = new UserImpl();

    @Override
    public List<Entity> getRole(User user) {
        return this.userRepository.getRole(user);
    }

    @Override
    public List<Entity> getAddress(User user) {
        return this.userRepository.getAddress(user);
    }

    @Override
    public List<Entity> getMusicTypes(User user) {
        return this.userRepository.getMusicTypes(user);
    }

    @Override
    public void addUser(User user) {
        this.userRepository.addUser(user);
    }

    @Override
    public void addUsersAddress(User user, Address address) {
        this.userRepository.addUsersAddress(user, address);
    }

    @Override
    public void addUsersMusicType(User user, MusicType musicType) {
        this.userRepository.addUsersMusicType(user, musicType);
    }

    @Override
    public void addUsersRole(User user, Role role) {
        this.userRepository.addUsersRole(user, role);
    }

    @Override
    public List<Entity> getByLogin(String login) {
        return this.userRepository.getByLogin(login);
    }

    @Override
    public List<Entity> getById(int id) {
        return this.userRepository.getById(id);
    }

    @Override
    public List<Entity> getByName(String name) {
        return this.userRepository.getByName(name);
    }

    @Override
    public List<Entity> getByRole(Role role) {
        return this.userRepository.getByRole(role);
    }

    @Override
    public List<Entity> getByMusicType(MusicType musicType) {
        return this.userRepository.getByMusicType(musicType);
    }
}
