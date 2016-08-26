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
     * Check version. If versions not equals - throws exception
     * @param newVersion
     * @param task
     * @return
     */
    private Task checkVersion(long newVersion, Task task) {
        if (newVersion != task.getVersion()) {
            log.info("optimistic lock occured");
            throw new RuntimeException("optimistic lock occured");
        } else {
            task.setVersion(newVersion);
            return task;
        }
    }

    /**
     * Delete task.
     *
     * @param id   the id
     * @param task the task
     */
    public void deleteTask(String id, Task task) {
        this.tasks.merge(id, task, (k,v) -> checkVersionForDelete(task.getVersion(), v));
    }

    /**
     * Check version. If versions equals - return null, because in this case merge() method will remove value
     * @param version
     * @param task
     * @return
     */
    private Task checkVersionForDelete(long version, Task task) {
        if (version == task.getVersion()) {
            return null;
        } else {
            log.info("optimistic lock occured");
            throw new RuntimeException("optimistic lock occured");
        }
    }

}
