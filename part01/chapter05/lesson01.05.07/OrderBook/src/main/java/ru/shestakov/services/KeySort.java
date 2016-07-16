package ru.shestakov.services;

public class KeySort {
    private int orderId;
    private float price;

    public KeySort(int orderId, float price) {
        this.orderId = orderId;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof KeySort)) return false;
        KeySort order = (KeySort) o;
        return orderId == order.orderId && price == order.price;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + orderId;
        result = 31 * result + Float.floatToIntBits(price);
        return result;
    }

    public int compareToAsc(KeySort ks) {
        int result;
        // asc
        result = Integer.compare(orderId, ks.orderId);
        if(result != 0) return result;
        // asc
        result = Float.compare(price, ks.price);
        if(result != 0) return result;
        return result;
    }

    public int compareToDesc(KeySort ks) {
        int result;
        // asc
        result = Integer.compare(orderId, ks.orderId);
        if(result != 0) return result;
        // desc
        result = Float.compare(ks.price, price);
        if(result != 0) return result;
        return result;
    }

    public int compareByPriceTo(KeySort ks) {
        int result;
        result = Float.compare(price, ks.price);
        if(result != 0) return result;
        return result;
    }

}
