package employee.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

@SuppressWarnings("ALL")
public class AddEmployee extends JFrame implements ActionListener {

    Random ran = new Random();
    int number = ran.nextInt(900000) + 100000;

    JTextField tname, tfname, taddress, tphone, tnid, temail, tsalary, tdesignation;
    JLabel tempid;
    JButton add, back;
    JDateChooser tdob;
    JComboBox<String> teducation;

    AddEmployee() {
        setTitle("Employee Management System - Add Employee");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        getContentPane().setBackground(new Color(163, 255, 188));

        JLabel heading = new JLabel("Add Employee Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(50, 150, 150, 30);
        name.setFont(new Font("SAN_SARIF", Font.BOLD, 20));
        add(name);

        tname = new JTextField();
        tname.setBounds(200, 150, 150, 30);
        tname.setBackground(new Color(177, 252, 197));
        add(tname);

        JLabel fname = new JLabel("Father's Name");
        fname.setBounds(400, 150, 150, 30);
        fname.setFont(new Font("SAN_SARIF", Font.BOLD, 20));
        add(fname);

        tfname = new JTextField();
        tfname.setBounds(600, 150, 150, 30);
        tfname.setBackground(new Color(177, 252, 197));
        add(tfname);

        JLabel dob = new JLabel("Date Of Birth");
        dob.setBounds(50, 200, 150, 30);
        dob.setFont(new Font("SAN_SARIF", Font.BOLD, 20));
        add(dob);

        tdob = new JDateChooser();
        tdob.setBounds(200, 200, 150, 30);
        tdob.setBackground(new Color(177, 252, 197));
        add(tdob);

        JLabel salary = new JLabel("Salary");
        salary.setBounds(400, 200, 150, 30);
        salary.setFont(new Font("SAN_SARIF", Font.BOLD, 20));
        add(salary);

        tsalary = new JTextField();
        tsalary.setBounds(600, 200, 150, 30);
        tsalary.setBackground(new Color(177, 252, 197));
        add(tsalary);

        JLabel address = new JLabel("Address");
        address.setBounds(50, 250, 150, 30);
        address.setFont(new Font("SAN_SARIF", Font.BOLD, 20));
        add(address);

        taddress = new JTextField();
        taddress.setBounds(200, 250, 150, 30);
        taddress.setBackground(new Color(177, 252, 197));
        add(taddress);

        JLabel phone = new JLabel("Phone No.");
        phone.setBounds(400, 250, 150, 30);
        phone.setFont(new Font("SAN_SARIF", Font.BOLD, 20));
        add(phone);

        tphone = new JTextField();
        tphone.setBounds(600, 250, 150, 30);
        tphone.setBackground(new Color(177, 252, 197));
        add(tphone);

        JLabel email = new JLabel("Email");
        email.setBounds(50, 300, 150, 30);
        email.setFont(new Font("SAN_SARIF", Font.BOLD, 20));
        add(email);

        temail = new JTextField();
        temail.setBounds(200, 300, 150, 30);
        temail.setBackground(new Color(177, 252, 197));
        add(temail);

        JLabel education = new JLabel("Education");
        education.setBounds(400, 300, 150, 30);
        education.setFont(new Font("SAN_SARIF", Font.BOLD, 20));
        add(education);

        String[] degrees = {"CSE", "SWE", "BBA", "MBA", "LLB", "BA", "MA"};
        teducation = new JComboBox<>(degrees);
        teducation.setBackground(new Color(177, 252, 197));
        teducation.setBounds(600, 300, 150, 30);
        add(teducation);

        JLabel empid = new JLabel("Employee ID");
        empid.setBounds(50, 400, 150, 30);
        empid.setFont(new Font("SAN_SARIF", Font.BOLD, 20));
        add(empid);

        tempid = new JLabel(String.valueOf(number));
        tempid.setBounds(200, 400, 150, 30);
        tempid.setFont(new Font("SAN_SARIF", Font.BOLD, 20));
        tempid.setForeground(Color.BLACK);
        add(tempid);

        JLabel nid = new JLabel("NID No.");
        nid.setBounds(400, 350, 150, 30);
        nid.setFont(new Font("SAN_SARIF", Font.BOLD, 20));
        add(nid);

        tnid = new JTextField();
        tnid.setBounds(600, 350, 150, 30);
        tnid.setBackground(new Color(177, 252, 197));
        add(tnid);

        JLabel designation = new JLabel("Designation");
        designation.setBounds(50, 350, 150, 30);
        designation.setFont(new Font("SAN_SARIF", Font.BOLD, 20));
        add(designation);

        tdesignation = new JTextField();
        tdesignation.setBounds(200, 350, 150, 30);
        tdesignation.setBackground(new Color(177, 252, 197));
        add(tdesignation);

        back = new JButton("Go Back");
        back.setBounds(250, 550, 150, 40);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.addActionListener(this);
        add(back);

        add = new JButton("Add Employee");
        add.setBounds(450, 550, 150, 40);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.setFocusPainted(false);
        add.addActionListener(this);
        add(add);

        setSize(900, 700);
        setLayout(null);
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String name = tname.getText().trim();
            String fname = tfname.getText().trim();
            String dob = "";
            if (tdob.getDateEditor() != null && tdob.getDateEditor().getUiComponent() instanceof JTextField) {
                dob = ((JTextField) tdob.getDateEditor().getUiComponent()).getText().trim();
            }
            String salary = tsalary.getText().trim();
            String address = taddress.getText().trim();
            String phone = tphone.getText().trim();
            String email = temail.getText().trim();
            String education = (String) teducation.getSelectedItem();
            String designation = tdesignation.getText().trim();
            String nid = tnid.getText().trim();
            String empid = tempid.getText().trim();

            if (name.isEmpty() || phone.isEmpty() || salary.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter at least Name, Phone No., and Salary.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                conn c = new conn();
                Connection connection = c.getConnection();

                if (connection == null || connection.isClosed()) {
                    JOptionPane.showMessageDialog(this, "Database connection failed! Please check MySQL service.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String query = "INSERT INTO employee (name, fname, dob, salary, address, phone, email, education, designation, nid, empid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setString(1, name);
                    pstmt.setString(2, fname);
                    pstmt.setString(3, dob);
                    pstmt.setString(4, salary);
                    pstmt.setString(5, address);
                    pstmt.setString(6, phone);
                    pstmt.setString(7, email);
                    pstmt.setString(8, education);
                    pstmt.setString(9, designation);
                    pstmt.setString(10, nid);
                    pstmt.setString(11, empid);

                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(this, "Employee details added successfully!");
                        setVisible(false);
                        dispose();
                        new Main();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to add employee. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: Unable to add employee details. " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            setVisible(false);
            dispose();
            new Main();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddEmployee());
    }
}
