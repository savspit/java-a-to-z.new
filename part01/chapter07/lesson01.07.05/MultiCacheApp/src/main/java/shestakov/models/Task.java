package shestakov.models;

import java.util.Random;

public class Task {
    private String id;
    private long version;
    private String name;
    private static final Random RN = new Random();

    public Task() {
        setVersion();
        setId();
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

    public void setVersion() {
        this.version = System.nanoTime();
    }

    public void setId() {
        this.id = String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    public void setName(String name) {
        this.name = name;
    }
}
