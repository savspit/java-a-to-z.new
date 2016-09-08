package shestakov.start;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.jsoup.JobOffersParsing;
import shestakov.quartz.CronScheduledTrigger;
import shestakov.sql.DBWorking;

public class JobOffers {
    private static final Logger Log = LoggerFactory.getLogger(JobOffers.class);
    private DBWorking dbConnect = new DBWorking();

    public void openConnection() {
        this.dbConnect.openConnection();
    }

    public void closeConnection() {
        this.dbConnect.closeConnection();
    }

    public void setStartTime() {
        this.dbConnect.setStartTime();
    }

    public void getJobOffers() {
        JobOffersParsing jsoupConnect = new JobOffersParsing(dbConnect);
        jsoupConnect.getJobOffers();
    }

    public void runAndSchedule() {
        CronScheduledTrigger cronRunner = new CronScheduledTrigger();
        try {
            cronRunner.runAndSchedule();
        } catch (SchedulerException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public void doAllWork() {
        openConnection();
        setStartTime();
        getJobOffers();
        //printAllJobOffers();
        closeConnection();
    }

    public void printAllJobOffers() {
        this.dbConnect.printAllJobOffers();
    }
}
