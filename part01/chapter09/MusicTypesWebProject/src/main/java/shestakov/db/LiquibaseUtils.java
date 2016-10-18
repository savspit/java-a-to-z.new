package shestakov.db;

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

public class LiquibaseUtils {
    private static final Logger Log = LoggerFactory.getLogger(LiquibaseUtils.class);
    private static final String LB_CHANGELOG_BY_DEFAULT = "liquibase/db.changelog-master.xml";
    private static final DataSource instance = DataSource.getInstance();

    public Properties getLiquibaseProperties() throws IOException {
        Properties props = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream("/liquibase/liquibase.properties");
        if (in == null)
        {
            props.setProperty("changeLogFile", LB_CHANGELOG_BY_DEFAULT);
        } else {
            props.load(in);
        }
        in.close();
        return props;
    }

    public void migrate() throws Exception {
        if (instance.isFirstStart()) {
            createTables();
        }
    }

    public void createTables() throws Exception {
        Properties prop = getLiquibaseProperties();
        Connection conn = null;
        try {
            conn = instance.getConnection();
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
            Liquibase liquibase = new liquibase.Liquibase(prop.getProperty("outputChangeLogFile"), new ClassLoaderResourceAccessor(), database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (LiquibaseException e) {
            Log.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.rollback();
                    conn.close();
                } catch (SQLException e) {
                    Log.error(e.getMessage(), e);
                }
            }
        }
    }
}
