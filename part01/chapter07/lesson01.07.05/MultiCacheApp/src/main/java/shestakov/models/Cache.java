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
     * @param newTask the new task
     */
    public void updateTask(Task newTask) {
        this.tasks.computeIfPresent(newTask.getId(), (k,v) -> checkVersionForUpdate(newTask, v));
    }

    /**
     * Check version for update task.
     *
     * @param newTask the new task
     * @param oldTask the old task
     * @return the task
     */
    private Task checkVersionForUpdate(Task newTask, Task oldTask) {
        if (newTask.getVersion() != oldTask.getVersion()) {
            log.info("optimistic lock occured");
            throw new RuntimeException("optimistic lock occured");
        } else {
            newTask.setNewVersion();
            return newTask;
        }
    }

    /**
     * Delete task.
     *
     * @param taskForDelete the task for delete
     */
    public void deleteTask(Task taskForDelete) {
        this.tasks.merge(taskForDelete.getId(), taskForDelete, (k,v) -> checkVersionForDelete(taskForDelete, v));
    }

    /**
     * Check version for delete task.
     *
     * @param taskForDelete the task for delete
     * @param taskInMap     the task in map
     * @return the task
     */
    private Task checkVersionForDelete(Task taskForDelete, Task taskInMap) {
        if (taskForDelete.getVersion() == taskInMap.getVersion()) {
            return null;
        } else {
            log.info("optimistic lock occured");
            throw new RuntimeException("optimistic lock occured");
        }
    }

}
