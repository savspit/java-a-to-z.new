package shestakov.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * The type Jdbc job store runner.
 */
public class JDBCJobStoreRunner {
    private static final Logger Log = LoggerFactory.getLogger(JDBCJobStoreRunner.class);

    /**
     * Run and schedule.
     *
     * @throws SchedulerException the scheduler exception
     */
    public void runAndSchedule() throws SchedulerException {
        // Initiate a Schedule Factory
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        // Retrieve a scheduler from schedule factory
        Scheduler scheduler = schedulerFactory.getScheduler();
        for (String groupName : scheduler.getJobGroupNames()) {
            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                if (jobKey.getName().equals("job1")) {
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    scheduler.rescheduleJob(triggers.get(0).getKey(), triggers.get(0));
                    Date nextFireTime = triggers.get(0).getNextFireTime();
                    System.out.println("[jobName] : " + jobKey.getName() + " [groupName] : "
                            + jobKey.getGroup() + " - " + nextFireTime);
                }
            }
        }
        // start the scheduler
        scheduler.start();
    }

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main (String args[]) {
        try {
            JDBCJobStoreRunner jdbcRunner = new JDBCJobStoreRunner();
            jdbcRunner.runAndSchedule();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }
}
