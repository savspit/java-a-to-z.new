package shestakov.start;

import shestakov.readers.FileSystemReaderIO;
import shestakov.models.Key;
import shestakov.readers.FileSystemReaderNIO;
import shestakov.services.KeysValidator;
import shestakov.services.ReaderEnum;

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
        if (kv.reader == ReaderEnum.IO) {
            FileSystemReaderIO reader = new FileSystemReaderIO(kv.pattern, kv.path, kv.numberOfThreads, kv.stopAtFirstResult, foundedFiles);
            reader.start();
        } else {
            FileSystemReaderNIO reader = new FileSystemReaderNIO(kv.pattern, kv.path, kv.numberOfThreads, kv.stopAtFirstResult, foundedFiles);
            reader.start();
        }
    }
}
