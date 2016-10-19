package shestakov.service.impl;

import shestakov.models.Role;
import shestakov.models.User;
import shestakov.repository.impl.RoleRepositoryImpl;
import shestakov.service.IRoleService;

import java.util.List;

public class RoleServiceImpl implements IRoleService{
    private RoleRepositoryImpl roleRepository = new RoleRepositoryImpl();

    @Override
    public List<User> getUsers(Role role) {
        return this.roleRepository.getUsers(role);
    }
}
