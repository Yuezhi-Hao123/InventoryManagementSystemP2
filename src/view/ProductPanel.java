package view;

import controller.InventoryController;
import model.Product;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProductPanel extends JPanel {

    private JTextField idField;
    private JTextField nameField;
    private JTextField categoryField;
    private JTextField priceField;
    private JTextField quantityField;
    private JTextArea displayArea;

    private JButton addButton;
    private JButton viewButton;
    private JButton searchButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton updateStockButton;
    private JButton lowStockButton;
    private JButton clearButton;

    private InventoryController controller;

    public ProductPanel() {
        controller = new InventoryController();

        setLayout(new BorderLayout(10, 10));

        add(createInputPanel(), BorderLayout.NORTH);
        add(createButtonPanel(), BorderLayout.CENTER);
        add(createDisplayPanel(), BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        panel.add(new JLabel("Product Id:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Product Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        panel.add(categoryField);

        panel.add(new JLabel("Price:"));
        priceField = new JTextField();
        panel.add(priceField);

        panel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        panel.add(quantityField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 4, 5, 5));

        addButton = new JButton("Add Product");
        viewButton = new JButton("View All");
        searchButton = new JButton("Search");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        updateStockButton = new JButton("Update Stock");
        lowStockButton = new JButton("Low Stock");
        clearButton = new JButton("Clear");

        panel.add(addButton);
        panel.add(viewButton);
        panel.add(searchButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(updateStockButton);
        panel.add(lowStockButton);
        panel.add(clearButton);

        addButton.addActionListener(e -> addProduct());
        viewButton.addActionListener(e -> viewAllProducts());
        searchButton.addActionListener(e -> searchProduct());
        updateButton.addActionListener(e -> updateProduct());
        deleteButton.addActionListener(e -> deleteProduct());
        updateStockButton.addActionListener(e -> updateStockQuantity());
        lowStockButton.addActionListener(e -> viewLowStockProducts());
        clearButton.addActionListener(e -> clearFields());

        return panel;
    }

    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout());

      displayArea = new JTextArea(14, 70);
displayArea.setEditable(false);
displayArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        panel.add(new JLabel("Product Information:"), BorderLayout.NORTH);
        panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        return panel;
    }

    private void addProduct() {
        try {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String category = categoryField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());

            boolean added = controller.addProduct(id, name, category, price, quantity);

            if (added) {
                JOptionPane.showMessageDialog(this, "Product added successfully.");
                clearFields();
                viewAllProducts();
            } else {
                JOptionPane.showMessageDialog(this, "Product was not added. Please check the input or product ID.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Price must be a number and quantity must be an integer.");
        }
    }

   private void viewAllProducts() {
    ArrayList<Product> products = controller.getAllProducts();

    displayArea.setText("");

    if (products.isEmpty()) {
        displayArea.setText("No products found.");
        return;
    }

    displayArea.append(formatHeader());

    for (Product p : products) {
        displayArea.append(formatProduct(p));
    }
}

    private void searchProduct() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();

        displayArea.setText("");

        if (!id.isEmpty()) {
            Product product = controller.searchProductById(id);

            if (product != null) {
    displayArea.append(formatHeader());
    displayArea.append(formatProduct(product));
        } else {
    displayArea.setText("Product not found.");
}

            return;
        }

        if (!name.isEmpty()) {
            ArrayList<Product> products = controller.searchProductsByName(name);

           if (products.isEmpty()) {
    displayArea.setText("No products found.");
} else {
    displayArea.append(formatHeader());

    for (Product p : products) {
        displayArea.append(formatProduct(p));
    }
}

            return;
        }

        JOptionPane.showMessageDialog(this, "Please enter Product ID or Product Name to search.");
    }

    private void updateProduct() {
        try {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String category = categoryField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());

            boolean updated = controller.updateProduct(id, name, category, price, quantity);

            if (updated) {
                JOptionPane.showMessageDialog(this, "Product updated successfully.");
                clearFields();
                viewAllProducts();
            } else {
                JOptionPane.showMessageDialog(this, "Product was not updated. Please check the product ID and input.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Price must be a number and quantity must be an integer.");
        }
    }

    private void deleteProduct() {
        String id = idField.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Product ID to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this product?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            boolean deleted = controller.deleteProduct(id);

            if (deleted) {
                JOptionPane.showMessageDialog(this, "Product deleted successfully.");
                clearFields();
                viewAllProducts();
            } else {
                JOptionPane.showMessageDialog(this, "Product was not deleted. Product ID may not exist.");
            }
        }
    }

    private void updateStockQuantity() {
        try {
            String id = idField.getText().trim();

            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Product ID.");
                return;
            }

            int change = Integer.parseInt(quantityField.getText().trim());

            boolean updated = controller.updateStockQuantity(id, change);

            if (updated) {
                JOptionPane.showMessageDialog(this, "Stock quantity updated successfully.");
                clearFields();
                viewAllProducts();
            } else {
                JOptionPane.showMessageDialog(this, "Stock was not updated. Please check the Product ID or quantity.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantity must be an integer. Use positive number to add stock or negative number to reduce stock.");
        }
    }

    private void viewLowStockProducts() {
        int limit = 10;

        try {
            String quantityText = quantityField.getText().trim();

            if (!quantityText.isEmpty()) {
                limit = Integer.parseInt(quantityText);
            }

            ArrayList<Product> products = controller.getLowStockProducts(limit);

            displayArea.setText("");

            if (products.isEmpty()) {
                displayArea.setText("No low-stock products found.");
            } else {
               displayArea.append("Low-stock products below " + limit + ":\n");
                displayArea.append(formatHeader());

                for (Product p : products) {
    displayArea.append(formatProduct(p));
}
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantity must be an integer for low-stock limit.");
        }
    }
private String formatHeader() {
    return String.format("%-10s %-22s %-15s %10s %8s%n",
            "ID", "Name", "Category", "Price", "Qty")
            + "--------------------------------------------------------------------------\n";
}

private String formatProduct(Product p) {
    return String.format("%-10s %-22s %-15s %10.2f %8d%n",
            p.getId(),
            p.getName(),
            p.getCategory(),
            p.getPrice(),
            p.getQuantity());
}

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        categoryField.setText("");
        priceField.setText("");
        quantityField.setText("");
        displayArea.setText("");
    }
}