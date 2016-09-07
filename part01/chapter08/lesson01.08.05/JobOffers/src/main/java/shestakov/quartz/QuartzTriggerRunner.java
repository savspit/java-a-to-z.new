package shestakov.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Quartz trigger runner.
 */
public class QuartzTriggerRunner {
    private static final Logger Log = LoggerFactory.getLogger(QuartzTriggerRunner.class);

    /**
     * Run and schedule.
     *
     * @throws SchedulerException the scheduler exception
     */
    public void runAndSchedule() throws SchedulerException {
        // First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        // jobs can be scheduled before sched.start() has been called
        // get a "nice round" time a few seconds in the future...
        Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
        // job1 will only fire once at date/time "ts"
        JobDetail job = newJob(QuartzJob.class).withIdentity("job1", "group1").build();
        sched.deleteJob(job.getKey());
        SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("trigger1", "group1").startAt(startTime).build();
        // schedule it to run!
        Date ft = sched.scheduleJob(job, trigger);
        Log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every "
                + trigger.getRepeatInterval() / 1000 + " seconds");
        // All of the jobs have been added to the scheduler, but none of the jobs
        // will run until the scheduler has been started
        sched.start();
        try {
            // wait five minutes to show jobs
            Thread.sleep(5 * 60 * 1000);
            // executing...
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        sched.shutdown(true);
        // display some stats about the schedule that just ran
        SchedulerMetaData metaData = sched.getMetaData();
        Log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main (String args[]) {
        try {
            QuartzTriggerRunner quartzRunner = new QuartzTriggerRunner();
            quartzRunner.runAndSchedule();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

}
