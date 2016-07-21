package shestakov.threads;

import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.Queue;

public class TaskThreadNIO extends TaskThread{
    private final Path fileName;
    private final PathMatcher matcher;

    public TaskThreadNIO(Path fileName, PathMatcher matcher, boolean stoppedByFirstResult, Queue foundedFiles) {
        super("parserNIO-thread",stoppedByFirstResult,foundedFiles);
        this.fileName = fileName;
        this.matcher = matcher;
    }

    @Override
    public void run() {
        if (this.matcher.matches(fileName)) {
            addToFoundedFilesAndShow(fileName.toString());
            if (this.stoppedByFirstResult) {
                System.exit(0);
            }
        }
    }
}
