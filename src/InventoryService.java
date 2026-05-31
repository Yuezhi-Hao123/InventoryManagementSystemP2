/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package service;

import dao.ProductDAO;
import model.Product;
import java.util.ArrayList;

public class InventoryService {
    private ProductDAO productDAO;

    public InventoryService() {
        productDAO = new ProductDAO();
    }

    public boolean addProduct(String id, String name, String category, double price, int quantity) {
        if (!isValidProduct(id, name, price, quantity)) {
            return false;
        }

        Product product = new Product(id, name, category, price, quantity);
        return productDAO.addProduct(product);
    }

    public ArrayList<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public Product searchProductById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }

        return productDAO.searchProductById(id);
    }

    public ArrayList<Product> searchProductsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return productDAO.searchProductsByName(name);
    }

    public boolean updateProduct(String id, String name, String category, double price, int quantity) {
        if (!isValidProduct(id, name, price, quantity)) {
            return false;
        }

        Product product = new Product(id, name, category, price, quantity);
        return productDAO.updateProduct(product);
    }

    public boolean deleteProduct(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        return productDAO.deleteProduct(id);
    }

    public boolean updateStockQuantity(String id, int change) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        return productDAO.updateStockQuantity(id, change);
    }

    public ArrayList<Product> getLowStockProducts(int limit) {
        return productDAO.getLowStockProducts(limit);
    }

    private boolean isValidProduct(String id, String name, double price, int quantity) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        if (price < 0) {
            return false;
        }

        if (quantity < 0) {
            return false;
        }

        return true;
    }
}