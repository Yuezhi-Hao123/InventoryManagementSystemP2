/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import model.Product;
import java.util.Scanner;

public class InventorySystem {
    private InventoryManager manager;
    private FileHandler fileHandler;
    private Scanner sc;

    public InventorySystem() {
        manager = new InventoryManager();
        fileHandler = new FileHandler("products.txt");
        sc = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\n===== Inventory Management System =====");
        System.out.println("1. Add Product");
        System.out.println("2. View All Products");
        System.out.println("3. Search Product");
        System.out.println("4. Update Product");
        System.out.println("5. Delete Product");
        System.out.println("6. Update Stock");
        System.out.println("7. View Low-Stock Products");
        System.out.println("8. Save Data");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }

    public void start() {
        int choice;

        do {
            displayMenu();

            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    manager.viewAllProducts();
                    break;
                case 3:
                    searchProduct();
                    break;
                case 4:
                    updateProduct();
                    break;
                case 5:
                    deleteProduct();
                    break;
                case 6:
                    updateStock();
                    break;
                case 7:
                    manager.viewLowStockProducts();
                    break;
                case 8:
                    fileHandler.saveData(manager.getProducts());
                    break;
                case 9:
                    System.out.print("Do you want to save data before exit? (yes/no): ");
                    String answer = sc.nextLine();
                    if (answer.equalsIgnoreCase("yes")) {
                        fileHandler.saveData(manager.getProducts());
                    }
                    System.out.println("System exited.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 9);
    }

    private void addProduct() {
        System.out.print("Enter product ID: ");
        String id = sc.nextLine();

        System.out.print("Enter product name: ");
        String name = sc.nextLine();

        System.out.print("Enter category: ");
        String category = sc.nextLine();

        System.out.print("Enter price: ");
        double price = sc.nextDouble();

        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        Product product = new Product(id, name, category, price, quantity);
        manager.addProduct(product);
    }

    private void searchProduct() {
        System.out.print("Enter product ID to search: ");
        String id = sc.nextLine();

        Product product = manager.searchProduct(id);

        if (product == null) {
            System.out.println("Product not found.");
        } else {
            System.out.println("Product found:");
            System.out.println(product);
        }
    }

    private void updateProduct() {
        System.out.print("Enter product ID to update: ");
        String id = sc.nextLine();

        Product product = manager.searchProduct(id);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter new product name: ");
        String newName = sc.nextLine();

        System.out.print("Enter new category: ");
        String newCategory = sc.nextLine();

        System.out.print("Enter new price: ");
        double newPrice = sc.nextDouble();
        sc.nextLine();

        manager.updateProduct(id, newName, newCategory, newPrice);
    }

    private void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        String id = sc.nextLine();
        manager.deleteProduct(id);
    }

    private void updateStock() {
        System.out.print("Enter product ID to update stock: ");
        String id = sc.nextLine();

        System.out.print("Enter stock change (e.g. 5 or -2): ");
        int delta = sc.nextInt();
        sc.nextLine();

        manager.updateStock(id, delta);
    }

    public static void main(String[] args) {
        InventorySystem system = new InventorySystem();
        system.start();
    }
}