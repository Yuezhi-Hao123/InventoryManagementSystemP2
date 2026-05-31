package main;

import dao.ProductDAO;
import model.Product;
import java.util.ArrayList;

public class DatabaseTest {

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();

       
        System.out.println("Add product:");
        Product product = new Product("P1002", "Mouse", "Electronics", 29.99, 15);

        boolean added = dao.addProduct(product);

        if (added) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Product was not added. It may already exist.");
        }

        
        System.out.println("\nAll products:");
        ArrayList<Product> products = dao.getAllProducts();

        for (Product p : products) {
            printProduct(p);
        }

        
        System.out.println("\nSearch by ID:");
        Product found = dao.searchProductById("P1001");

        if (found != null) {
            printProduct(found);
        } else {
            System.out.println("Product not found.");
        }

      
        System.out.println("\nSearch by name:");
        ArrayList<Product> searchResults = dao.searchProductsByName("key");

        if (searchResults.isEmpty()) {
            System.out.println("No products found.");
        } else {
            for (Product p : searchResults) {
                printProduct(p);
            }
        }

       
        System.out.println("\nUpdate product:");
        Product updatedProduct = new Product("P1002", "Gaming Mouse", "Electronics", 39.99, 20);

        boolean updated = dao.updateProduct(updatedProduct);

        if (updated) {
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product was not updated.");
        }

        
        System.out.println("\nProduct after update:");
        Product afterUpdate = dao.searchProductById("P1002");

        if (afterUpdate != null) {
            printProduct(afterUpdate);
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void printProduct(Product p) {
        System.out.println(
                p.getId() + " | " +
                p.getName() + " | " +
                p.getCategory() + " | " +
                p.getPrice() + " | " +
                p.getQuantity()
        );
    }
}