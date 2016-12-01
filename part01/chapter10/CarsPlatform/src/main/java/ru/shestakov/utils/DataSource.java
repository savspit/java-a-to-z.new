package ru.shestakov.utils;

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
    /**
     * logger field.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DataSource.class);
    /**
     * DB_DRIVER_BY_DEFAULT field.
     */
    private static final String DB_DRIVER_BY_DEFAULT = "org.hsqldb.jdbcDriver";
    /**
     * DB_URL_BY_DEFAULT field.
     */
    private static final String DB_URL_BY_DEFAULT = "jdbc:hsqldb:file:dbpath/dbname";
    /**
     * DB_USERNAME_BY_DEFAULT field.
     */
    private static final String DB_USERNAME_BY_DEFAULT = "sa";
    /**
     * DB_PASSWORD_BY_DEFAULT field.
     */
    private static final String DB_PASSWORD_BY_DEFAULT = "";
    /**
     * pool datasource field.
     */
    private ComboPooledDataSource cpds;
    /**
     * instance datasource field.
     */
    private static final DataSource INSTANCE = new DataSource();
    /**
     * MinPoolSize pool parameter.
     */
    private static final int MIN_POOL_SIZE = 5;
    /**
     * MaxPoolSize pool parameter.
     */
    private static final int MAX_POOL_SIZE = 5;
    /**
     * AcquireIncrement pool parameter.
     */
    private static final int ACQUIRE_INCREMENT = 5;
    /**
     * MaxStatements pool parameter.
     */
    private static final int MAX_STATEMENTS = 5;
    /**
     * firstStart field.
     */
    private boolean firstStart = true;

    /**
     * Constructor of datasource.
     */
    private DataSource() {
        try {
            init();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DataSource getInstance() {
        return INSTANCE;
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
        boolean result = this.firstStart;
        if (result) {
            this.firstStart = false;
        }
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
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
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
        if (in == null) {
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
        cpds.setMinPoolSize(MIN_POOL_SIZE);
        cpds.setMaxPoolSize(MAX_POOL_SIZE);
        cpds.setAcquireIncrement(ACQUIRE_INCREMENT);
        cpds.setMaxStatements(MAX_STATEMENTS);
    }

}
