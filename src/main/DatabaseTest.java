/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import dao.ProductDAO;
import model.Product;
import java.util.ArrayList;

public class DatabaseTest {
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();

        Product product = new Product("P1002", "Mouse", "Electronics", 29.99, 15);

        boolean added = dao.addProduct(product);

        if (added) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Product was not added. It may already exist.");
        }

        ArrayList<Product> products = dao.getAllProducts();

        System.out.println("\nAll products:");
        for (Product p : products) {
            System.out.println(
                    p.getId() + " | " +
                    p.getName() + " | " +
                    p.getCategory() + " | " +
                    p.getPrice() + " | " +
                    p.getQuantity()
            );
        }

        System.out.println("\nSearch by ID:");
        Product found = dao.searchProductById("P1001");

        if (found != null) {
            System.out.println(
                    found.getId() + " | " +
                    found.getName() + " | " +
                    found.getCategory() + " | " +
                    found.getPrice() + " | " +
                    found.getQuantity()
            );
        } else {
            System.out.println("Product not found.");
        }

        System.out.println("\nSearch by name:");
        ArrayList<Product> searchResults = dao.searchProductsByName("key");

        for (Product p : searchResults) {
            System.out.println(
                    p.getId() + " | " +
                    p.getName() + " | " +
                    p.getCategory() + " | " +
                    p.getPrice() + " | " +
                    p.getQuantity()
            );
        }
    }
}