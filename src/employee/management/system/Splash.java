package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Splash extends JFrame {

    Splash() {
        setTitle("Employee Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        URL imgUrl = ClassLoader.getSystemResource("icons/front.gif");
        if (imgUrl != null) {
            ImageIcon i1 = new ImageIcon(imgUrl);
            Image i2 = i1.getImage().getScaledInstance(1170, 650, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel image = new JLabel(i3);
            image.setBounds(0, 0, 1170, 650);
            add(image);
        } else {
            JLabel label = new JLabel("Employee Management System", SwingConstants.CENTER);
            label.setFont(new Font("Raleway", Font.BOLD, 36));
            label.setBounds(0, 0, 1170, 650);
            add(label);
        }

        setSize(1170, 650);
        setLayout(null);
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);

        try {
            Thread.sleep(4000);
            setVisible(false);
            dispose();
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
            setVisible(false);
            dispose();
            new Login();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Splash());
    }
}
