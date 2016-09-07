package shestakov.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * The type Cron trigger runner.
 */
public class CronTriggerRunner {
    private static final Logger Log = LoggerFactory.getLogger(CronTriggerRunner.class);

    /**
     * Run and schedule.
     *
     * @throws SchedulerException the scheduler exception
     */
    public void runAndSchedule() throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        // job1 will run every "scheduleIntervalInSec" seconds
        JobDetail job = newJob(QuartzJob.class).withIdentity("job1", "group1").build();
        String cronExpression = String.format("0/%s * * * * ?", getscheduleIntervalInMin());
        CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule(cronExpression)).build();
        sched.deleteJob(job.getKey());
        Date ft = sched.scheduleJob(job, trigger);
        sched.start();
        try {
            // wait five minutes to show jobs
            Thread.sleep(5 * 60 * 1000);
            // executing...
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        sched.shutdown(true);
        SchedulerMetaData metaData = sched.getMetaData();
        Log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }

    /**
     * Gets interval in min.
     *
     * @return the interval in min
     */
    public int getscheduleIntervalInMin() {
        Properties prop = new Properties();
        try (
                FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/part01/chapter08/lesson01.08.05/JobOffers/jobOffers.cfg");
        ) {
            prop.load(fis);
        } catch (IOException e) {
            Log.error(e.getMessage(), e);
        }
        return Integer.parseInt(prop.getProperty("scheduleIntervalInMin").toString());
    }
}
