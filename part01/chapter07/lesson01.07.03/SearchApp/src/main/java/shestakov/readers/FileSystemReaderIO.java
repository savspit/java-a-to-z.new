package shestakov.readers;

import shestakov.threads.TaskThreadIO;

import java.io.File;
import java.util.Queue;

public class FileSystemReaderIO extends FileSystemReader{
    private final String startPath;

    public FileSystemReaderIO(final String pattern, final String startPath, final int numberOfThreads, final boolean stoppedByFirstResult, final Queue foundedFiles) {
        super("fileSystemReaderIO-thread", pattern, numberOfThreads, stoppedByFirstResult, foundedFiles);
        this.startPath = startPath;
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
