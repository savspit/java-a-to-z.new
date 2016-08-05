package shestakov.start;

import shestakov.models.Cache;

import java.util.concurrent.ConcurrentHashMap;

public class StartUI {

    public static void main(String[] args) {
        Cache cache = new Cache();
        new StartUI().init(cache);
    }

    public void init(Cache cache) {

    }
}
