package ru.shestakov.utils;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The type Liquibase utils.
 */
public class LiquibaseUtils {
    /**
     * logger field.
     */
    private static final Logger LOG = LoggerFactory.getLogger(LiquibaseUtils.class);
    /**
     * LB_CHANGELOG_BY_DEFAULT field.
     */
    private static final String LB_CHANGELOG_BY_DEFAULT = "liquibase/db.changelog-master.xml";
    /**
     * datasource instance field.
     */
    private static final DataSource INSTANCE = DataSource.getInstance();

    /**
     * Gets liquibase properties.
     *
     * @return the liquibase properties
     * @throws IOException the io exception
     */
    public Properties getLiquibaseProperties() throws IOException {
        Properties props = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream("/liquibase/liquibase.properties");
        if (in == null) {
            props.setProperty("changeLogFile", LB_CHANGELOG_BY_DEFAULT);
        } else {
            props.load(in);
        }
        in.close();
        return props;
    }

    /**
     * Migrate.
     */
    public void migrate() {
        if (INSTANCE.isFirstStart()) {
            createTables();
        }
    }

    /**
     * Create tables.
     */
    public void createTables() {
        Properties prop = null;
        try {
            prop = getLiquibaseProperties();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        Connection conn = null;
        try {
            conn = INSTANCE.getConnection();
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
            Liquibase liquibase = new Liquibase(prop.getProperty("outputChangeLogFile"), new ClassLoaderResourceAccessor(), database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (LiquibaseException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.rollback();
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }
}
