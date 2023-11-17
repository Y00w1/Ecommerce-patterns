package model.cart;

import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCart {
    private static ShoppingCart instance;
    private List<CartItem> cartItems;
    private User user;

    private ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }
    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void clear() {
        cartItems.clear();
    }
}
