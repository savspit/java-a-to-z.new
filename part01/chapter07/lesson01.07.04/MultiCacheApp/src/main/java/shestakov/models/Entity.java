package shestakov.models;

public abstract class Entity {
    private long version;
    private String name;

    public Entity(String name) {
        this.name = name;
    }

    public Entity() {
        this.version = setVersion();
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
