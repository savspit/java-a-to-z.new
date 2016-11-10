package ru.shestakov.models;

/**
 * The type Car.
 */
public class Car {
    private int id;
    private String name;
    private Engine engine;
    private Gearbox gearbox;
    private Transmission transmission;

    /**
     * Instantiates a new Car.
     */
    public Car() {
    }

    /**
     * Instantiates a new Car.
     *
     * @param id the id
     */
    public Car(int id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets engine.
     *
     * @return the engine
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * Sets engine.
     *
     * @param engine the engine
     */
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    /**
     * Gets gearbox.
     *
     * @return the gearbox
     */
    public Gearbox getGearbox() {
        return gearbox;
    }

    /**
     * Sets gearbox.
     *
     * @param gearbox the gearbox
     */
    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    /**
     * Gets transmission.
     *
     * @return the transmission
     */
    public Transmission getTransmission() {
        return transmission;
    }

    /**
     * Sets transmission.
     *
     * @param transmission the transmission
     */
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public String toString() {
        return "Car [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", engine=" + engine +
                ", gearbox=" + gearbox +
                ", transmission=" + transmission +
                ']';
    }
}
