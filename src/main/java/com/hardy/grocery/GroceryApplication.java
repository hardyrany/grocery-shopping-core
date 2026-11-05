package com.hardy.grocery;

import com.hardy.grocery.cart.application.service.CartService;
import com.hardy.grocery.cart.domain.entity.Cart;
import com.hardy.grocery.cart.domain.entity.CartItem;
import com.hardy.grocery.cart.infrastructure.repository.CartRepository;
import com.hardy.grocery.infra.exception.GlobalExceptionHandler;
import com.hardy.grocery.infra.exception.InvalidQuantityException;
import com.hardy.grocery.infra.exception.ProductNotFoundException;
import com.hardy.grocery.infra.util.ConsoleUtils;
import com.hardy.grocery.product.application.service.ProductService;
import com.hardy.grocery.product.domain.entity.Product;
import com.hardy.grocery.product.infrastructure.repository.ProductRepository;

import java.util.List;

/**
 * Classe principal da aplicação Grocery Shopping.
 * Responsável por iniciar as dependências e exibir o menu interativo para o
 * utilizador.
 */
public class GroceryApplication {

    // 1. Instanciar os repositórios (Infraestrutura)
    private static final ProductRepository productRepository = new ProductRepository();
    private static final CartRepository cartRepository = new CartRepository();

    // 2. Instanciar os serviços (Aplicação) - Injetando as dependências manualmente
    private static final ProductService productService = new ProductService(productRepository);
    private static final CartService cartService = new CartService(cartRepository, productService);

    // Carrinho atual da sessão
    private static Cart currentCart = null;

    /**
     * Ponto de entrada da aplicação.
     * 
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        boolean running = true;

        System.out.println("=================================");
        System.out.println("   Welcome to Grocery Shopping   ");
        System.out.println("=================================");

        while (running) {
            printMenu();
            int choice = ConsoleUtils.readInt("Choose an option: ");
            ConsoleUtils.printBlankLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    listProducts();
                    break;
                case 3:
                    removeProduct();
                    break;
                case 4:
                    addStock();
                    break;
                case 5:
                    startCart();
                    break;
                case 6:
                    addToCart();
                    break;
                case 7:
                    viewCart();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting... Thank you for using Grocery Shopping!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            ConsoleUtils.printBlankLine();
        }
    }

    /**
     * Imprime o menu de opções na consola.
     */
    private static void printMenu() {
        System.out.println("--- MENU ---");
        System.out.println("1. Add Product");
        System.out.println("2. List Products");
        System.out.println("3. Remove Product");
        System.out.println("4. Add Stock to Product");
        System.out.println("-------------------");
        System.out.println("5. Start/Select Cart");
        System.out.println("6. Add Product to Cart");
        System.out.println("7. View Cart & Total");
        System.out.println("0. Exit");
    }

    // ==========================================
    // MÉTODOS DE PRODUTO
    // ==========================================

    /**
     * Lê os dados do utilizador e adiciona um novo produto.
     */
    private static void addProduct() {
        System.out.println("--- Add Product ---");
        try {
            String id = ConsoleUtils.readString("Enter Product ID (e.g., PROD-001): ");
            String name = ConsoleUtils.readString("Enter Product Name: ");
            double price = ConsoleUtils.readDouble("Enter Product Price: ");
            int stock = ConsoleUtils.readInt("Enter Initial Stock: ");

            productService.createProduct(id, name, price, stock);
            System.out.println("✔ Product added successfully!");
        } catch (InvalidQuantityException | IllegalArgumentException e) {
            GlobalExceptionHandler.handleGenericException(e);
        }
    }

    /**
     * Lista todos os produtos disponíveis no sistema.
     */
    private static void listProducts() {
        System.out.println("--- Product List ---");
        List<Product> products = productService.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products registered yet.");
            return;
        }

        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    /**
     * Remove um produto do sistema pelo ID.
     */
    private static void removeProduct() {
        System.out.println("--- Remove Product ---");
        try {
            String id = ConsoleUtils.readString("Enter Product ID to remove: ");
            productService.deleteProduct(id);
            System.out.println("✔ Product removed successfully!");
        } catch (ProductNotFoundException e) {
            GlobalExceptionHandler.handleProductNotFound(e);
        }
    }

    /**
     * Adiciona stock a um produto existente.
     */
    private static void addStock() {
        System.out.println("--- Add Stock ---");
        try {
            String id = ConsoleUtils.readString("Enter Product ID: ");
            int amount = ConsoleUtils.readInt("Enter amount to add: ");

            productService.addStock(id, amount);
            System.out.println("✔ Stock updated successfully!");
        } catch (ProductNotFoundException e) {
            GlobalExceptionHandler.handleProductNotFound(e);
        } catch (InvalidQuantityException e) {
            GlobalExceptionHandler.handleInvalidQuantity(e);
        }
    }

    // ==========================================
    // MÉTODOS DE CARRINHO
    // ==========================================

    /**
     * Cria ou seleciona um carrinho para a sessão atual.
     */
    private static void startCart() {
        System.out.println("--- Start/Select Cart ---");
        String cartId = ConsoleUtils.readString("Enter Cart ID (e.g., CART-01): ");

        currentCart = cartService.createOrGetCart(cartId);
        System.out.println("✔ Cart selected: " + currentCart.getId());
    }

    /**
     * Adiciona um produto ao carrinho atual.
     */
    private static void addToCart() {
        System.out.println("--- Add Product to Cart ---");

        if (currentCart == null) {
            System.out.println("⚠ Please start/select a cart first (Option 5).");
            return;
        }

        try {
            String productId = ConsoleUtils.readString("Enter Product ID: ");
            int quantity = ConsoleUtils.readInt("Enter Quantity: ");

            cartService.addItemToCart(currentCart.getId(), productId, quantity);
            System.out.println("✔ Product added to cart successfully!");

            // Atualiza a referência local com o carrinho atualizado
            currentCart = cartService.getCart(currentCart.getId());

        } catch (ProductNotFoundException e) {
            GlobalExceptionHandler.handleProductNotFound(e);
        } catch (InvalidQuantityException e) {
            GlobalExceptionHandler.handleInvalidQuantity(e);
        } catch (Exception e) {
            GlobalExceptionHandler.handleGenericException(e);
        }
    }

    /**
     * Exibe o conteúdo do carrinho atual e o valor total.
     */
    private static void viewCart() {
        System.out.println("--- View Cart ---");

        if (currentCart == null) {
            System.out.println("⚠ No cart selected.");
            return;
        }

        currentCart = cartService.getCart(currentCart.getId());

        if (currentCart.getItems().isEmpty()) {
            System.out.println("The cart is empty.");
            return;
        }

        System.out.println("Cart ID: " + currentCart.getId());
        System.out.println("Items:");

        for (CartItem item : currentCart.getItems()) {
            System.out.println(" - " + item.toString());
        }

        System.out.println("---------------------------------");
        System.out.printf("TOTAL TO PAY: $ %.2f\n", currentCart.getTotal());
    }
}