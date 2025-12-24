package com.library.ui;

import javax.swing.*;

public class Home {

    public Home() {

        JFrame frame = new JFrame("Library Management System - Home");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel lblTitle = new JLabel("Library Management System", JLabel.CENTER);
        lblTitle.setBounds(100, 20, 300, 30);
        frame.add(lblTitle);

        JButton btnAddBook = new JButton("Add Book");
        btnAddBook.setBounds(150, 80, 200, 30);
        frame.add(btnAddBook);
        btnAddBook.addActionListener(e -> new AddBook());
        
        JButton btnIssueBook = new JButton("Issue Book");
        btnIssueBook.setBounds(150, 130, 200, 30);
        frame.add(btnIssueBook);
        btnIssueBook.addActionListener(e -> new IssueBook());
     
        JButton btnReturnBook = new JButton("Return Book");
        btnReturnBook.setBounds(150, 180, 200, 30);
        frame.add(btnReturnBook);
        btnReturnBook.addActionListener(e -> new ReturnBook());

        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(150, 230, 200, 30);
        frame.add(btnExit);
        btnExit.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }
}
