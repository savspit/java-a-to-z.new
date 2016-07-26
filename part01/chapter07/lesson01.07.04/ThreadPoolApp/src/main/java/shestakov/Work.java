package shestakov;

public class Work extends Thread{

    public void execute() {}

    @Override
    public void run() {
        execute();
    }
}
