package shestakov.models;

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cache {
    private final CopyOnWriteArrayList<Entity> data = new CopyOnWriteArrayList<>();

    public void add(Entity e) {
        synchronized (e) {
            this.data.add(e);
        }
    }

    public Entity get(int index) {
        return this.data.get(index);
    }

    public CopyOnWriteArrayList<Entity> getData() {
        return this.data;
    }

    public void set(int index, Entity e) {
        synchronized (e) {
            this.data.set(index, e);
        }
    }

    public int entityExists(Entity e) {
        int result = -1;
        for (int i=0; i<this.data.size(); i++) {
            if (e.equals(this.data.get(i))) {
                if (this.data.get(i).getVersion() == e.getVersion()) {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }

    public void update(Entity e) {
        int index = entityExists(e);
        if(index != -1) {
            set(index,e);
        } else {
            System.out.println("optimistic lock uccured. can`t change data");
        }
    }

    public void remove(Entity e) {
        this.data.remove(e);
    }

    public int size() {
        return this.data.size();
    }

}
