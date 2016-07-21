package shestakov.start;

import shestakov.readers.FileSystemReaderIO;
import shestakov.models.Key;
import shestakov.readers.FileSystemReaderNIO;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SearchApp {
    private Key[] keys;
    private Queue foundedFiles;

    public SearchApp() {
        this.foundedFiles = new ConcurrentLinkedQueue<String>();
    }

    public Queue getFoundedFiles() {
        return foundedFiles;
    }

    public void setKeys(Key[] keys) {
        this.keys = keys;
    }

    public void startSearching() {
        if (getValueOfKeyR().equals("io")) {
            FileSystemReaderIO reader = new FileSystemReaderIO(getValueOfKeyFio(), getValueOfKeyPio(), getValueOfKeyT(), getValueOfKeyS(), foundedFiles);
            reader.start();
        } else {
            FileSystemReaderNIO reader = new FileSystemReaderNIO(getValueOfKeyFnio(), getValueOfKeyPnio(), getValueOfKeyT(), getValueOfKeyS(), foundedFiles);
            reader.start();
        }
    }

    public String getValueOfKeyFnio() {
        return String.format("glob:*%s*",getValueOfKey("f"));
    }

    public String getValueOfKeyFio() {
        return getValueOfKey("f");
    }

    public String getValueOfKeyPio() {
        return getValueOfKey("p") == null ? "\\" : getValueOfKey("p");
    }

    public String getValueOfKeyPnio() {
        return getValueOfKey("p") == null ? "" : getValueOfKey("p");
    }

    public String getValueOfKeyR() {
        return getValueOfKey("r") == null ? "io" : getValueOfKey("r");
    }

    public int getValueOfKeyT() {
        return getValueOfKey("t") == null ? 0 : Integer.valueOf(getValueOfKey("t"));
    }

    public boolean getValueOfKeyS() {
        return getValueOfKey("s") == null ? false : true;
    }

    public String getValueOfKey(String key) {
        for (int i=0; i<keys.length; i++) {
            if(keys[i] != null && keys[i].getKey().equals(key)) {
                return keys[i].getValue();
            }
        }
        return null;
    }
}
