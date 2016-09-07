package shestakov.start;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.jsoup.JsoupConnect;
import shestakov.quartz.CronTriggerRunner;
import shestakov.sql.DBConnect;

/**
 * The type Job offers.
 */
public class JobOffers {
    private static final Logger Log = LoggerFactory.getLogger(JobOffers.class);
    private DBConnect dbConnect = new DBConnect();

    /**
     * Open connection.
     */
    public void openConnection() {
        this.dbConnect.openConnection();
    }

    /**
     * Close connection.
     */
    public void closeConnection() {
        this.dbConnect.closeConnection();
    }

    /**
     * Sets start time.
     */
    public void setStartTime() {
        this.dbConnect.setStartTime();
    }

    /**
     * Gets job offers.
     */
    public void getJobOffers() {
        JsoupConnect jsoupConnect = new JsoupConnect(dbConnect);
        jsoupConnect.getJobOffers();
    }

    /**
     * Run and schedule.
     */
    public void runAndSchedule() {
        CronTriggerRunner cronRunner = new CronTriggerRunner();
        try {
            cronRunner.runAndSchedule();
        } catch (SchedulerException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Do all work.
     */
    public void doAllWork() {
        openConnection();
        setStartTime();
        getJobOffers();
        //printAllJobOffers();
        closeConnection();
    }

    /**
     * Print all job offers.
     */
    public void printAllJobOffers() {
        this.dbConnect.printAllJobOffers();
    }
}
