package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings("ALL")
public class RemoveEmployee extends JFrame implements ActionListener {

    Choice choiceempid;
    JButton delete, back;
    JLabel textname, textphone, textemail;

    RemoveEmployee() {
        setTitle("Employee Management System - Remove Employee");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(null);

        JLabel label = new JLabel("Employee ID");
        label.setBounds(50, 50, 120, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(label);

        choiceempid = new Choice();
        choiceempid.setBounds(200, 50, 150, 30);
        add(choiceempid);

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 100, 100, 30);
        labelname.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelname);

        textname = new JLabel();
        textname.setBounds(200, 100, 200, 30);
        textname.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(textname);

        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(50, 150, 100, 30);
        labelphone.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelphone);

        textphone = new JLabel();
        textphone.setBounds(200, 150, 200, 30);
        textphone.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(textphone);

        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50, 200, 100, 30);
        labelemail.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelemail);

        textemail = new JLabel();
        textemail.setBounds(200, 200, 250, 30);
        textemail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(textemail);

        loadEmployeeList();

        choiceempid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updateEmployeeDetails(choiceempid.getSelectedItem());
            }
        });

        delete = new JButton("Delete");
        delete.setBounds(80, 300, 100, 30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.setFocusPainted(false);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBounds(220, 300, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.addActionListener(this);
        add(back);

        URL deleteImgUrl = ClassLoader.getSystemResource("icons/delete.png");
        if (deleteImgUrl != null) {
            ImageIcon i1 = new ImageIcon(deleteImgUrl);
            Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel img = new JLabel(i3);
            img.setBounds(700, 80, 200, 200);
            add(img);
        }

        URL rbackImgUrl = ClassLoader.getSystemResource("icons/rback.png");
        if (rbackImgUrl != null) {
            ImageIcon i11 = new ImageIcon(rbackImgUrl);
            Image i12 = i11.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
            ImageIcon i13 = new ImageIcon(i12);
            JLabel image = new JLabel(i13);
            image.setBounds(0, 0, 1120, 630);
            add(image);
        }

        setSize(1000, 400);
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    private void loadEmployeeList() {
        try {
            conn c = new conn();
            Connection connection = c.getConnection();
            if (connection != null) {
                try (Statement stmt = connection.createStatement();
                     ResultSet resultSet = stmt.executeQuery("SELECT empid FROM employee ORDER BY empid ASC")) {
                    while (resultSet.next()) {
                        choiceempid.add(resultSet.getString("empid"));
                    }
                }
            }
            if (choiceempid.getItemCount() > 0) {
                updateEmployeeDetails(choiceempid.getSelectedItem());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateEmployeeDetails(String empid) {
        if (empid == null || empid.trim().isEmpty()) {
            textname.setText("");
            textphone.setText("");
            textemail.setText("");
            return;
        }

        try {
            conn c = new conn();
            Connection connection = c.getConnection();
            if (connection != null) {
                String query = "SELECT name, phone, email FROM employee WHERE empid = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setString(1, empid.trim());
                    try (ResultSet resultSet = pstmt.executeQuery()) {
                        if (resultSet.next()) {
                            textname.setText(resultSet.getString("name"));
                            textphone.setText(resultSet.getString("phone"));
                            textemail.setText(resultSet.getString("email"));
                        } else {
                            textname.setText("");
                            textphone.setText("");
                            textemail.setText("");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            String selectedId = choiceempid.getSelectedItem();
            if (selectedId == null || selectedId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No employee selected to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to permanently delete Employee ID: " + selectedId + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            try {
                conn c = new conn();
                Connection connection = c.getConnection();
                if (connection == null || connection.isClosed()) {
                    JOptionPane.showMessageDialog(this, "Database connection failed! Please check MySQL service.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String query = "DELETE FROM employee WHERE empid = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setString(1, selectedId.trim());

                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
                        setVisible(false);
                        dispose();
                        new Main();
                    } else {
                        JOptionPane.showMessageDialog(this, "Employee ID not found in database.", "Deletion Result", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            setVisible(false);
            dispose();
            new Main();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RemoveEmployee());
    }
}
