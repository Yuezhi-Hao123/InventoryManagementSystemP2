package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import model.Product;
import service.InventoryService;
import java.util.ArrayList;

public class InventoryController {
    private InventoryService service;

    public InventoryController() {
        service = new InventoryService();
    }

    public boolean addProduct(String id, String name, String category, double price, int quantity) {
        return service.addProduct(id, name, category, price, quantity);
    }

    public ArrayList<Product> getAllProducts() {
        return service.getAllProducts();
    }

    public Product searchProductById(String id) {
        return service.searchProductById(id);
    }

    public ArrayList<Product> searchProductsByName(String name) {
        return service.searchProductsByName(name);
    }

    public boolean updateProduct(String id, String name, String category, double price, int quantity) {
        return service.updateProduct(id, name, category, price, quantity);
    }

    public boolean deleteProduct(String id) {
        return service.deleteProduct(id);
    }

 public boolean updateStockQuantity(String id, int newQuantity) {
    return service.updateStockQuantity(id, newQuantity);
}

    public ArrayList<Product> getLowStockProducts(int limit) {
        return service.getLowStockProducts(limit);
    }
}