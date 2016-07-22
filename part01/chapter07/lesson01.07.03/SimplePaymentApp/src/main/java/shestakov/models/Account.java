package shestakov.models;

public class Account {
    private long id;
    private float amount;
    private User user;

    public Account(User user) {
        this.user = user;
        this.amount = 0;
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
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        return id == account.id &&
                user == account.user;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (int) this.id;
        result = 31 * result + (this.user != null ? this.user.hashCode() : 0);
        return result;
    }
}
