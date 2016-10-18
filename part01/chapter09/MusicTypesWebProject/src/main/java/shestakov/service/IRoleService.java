package shestakov.service;

import shestakov.models.Role;
import shestakov.models.User;

import java.util.List;

public interface IRoleService {
    List<User> getUser(Role role);
}
