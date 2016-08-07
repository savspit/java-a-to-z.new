package shestakov.threads;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * The type Worker.
 */
public class Worker implements Callable {
    private static final Logger log = Logger.getLogger(Worker.class);
    /**
     * The Current file or dir.
     */
    File currentFileOrDir;
    /**
     * The Pattern.
     */
    String pattern;
    /**
     * The Result.
     */
    List<String> result = new ArrayList<>();

    /**
     * Instantiates a new Worker.
     *
     * @param currentFileOrDir the current file or dir
     * @param pattern          the pattern
     */
    public Worker(File currentFileOrDir, String pattern) {
        this.currentFileOrDir = currentFileOrDir;
        this.pattern = pattern;
    }

    @Override
    public Object call() throws Exception {
        if (currentFileOrDir.canRead() && currentFileOrDir.canWrite()) {
            try {
                if (currentFileOrDir.isFile()) {
                    if (currentFileOrDir.getPath().contains(pattern)) {
                        result.add(currentFileOrDir.getPath());
                    }
                } else if (currentFileOrDir.isDirectory() && currentFileOrDir.list() != null) {
                    readDirectoryRec(currentFileOrDir);
                }
            } catch (SecurityException se) {
                log.error("some kind of security exception occured", se);
            }
        }
        return result;
    }

    /**
     * Read directory rec boolean.
     *
     * @param currentDir the current dir
     * @return the boolean
     */
    public boolean readDirectoryRec(File currentDir) {
        for (File currentFile : currentDir.listFiles()) {
            if (currentFile.isFile()) {
                if (currentFile.getPath().contains(pattern)) {
                    result.add(currentFile.getPath());
                }
            } else if (currentFile.isDirectory() && currentFile.list() != null) {
                readDirectoryRec(currentFile);
                continue;
            }
        }
        return false;
    }
}
