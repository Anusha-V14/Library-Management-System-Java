package com.library.ui;

import com.library.db.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddBook {

    public AddBook() {

        JFrame frame = new JFrame("Add Book");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel lblTitle = new JLabel("Book Title:");
        lblTitle.setBounds(50, 50, 100, 25);
        frame.add(lblTitle);

        JTextField txtTitle = new JTextField();
        txtTitle.setBounds(150, 50, 180, 25);
        frame.add(txtTitle);

        JLabel lblAuthor = new JLabel("Author:");
        lblAuthor.setBounds(50, 90, 100, 25);
        frame.add(lblAuthor);

        JTextField txtAuthor = new JTextField();
        txtAuthor.setBounds(150, 90, 180, 25);
        frame.add(txtAuthor);

        JLabel lblQty = new JLabel("Quantity:");
        lblQty.setBounds(50, 130, 100, 25);
        frame.add(lblQty);

        JTextField txtQty = new JTextField();
        txtQty.setBounds(150, 130, 180, 25);
        frame.add(txtQty);

        JButton btnAdd = new JButton("Add Book");
        btnAdd.setBounds(150, 180, 120, 30);
        frame.add(btnAdd);

        btnAdd.addActionListener(e -> {
            try {
                String title = txtTitle.getText();
                String author = txtAuthor.getText();
                int quantity = Integer.parseInt(txtQty.getText());

                Connection con = DBConnection.getConnection();
                String sql = "INSERT INTO books (title, author, quantity) VALUES (?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, title);
                ps.setString(2, author);
                ps.setInt(3, quantity);

                ps.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Book Added Successfully");

                txtTitle.setText("");
                txtAuthor.setText("");
                txtQty.setText("");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error adding book");
            }
        });

        frame.setVisible(true);
    }
}
