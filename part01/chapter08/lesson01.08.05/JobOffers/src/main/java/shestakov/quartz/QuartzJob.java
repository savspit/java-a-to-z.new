package shestakov.quartz;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.start.JobOffers;

/**
 * The type Quartz job.
 */
public class QuartzJob implements Job {
    private static final Logger Log = LoggerFactory.getLogger(CronTriggerRunner.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobOffers jobOffers = new JobOffers();
        jobOffers.doAllWork();
    }
}
