package shestakov.start;

import shestakov.services.KeysValidator;
import shestakov.threads.Worker;

import java.io.File;
import java.util.*;
import java.util.concurrent.*;

public class SearchApp {
    private KeysValidator kv;
    private Queue<File> fileTree = new PriorityQueue<>();
    private List<ScheduledFuture> results = new ArrayList<>();

    public SearchApp(KeysValidator kv) {
        this.kv = kv;
    }

    public void startSearching() throws ExecutionException, InterruptedException {
        fillFileTree();
        doSomeScheduledSearch();
    }

    public void doSomeScheduledSearch() throws ExecutionException, InterruptedException {
        ScheduledThreadPoolExecutor ex = new ScheduledThreadPoolExecutor(5);
        for (File currentFileOrDir : this.fileTree) {
            results.add(ex.schedule(new Worker(currentFileOrDir, this.kv.pattern), 1, TimeUnit.SECONDS));
        }
        for (ScheduledFuture result : this.results) {
            List<String> listOfMatches = (List<String>) result.get();
            if (listOfMatches.size() != 0 && this.kv.stopAtFirstResult) {
                System.out.println(listOfMatches.get(0));
                break;
            } else if (listOfMatches.size() != 0 && !this.kv.stopAtFirstResult) {
                for (String match : listOfMatches) {
                    System.out.println(match);
                }
            }
        }
        ex.shutdown();
    }

    public void fillFileTree() {
        File[] paths;
        try {
            paths = File.listRoots();
            for(File path:paths)
            {
                Collections.addAll(this.fileTree, path.listFiles());
            }
        } catch (Exception ignore){ /*NOP*/ }
    }
}
