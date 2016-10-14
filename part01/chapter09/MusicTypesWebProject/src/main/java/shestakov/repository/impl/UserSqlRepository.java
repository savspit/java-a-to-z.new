package shestakov.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.models.Address;
import shestakov.models.Role;
import shestakov.models.User;
import shestakov.repository.IRepository;
import shestakov.repository.ISpecification;
import shestakov.repository.ISqlSpecification;
import shestakov.services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The type User sql repository.
 */
public class UserSqlRepository implements IRepository<User> {
    private static final Logger Log = LoggerFactory.getLogger(UserSqlRepository.class);
    private static final DBConnector instance = DBConnector.getInstance();

    @Override
    public void add(ISpecification specification) {
        final ISqlSpecification sqlSpecification = (ISqlSpecification) specification;
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st = conn.prepareStatement(sqlSpecification.toSqlQuery());
        ) {
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

    @Override
    public List<User> get(ISpecification specification) {
        final ISqlSpecification sqlSpecification = (ISqlSpecification) specification;
        Connection conn = instance.getConnection();
        List<User> users = new CopyOnWriteArrayList<User>();
        try (
                PreparedStatement st = conn.prepareStatement(sqlSpecification.toSqlQuery());
        ) {
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    user.setName(rs.getString("name"));
                    user.setRole(new Role(rs.getInt("roleId")));
                    user.setAddress(new Address(rs.getInt("addressId")));
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
