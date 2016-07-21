package shestakov.threads;

import java.util.Queue;

public abstract class TaskThread extends Thread{
    public final boolean stoppedByFirstResult;
    public final Queue foundedFiles;

    public TaskThread(String name, boolean stoppedByFirstResult, Queue foundedFiles) {
        super(name);
        this.stoppedByFirstResult = stoppedByFirstResult;
        this.foundedFiles = foundedFiles;
    }

    @Override
    public void run() {
        super.run();
    }

    public void addToFoundedFilesAndShow(String fileName) {
        synchronized (this.foundedFiles) {
            this.foundedFiles.add(fileName);
            System.out.println(fileName);
        }
    }
}
