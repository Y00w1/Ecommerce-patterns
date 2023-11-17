import model.Product;
import model.User;
import model.cart.CartItem;
import model.payment.CreditCardPayment;
import model.payment.NequiPayment;
import service.CartService;
import service.ProductService;
import service.UserService;
import util.DataInitializer;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        CartService cartService = new CartService();
        ProductService productService = new ProductService();
        DataInitializer.initializeUserData(userService);
        DataInitializer.initializeProductData(productService);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    loginUser(scanner, userService, cartService, productService);
                    break;
                case 2:
                    registerUser(scanner, userService, cartService, productService);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    private static void loginUser(Scanner scanner, UserService userService, CartService cartService, ProductService productService) {
        System.out.print("Ingrese el email: ");
        String email = scanner.nextLine();

        System.out.print("ingrese la contraseña: ");
        String password = scanner.nextLine();

        Optional<User> authenticatedUser = userService.login(email, password);

        if (authenticatedUser.isPresent()) {
            cartService.setUser(authenticatedUser.get());
            System.out.println("Bienvenido, " + authenticatedUser.get().getUsername() + "!");
            showInteractiveMenu(scanner, cartService, productService);
        } else {
            System.out.println("Inicio de sesión fallido. Por favor, inténtelo de nuevo.");
        }
    }

    private static void registerUser(Scanner scanner, UserService userService, CartService cartService, ProductService productService) {
        System.out.print("Ingrese su nombre de usuario: ");
        String username = scanner.nextLine();

        System.out.print("Ingrese su email: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        User newUser = userService.createUser(username, email, password);
        cartService.setUser(newUser);
        System.out.println("Registro exitoso. Bienvenido, " + newUser.getUsername() + "!");
        showInteractiveMenu(scanner, cartService, productService);
    }


    private static void showInteractiveMenu(Scanner scanner, CartService cartService, ProductService productService) {
        while (true) {
            System.out.println("\n----- Menu interactivo -----");
            System.out.println("1. Crear producto");
            System.out.println("2. Mostrar lista de productos");
            System.out.println("3. Agregar producto al carrito");
            System.out.println("4. Ver carrito");
            System.out.println("5. Comprar carrito");
            System.out.println("6. Cerrar sesión");
            System.out.print("Selecciona una opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createProduct(scanner, productService);
                    break;
                case 2:
                    showProductList(productService, scanner);
                    break;
                case 3:
                    addProductToCart(scanner, cartService, productService);
                    break;
                case 4:
                    viewCart(cartService);
                    break;
                case 5:
                    purchase(scanner, cartService);
                    break;
                case 6:
                    cartService.setUser(null);
                    System.out.println("Cerraste sesión. Volviendo al menú principal.");
                    return;
                default:
                    System.out.println("Opción invalida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    private static void createProduct(Scanner scanner, ProductService productService) {
        System.out.print("Ingrese el nombre del producto: ");
        String productName = scanner.nextLine();

        System.out.print("Ingrese el precio del producto: ");
        double productPrice = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Ingrese el tipo del producto: ");
        String productType = scanner.nextLine();

        System.out.print("Ingrese la cantidad en stock del producto: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        Product newProduct = productService.createProduct(productName, productPrice, productType, stock);

        System.out.println("Producto creado con éxito:");
        System.out.println("ID: " + newProduct.getProductId());
        System.out.println("Nombre: " + newProduct.getProductName());
        System.out.println("Precio: $" + newProduct.getPrice());
        System.out.println("Tipo: " + newProduct.getType());
        System.out.println("Stock: " + newProduct.getStock());
    }

    private static void showProductList(ProductService productService, Scanner scanner) {
        System.out.println("Opciones de listado:");
        System.out.println("1. Mostrar todos los productos");
        System.out.println("2. Filtrar por nombre");
        System.out.println("3. Filtrar por tipo");
        System.out.println("4. Volver");
        System.out.print("Selecciona una opción: ");

        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                showAllProducts(productService);
                break;
            case 2:
                searchProductsByName(scanner, productService);
                break;
            case 3:
                searchProductsByType(scanner, productService);
                break;
            case 4:
                return;
            default:
                System.out.println("Opción inválida. Por favor, intente de nuevo");
        }
    }
    private static void showAllProducts(ProductService productService) {
        List<Product> productList = productService.getAllProducts();
        displayProducts(productList);
    }

    private static void searchProductsByName(Scanner scanner, ProductService productService) {
        System.out.print("Ingrese el nombre del producto a buscar: ");
        String productName = scanner.nextLine();

        List<Product> searchResult = productService.searchProductsByName(productName);
        displayProducts(searchResult);
    }

    private static void searchProductsByType(Scanner scanner, ProductService productService) {
        System.out.print("Ingrese el tipo de producto a buscar: ");
        String productType = scanner.nextLine();

        List<Product> searchResult = productService.searchProductsByType(productType);
        displayProducts(searchResult);
    }

    private static void displayProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No se encontraron productos.");
        } else {
            System.out.println("Lista de productos:");
            for (Product product : products) {
                System.out.println("ID: " + product.getProductId());
                System.out.println("Nomrbe: " + product.getProductName());
                System.out.println("Precio: $" + product.getPrice());
                System.out.println("Tipo: " + product.getType());
                System.out.println("Stock: " + product.getStock());
                System.out.println("-------------------");
            }
        }
    }

    private static void addProductToCart(Scanner scanner, CartService cartService, ProductService productService) {

        System.out.println("\n----- Lista de Productos -----");
        showAllProducts(productService);

        System.out.print("Ingrese el ID del producto que desea agregar al carrito: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese la cantidad que desea agregar al carrito: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();


        Optional<Product> selectedProduct = productService.getProductById(productId);

        if (selectedProduct.isPresent()) {
            cartService.addToCart(selectedProduct.get(), quantity);
            System.out.println("Producto agregado al carrito exitosamente.");
        } else {
            System.out.println("No se encontró el producto con el ID ingresado.");
        }
    }

    private static void viewCart(CartService cartService) {
        User currentUser = cartService.getUser();

        List<CartItem> cartItems = cartService.getCartItems();

        if (cartItems.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            System.out.println("----- Contenido del Carrito -----");
            System.out.println("Usuario: " + currentUser.getUsername());

            for (CartItem cartItem : cartItems) {
                Product product = cartItem.getProduct();
                int quantity = cartItem.getQuantity();
                double unitPrice = product.getPrice();
                double totalPrice = quantity * unitPrice;

                System.out.println("ID Producto: " + product.getProductId());
                System.out.println("Nombre: " + product.getProductName());
                System.out.println("Cantidad: " + quantity);
                System.out.println("Precio Unitario: $" + unitPrice);
                System.out.println("Precio Total: $" + totalPrice);
                System.out.println("-------------------");
            }

            double totalCartPrice = cartService.calculateTotalCartPrice();
            System.out.println("\nTotal del Carrito: $" + totalCartPrice);
        }
    }
    private static void purchase(Scanner scanner, CartService cartService) {
        selectPaymentMethod(scanner, cartService);
        Optional<List<CartItem>> purchasedItems = cartService.purchaseCart();
        //TODO: Create a order.
    }
    private static void selectPaymentMethod(Scanner scanner, CartService cartService) {
        System.out.println("Seleccione un método de pago:");
        System.out.println("1. Tarjeta de Crédito");
        System.out.println("2. Nequi");
        System.out.print("Elija una opción: ");

        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                cartService.setPaymentMethod(new CreditCardPayment());
                break;
            case 2:
                cartService.setPaymentMethod(new NequiPayment());
                break;
            default:
                System.out.println("Opción no válida. Seleccione un método de pago válido.");
                break;
        }
    }

    private static void showOrderStatus() {
       //TODO
    }
}