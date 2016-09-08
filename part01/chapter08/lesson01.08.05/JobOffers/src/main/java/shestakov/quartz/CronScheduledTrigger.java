package shestakov.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * The type Cron scheduled trigger.
 */
public class CronScheduledTrigger {
    private static final Logger Log = LoggerFactory.getLogger(CronScheduledTrigger.class);

    /**
     * Schedule.
     *
     * @throws SchedulerException the scheduler exception
     */
    public void schedule() throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();
        JobDetail job = newJob(ScheduledJob.class).withIdentity("jobOffers_from_SQLRU_job", "jobOffers_from_SQLRU_group").build();
        setJobProperties(job);
        String cronExpression = String.format("0/%s * * * * ?", job.getJobDataMap().get("scheduleIntervalMin"));
        CronTrigger trigger = newTrigger().withIdentity("jobOffers_from_SQLRU_trigger", "jobOffers_from_SQLRU_group").withSchedule(cronSchedule(cronExpression)).build();
        scheduler.deleteJob(job.getKey());
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
        try {
            // wait five minutes to show jobs
            Thread.sleep(5 * 60 * 1000);
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        scheduler.shutdown(true);
        SchedulerMetaData metaData = scheduler.getMetaData();
        Log.info(String.format("Executed %s jobs.", metaData.getNumberOfJobsExecuted()));
    }

    /**
     * Sets job properties.
     *
     * @param job the job
     */
    public void setJobProperties(JobDetail job) {
        Properties prop = new Properties();
        try (
                FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/part01/chapter08/lesson01.08.05/JobOffers/jobOffers.cfg");
        ) {
            prop.load(fis);
        } catch (IOException e) {
            Log.error(e.getMessage(), e);
        }
        JobDataMap jobDataMap = job.getJobDataMap();
        jobDataMap.put("url", prop.getProperty("url").toString());
        jobDataMap.put("username", prop.getProperty("username").toString());
        jobDataMap.put("password", prop.getProperty("password").toString());
        jobDataMap.put("offersUrl", prop.getProperty("offersUrl").toString());
        jobDataMap.put("scheduleIntervalMin", prop.getProperty("scheduleIntervalMin").toString());
    }

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main (String args[]) {
        try {
            CronScheduledTrigger cronTrigger = new CronScheduledTrigger();
            cronTrigger.schedule();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }
}
