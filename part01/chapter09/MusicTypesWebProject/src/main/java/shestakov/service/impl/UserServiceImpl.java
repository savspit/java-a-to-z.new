package shestakov.service.impl;

import shestakov.models.Address;
import shestakov.models.MusicType;
import shestakov.models.Role;
import shestakov.models.User;
import shestakov.repository.impl.UserRepositoryImpl;
import shestakov.service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {
    private UserRepositoryImpl userRepository = new UserRepositoryImpl();

    @Override
    public Role getRole(User user) {
        return this.userRepository.getRole(user);
    }

    @Override
    public Address getAddress(User user) {
        return this.userRepository.getAddress(user);
    }

    @Override
    public List<MusicType> getMusicTypes(User user) {
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
    public User getByLogin(String login) {
        return this.userRepository.getByLogin(login);
    }

    @Override
    public User getById(int id) {
        return this.userRepository.getById(id);
    }

    @Override
    public List<User> getByName(String name) {
        return this.userRepository.getByName(name);
    }

    @Override
    public List<User> getByRole(Role role) {
        return this.userRepository.getByRole(role);
    }

    @Override
    public List<User> getByMusicType(MusicType musicType) {
        return this.userRepository.getByMusicType(musicType);
    }
}
