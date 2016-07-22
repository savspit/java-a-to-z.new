package shestakov.start;

import org.junit.Assert;
import org.junit.Test;
import shestakov.models.Cache;
import shestakov.models.Entity;
import shestakov.models.Task;
import shestakov.threads.WorkingThreadAdd;
import shestakov.threads.WorkingThreadUpdate;

public class StartUITest {

    @Test
    public void whenThreadsAddDataShouldBeCorrectSize() {
        Cache cache = new Cache();
        /*Task task1 = new Task("task");
        Task task2 = new Task("task");
        Task task3 = new Task("task");
        Task task4 = new Task("task");
        Task task5 = new Task("task");
        WorkingThreadAdd thread1 = new WorkingThreadAdd(cache,task1);
        WorkingThreadAdd thread2 = new WorkingThreadAdd(cache,task1);
        */

        for (int i=0; i<10; i++) {
            // add
            Task task1 = new Task("task");
            Task task2 = new Task("task");
            Task task3 = new Task("task");
            new WorkingThreadAdd(cache,task1).start();
            new WorkingThreadAdd(cache,task2).start();
            new WorkingThreadAdd(cache,task3).start();
            // sleep
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // update
            task1.setName("updated");
            new WorkingThreadUpdate(cache,task1).start();
            task2.setName("updated");
            new WorkingThreadUpdate(cache,task2).start();
            Task task4 = new Task("task");
            Task task5 = new Task("task");
            Task task6 = new Task("task");
            // add
            new WorkingThreadAdd(cache,task4).start();
            new WorkingThreadAdd(cache,task5).start();
            new WorkingThreadAdd(cache,task6).start();
            // update
            task4.setName("updated");
            new WorkingThreadUpdate(cache,task4).start();
            task5.setName("updated");
            new WorkingThreadUpdate(cache,task5).start();
        }
        // results
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int counterNew = 0;
        int counterUpdated = 0;
        for (Entity e : cache.getData()) {
            if ("task".equals(e.getName())) {
                counterNew++;
            } else {
                counterUpdated++;
            }
        }
        Assert.assertFalse(counterNew == counterUpdated); // optimistic locks occured
    }

}