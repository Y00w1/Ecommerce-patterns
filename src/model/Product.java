package model;

public class Product {
    private static int nextProductId = 1;
    private int productId;
    private String productName;
    private double price;
    private String type;
    private int stock;

    public Product(String productName, double price, String type, int stock) {
        this.productId = nextProductId++;
        this.productName = productName;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
