package service;

import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductService {
    private List<Product> productList;

    public ProductService() {
        this.productList = new ArrayList<>();
    }

    public Product createProduct(String productName, double price, String type, int stock) {
        Product product = new Product(productName, price, type, stock);
        productList.add(product);
        return product;
    }
    public List<Product> getAllProducts() {
        return productList;
    }
    public List<Product> searchProductsByName(String productName) {
        return productList.stream()
                .filter(product -> product.getProductName().equalsIgnoreCase(productName))
                .collect(Collectors.toList());
    }
    public List<Product> searchProductsByType(String type) {
        return productList.stream()
                .filter(product -> product.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public Optional<Product> getProductById(int id) {
        return productList.stream()
                .filter(product -> product.getProductId() == id)
                .findFirst();
    }
}
