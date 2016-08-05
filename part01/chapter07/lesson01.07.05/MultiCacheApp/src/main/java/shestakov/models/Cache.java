package shestakov.models;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private static final Logger log = Logger.getLogger(Cache.class);
    private final ConcurrentHashMap<String,Task> tasks = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String,LinkedList<Message>> messages = new ConcurrentHashMap<>();

    public void addTask(Task task) {
        this.tasks.put(task.getIdAndVersion(), task);
    }

    public Task getTask(int id) {
        return this.tasks.get(id);
    }

    public void updateTask(Task task) {
        if (this.tasks.replace(task.getIdAndVersion(), task) == null) {
            log.info("optimistic lock occured. can`t change data");
        }
    }
}
