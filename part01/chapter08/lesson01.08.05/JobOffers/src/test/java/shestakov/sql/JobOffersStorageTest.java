package shestakov.sql;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * The type Job offers storage test.
 */
public class JobOffersStorageTest {

    /**
     * When app have first run time should be first run time.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAppHaveFirstRunTimeShouldBeFirstRunTime() throws Exception {
        JobOffersStorage storage = new JobOffersStorage();
        storage.setUrl("jdbc:postgresql://localhost:5432/joboffers");
        storage.setUsername("postgres");
        storage.setPassword("1");
        storage.setOffersUrl("http://www.sql.ru/forum/job-offers");
        storage.openConnection();
        storage.clearTables();
        Thread.sleep(2000);
        storage.setLastRunTime();
        storage.setCurrentRunTime();
        storage.getJobOffersFromOffersUrl();
        storage.closeConnection();
        Thread.sleep(10000);
        assertTrue(storage.isFirstRun());
    }

    /**
     * When app have not first run time should not be first run time.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAppHaveNotFirstRunTimeShouldNotBeFirstRunTime() throws Exception {
        JobOffersStorage storage = new JobOffersStorage();
        storage.setUrl("jdbc:postgresql://localhost:5432/joboffers");
        storage.setUsername("postgres");
        storage.setPassword("1");
        storage.setOffersUrl("http://www.sql.ru/forum/job-offers");
        Thread.sleep(2000);
        storage.setLastRunTime();
        storage.setCurrentRunTime();
        storage.getJobOffers();
        storage.closeConnection();
        Thread.sleep(10000);
        assertFalse(storage.isFirstRun());
    }

    /**
     * When first run should add data by last year.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenFirstRunShouldAddDataByLastYear() throws Exception {
        JobOffersStorage storage = new JobOffersStorage();
        storage.setUrl("jdbc:postgresql://localhost:5432/joboffers");
        storage.setUsername("postgres");
        storage.setPassword("1");
        storage.setOffersUrl("http://www.sql.ru/forum/job-offers");
        storage.openConnection();
        storage.clearTables();
        Thread.sleep(2000);
        storage.setLastRunTime();
        storage.setCurrentRunTime();
        storage.getJobOffersFromOffersUrl();
        Thread.sleep(10000);
        int delta = storage.getDeltaOfMaxAndMinDatesInMonth();
        storage.closeConnection();
        assertThat(delta, is(12));
    }

    /**
     * When add data should be do it correct.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddDataShouldBeDoItCorrect() throws Exception {
        JobOffersStorage storage = new JobOffersStorage();
        storage.setUrl("jdbc:postgresql://localhost:5432/joboffers");
        storage.setUsername("postgres");
        storage.setPassword("1");
        storage.setOffersUrl("http://www.sql.ru/forum/job-offers");
        storage.clearTables();
        storage.addDataInDB("test", "test", "test", "test", 0L);
        Thread.sleep(1000);
        int count = storage.getCountOfFilterByOfferTextFromDB("test");
        storage.closeConnection();
        assertThat(count, is(1));
    }

    /**
     * When add duplicates data should ignore duplicates.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddDuplicatesDataShouldIgnoreDuplicates() throws Exception {
        JobOffersStorage storage = new JobOffersStorage();
        storage.setUrl("jdbc:postgresql://localhost:5432/joboffers");
        storage.setUsername("postgres");
        storage.setPassword("1");
        storage.setOffersUrl("http://www.sql.ru/forum/job-offers");
        storage.clearTables();
        storage.addDataInDB("test", "test", "test", "test", 0L);
        storage.addDataInDB("test", "test", "test", "test", 0L);
        storage.addDataInDB("test", "test", "test", "test", 0L);
        Thread.sleep(1000);
        int count = storage.getCountOfFilterByOfferTextFromDB("test");
        storage.closeConnection();
        assertThat(count, is(1));
    }
}