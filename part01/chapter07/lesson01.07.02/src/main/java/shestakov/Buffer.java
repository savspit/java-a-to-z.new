package shestakov;

public class Buffer {

    public void show(String value1, int value2) {
        System.out.println(String.format("%s: %d", value1, value2));
    }

    public void show(String value1, int value2, int value3) {
        System.out.println(String.format("%s: %d %d", value1, value2, value3));
    }
}
