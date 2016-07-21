package shestakov.readers;

import java.io.IOException;
import java.nio.file.*;
import java.util.Queue;

public class FileSystemReaderNIO extends FileSystemReader {
    private final Path startPath;

    public FileSystemReaderNIO(final String pattern, final String startPath, final int numberOfThreads, final boolean stoppedByFirstResult, final Queue foundedFiles) {
        super("fileSystemReaderNIO-thread", pattern, numberOfThreads, stoppedByFirstResult, foundedFiles);
        this.startPath = Paths.get(startPath);
    }

    @Override
    public void run() {
        try {
            Files.walkFileTree(this.startPath, new FileFinder(this.pattern, this.pool, this.stoppedByFirstResult, this.foundedFiles));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.pool.setNeedToBeStoped();
    }
}
