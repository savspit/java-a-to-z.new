package ru.shestakov.models;

import ru.shestakov.services.OperationEnum;

public class Order {

    private Book book;
    private OperationEnum operation;
    private float price;
    private int volume;
    private int orderId;

    public Order(Book book, OperationEnum operation, float price, int volume, int orderId) {
        this.book = book;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
        this.orderId = orderId;
    }

    public float getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + orderId;
        return result;
    }

}
