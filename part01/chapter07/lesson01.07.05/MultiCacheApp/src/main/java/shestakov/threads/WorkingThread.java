package shestakov.threads;

import shestakov.models.Cache;
import shestakov.models.Entity;
import shestakov.models.Task;

public class WorkingThread extends Thread{
    public Cache data = new Cache();
    public Task e;
    public int id;

    public WorkingThread(Cache data, Task e, int id) {
        this.data = data;
        this.e = e;
        this.id = id;
    }

    @Override
    public void run() {

    }
}
