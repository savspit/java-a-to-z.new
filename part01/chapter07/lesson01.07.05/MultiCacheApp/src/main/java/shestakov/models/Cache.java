package shestakov.models;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {
    private static final Logger log = Logger.getLogger(Cache.class);
    private final ConcurrentHashMap<String,Task> tasks = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String,LinkedList<Message>> messages = new ConcurrentHashMap<>();

    public void addTask(Task task) {
        this.tasks.put(task.getId(), task);
    }

    public Task getTask(int id) {
        return this.tasks.get(id);
    }

    public void updateTask(Task newTask) {
        this.tasks.computeIfPresent(newTask.getId(), (k,v) -> checkVersionForUpdate(newTask, v));
    }

    private Task checkVersionForUpdate(Task newTask, Task oldTask) {
        if (newTask.getVersion() != oldTask.getVersion()) {
            log.info("optimistic lock occured");
            throw new RuntimeException("optimistic lock occured");
        } else {
            newTask.setNewVersion();
            return newTask;
        }
    }

    public void deleteTask(Task taskForDelete) {
        this.tasks.merge(taskForDelete.getId(), taskForDelete, (k,v) -> checkVersionForDelete(taskForDelete, v));
    }

    private Task checkVersionForDelete(Task taskForDelete, Task taskInMap) {
        if (taskForDelete.getVersion() == taskInMap.getVersion()) {
            return null;
        } else {
            log.info("optimistic lock occured");
            throw new RuntimeException("optimistic lock occured");
        }
    }

}
