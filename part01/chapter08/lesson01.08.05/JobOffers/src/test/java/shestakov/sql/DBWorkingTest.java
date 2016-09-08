package shestakov.sql;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DBWorkingTest {

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