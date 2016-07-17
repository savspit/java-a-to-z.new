package shestakov.models;

public class Buffer {
    private String name;
    private int count;

    public Buffer(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public void show() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(count);
        System.out.println(sb.toString());
    }
}
