package shestakov.start;

import java.util.concurrent.ExecutionException;

public class StartUI {

    public void init(World world) {


    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        World world = new World();
        new StartUI().init(world);
    }

}
