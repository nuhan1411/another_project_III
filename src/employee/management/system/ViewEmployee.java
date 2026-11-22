package employee.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings("ALL")
public class ViewEmployee extends JFrame implements ActionListener {

    JTable table;
    Choice choiceemp;
    JButton searchbtn, print, update, back;

    ViewEmployee() {
        setTitle("Employee Management System - View Employees");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        getContentPane().setBackground(new Color(255, 131, 122));
        setLayout(null);

        JLabel search = new JLabel("Search by employee ID");
        search.setBounds(20, 20, 160, 20);
        search.setFont(new Font("Tahoma", Font.BOLD, 13));
        add(search);

        choiceemp = new Choice();
        choiceemp.setBounds(190, 20, 150, 20);
        add(choiceemp);

        loadEmployeeIds();

        table = new JTable();
        table.setRowHeight(22);
        loadTableData("SELECT * FROM employee", null);

        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(0, 100, 900, 560);
        add(jp);

        searchbtn = new JButton("Search");
        searchbtn.setBounds(20, 65, 90, 25);
        searchbtn.setBackground(Color.BLACK);
        searchbtn.setForeground(Color.WHITE);
        searchbtn.setFocusPainted(false);
        searchbtn.addActionListener(this);
        add(searchbtn);

        print = new JButton("Print");
        print.setBounds(130, 65, 90, 25);
        print.setBackground(Color.BLACK);
        print.setForeground(Color.WHITE);
        print.setFocusPainted(false);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBounds(240, 65, 90, 25);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setFocusPainted(false);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(350, 65, 90, 25);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.addActionListener(this);
        add(back);

        setSize(900, 700);
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    private void loadEmployeeIds() {
        try {
            conn c = new conn();
            Connection connection = c.getConnection();
            if (connection != null) {
                try (Statement stmt = connection.createStatement();
                     ResultSet resultSet = stmt.executeQuery("SELECT empid FROM employee ORDER BY empid ASC")) {
                    while (resultSet.next()) {
                        choiceemp.add(resultSet.getString("empid"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTableData(String query, String param) {
        try {
            conn c = new conn();
            Connection connection = c.getConnection();
            if (connection != null) {
                if (param == null) {
                    try (Statement stmt = connection.createStatement();
                         ResultSet resultSet = stmt.executeQuery(query)) {
                        table.setModel(DbUtils.resultSetToTableModel(resultSet));
                    }
                } else {
                    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                        pstmt.setString(1, param);
                        try (ResultSet resultSet = pstmt.executeQuery()) {
                            table.setModel(DbUtils.resultSetToTableModel(resultSet));
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
        if (e.getSource() == searchbtn) {
            String empid = choiceemp.getSelectedItem();
            if (empid != null && !empid.trim().isEmpty()) {
                String query = "SELECT * FROM employee WHERE empid = ?";
                loadTableData(query, empid.trim());
            } else {
                JOptionPane.showMessageDialog(this, "No Employee ID selected to search.", "Notice", JOptionPane.INFORMATION_MESSAGE);
                loadTableData("SELECT * FROM employee", null);
            }
        } else if (e.getSource() == print) {
            try {
                if (table.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No employee records to print.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error printing table: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == update) {
            String empid = choiceemp.getSelectedItem();
            if (empid != null && !empid.trim().isEmpty()) {
                setVisible(false);
                dispose();
                new UpdateEmployee(empid.trim());
            } else {
                JOptionPane.showMessageDialog(this, "Please select an Employee ID to update.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            setVisible(false);
            dispose();
            new Main();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewEmployee());
    }
}
