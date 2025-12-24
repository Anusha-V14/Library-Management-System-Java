package com.library.ui;

import com.library.db.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReturnBook {

    public ReturnBook() {

        JFrame frame = new JFrame("Return Book");
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel lblIssueId = new JLabel("Issue ID:");
        lblIssueId.setBounds(50, 60, 100, 25);
        frame.add(lblIssueId);

        JTextField txtIssueId = new JTextField();
        txtIssueId.setBounds(150, 60, 180, 25);
        frame.add(txtIssueId);

        JButton btnReturn = new JButton("Return Book");
        btnReturn.setBounds(150, 120, 120, 30);
        frame.add(btnReturn);

        btnReturn.addActionListener(e -> {
            try {
                int issueId = Integer.parseInt(txtIssueId.getText());

                Connection con = DBConnection.getConnection();

                // Get book_id from issued_books
                PreparedStatement ps =
                        con.prepareStatement("SELECT book_id FROM issued_books WHERE issue_id = ?");
                ps.setInt(1, issueId);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int bookId = rs.getInt("book_id");

                    // Delete issue record
                    PreparedStatement deletePs =
                            con.prepareStatement("DELETE FROM issued_books WHERE issue_id = ?");
                    deletePs.setInt(1, issueId);
                    deletePs.executeUpdate();

                    // Increase book quantity
                    PreparedStatement updatePs =
                            con.prepareStatement(
                                    "UPDATE books SET quantity = quantity + 1 WHERE book_id = ?");
                    updatePs.setInt(1, bookId);
                    updatePs.executeUpdate();

                    JOptionPane.showMessageDialog(frame, "Book Returned Successfully");

                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Issue ID");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error returning book");
            }
        });

        frame.setVisible(true);
    }
}
