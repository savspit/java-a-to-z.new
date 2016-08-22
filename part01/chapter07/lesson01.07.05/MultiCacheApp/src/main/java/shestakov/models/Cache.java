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

    /*public void addMessage(Task task, Message message) {
        LinkedList<Message> current = this.messages.get(task.getIdAndVersion());
        if (current == null) {
            current = new LinkedList<Message>();
        }
        current.add(message);
        this.messages.put(task.getIdAndVersion(), current);
    }*/

    public Task getTask(int id) {
        return this.tasks.get(id);
    }

    public void updateTask(final Task task) {
        if (this.tasks.replace(task.getId(), task) != null) {
            task.setVersion();
        } else {
            log.info("optimistic lock occured. can`t change data");
        }
    }

}
