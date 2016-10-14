package shestakov.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.models.Role;
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
 * The type Role sql repository.
 */
public class RoleSqlRepository implements IRepository<Role> {
    private static final Logger Log = LoggerFactory.getLogger(RoleSqlRepository.class);
    private static final DBConnector instance = DBConnector.getInstance();

    @Override
    public void add(ISpecification spec) {

    }

    @Override
    public List<Role> get(ISpecification specification) {
        final ISqlSpecification sqlSpecification = (ISqlSpecification) specification;
        Connection conn = instance.getConnection();
        List<Role> roles = new CopyOnWriteArrayList<Role>();
        try (
                PreparedStatement st = conn.prepareStatement(sqlSpecification.toSqlQuery());
        ) {
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                while (rs.next()) {
                    Role role = new Role();
                    role.setId(rs.getInt("id"));
                    role.setName(rs.getString("name"));
                    roles.add(role);
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
        return roles;
    }
}
