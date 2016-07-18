package shestakov.models;

public class Informer implements Runnable {
    public final String text;

    public Informer(String text) {
        this.text = text;
    }

    public void run() {
        System.out.println(this.text);
    }
}
