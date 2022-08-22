package GUI;

import javax.swing.*;

import CourseManagementSystem.Admin;

import java.awt.*;
import java.util.Arrays;

public class MainGUI extends JFrame {

    public MainGUI() {
        setVisible(true);
        setTitle("Coursework");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(900, 700);
        setLocationRelativeTo(null);
        setLayout(null);

        add(mainPanel());
    }

    JPanel mainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1, 0, 30));
        mainPanel.setLocation(275, 150);
        mainPanel.setSize(350, 300);

        JLabel text = new JLabel("LOGIN AS:");
        text.setFont(new Font("Arial", Font.BOLD, 25));
        mainPanel.add(text);
        JButton stdButton = new JButton("Student");
        JButton insButton = new JButton("Instructor");
        JButton adButton = new JButton("Course Administrator");
        JButton exitButton = new JButton("Log Out ( EXIT )");
        for (JButton b : Arrays.asList(stdButton, insButton, adButton, exitButton)) {
            b.setFont(new Font("Arial", Font.BOLD, 15));
            b.setFocusable(false);
            b.setBackground(Color.darkGray);
            b.setForeground(Color.WHITE);
            b.setBorder(BorderFactory.createEtchedBorder());
            mainPanel.add(b);
        }

        stdButton.addActionListener(ae -> {
            StudentGUI std = new StudentGUI(); 
            std.stdLandPanel();
            dispose();
        });

        adButton.addActionListener(ae -> {
            AdminGUI ad = new AdminGUI();
            ad.adLandPanel();
            dispose();
        });

        insButton.addActionListener(ae -> {
            InstructorGUI ins = new InstructorGUI();
            ins.insLandPanel();
            dispose();
        });
 
        exitButton.addActionListener(ae -> {
            System.exit(0);
        });


        return mainPanel;
    }

    public static void main(String[] args) {
        new Admin().getFromCourseFile();
        new MainGUI();
    }
}
