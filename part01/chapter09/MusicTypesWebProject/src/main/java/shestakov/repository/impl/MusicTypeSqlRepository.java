package shestakov.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.models.MusicType;
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
 * The type Music type sql repository.
 */
public class MusicTypeSqlRepository implements IRepository<MusicType> {
    private static final Logger Log = LoggerFactory.getLogger(MusicTypeSqlRepository.class);
    private static final DBConnector instance = DBConnector.getInstance();

    @Override
    public void add(ISpecification spec) {

    }

    @Override
    public List<MusicType> get(ISpecification specification) {
        final ISqlSpecification sqlSpecification = (ISqlSpecification) specification;
        Connection conn = instance.getConnection();
        List<MusicType> musicTypes = new CopyOnWriteArrayList<MusicType>();
        try (
                PreparedStatement st = conn.prepareStatement(sqlSpecification.toSqlQuery());
        ) {
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                while (rs.next()) {
                    MusicType musicType = new MusicType();
                    musicType.setId(rs.getInt("id"));
                    musicType.setName(rs.getString("name"));
                    musicTypes.add(musicType);
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
        return musicTypes;
    }
}
