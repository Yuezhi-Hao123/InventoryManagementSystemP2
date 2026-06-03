/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Inventory Management System");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Inventory Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        ProductPanel productPanel = new ProductPanel();

        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(productPanel, BorderLayout.CENTER);
    }
}