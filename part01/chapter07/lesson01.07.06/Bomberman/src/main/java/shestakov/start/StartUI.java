package shestakov.start;

public class StartUI {

    public static void main(String[] args) {
        World bb = new World();
        bb.fill();
        new StartUI().init(bb);
    }

    public void init(World bb) {

    }

}
