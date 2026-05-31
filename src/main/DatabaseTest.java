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

        Product product = new Product("P1001", "Keyboard", "Electronics", 49.99, 10);

        boolean added = dao.addProduct(product);

        if (added) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Product was not added. It may already exist.");
        }

        ArrayList<Product> products = dao.getAllProducts();

        System.out.println("All products:");
        for (Product p : products) {
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