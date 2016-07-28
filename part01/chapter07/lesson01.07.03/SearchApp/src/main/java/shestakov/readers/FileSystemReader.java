package shestakov.readers;

import shestakov.threads.TaskThreadIO;
import shestakov.threads.TaskThreadPool;

import java.io.File;
import java.util.Queue;

public class FileSystemReader extends Thread {
    public final TaskThreadPool pool;
    public final String pattern;
    public final int numberOfThreads;
    public final boolean stoppedByFirstResult;
    public final Queue foundedFiles;
    private final String startPath;

    public FileSystemReader(String pattern, String startPath, int numberOfThreads, boolean stoppedByFirstResult, Queue foundedFiles) {
        super("fileSystemReaderIO-thread");
        this.pattern = pattern;
        this.startPath = startPath;
        this.numberOfThreads = numberOfThreads;
        this.stoppedByFirstResult = stoppedByFirstResult;
        this.foundedFiles = foundedFiles;
        this.pool = new TaskThreadPool(this.numberOfThreads);
        this.pool.start();
    }

    @Override
    public void run() {
        walkin(new File(this.startPath));
        this.pool.interrupt();
    }

    public void walkin(File dir) {
        File[] listFile = dir.listFiles();
        if (listFile != null) {
            for (int i=0; i<listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    walkin(listFile[i]);
                } else {
                    this.pool.add(new TaskThreadIO(listFile[i].getName(), this.pattern, this.stoppedByFirstResult, this.foundedFiles));
                }
            }
        }
        this.pool.setNeedToBeStoped();
    }
}
