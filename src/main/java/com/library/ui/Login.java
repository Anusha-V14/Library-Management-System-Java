package com.library.ui;

import javax.swing.*;

public class Login {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Library Management System - Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(50, 80, 80, 25);
        frame.add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(150, 80, 150, 25);
        frame.add(txtUser);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(50, 120, 80, 25);
        frame.add(lblPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(150, 120, 150, 25);
        frame.add(txtPass);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(150, 170, 80, 30);
        frame.add(btnLogin);

        // FINAL SAFE LOGIN LOGIC
        btnLogin.addActionListener(e -> {

            String username = txtUser.getText().trim();
            String password = String.valueOf(txtPass.getPassword()).trim();

            // Debug prints (can remove later)
            System.out.println("Username = [" + username + "]");
            System.out.println("Password = [" + password + "]");

            if ("admin".equalsIgnoreCase(username) && "admin123".equals(password)) {
                frame.dispose();   // close login window
                new Home();        // open home page
            } else {
                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid username or password",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        frame.setVisible(true);
    }
}
