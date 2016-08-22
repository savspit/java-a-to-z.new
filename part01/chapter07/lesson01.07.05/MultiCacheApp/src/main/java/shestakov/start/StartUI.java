package shestakov.start;

import shestakov.models.Cache;
import shestakov.models.Task;

import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Start ui.
 */
public class StartUI {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Cache cache = new Cache();
        new StartUI().init(cache);
    }

    /**
     * Init.
     *
     * @param cache the cache
     */
    public void init(Cache cache) {

        Task task1 = new Task();
        Task task2 = new Task();
        Task task3 = new Task();
        cache.addTask(task1);
        cache.addTask(task2);
        cache.addTask(task3);

        System.out.println("task1 " + task1.getId() + "  ||  " + task1.getVersion());
        System.out.println("task2 " + task2.getId() + "  ||  " + task2.getVersion());
        System.out.println("task3 " + task3.getId() + "  ||  " + task3.getVersion());
        System.out.println("");

        task3.setVersion();
        cache.updateTask(task3);

        System.out.println("task1 " + task1.getId() + "  ||  " + task1.getVersion());
        System.out.println("task2 " + task2.getId() + "  ||  " + task2.getVersion());
        System.out.println("task3 " + task3.getId() + "  ||  " + task3.getVersion());

    }
}
