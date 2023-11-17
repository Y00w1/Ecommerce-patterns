package model.order;


import model.cart.CartItem;

import java.util.ArrayList;
import java.util.List;
//TODO:
/*
public class Order {
    private List<OrderObserver> observers;
    private String orderStatus;
    private List<CartItem> cartItems;



    public Order(List<CartItem> cartItems) {
        observers = new ArrayList<>();
        orderStatus = "Pending";
        this.cartItems = cartItems;
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(orderStatus);
        }
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        notifyObservers();
    }

    public String getOrderStatus() {
        return orderStatus;
    }
    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
*/
