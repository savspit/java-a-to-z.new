package shestakov.models;

import java.util.Random;

public class Task {
    private String id;
    private long version;
    private String name;
    private static final Random RN = new Random();

    public Task() {
        this.version = setVersion();
        this.id = setId();
    }

    public Task(String name) {
        this();
        this.name = name;
    }

    public String getIdAndVersion() {
        return this.id + String.valueOf(this.version);
    }

    public String getName() {
        return name;
    }

    public long setVersion() {
        return System.nanoTime();
    }

    public String setId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    public void setName(String name) {
        this.name = name;
    }
}
