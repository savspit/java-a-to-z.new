package shestakov.start;

import shestakov.readers.FileSystemReader;
import shestakov.services.KeysValidator;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SearchApp {
    private Queue foundedFiles;
    private KeysValidator kv;

    public SearchApp(KeysValidator kv) {
        this.kv = kv;
        this.foundedFiles = new ConcurrentLinkedQueue<>();
    }

    public Queue getFoundedFiles() {
        return foundedFiles;
    }

    public void startSearching() {
        FileSystemReader reader = new FileSystemReader(kv.pattern, kv.path, kv.numberOfThreads, kv.stopAtFirstResult, foundedFiles);
        reader.start();
    }
}
