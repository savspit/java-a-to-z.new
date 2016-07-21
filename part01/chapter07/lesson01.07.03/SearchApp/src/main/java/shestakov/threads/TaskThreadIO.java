package shestakov.threads;

import java.util.Queue;

public class TaskThreadIO extends TaskThread{
    private final String fileName;
    private final String pattern;

    public TaskThreadIO(String fileName, String pattern, boolean stoppedByFirstResult, Queue foundedFiles) {
        super("parserIO-thread",stoppedByFirstResult,foundedFiles);
        this.fileName = fileName;
        this.pattern = pattern;
    }

    @Override
    public void run() {
        if (this.fileName.contains(pattern)) {
            addToFoundedFilesAndShow(this.fileName);
            if (this.stoppedByFirstResult) {
                System.exit(0);
            }
        }
    }
}
