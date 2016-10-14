package shestakov.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.models.Address;
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
 * The type Address sql repository.
 */
public class AddressSqlRepository implements IRepository<Address> {
    private static final Logger Log = LoggerFactory.getLogger(AddressSqlRepository.class);
    private static final DBConnector instance = DBConnector.getInstance();

    @Override
    public void add(ISpecification spec) {

    }

    @Override
    public List<Address> get(ISpecification specification) {
        final ISqlSpecification sqlSpecification = (ISqlSpecification) specification;
        Connection conn = instance.getConnection();
        List<Address> addresses = new CopyOnWriteArrayList<Address>();
        try (
                PreparedStatement st = conn.prepareStatement(sqlSpecification.toSqlQuery());
        ) {
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                while (rs.next()) {
                    Address address = new Address();
                    address.setId(rs.getInt("id"));
                    address.setText(rs.getString("text"));
                    addresses.add(address);
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
        return addresses;
    }
}
