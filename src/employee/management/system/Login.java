package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SuppressWarnings("ALL")
public class Login extends JFrame implements ActionListener {

    JTextField tusername;
    JPasswordField tpassword;
    JButton login, back;

    Login() {
        setTitle("Employee Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JLabel username = new JLabel("Username");
        username.setBounds(40, 20, 100, 30);
        add(username);

        tusername = new JTextField();
        tusername.setBounds(150, 20, 150, 30);
        add(tusername);

        JLabel password = new JLabel("Password");
        password.setBounds(40, 70, 100, 30);
        add(password);

        tpassword = new JPasswordField();
        tpassword.setBounds(150, 70, 150, 30);
        add(tpassword);

        // Allow pressing Enter in password field to login
        tpassword.addActionListener(e -> login.doClick());
        tusername.addActionListener(e -> login.doClick());

        login = new JButton("LOGIN");
        login.setBounds(150, 140, 150, 30);
        login.setBackground(Color.black);
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);

        back = new JButton("BACK");
        back.setBounds(150, 180, 150, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        URL imgSecondUrl = ClassLoader.getSystemResource("icons/second.jpg");
        if (imgSecondUrl != null) {
            ImageIcon i11 = new ImageIcon(imgSecondUrl);
            Image i22 = i11.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
            ImageIcon i33 = new ImageIcon(i22);
            JLabel imgg = new JLabel(i33);
            imgg.setBounds(350, 10, 600, 400);
            add(imgg);
        }

        URL imgLoginBUrl = ClassLoader.getSystemResource("icons/LoginB.jpg");
        if (imgLoginBUrl != null) {
            ImageIcon i1 = new ImageIcon(imgLoginBUrl);
            Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel img = new JLabel(i3);
            img.setBounds(0, 0, 600, 300);
            add(img);
        }

        setSize(600, 300);
        setLayout(null);
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String username = tusername.getText().trim();
            String password = new String(tpassword.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                conn dbConn = new conn();
                Connection connection = dbConn.getConnection();

                if (connection != null && !connection.isClosed()) {
                    String query = "SELECT * FROM login WHERE username = ? AND password = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, password);

                        try (ResultSet resultSet = preparedStatement.executeQuery()) {
                            if (resultSet.next()) {
                                setVisible(false);
                                dispose();
                                new Main();
                            } else {
                                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Database connection failed! Please ensure MySQL is running and check settings.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred during login: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == back) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}
