package shestakov.sql;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.*;

import java.sql.*;
import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The type Job offers storage test.
 */
public class JobOffersStorageTest {
    private static final String DRIVER = org.hsqldb.jdbcDriver.class.getName();
    private static final String URL = "jdbc:hsqldb:file:dbpath/dbname";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String OFFERS_URL = "http://www.sql.ru/forum/job-offers";

    private static IDatabaseTester databaseTester;
    private static JobOffersStorage storage;

    /**
     * Init.
     *
     * @throws Exception the exception
     */
    @BeforeClass
    public static void init() throws Exception
    {
        storage = new JobOffersStorage();
        databaseTester = new JdbcDatabaseTester(DRIVER, URL, USER, PASSWORD);
        createTablesSinceDbUnitDoesNot(databaseTester.getConnection().getConnection());
        IDataSet dataSet = new XmlDataSet(storage.getClass().getClassLoader().getResourceAsStream("hsqldb_schema.xml"));
        databaseTester.setDataSet(dataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
        databaseTester.onSetup();
        storage.setConnection(databaseTester.getConnection().getConnection());
        storage.setOffersUrl(OFFERS_URL);
        storage.openConnection();
    }

    private static void createTablesSinceDbUnitDoesNot(Connection connection) throws SQLException {
        PreparedStatement st1 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS properties (id IDENTITY,runTime TIMESTAMP)");
        PreparedStatement st2 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS authors (id IDENTITY, name VARCHAR(255),link VARCHAR(2000))");
        PreparedStatement st3 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS offers (id IDENTITY,text VARCHAR(2000),link VARCHAR(2000),createDate TIMESTAMP,authorId INTEGER REFERENCES authors(id))");
        st1.execute();
        st2.execute();
        st3.execute();
        st1.close();
        st2.close();
        st3.close();
    }

    /**
     * Clean up.
     *
     * @throws Exception the exception
     */
    @AfterClass
    public static void cleanUp() throws Exception {
        storage.clearTables();
        storage.closeConnection();
        databaseTester.onTearDown();
        databaseTester = null;
    }

    /**
     * When first run should add data by last year.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenFirstRunShouldAddDataByLastYear() throws Exception {
        storage.setLastRunTime();
        storage.setCurrentRunTime();
        storage.getJobOffersFromOffersUrl();
        Thread.sleep(10000);
        Date dateMin = new Date(storage.getMinCreateDate().getTime());
        Date dateMax = new Date(storage.getMaxCreateDate().getTime());
        int delta = monthsBetween(dateMin, dateMax);
        assertThat(delta, is(12));
    }

    /**
     * When add data should be do it correct.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddDataShouldBeDoItCorrect() throws Exception {
        storage.addDataInDB("test", "test", "test", "test", 0L);
        Thread.sleep(1000);
        int count = storage.getCountOfFilterByOfferTextFromDB("test");
        assertThat(count, is(1));
    }

    /**
     * When add duplicates data should ignore duplicates.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddDuplicatesDataShouldIgnoreDuplicates() throws Exception {
        for (int i=0; i<2; i++) {
            storage.addDataInDB("test", "test", "test", "test", 0L);
        }
        Thread.sleep(1000);
        int count = storage.getCountOfFilterByOfferTextFromDB("test");
        assertThat(count, is(1));
    }

    /**
     * Months between int.
     *
     * @param minDate the min date
     * @param maxDate the max date
     * @return the int
     */
    public int monthsBetween(Date minDate, Date maxDate)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(minDate);
        int minDateMonth =  cal.get(Calendar.MONTH);
        int minDateYear = cal.get(Calendar.YEAR);
        cal.setTime(maxDate);
        int maxDateMonth =  cal.get(Calendar.MONTH);
        int maxDateYear = cal.get(Calendar.YEAR);
        return ((maxDateYear - minDateYear) * cal.getMaximum(Calendar.MONTH)) + (maxDateMonth - minDateMonth) + 1;
    }
}