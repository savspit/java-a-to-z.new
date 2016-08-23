package shestakov.models;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * The type Cache.
 */
public class Cache {
    private static final Logger log = Logger.getLogger(Cache.class);
    private final ConcurrentHashMap<String,Task> tasks = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String,LinkedList<Message>> messages = new ConcurrentHashMap<>();

    /**
     * Add task.
     *
     * @param task the task
     */
    public void addTask(Task task) {
        this.tasks.put(task.getId(), task);
    }

    /**
     * Gets task.
     *
     * @param id the id
     * @return the task
     */
    public Task getTask(int id) {
        return this.tasks.get(id);
    }

    /**
     * Update task.
     *
     * @param id         the id
     * @param newVersion the new version
     */
    public void updateTask(String id, long newVersion) {
        this.tasks.computeIfPresent(id, (k,v) -> checkVersion(newVersion, v));
    }

    /**
     * Check version task.
     *
     * @param newVersion the new version
     * @param task       the task
     * @return the task
     */
    public Task checkVersion(long newVersion, Task task) {
        if (newVersion != task.getVersion()) {
            log.info("optimistic lock occured");
            return null;
        } else {
            task.setVersion(newVersion);
            return task;
        }
    }

}
