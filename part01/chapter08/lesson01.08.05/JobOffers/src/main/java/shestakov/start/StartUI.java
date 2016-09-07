package shestakov.start;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.quartz.CronTriggerRunner;

/**
 * The type Start ui.
 */
public class StartUI {
    private static final Logger Log = LoggerFactory.getLogger(CronTriggerRunner.class);

    /**
     * Init.
     *
     * @param cronRunner the cron runner
     */
    public void init(CronTriggerRunner cronRunner) {
        try {
            cronRunner.runAndSchedule();
        } catch (SchedulerException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        CronTriggerRunner cronRunner = new CronTriggerRunner();
        new StartUI().init(cronRunner);
    }

}