package shestakov.templates;

import shestakov.models.Entity;
import shestakov.models.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The type Role template.
 */
public class RoleTemplate extends Template{

    @Override
    public List<Entity> getListOfResults(ResultSet rs) throws SQLException {
        List<Entity> result = new CopyOnWriteArrayList<Entity>();
        while (rs.next()) {
            Role role = new Role();
            role.setId(rs.getInt("id"));
            role.setName(rs.getString("name"));
            result.add(role);
        }
        return result;
    }
}
