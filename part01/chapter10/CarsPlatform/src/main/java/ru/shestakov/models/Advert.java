package ru.shestakov.models;

import java.util.List;

/**
 * The type Advert.
 */
public class Advert {
    /**
     * id field.
     */
    private int id;
    /**
     * description field.
     */
    private String description;
    /**
     * user field.
     */
    private User user;
    /**
     * car field.
     */
    private Car car;
    /**
     * images field.
     */
    private List<Image> images;
    /**
     * sold field.
     */
    private boolean sold;

    /**
     * Instantiates a new Advert.
     */
    public Advert() {
    }

    /**
     * Instantiates a new Advert.
     *
     * @param id the id
     */
    public Advert(int id) {
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param name the name
     */
    public void setDescription(String name) {
        this.description = name;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets car.
     *
     * @return the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * Sets car.
     *
     * @param car the car
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Gets images.
     *
     * @return the images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * Sets images.
     *
     * @param images the images
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     * Is sold boolean.
     *
     * @return the boolean
     */
    public boolean isSold() {
        return sold;
    }

    /**
     * Sets sold.
     *
     * @param sold the sold
     */
    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return "Advert ["
                + "id=" + id
                + ", description='" + description + '\''
                + ", user=" + user
                + ", car=" + car
                + ", sold=" + sold
                + ']';
    }
}
