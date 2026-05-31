package dao;

import model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {

    public ProductDAO() {
        DBConnection.createTable();
    }

    public boolean addProduct(Product product) {
        String sql = "INSERT INTO PRODUCTS (PRODUCT_ID, NAME, CATEGORY, PRICE, QUANTITY) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getCategory());
            ps.setDouble(4, product.getPrice());
            ps.setInt(5, product.getQuantity());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Add product error: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTS";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getString("PRODUCT_ID"),
                        rs.getString("NAME"),
                        rs.getString("CATEGORY"),
                        rs.getDouble("PRICE"),
                        rs.getInt("QUANTITY")
                );

                products.add(product);
            }

        } catch (SQLException e) {
            System.out.println("View products error: " + e.getMessage());
        }

        return products;
    }

    public Product searchProductById(String id) {
        String sql = "SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getString("PRODUCT_ID"),
                            rs.getString("NAME"),
                            rs.getString("CATEGORY"),
                            rs.getDouble("PRICE"),
                            rs.getInt("QUANTITY")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Search product by ID error: " + e.getMessage());
        }

        return null;
    }

    public ArrayList<Product> searchProductsByName(String name) {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTS WHERE LOWER(NAME) LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name.toLowerCase() + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                            rs.getString("PRODUCT_ID"),
                            rs.getString("NAME"),
                            rs.getString("CATEGORY"),
                            rs.getDouble("PRICE"),
                            rs.getInt("QUANTITY")
                    );

                    products.add(product);
                }
            }

        } catch (SQLException e) {
            System.out.println("Search product by name error: " + e.getMessage());
        }

        return products;
    }
}