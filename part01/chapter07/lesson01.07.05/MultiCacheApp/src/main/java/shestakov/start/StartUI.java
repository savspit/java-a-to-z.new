package shestakov.start;

import shestakov.models.Cache;
import shestakov.models.Task;

import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Start ui.
 */
public class StartUI {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Cache cache = new Cache();
        new StartUI().init(cache);
    }

    /**
     * Init.
     *
     * @param cache the cache
     */
    public void init(Cache cache) {

    }
}
