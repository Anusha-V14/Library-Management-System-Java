package com.library.ui;

import com.library.db.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class IssueBook {

    public IssueBook() {

        JFrame frame = new JFrame("Issue Book");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel lblBookId = new JLabel("Book ID:");
        lblBookId.setBounds(50, 50, 100, 25);
        frame.add(lblBookId);

        JTextField txtBookId = new JTextField();
        txtBookId.setBounds(150, 50, 180, 25);
        frame.add(txtBookId);

        JLabel lblStudentId = new JLabel("Student ID:");
        lblStudentId.setBounds(50, 90, 100, 25);
        frame.add(lblStudentId);

        JTextField txtStudentId = new JTextField();
        txtStudentId.setBounds(150, 90, 180, 25);
        frame.add(txtStudentId);

        JButton btnIssue = new JButton("Issue Book");
        btnIssue.setBounds(150, 150, 120, 30);
        frame.add(btnIssue);

        btnIssue.addActionListener(e -> {
            try {
                int bookId = Integer.parseInt(txtBookId.getText());
                int studentId = Integer.parseInt(txtStudentId.getText());

                Connection con = DBConnection.getConnection();

                // Check quantity
                PreparedStatement checkPs =
                        con.prepareStatement("SELECT quantity FROM books WHERE book_id = ?");
                checkPs.setInt(1, bookId);
                ResultSet rs = checkPs.executeQuery();

                if (rs.next() && rs.getInt("quantity") > 0) {

                    // Insert issue record
                    PreparedStatement issuePs =
                            con.prepareStatement(
                                    "INSERT INTO issued_books (book_id, student_id, issue_date) VALUES (?, ?, ?)");
                    issuePs.setInt(1, bookId);
                    issuePs.setInt(2, studentId);
                    issuePs.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                    issuePs.executeUpdate();

                    // Reduce quantity
                    PreparedStatement updatePs =
                            con.prepareStatement(
                                    "UPDATE books SET quantity = quantity - 1 WHERE book_id = ?");
                    updatePs.setInt(1, bookId);
                    updatePs.executeUpdate();

                    JOptionPane.showMessageDialog(frame, "Book Issued Successfully");

                } else {
                    JOptionPane.showMessageDialog(frame, "Book not available");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error issuing book");
            }
        });

        frame.setVisible(true);
    }
}
