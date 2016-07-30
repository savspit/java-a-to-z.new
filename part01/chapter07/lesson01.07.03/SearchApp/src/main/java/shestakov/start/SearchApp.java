package shestakov.start;

import org.quartz.*;
import org.quartz.core.QuartzScheduler;
import org.quartz.core.QuartzSchedulerResources;
import org.quartz.impl.DirectSchedulerFactory;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.simpl.RAMJobStore;
import org.quartz.simpl.SimpleThreadPool;
import org.quartz.spi.JobStore;
import org.quartz.spi.ThreadPool;
import shestakov.services.KeysValidator;
import shestakov.threads.Worker;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SearchApp {
    private KeysValidator kv;
    public static final String DEFAULT_INSTANCE_ID = "SIMPLE_NON_CLUSTERED";
    public static final String DEFAULT_SCHEDULER_NAME = "SimpleQuartzScheduler";

    public SearchApp(KeysValidator kv) {
        this.kv = kv;
    }

    public void startSearching() throws SchedulerException {
        File[] paths;
        Queue<File> fileTree = new PriorityQueue<>();
        try {
            paths = File.listRoots();
            for(File path:paths)
            {
                Collections.addAll(fileTree, path.listFiles());
            }
        } catch (Exception ignore){ /*NOP*/ }

        /*SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sl = sf.getScheduler();
        sl.start();*/

        //TODO threadpool
        /*ExecutorService pool = Executors.newFixedThreadPool(fileTree.size());
        List<Future> results = new ArrayList<>();
        for (File currentFileOrDir : fileTree) {
            results.add(pool.submit(new Worker(currentFileOrDir)));
        }*/

        ThreadPool pool = new SimpleThreadPool(fileTree.size(), Thread.NORM_PRIORITY);
        pool.initialize();
        JobStore store = new RAMJobStore();
        createScheduler(pool, store, null, 0, -1, -1);



        /*DirectSchedulerFactory.getInstance().createScheduler(
                "QuartzScheduler", "Instance1", pool,
                new RAMJobStore());
        );*/

        //JobDetail job = JobBuilder.newJob(Worker.class).withIdentity("d").build();

        Trigger trigger = TriggerBuilder.newTrigger().startNow().build();




    }

    public void createScheduler(ThreadPool pool,
                                JobStore store,
                                //Map SchedulerPluginMap,
                                String rmiRegistryHost,
                                int rmiRegistryPort,
                                long idleWaitTime,
                                long dbFailureRetryInterval) throws SchedulerException{
        pool.initialize();

        QuartzSchedulerResources qsr = new QuartzSchedulerResources();
        qsr.setName(DEFAULT_SCHEDULER_NAME);
        qsr.setInstanceId(DEFAULT_INSTANCE_ID);
        //SchedulerDetailsSetter.setDetails(pool, DEFAULT_SCHEDULER_NAME, DEFAULT_INSTANCE_ID);
        qsr.setThreadPool(pool);
        qsr.setJobStore(store);
        qsr.setRMIRegistryHost(rmiRegistryHost);
        qsr.setRMIRegistryPort(rmiRegistryPort);

        QuartzScheduler qs = new QuartzScheduler(qsr, idleWaitTime, dbFailureRetryInterval);
        //store,initialize();
        qs.initialize();

        qs.start();

    }
}
