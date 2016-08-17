package shestakov.start;

import java.util.concurrent.ExecutionException;

/**
 * The type Start ui.
 */
public class StartUI {

    /**
     * Init.
     *
     * @param world the world
     */
    public void init(World world) {

    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        World world = new World();
        new StartUI().init(world);
    }

}
