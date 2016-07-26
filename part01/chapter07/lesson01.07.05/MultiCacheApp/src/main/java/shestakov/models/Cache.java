package shestakov.models;

import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final ConcurrentHashMap<Integer,Task> tasks = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Task,Message> messages = new ConcurrentHashMap<>();

    public void add(int id, Task e) {
        this.tasks.put(id,e);
    }

    public Entity get(int id) {
        return this.tasks.get(id);
    }

    public ConcurrentHashMap<Integer,Task> getData() {
        return this.tasks;
    }

    public void set(int id, Task e) {
        this.tasks.put(id,e);
    }

    public int entityExists(Task e) {
        int result = -1;
        for (int i=0; i<this.tasks.size(); i++) {
            if (e.equals(this.tasks.get(i))) {
                if (this.tasks.get(i).getVersion() == e.getVersion()) {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }

    public void update(Task e) {
        synchronized (this.tasks) {
            int index = entityExists(e);
            if (index != -1) {
                set(index, e);
            } else {
                System.out.println("optimistic lock uccured. can`t change data");
            }
        }
    }

    public void remove(int id) {
        this.tasks.remove(id);
    }

    public int size() {
        return this.tasks.size();
    }

}
