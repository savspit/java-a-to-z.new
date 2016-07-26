package shestakov.threads;

import shestakov.models.Cache;
import shestakov.models.Entity;
import shestakov.models.Task;

public class WorkingThreadAdd extends WorkingThread{

    public WorkingThreadAdd(Cache data, Task e, int id) {
        super(data, e, id);
    }

    @Override
    public void run() {
        this.data.add(id, e);
    }
}
