package main;

import dao.ProductDAO;
import model.Product;
import java.util.ArrayList;

public class DatabaseTest {

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();

        // 1. Add product
        System.out.println("Add product:");
        Product product = new Product("P1002", "Mouse", "Electronics", 29.99, 15);

        boolean added = dao.addProduct(product);

        if (added) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Product was not added. It may already exist.");
        }

        // 2. View all products
        System.out.println("\nAll products:");
        ArrayList<Product> products = dao.getAllProducts();

        for (Product p : products) {
            printProduct(p);
        }

        // 3. Search product by ID
        System.out.println("\nSearch by ID:");
        Product found = dao.searchProductById("P1001");

        if (found != null) {
            printProduct(found);
        } else {
            System.out.println("Product not found.");
        }

        // 4. Search product by name
        System.out.println("\nSearch by name:");
        ArrayList<Product> searchResults = dao.searchProductsByName("key");

        if (searchResults.isEmpty()) {
            System.out.println("No products found.");
        } else {
            for (Product p : searchResults) {
                printProduct(p);
            }
        }

        // 5. Update product
        System.out.println("\nUpdate product:");
        Product updatedProduct = new Product("P1002", "Gaming Mouse", "Electronics", 39.99, 20);

        boolean updated = dao.updateProduct(updatedProduct);

        if (updated) {
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product was not updated.");
        }

        // 6. Check updated product
        System.out.println("\nProduct after update:");
        Product afterUpdate = dao.searchProductById("P1002");

        if (afterUpdate != null) {
            printProduct(afterUpdate);
        } else {
            System.out.println("Product not found.");
        }

        // 7. Delete product test
        System.out.println("\nDelete product test:");

        Product deleteTestProduct = new Product("P9999", "Delete Test Item", "Test", 9.99, 1);
        dao.addProduct(deleteTestProduct);

        boolean deleted = dao.deleteProduct("P9999");

        if (deleted) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product was not deleted.");
        }

        Product deletedProduct = dao.searchProductById("P9999");

        if (deletedProduct == null) {
            System.out.println("Deleted product cannot be found. Delete test passed.");
        } else {
            System.out.println("Delete test failed.");
        }
        
        // 8. Update stock quantity
System.out.println("\nUpdate stock quantity:");

boolean stockUpdated = dao.updateStockQuantity("P1002", -15);

if (stockUpdated) {
    System.out.println("Stock quantity updated successfully.");
} else {
    System.out.println("Stock quantity was not updated.");
}

Product afterStockUpdate = dao.searchProductById("P1002");

if (afterStockUpdate != null) {
    printProduct(afterStockUpdate);
}

// 9. Low-stock products
System.out.println("\nLow-stock products:");

ArrayList<Product> lowStockProducts = dao.getLowStockProducts(10);

if (lowStockProducts.isEmpty()) {
    System.out.println("No low-stock products found.");
} else {
    for (Product p : lowStockProducts) {
        printProduct(p);
    }
}

// 10. Invalid stock update test
System.out.println("\nInvalid stock update test:");

boolean invalidStockUpdate = dao.updateStockQuantity("P1002", -1000);

if (!invalidStockUpdate) {
    System.out.println("Invalid stock update was prevented.");
} else {
    System.out.println("Invalid stock update failed.");
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