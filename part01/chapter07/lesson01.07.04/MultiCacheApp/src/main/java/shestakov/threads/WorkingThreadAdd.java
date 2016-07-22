package shestakov.threads;

import shestakov.models.Cache;
import shestakov.models.Entity;

public class WorkingThreadAdd extends WorkingThread{

    public WorkingThreadAdd(Cache data, Entity e) {
        super(data, e);
    }

    @Override
    public void run() {
        this.data.add(e);
    }
}
