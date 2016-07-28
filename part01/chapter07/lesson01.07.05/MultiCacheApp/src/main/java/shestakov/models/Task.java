package shestakov.models;

public class Task {
    private long version;
    private String name;

    public Task() {
        this.version = setVersion();
    }

    public Task(String name) {
        this();
        this.name = name;
    }

    public long getVersion() {
        return this.version;
    }

    public String getName() {
        return name;
    }

    public long setVersion() {
        return System.nanoTime();
    }

    public void setName(String name) {
        this.name = name;
    }
}
