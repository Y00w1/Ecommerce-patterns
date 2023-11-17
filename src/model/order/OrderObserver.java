package model.order;

import model.cart.CartItem;

import java.util.List;
//TODO
public interface OrderObserver {
    void update(String orderStatus);
}
