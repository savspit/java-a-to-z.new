package shestakov.readers;

import shestakov.threads.TaskThreadNIO;
import shestakov.threads.TaskThreadPool;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Queue;

public class FileFinder extends SimpleFileVisitor<Path> {
    private PathMatcher matcher;
    private final TaskThreadPool pool;
    private boolean stoppedByFirstResult;
    private final Queue foundedFiles;

    public FileFinder(String pattern, final TaskThreadPool pool, boolean stoppedByFirstResult, final Queue foundedFiles) {
        this.pool = pool;
        this.stoppedByFirstResult = stoppedByFirstResult;
        this.foundedFiles = foundedFiles;
        matcher = FileSystems.getDefault().getPathMatcher(pattern);
    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
        find(path);
        return FileVisitResult.CONTINUE;
    }

    private void find(Path path) {
        this.pool.add(new TaskThreadNIO(path.getFileName(), this.matcher, this.stoppedByFirstResult, this.foundedFiles));
    }

    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) {
        find(path);
        return FileVisitResult.CONTINUE;
    }
}

