package shestakov.templates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.db.DataSource;
import shestakov.models.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The type Template.
 */
public abstract class Template {
    private static final Logger Log = LoggerFactory.getLogger(Template.class);

    /**
     * Execute.
     *
     * @param instance the instance
     * @param query    the query
     */
    public final void execute(final DataSource instance, final String query) {
        executeQuery(instance, query, new Object[0]);
    };

    /**
     * Execute.
     *
     * @param instance the instance
     * @param query    the query
     * @param args     the args
     */
    public final void execute(final DataSource instance, final String query, Object... args) {
        executeQuery(instance, query, args);
    };

    /**
     * Execute and return list.
     *
     * @param instance the instance
     * @param query    the query
     * @param args     the args
     * @return the list
     */
    public final List<Entity> executeAndReturn(final DataSource instance, final String query, Object... args) {
        return executeAndReturnValue(instance, query, args);
    };

    /**
     * Execute and return list.
     *
     * @param instance the instance
     * @param query    the query
     * @return the list
     */
    public final List<Entity> executeAndReturn(final DataSource instance, final String query) {
        return executeAndReturnValue(instance, query, new Object[0]);
    };

    /**
     * Execute query.
     *
     * @param instance the instance
     * @param query    the query
     * @param args     the args
     */
    public final void executeQuery(final DataSource instance, final String query, Object... args) {
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st = conn.prepareStatement(query);
        ) {
            setArgsOfPreparedStatement(st, args);
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    };

    /**
     * Execute and return value list.
     *
     * @param instance the instance
     * @param query    the query
     * @param args     the args
     * @return the list
     */
    public final List<Entity> executeAndReturnValue(final DataSource instance, final String query, Object... args) {
        Connection conn = instance.getConnection();
        List<Entity> entities = null;
        try (
                PreparedStatement st = conn.prepareStatement(query);
        ) {
            setArgsOfPreparedStatement(st, args);
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                entities = getListOfResults(rs);
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
        return entities;
    };

    /**
     * Sets args of prepared statement.
     *
     * @param st   the st
     * @param args the args
     * @throws SQLException the sql exception
     */
    public final void setArgsOfPreparedStatement(PreparedStatement st, Object... args) throws SQLException {
        for (int i=0; i<args.length; i++) {
            st.setObject(i+1, args[i]);
        }
    }

    /**
     * Gets list of results.
     *
     * @param rs the rs
     * @return the list of results
     * @throws SQLException the sql exception
     */
    public abstract List<Entity> getListOfResults(ResultSet rs) throws SQLException;
}
