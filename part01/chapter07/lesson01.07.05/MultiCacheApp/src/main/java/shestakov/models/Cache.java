package shestakov.models;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final ConcurrentHashMap<Long,Task> tasks = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long,LinkedList<Message>> messages = new ConcurrentHashMap<>();

    public void addTask(Task task) {
        this.tasks.put(task.getVersion(), task);
    }

    public void addMessage(Task task, Message message) {
        synchronized (this.messages) {
            LinkedList<Message> mess = getMessages(task);
            if (mess == null) {
                mess = new LinkedList<>();
            }
            mess.add(message);
            this.messages.put(task.getVersion(), mess);
        }
    }

    public Task getTask(int id) {
        return this.tasks.get(id);
    }

    public LinkedList<Message> getMessages(Task task) {
        return this.messages.get(task.getVersion());
    }

    public boolean optimisticLockExistsInTasks(Task e) {
        boolean result = true;
        for (int i=0; i<this.tasks.size(); i++) {
            if (e.equals(this.tasks.get(i))) {
                if (this.tasks.get(i).getVersion() == e.getVersion()) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public void updateTask(Task task) {
        synchronized (this.tasks) {
            if (!optimisticLockExistsInTasks(task)) {
                this.tasks.replace(task.getVersion(), task);
            } else {
                System.out.println("optimistic lock occured. can`t change data");
            }
        }
    }
}
