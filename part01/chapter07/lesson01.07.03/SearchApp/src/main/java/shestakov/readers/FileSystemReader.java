package shestakov.readers;

import shestakov.threads.TaskThreadPool;

import java.util.Queue;

public abstract class FileSystemReader extends Thread{
    public final TaskThreadPool pool;
    public final String pattern;
    public final int numberOfThreads;
    public final boolean stoppedByFirstResult;
    public final Queue foundedFiles;

    public FileSystemReader(String name, String pattern, int numberOfThreads, boolean stoppedByFirstResult, Queue foundedFiles) {
        super(name);
        this.pattern = pattern;
        this.numberOfThreads = numberOfThreads;
        this.stoppedByFirstResult = stoppedByFirstResult;
        this.foundedFiles = foundedFiles;
        this.pool = new TaskThreadPool(this.numberOfThreads);
        this.pool.start();
    }

    @Override
    public void run() {
        super.run();
    }
}
