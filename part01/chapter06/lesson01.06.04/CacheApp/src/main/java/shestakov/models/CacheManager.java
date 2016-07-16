package shestakov.models;

import shestakov.services.ReadTXTFile;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;

public class CacheManager {
    private HashMap<String,SoftReference<String>> cache;

    public SoftReference<String> getValue(String key) {
        return this.cache.get(key);
    }

    public void checkKey(String key) throws IOException {
        if (this.cache == null) { cache = new HashMap<>(); }
        if (!valueExists(key)) {
            loadDataFromFile(key);
        }
    }

    public void loadDataFromFile(String key) throws IOException {
        ReadTXTFile reader = new ReadTXTFile();
        this.cache.put(key, reader.getContent(key));
    }

    public boolean valueExists(String key) {
        return this.cache.containsKey(key) && this.cache.get(key) != null;
    }

    public void showValue(String key) {
        System.out.println(getValue(key).get());
    }

}
