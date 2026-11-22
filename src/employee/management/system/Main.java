package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

@SuppressWarnings("ALL")
public class Main extends JFrame {

    Main() {
        setTitle("Employee Management System - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        URL homeImgUrl = ClassLoader.getSystemResource("icons/home.jpg");
        JLabel img;
        if (homeImgUrl != null) {
            ImageIcon i1 = new ImageIcon(homeImgUrl);
            Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            img = new JLabel(i3);
            img.setBounds(0, 0, 1120, 630);
            add(img);
        } else {
            img = new JLabel();
            img.setBounds(0, 0, 1120, 630);
            img.setBackground(new Color(240, 240, 240));
            img.setOpaque(true);
            add(img);
        }

        JLabel heading = new JLabel("Employee Management System");
        heading.setBounds(340, 155, 450, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        img.add(heading);

        JButton addBtn = new JButton("Add Employee");
        addBtn.setBounds(335, 270, 160, 40);
        addBtn.setForeground(Color.WHITE);
        addBtn.setBackground(Color.BLACK);
        addBtn.setFocusPainted(false);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                new AddEmployee();
            }
        });
        img.add(addBtn);

        JButton viewBtn = new JButton("View Employee");
        viewBtn.setBounds(565, 270, 160, 40);
        viewBtn.setForeground(Color.WHITE);
        viewBtn.setBackground(Color.BLACK);
        viewBtn.setFocusPainted(false);
        viewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                new ViewEmployee();
            }
        });
        img.add(viewBtn);

        JButton remBtn = new JButton("Remove Employee");
        remBtn.setBounds(440, 370, 160, 40);
        remBtn.setForeground(Color.WHITE);
        remBtn.setBackground(Color.BLACK);
        remBtn.setFocusPainted(false);
        remBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                new RemoveEmployee();
            }
        });
        img.add(remBtn);

        setSize(1120, 630);
        setLayout(null);
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
