package shestakov.threads;

import java.io.File;
import java.util.concurrent.Callable;

public class Worker implements Callable {
    File currentFileOrDir;

    public Worker(File currentFileOrDir) {
        this.currentFileOrDir = currentFileOrDir;
    }

    @Override
    public Object call() throws Exception {

        //TODO some serch & matcher logic here

        return null;
    }
}
