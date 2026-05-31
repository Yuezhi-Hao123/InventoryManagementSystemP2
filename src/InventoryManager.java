/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 123
 */
import model.Product;
import java.util.ArrayList;

public class InventoryManager {
    private ArrayList<Product> products;

    public InventoryManager() {
        products = new ArrayList<>();
    }

    public void addProduct(Product p) {
        if (searchProduct(p.getId()) != null) {
            System.out.println("Product ID already exists.");
            return;
        }

        products.add(p);
        System.out.println("Product added successfully.");
    }

    public void deleteProduct(String id) {
        Product product = searchProduct(id);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        products.remove(product);
        System.out.println("Product deleted successfully.");
    }

    public void viewAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products in inventory.");
            return;
        }

        System.out.println("\n--- Product List ---");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public Product searchProduct(String id) {
        for (Product product : products) {
            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;
    }

    public void updateProduct(String id, String newName, String newCategory, double newPrice) {
        Product product = searchProduct(id);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        product.setName(newName);
        product.setCategory(newCategory);
        product.setPrice(newPrice);

        System.out.println("Product information updated successfully.");
    }

    public void updateStock(String id, int delta) {
        Product product = searchProduct(id);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        int newQuantity = product.getQuantity() + delta;

        if (newQuantity < 0) {
            System.out.println("Invalid stock update. Quantity cannot be negative.");
            return;
        }

        product.setQuantity(newQuantity);
        System.out.println("Stock updated successfully.");
    }

    public void viewLowStockProducts() {
        boolean found = false;

        System.out.println("\n--- Low Stock Products ---");
        for (Product product : products) {
            if (product.getQuantity() < 5) {
                System.out.println(product);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No low-stock products found.");
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}