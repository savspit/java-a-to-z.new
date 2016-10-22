package shestakov.service.impl;

import shestakov.dao.impl.RoleImpl;
import shestakov.models.Entity;
import shestakov.models.Role;
import shestakov.service.IRoleService;

import java.util.List;

/**
 * The type Role service.
 */
public class RoleServiceImpl implements IRoleService{
    private RoleImpl roleRepository = new RoleImpl();

    @Override
    public List<Entity> getUsers(Role role) {
        return this.roleRepository.getUsers(role);
    }
}
