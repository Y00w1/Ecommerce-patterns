package util;

import service.ProductService;
import service.UserService;

public class DataInitializer {
    public static void initializeProductData(ProductService productService) {
        productService.createProduct("Product 1", 19.99, "Type1", 3);
        productService.createProduct("Product 2", 29.99, "Type2", 4);
    }

    public static void initializeUserData(UserService userService) {
        userService.createUser("User 1", "mail@example.com", "password");
    }

}
