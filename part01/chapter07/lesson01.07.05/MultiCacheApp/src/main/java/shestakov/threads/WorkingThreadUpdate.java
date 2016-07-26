package shestakov.threads;

import shestakov.models.Cache;
import shestakov.models.Entity;
import shestakov.models.Task;

public class WorkingThreadUpdate extends WorkingThread{

    public WorkingThreadUpdate(Cache data, Task e, int id) {
        super(data, e, id);
    }

    @Override
    public void run() {
        this.data.update(e);
    }
}
