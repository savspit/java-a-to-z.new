package shestakov.threads;

import shestakov.models.Cache;
import shestakov.models.Entity;

public class WorkingThread extends Thread{
    public Cache data = new Cache();
    public Entity e;

    public WorkingThread(Cache data, Entity e) {
        this.data = data;
        this.e = e;
    }

    @Override
    public void run() {

    }
}
