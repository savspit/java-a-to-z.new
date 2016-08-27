package shestakov.models;

import java.util.Random;

/**
 * The type Task.
 */
public class Task {
    private String id;
    private long version;
    private String name;
    private static final Random RN = new Random();

    /**
     * Instantiates a new Task.
     */
    public Task() {
        this.version = System.nanoTime();
        setId();
    }

    /**
     * Instantiates a new Task.
     *
     * @param name the name
     */
    public Task(String name) {
        this();
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public long getVersion() {
        return this.version;
    }

    public long getNewVersion() {
        return System.nanoTime();
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets version.
     */
    public void setNewVersion() {
        this.version++;
    }

    /**
     * Sets id.
     */
    public void setId() {
        this.id = String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
