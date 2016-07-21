package shestakov.models;

public class Key {
    private String key;
    private String value;

    public Key(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
