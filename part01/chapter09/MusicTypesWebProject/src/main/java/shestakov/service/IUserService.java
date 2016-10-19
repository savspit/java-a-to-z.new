package shestakov.service;

import shestakov.models.Address;
import shestakov.models.MusicType;
import shestakov.models.Role;
import shestakov.models.User;

import java.util.List;

public interface IUserService {
    Role getRole(User user);
    Address getAddress(User user);
    List<MusicType> getMusicTypes(User user);
    void addUser(User user);
    void addUsersAddress(User user, Address address);
    void addUsersMusicType(User user, MusicType musicType);
    void addUsersRole(User user, Role role);
    User getByLogin(String login);
    User getById(int id);
    List<User> getByName(String name);
    List<User> getByRole(Role role);
    List<User> getByMusicType(MusicType musicType);
}
