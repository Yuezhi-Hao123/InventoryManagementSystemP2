/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static final String DB_URL = "jdbc:derby:inventoryDB;create=true";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void createTable() {
        String sql = "CREATE TABLE PRODUCTS ("
                + "PRODUCT_ID VARCHAR(20) PRIMARY KEY, "
                + "NAME VARCHAR(100) NOT NULL, "
                + "CATEGORY VARCHAR(50), "
                + "PRICE DOUBLE, "
                + "QUANTITY INT"
                + ")";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("PRODUCTS table created.");

        } catch (SQLException e) {
            if ("X0Y32".equals(e.getSQLState())) {
                System.out.println("PRODUCTS table already exists.");
            } else {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }
}