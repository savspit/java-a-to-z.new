package shestakov.quartz;

import org.quartz.*;
import shestakov.start.JobOffers;

public class ScheduledJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobOffers jobOffers = new JobOffers();
        jobOffers.doAllWork();
    }
}
