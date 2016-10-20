package shestakov.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The type Data source.
 */
public class DataSource {
    private static final Logger Log = LoggerFactory.getLogger(DataSource.class);
    private static final String DB_DRIVER_BY_DEFAULT = "org.hsqldb.jdbcDriver";
    private static final String DB_URL_BY_DEFAULT = "jdbc:hsqldb:file:dbpath/dbname";
    private static final String DB_USERNAME_BY_DEFAULT = "sa";
    private static final String DB_PASSWORD_BY_DEFAULT = "";
    private ComboPooledDataSource cpds;
    private static final DataSource instance = new DataSource();
    private boolean firstStart = true;

    private DataSource() {
        try {
            init();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DataSource getInstance() {
        return instance;
    }

    /**
     * Init.
     *
     * @throws Exception the exception
     */
    public void init() throws Exception {
        createPool();
    }

    /**
     * Is first start boolean.
     *
     * @return the boolean
     */
    public boolean isFirstStart() {
        boolean result = firstStart;
        if (result == true) { firstStart = false; }
        return result;
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = this.cpds.getConnection();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return conn;
    }

    /**
     * Close connection.
     *
     * @param conn the conn
     */
    public void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Gets db properties.
     *
     * @return the db properties
     * @throws IOException the io exception
     */
    public Properties getDBProperties() throws IOException {
        Properties props = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream("db.properties");
        if (in == null)
        {
            props.setProperty("driver", DB_DRIVER_BY_DEFAULT);
            props.setProperty("url", DB_URL_BY_DEFAULT);
            props.setProperty("username", DB_USERNAME_BY_DEFAULT);
            props.setProperty("password", DB_PASSWORD_BY_DEFAULT);
        } else {
            props.load(in);
        }
        in.close();
        return props;
    }

    /**
     * Create pool.
     *
     * @throws Exception the exception
     */
    public void createPool() throws Exception {
        Properties prop = getDBProperties();
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass(prop.getProperty("driver"));
        cpds.setJdbcUrl(prop.getProperty("url"));
        cpds.setUser(prop.getProperty("username"));
        cpds.setPassword(prop.getProperty("password"));
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(180);
    }

}
