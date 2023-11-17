package service;

import model.Product;
import model.User;
import model.cart.CartItem;
import model.cart.ShoppingCart;
import model.payment.PaymentStrategy;

import java.util.List;
import java.util.Optional;

public class CartService {
    private ShoppingCart shoppingCart;
    private PaymentStrategy paymentMethod;

    public CartService() {
        shoppingCart = ShoppingCart.getInstance();
    }

    public void setUser(User User) {
        shoppingCart.setUser(User);
    }

    public void setPaymentMethod(PaymentStrategy paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void addToCart(Product product, int quantity) {
        if (product.getStock() >= quantity) {
            Optional<CartItem> cartItem = shoppingCart.getCartItems().stream()
                    .filter(item -> item.getProduct().getProductId() == product.getProductId())
                    .findFirst();
            if (cartItem.isPresent()) {
                cartItem.get().setQuantity(cartItem.get().getQuantity() + quantity);
            } else {
                CartItem newCartItem = new CartItem(product, quantity);
                shoppingCart.addCartItem(newCartItem);
            }
        }else {
            System.out.println("No hay suficiente stock para agregar al carrito.");
        }
    }

    public List<CartItem> getCartItems() {
        return shoppingCart.getCartItems();
    }

    public User getUser() {
        return shoppingCart.getUser();
    }

    public double calculateTotalCartPrice() {
        return shoppingCart.getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
    public Optional<List<CartItem>> purchaseCart() {
        if (shoppingCart.getCartItems().isEmpty()) {
            System.out.println("El carrito está vacío. No hay productos para comprar.");
            return Optional.empty();
        }
        if (paymentMethod == null) {
            System.out.println("Seleccione un método de pago antes de realizar la compra.");
            return Optional.empty();
        }

        double totalCartPrice = calculateTotalCartPrice();
        paymentMethod.pay(totalCartPrice);

        List<CartItem> purchasedItems = shoppingCart.getCartItems();
        for (CartItem cartItem : purchasedItems) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();
            product.setStock(product.getStock() - quantity);
        }

        System.out.println("Compra realizada con éxito.");
        shoppingCart.clear();
        return Optional.of(purchasedItems);
    }

}
