package ru.shestakov.models;

import ru.shestakov.services.*;

import java.util.*;

public class Book {

    private String name;
    private TreeMap<KeySort,Order> sellOrders;
    private TreeMap<KeySort,Order> buyOrders;
    private TreeMap<KeySort,Order> sellOrdersSort;
    private TreeMap<KeySort,Order> buyOrdersSort;

    public Book(String name) {
        this.name = name;
    }

    public TreeMap<KeySort,Order> getSellOrders() {
        return this.sellOrdersSort;
    }

    public TreeMap<KeySort,Order> getBuyOrders() {
        return this.buyOrdersSort;
    }

    public void convert() {
        sellOrdersSort = new TreeMap<>(new OrderCompAsc());
        buyOrdersSort = new TreeMap<>(new OrderCompDesc());
        sellOrdersSort.putAll(sellOrders);
        buyOrdersSort.putAll(buyOrders);
    }

    public void add(OperationEnum operationValue, Float priceValue, int volumeValue, int orderIdValue) {
        if (this.sellOrders == null) { sellOrders = new TreeMap<>(new OrderKeySortCompAsc()); }
        if (this.buyOrders == null) { buyOrders = new TreeMap<>(new OrderKeySortCompDesc()); }
        if(operationValue == OperationEnum.SELL) {
            boolean founded = false;
            Iterator<Map.Entry<KeySort,Order>> itBuy = buyOrders.entrySet().iterator();
            while (itBuy.hasNext()) {
                Map.Entry<KeySort,Order> entry = itBuy.next();
                if (entry.getValue().getPrice() >= priceValue) {
                    founded = true;
                    int buyVolume = entry.getValue().getVolume();
                    int calcVolume = volumeValue - Math.min(volumeValue, buyVolume);
                    if (buyVolume <= volumeValue) {
                        itBuy.remove();
                        if (calcVolume > 0) {
                            sellOrders.put(new KeySort(orderIdValue,priceValue), new Order(this, operationValue, priceValue, calcVolume, orderIdValue));
                            volumeValue = volumeValue - buyVolume;
                        } else {
                            break;
                        }
                    } else {
                        entry.getValue().setVolume(buyVolume - Math.min(volumeValue, buyVolume));
                        break;
                    }
                }
            }
            if (!founded) {
                sellOrders.put(new KeySort(orderIdValue,priceValue), new Order(this, operationValue, priceValue, volumeValue, orderIdValue));
            }
        }
        if(operationValue == OperationEnum.BUY) {
            boolean founded = false;
            Iterator<Map.Entry<KeySort,Order>> itSell = sellOrders.entrySet().iterator();
            while (itSell.hasNext()) {
                Map.Entry<KeySort,Order> entry = itSell.next();
                if (entry.getValue().getPrice() <= priceValue) {
                    founded = true;
                    int sellVolume = entry.getValue().getVolume();
                    int calcVolume = volumeValue - Math.min(volumeValue, sellVolume);
                    if (sellVolume <= volumeValue) {
                        itSell.remove();
                        if (calcVolume > 0) {
                            buyOrders.put(new KeySort(orderIdValue,priceValue), new Order(this, operationValue, priceValue, calcVolume, orderIdValue));
                            volumeValue = volumeValue - sellVolume;
                        } else {
                            break;
                        }
                    } else {
                        entry.getValue().setVolume(sellVolume - Math.min(volumeValue, sellVolume));
                        break;
                    }
                }
            }
            if (!founded) {
                buyOrders.put(new KeySort(orderIdValue,priceValue), new Order(this, operationValue, priceValue, volumeValue, orderIdValue));
            }
        }
    }

    public void remove(int orderIdValue) {
        boolean founded = false;
        Iterator itSell = this.sellOrders.entrySet().iterator();
        while (itSell.hasNext()) {
            Map.Entry<KeySort, Order> entry = (Map.Entry<KeySort, Order>) itSell.next();
            if (entry.getKey().getOrderId() == orderIdValue) {
                itSell.remove();
                founded = true;
                break;
            }
        }
        if (founded == false) {
            Iterator itBuy = this.buyOrders.entrySet().iterator();
            while (itBuy.hasNext()) {
                Map.Entry<KeySort, Order> entry = (Map.Entry<KeySort, Order>) itBuy.next();
                if (entry.getKey().getOrderId() == orderIdValue) {
                    itBuy.remove();
                    break;
                }
            }
        }
    }

}
