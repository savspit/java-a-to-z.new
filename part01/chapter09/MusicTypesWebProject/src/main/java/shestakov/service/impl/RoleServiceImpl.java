package shestakov.service.impl;

import shestakov.models.Entity;
import shestakov.models.Role;
import shestakov.repository.impl.RoleRepositoryImpl;
import shestakov.service.IRoleService;

import java.util.List;

/**
 * The type Role service.
 */
public class RoleServiceImpl implements IRoleService{
    private RoleRepositoryImpl roleRepository = new RoleRepositoryImpl();

    @Override
    public List<Entity> getUsers(Role role) {
        return this.roleRepository.getUsers(role);
    }
}
