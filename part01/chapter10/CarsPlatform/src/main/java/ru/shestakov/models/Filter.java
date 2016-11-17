package ru.shestakov.models;

/**
 * The type Filter.
 */
public class Filter {
    private String[] sold;
    private String[] transmission;
    private String[] engine;
    private String[] gearbox;

    /**
     * Instantiates a new Filter.
     */
    public Filter() {
    }

    /**
     * Get sold string [ ].
     *
     * @return the string [ ]
     */
    public String[] getSold() {
        return sold;
    }

    /**
     * Sets sold.
     *
     * @param sold the sold
     */
    public void setSold(String[] sold) {
        this.sold = sold;
    }

    /**
     * Get transmission string [ ].
     *
     * @return the string [ ]
     */
    public String[] getTransmission() {
        return transmission;
    }

    /**
     * Sets transmission.
     *
     * @param transmission the transmission
     */
    public void setTransmission(String[] transmission) {
        this.transmission = transmission;
    }

    /**
     * Get engine string [ ].
     *
     * @return the string [ ]
     */
    public String[] getEngine() {
        return engine;
    }

    /**
     * Sets engine.
     *
     * @param engine the engine
     */
    public void setEngine(String[] engine) {
        this.engine = engine;
    }

    /**
     * Get gearbox string [ ].
     *
     * @return the string [ ]
     */
    public String[] getGearbox() {
        return gearbox;
    }

    /**
     * Sets gearbox.
     *
     * @param gearbox the gearbox
     */
    public void setGearbox(String[] gearbox) {
        this.gearbox = gearbox;
    }
}
