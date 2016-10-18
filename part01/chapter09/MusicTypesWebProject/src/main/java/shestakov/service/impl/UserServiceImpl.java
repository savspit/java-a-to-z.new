package shestakov.service.impl;

import shestakov.models.Address;
import shestakov.models.MusicType;
import shestakov.models.Role;
import shestakov.models.User;
import shestakov.service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {
    @Override
    public Role getRole(User user) {
        return null;
    }

    @Override
    public Address getAddress(User user) {
        return null;
    }

    @Override
    public List<MusicType> getMusicTypes(User user) {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User addUsersAddress(User user, Address address) {
        return null;
    }

    @Override
    public User addUsersMusicType(User user, MusicType musicType) {
        return null;
    }

    @Override
    public User addUsersRole(User user, Role role) {
        return null;
    }

    @Override
    public User getByLogin(String login) {
        return null;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public List<User> getByName(String name) {
        return null;
    }

    @Override
    public List<User> getByRole(Role role) {
        return null;
    }

    @Override
    public List<User> getByMusicType(MusicType musicType) {
        return null;
    }
}
