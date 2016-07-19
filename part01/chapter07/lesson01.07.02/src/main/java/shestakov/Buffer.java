package shestakov;

public class Buffer {
    /*private String name;
    private int value;
    private int value2;

    public Buffer(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public Buffer(String name, int value, ) {
        this.name = name;
        this.value = value;
    }*/

    public void show(String value1, int value2) {
        System.out.println(String.format("%s: %d", value1, value2));
    }

    public void show(String value1, int value2, int value3) {
        System.out.println(String.format("%s: %d %d", value1, value2, value3));
    }
}
