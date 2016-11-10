package ru.shestakov.models;

import java.util.List;

/**
 * The type Advert.
 */
public class Advert {
    private int id;
    private String description;
    private User user;
    private Car car;
    private List<Image> images;
    private boolean status;

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
     * Is status boolean.
     *
     * @return the boolean
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Advert [" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", car=" + car +
                ", status=" + status +
                ']';
    }
}
