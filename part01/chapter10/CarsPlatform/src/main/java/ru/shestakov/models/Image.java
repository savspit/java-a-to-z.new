package ru.shestakov.models;

/**
 * The type Image.
 */
public class Image {
    private int id;
    private String path;
    private Advert advert;

    /**
     * Instantiates a new Image.
     */
    public Image() {
    }

    /**
     * Instantiates a new Image.
     *
     * @param id the id
     */
    public Image(int id) {
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
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets path.
     *
     * @param path the path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets advert.
     *
     * @return the advert
     */
    public Advert getAdvert() {
        return advert;
    }

    /**
     * Sets advert.
     *
     * @param advert the advert
     */
    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    @Override
    public String toString() {
        return "Image [" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", advert=" + advert +
                ']';
    }
}
