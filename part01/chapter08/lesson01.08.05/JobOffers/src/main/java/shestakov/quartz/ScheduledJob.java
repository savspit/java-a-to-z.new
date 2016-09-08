package shestakov.quartz;

import org.quartz.*;
import shestakov.sql.JobOffersStorage;

public class ScheduledJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobOffersStorage storage = new JobOffersStorage();
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        storage.setUrl(jobDataMap.get("url").toString());
        storage.setUsername(jobDataMap.get("username").toString());
        storage.setPassword(jobDataMap.get("password").toString());
        storage.setOffersUrl(jobDataMap.get("offersUrl").toString());
        storage.getJobOffers();
    }
}
