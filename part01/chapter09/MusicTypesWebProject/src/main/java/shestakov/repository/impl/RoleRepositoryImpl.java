package shestakov.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.dao.impl.RoleImpl;
import shestakov.db.DataSource;
import shestakov.models.Role;
import shestakov.models.User;
import shestakov.repository.IRoleRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RoleRepositoryImpl implements IRoleRepository{
    private static final Logger Log = LoggerFactory.getLogger(RoleRepositoryImpl.class);
    private static final String SQL_GET_USERS = "SELECT u.id, u.login, u.name FROM users AS u JOIN roles AS r ON u.roleId = r.id AND r.name = ?";
    private static final DataSource instance = DataSource.getInstance();

    public List<User> getUsers(Role role) {
        Connection conn = instance.getConnection();
        List<User> users = new CopyOnWriteArrayList<User>();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_GET_USERS);
        ) {
            st.setString(1, role.getName().trim());
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    user.setName(rs.getString("name"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
        return users;
    }

}
