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
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JPanel topPanel = new JPanel(new BorderLayout(10, 10));
    topPanel.add(createInputPanel(), BorderLayout.NORTH);
    topPanel.add(createButtonPanel(), BorderLayout.SOUTH);

    add(topPanel, BorderLayout.NORTH);
    add(createDisplayPanel(), BorderLayout.CENTER);
}
 private JPanel createInputPanel() {
    JPanel outerPanel = new JPanel(new BorderLayout(0, 8));
    outerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    JLabel titleLabel = new JLabel("Product Details");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

    JPanel formPanel = new JPanel(new GridBagLayout());
    formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(190, 200, 210)),
            BorderFactory.createEmptyBorder(12, 14, 12, 14)
    ));

    idField = new JTextField();
    nameField = new JTextField();
    categoryField = new JTextField();
    priceField = new JTextField();
    quantityField = new JTextField();

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(6, 6, 6, 6);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    addFormRow(formPanel, gbc, 0, "Product ID:", idField);
    addFormRow(formPanel, gbc, 1, "Product Name:", nameField);
    addFormRow(formPanel, gbc, 2, "Category:", categoryField);
    addFormRow(formPanel, gbc, 3, "Price:", priceField);
    addFormRow(formPanel, gbc, 4, "Quantity:", quantityField);

    outerPanel.add(titleLabel, BorderLayout.NORTH);
    outerPanel.add(formPanel, BorderLayout.CENTER);

    return outerPanel;
}
 
 private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, JTextField textField) {
    JLabel label = new JLabel(labelText);
    label.setFont(new Font("Arial", Font.BOLD, 14));
    label.setPreferredSize(new Dimension(140, 26));

    textField.setFont(new Font("Arial", Font.PLAIN, 14));
    textField.setPreferredSize(new Dimension(320, 28));

    gbc.gridx = 0;
    gbc.gridy = row;
    gbc.weightx = 0;
    panel.add(label, gbc);

    gbc.gridx = 1;
    gbc.gridy = row;
    gbc.weightx = 1;
    panel.add(textField, gbc);
}
 

   private JPanel createButtonPanel() {
    JPanel panel = new JPanel(new GridLayout(2, 4, 8, 8));
    panel.setBorder(BorderFactory.createTitledBorder("Actions"));

        addButton = new JButton("Add Product");
        viewButton = new JButton("View All");
        searchButton = new JButton("Search");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        updateStockButton = new JButton("Update Stock");
        lowStockButton = new JButton("Low Stock");
        clearButton = new JButton("Clear");

        
styleButton(addButton);
styleButton(viewButton);
searchButton.setToolTipText("Search by product ID or product name");
styleButton(searchButton);
styleButton(updateButton);
styleButton(deleteButton);
styleButton(updateStockButton);
styleButton(lowStockButton);
styleButton(clearButton);
        
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
   
private void styleButton(JButton button) {
    button.setFont(new Font("Arial", Font.BOLD, 14));
    button.setFocusPainted(false);
    button.setMargin(new Insets(8, 10, 8, 10));
}
    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout());

     displayArea = new JTextArea(16, 60);
displayArea.setEditable(false);
displayArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

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