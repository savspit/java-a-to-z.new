package shestakov.templates;

import shestakov.models.Entity;
import shestakov.models.Role;
import shestakov.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The type User template.
 */
public class UserTemplate extends Template{

    @Override
    public List<Entity> getListOfResults(ResultSet rs) throws SQLException {
        List<Entity> result = new CopyOnWriteArrayList<Entity>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setName(rs.getString("name"));
            user.setRole(new Role(rs.getString("role")));
            result.add(user);
        }
        return result;
    }
}
