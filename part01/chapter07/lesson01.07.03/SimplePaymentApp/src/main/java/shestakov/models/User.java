package shestakov.models;

public class User {
    private long id;
    private String name;
    private float amount;

    public User(String name, float amount) {
        this.name = name;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public float getAmount() {
        return amount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return id == user.id;

    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (int) this.id;
        return result;
    }
}
