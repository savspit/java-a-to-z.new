package shestakov.threads;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Worker implements Callable {
    File currentFileOrDir;
    String pattern;
    List<String> result = new ArrayList<>();

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
            } catch (SecurityException ignore) {/*NOP*/ }
        }
        return result;
    }

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
