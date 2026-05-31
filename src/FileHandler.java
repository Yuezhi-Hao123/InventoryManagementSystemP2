/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 123
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {
    private String dataFile;

    public FileHandler(String dataFile) {
        this.dataFile = dataFile;
    }

    public void saveData(ArrayList<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
            for (Product product : products) {
                writer.write(product.getId() + "," +
                             product.getName() + "," +
                             product.getCategory() + "," +
                             product.getPrice() + "," +
                             product.getQuantity());
                writer.newLine();
            }
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}