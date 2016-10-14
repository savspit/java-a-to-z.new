package shestakov.services;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The type Db connector.
 */
public class DBConnector {
    private static final Logger Log = LoggerFactory.getLogger(DBConnector.class);
    private static final String DB_DRIVER_BY_DEFAULT = "org.hsqldb.jdbcDriver";
    private static final String DB_URL_BY_DEFAULT = "jdbc:hsqldb:file:dbpath/dbname";
    private static final String DB_USERNAME_BY_DEFAULT = "sa";
    private static final String DB_PASSWORD_BY_DEFAULT = "";
    private static final DBConnector instance = new DBConnector();
    private DataSource datasource;
    private boolean firstStart = true;

    private DBConnector() {
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
    public static DBConnector getInstance() {
        return instance;
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
            conn = this.datasource.getConnection();
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
     * Init.
     *
     * @throws Exception the exception
     */
    public void init() throws Exception {
        try {
            Properties prop = getDBProperties();

            Class.forName(prop.getProperty("driver"));

            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES,
                    "org.apache.naming");
            InitialContext ic = new InitialContext();

            ic.createSubcontext("java:");
            ic.createSubcontext("java:/comp");
            ic.createSubcontext("java:/comp/env");
            ic.createSubcontext("java:/comp/env/jdbc");

            DataSource ds = new DataSource();
            ds.setUrl(prop.getProperty("url"));
            ds.setUsername(prop.getProperty("username"));
            ds.setPassword(prop.getProperty("password"));

            ic.bind("java:/comp/env/jdbc/postgres", ds);

            Context initContext = new InitialContext();
            Context webContext = (Context)initContext.lookup("java:/comp/env");

            this.datasource = (DataSource) webContext.lookup("jdbc/postgres");

        } catch (NamingException e) {
            Log.error(e.getMessage(), e);
        }
    }

}
