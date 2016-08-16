package shestakov.start;

import shestakov.models.BomberMan;
import shestakov.services.DirectionEnum;

import java.util.concurrent.ExecutionException;

public class StartUI {

    public void init(World world) {

        BomberMan bomberMan = world.getHero();
        bomberMan.go(DirectionEnum.RIGHT);
        bomberMan.go(DirectionEnum.DOWN);
        bomberMan.putBomb();
        bomberMan.go(DirectionEnum.UP);
        bomberMan.go(DirectionEnum.LEFT);

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        World world = new World();
        world.createObjects();
        world.setHero(new BomberMan());
        world.releaseMonsters();
        new StartUI().init(world);
    }

}
