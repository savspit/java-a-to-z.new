package shestakov.threads;

import shestakov.models.Cache;
import shestakov.models.Entity;

public class WorkingThreadUpdate extends WorkingThread{

    public WorkingThreadUpdate(Cache data, Entity e) {
        super(data, e);
    }

    @Override
    public void run() {
        this.data.update(e);
    }
}
