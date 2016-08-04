package shestakov.start;

import org.apache.log4j.Logger;
import shestakov.services.KeysValidator;
import shestakov.threads.Worker;

import java.io.File;
import java.util.*;
import java.util.concurrent.*;

public class SearchApp {
    private static final Logger log = Logger.getLogger(SearchApp.class);
    private KeysValidator kv;
    private Queue<File> fileTree = new PriorityQueue<>();
    private List<ScheduledFuture> results = new ArrayList<>();
    private static final int DEFAULT_CAPACITY = Runtime.getRuntime().availableProcessors();

    public SearchApp(KeysValidator kv) {
        this.kv = kv;
    }

    public void startSearching() throws ExecutionException, InterruptedException {
        fillFileTree();
        doSomeScheduledSearch();
    }

    public void doSomeScheduledSearch() throws ExecutionException, InterruptedException {
        ScheduledThreadPoolExecutor ex = new ScheduledThreadPoolExecutor(DEFAULT_CAPACITY);
        for (File currentFileOrDir : this.fileTree) {
            results.add(ex.schedule(new Worker(currentFileOrDir, this.kv.pattern), 1, TimeUnit.SECONDS));
        }
        for (ScheduledFuture result : this.results) {
            List<String> listOfMatches = (List<String>) result.get();
            if (listOfMatches.size() != 0 && this.kv.stopAtFirstResult) {
                log.info(listOfMatches.get(0));
                break;
            } else if (listOfMatches.size() != 0 && !this.kv.stopAtFirstResult) {
                for (String match : listOfMatches) {
                    log.info(match);
                }
            }
        }
        ex.shutdown();
    }

    public void fillFileTree() {
        File[] paths;
        paths = File.listRoots();
        for(File path : paths) {
            if (path.canRead()) {
                Collections.addAll(this.fileTree, path.listFiles());
            }
        }
    }
}
