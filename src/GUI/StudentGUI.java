package GUI;

import javax.swing.*;

import CourseManagementSystem.Admin;
import CourseManagementSystem.Instructor;
import CourseManagementSystem.Student;
import CourseManagementSystem.StudentDetails;
import CourseManagementSystem.User;

import java.awt.*;
import java.io.IOException;
import java.util.Iterator;

public class StudentGUI extends JFrame {
    public static Admin adm = new Admin();

    public static Instructor ins = new Instructor();

    public static Student std = new Student();

    StudentGUI() {
        std.getStdDataFromFile();
    }

    public void stdLandPanel() {
        setTitle("Coursework");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(900, 700);
        setLocationRelativeTo(null);

        JPanel stdLandPanel = new JPanel(new GridLayout(2, 2));

        JPanel registrationDet = new JPanel(new GridLayout(4, 1));
        registrationDet.setBorder(BorderFactory.createTitledBorder("Registration Status"));
        registrationDet.add(new JLabel("Select your Resgistration Status:"));
        JRadioButton notReg = new JRadioButton("Not Registered");
        JRadioButton reg = new JRadioButton("Already Registered");
        ButtonGroup btnGrp = new ButtonGroup();
        btnGrp.add(notReg);
        btnGrp.add(reg);
        notReg.setSelected(true);
        registrationDet.add(notReg);
        registrationDet.add(reg);

        JPanel coursesPanel = new JPanel(new GridLayout(4, 1));
        coursesPanel.setBorder(BorderFactory.createTitledBorder("Available Courses"));
        int count = adm.getAllCourses().size();
        String courses[] = new String[count];
        for (int i = 0; i < count; i++) {
            String crseStatus;
            Boolean status = adm.getAllCourses().get(i).getCourseStatus();
            if (status) {
                crseStatus = "Available";
            } else {
                crseStatus = "Cancelled";
            }
            coursesPanel.add(new JLabel(i + 1 + ". " + adm.getAllCourses().get(i).getCourseName() + " ( "
                    + adm.getAllCourses().get(i).getCourseShortName() + " ) [ " + crseStatus + " ]"));
            courses[i] = adm.getAllCourses().get(i).getCourseShortName();
        }

        JPanel selectCrsePanel = new JPanel(new GridLayout(3, 1));
        selectCrsePanel.setBorder(BorderFactory.createTitledBorder("Choose your course"));
        selectCrsePanel.add(new JPanel());
        JComboBox<String> comboBox = new JComboBox<>(courses);
        selectCrsePanel.add(comboBox);
        JPanel actionsPanel = new JPanel(new GridLayout(2, 1, 0, 20));
        actionsPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        JButton confirmBtn = new JButton("Confirm");
        JButton returnBtn = new JButton("Return to Main Panel");
        actionsPanel.add(confirmBtn);
        actionsPanel.add(returnBtn);

        confirmBtn.addActionListener(ae -> {
            if (notReg.isSelected()) {
                String course = (String) comboBox.getSelectedItem();
                StudentGUI notRegis = new StudentGUI();
                notRegis.stdRegisterPanel(course);
                dispose();
            } else {
                String course = (String) comboBox.getSelectedItem();
                StudentGUI regis = new StudentGUI();
                regis.stdLogInPanel(course);
                dispose();
            }
        });

        returnBtn.addActionListener(ae -> {
            new MainGUI();
        });

        stdLandPanel.add(registrationDet);
        stdLandPanel.add(coursesPanel);
        stdLandPanel.add(selectCrsePanel);
        stdLandPanel.add(actionsPanel);

        setVisible(true);
        add(stdLandPanel);

    }

    public void stdLogInPanel(String course) {
        setTitle("Student Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel stdLogInPanel = new JPanel(new GridLayout(4, 1));
        stdLogInPanel.setBorder(BorderFactory.createTitledBorder("Student Login Panel"));
        stdLogInPanel.add(new JLabel("Enter your Student Code:"));
        JTextField insID = new JTextField();
        stdLogInPanel.add(insID);
        stdLogInPanel.add(new JLabel());
        JButton logInBtn = new JButton("Log IN");
        stdLogInPanel.add(logInBtn);

        logInBtn.addActionListener(ae -> {
            // std.showAllStdDetails();
            String id = insID.getText();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter your instructor code.");
            } else {
                new Student();
                Boolean flag = true;
                Iterator<StudentDetails> itr = User.stdTreeSet.iterator();
                while (itr.hasNext()) {
                    if (itr.next().getStdId().equals(id)) {
                        new StudentGUI().studentPanel(id);
                        flag = false;
                        dispose();
                    }
                }
                if (flag) {
                    JOptionPane.showMessageDialog(this,
                            "The ID you entered could not be found. Please check and try again. The Instructor IDs are in the Instructor file in the Files folder.",
                            "ID not found", JOptionPane.OK_OPTION);
                }
            }
        });

        setVisible(true);
        add(stdLogInPanel);
    }

    public void studentPanel(String id) {
        setTitle("Coursework");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        JPanel stdPanel = new JPanel(new GridLayout(1, 2));

        JPanel leftPanel = new JPanel(new GridLayout(2, 1));

        JPanel stdDet = new JPanel(new GridLayout(12, 2));
        stdDet.setBorder(BorderFactory.createTitledBorder("Student Details"));
        String mods[] = std.getStudent(id, stdDet);

        JPanel actionsPanel = new JPanel(new GridLayout(4, 1));
        actionsPanel.add(new JLabel());

        JButton getResult = new JButton("Get Result");
        actionsPanel.add(getResult);


        getResult.addActionListener(ae -> {
            std.viewResult(id);
            JOptionPane.showMessageDialog(this, "The Result has been printed in the console.");
        });

        actionsPanel.add(new JLabel());

        JButton returnBtn = new JButton("Return to Main Panel");
        actionsPanel.add(returnBtn);

        returnBtn.addActionListener(ae -> {
            new MainGUI();
            dispose();
        });

        JPanel insDet = new JPanel(new GridLayout(4, 1));
        stdDet.setBorder(BorderFactory.createTitledBorder("Instructor Details"));

        JPanel mod1st = new JPanel(new GridLayout(1, 1));
        mod1st.setBorder(BorderFactory.createTitledBorder(mods[0]));
        JPanel mod1stStd = new JPanel(new GridLayout(5, 1));
        JPanel mod1stStdPanel = new JPanel(new GridLayout(1, 3));
        mod1stStdPanel.add(new JLabel("Instructor ID"));
        mod1stStdPanel.add(new JLabel("Instructor Name"));
        mod1stStdPanel.add(new JLabel("Email"));
        mod1stStd.add(mod1stStdPanel);
        new User().showInsOfMods(mods[0], mod1stStd);
        JScrollPane spMod1st = new JScrollPane(mod1stStd);
        mod1st.add(spMod1st);

        JPanel mod2nd = new JPanel(new GridLayout(1, 1));
        mod2nd.setBorder(BorderFactory.createTitledBorder(mods[1]));
        JPanel mod2ndStd = new JPanel(new GridLayout(5, 1));
        JPanel mod2ndStdPanel = new JPanel(new GridLayout(1, 3));
        mod2ndStdPanel.add(new JLabel("Instructor ID"));
        mod2ndStdPanel.add(new JLabel("Instructor Name"));
        mod2ndStdPanel.add(new JLabel("Email"));
        mod2ndStd.add(mod2ndStdPanel);
        new User().showInsOfMods(mods[1], mod2ndStd);
        JScrollPane spMod2nd = new JScrollPane(mod2ndStd);
        mod2nd.add(spMod2nd);

        JPanel mod3rd = new JPanel(new GridLayout(1, 1));
        mod3rd.setBorder(BorderFactory.createTitledBorder(mods[2]));
        JPanel mod3rdStd = new JPanel(new GridLayout(5, 1));
        JPanel mod3rdStdPanel = new JPanel(new GridLayout(1, 3));
        mod3rdStdPanel.add(new JLabel("Instructor ID"));
        mod3rdStdPanel.add(new JLabel("Instructor Name"));
        mod3rdStdPanel.add(new JLabel("Email"));
        mod3rdStd.add(mod3rdStdPanel);
        new User().showInsOfMods(mods[2], mod3rdStd);
        JScrollPane spMod3rd = new JScrollPane(mod3rdStd);
        mod3rd.add(spMod3rd);

        JPanel mod4th = new JPanel(new GridLayout(1, 1));
        mod4th.setBorder(BorderFactory.createTitledBorder(mods[3]));
        JPanel mod4thStd = new JPanel(new GridLayout(5, 1));
        JPanel mod4thStdPanel = new JPanel(new GridLayout(1, 3));
        mod4thStdPanel.add(new JLabel("Instructor ID"));
        mod4thStdPanel.add(new JLabel("Instructor Name"));
        mod4thStdPanel.add(new JLabel("Email"));
        mod4thStd.add(mod4thStdPanel);
        new User().showInsOfMods(mods[3], mod4thStd);
        JScrollPane spMod4th = new JScrollPane(mod4thStd);
        mod4th.add(spMod4th);

        insDet.add(mod1st);
        insDet.add(mod2nd);
        insDet.add(mod3rd);
        insDet.add(mod4th);

        leftPanel.add(stdDet);
        leftPanel.add(actionsPanel);
        stdPanel.add(leftPanel);
        stdPanel.add(insDet);

        setVisible(true);
        add(stdPanel);
    }

    public void stdRegisterPanel(String courseShortName) {
        setTitle("Coursework");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        JPanel registerPanel = new JPanel(new GridLayout(1, 2));

        JPanel regLeftPanel = new JPanel(new GridLayout(9, 2));
        regLeftPanel.setBorder(BorderFactory.createTitledBorder("Instructor Details"));
        regLeftPanel.add(new JLabel());
        regLeftPanel.add(new JLabel());
        regLeftPanel.add(new JLabel("First Name: "));
        JTextField fname = new JTextField(20);
        regLeftPanel.add(fname);
        regLeftPanel.add(new JLabel("Last Name: "));
        JTextField lname = new JTextField(20);
        regLeftPanel.add(lname);
        regLeftPanel.add(new JLabel("Email: "));
        JTextField email = new JTextField(20);
        regLeftPanel.add(email);
        regLeftPanel.add(new JLabel("Course: "));
        regLeftPanel.add(new JLabel(courseShortName));
        regLeftPanel.add(new JLabel("Sem: "));
        String semesters[] = {"1", "2", "3", "4", "5", "6"};
        JComboBox<String> sems = new JComboBox<>(semesters);
        regLeftPanel.add(sems);
        regLeftPanel.add(new JLabel());
        regLeftPanel.add(new JLabel());
        regLeftPanel.add(new JLabel());
        regLeftPanel.add(new JLabel());

        JButton register = new JButton("Register");
        JButton cancel = new JButton("Cancel");
        regLeftPanel.add(register);
        regLeftPanel.add(cancel);

        register.addActionListener(ae -> {
            String firstName = fname.getText();
            String lastName = lname.getText();
            String emailAdd = email.getText();
            int sem = Integer.parseInt((String) sems.getSelectedItem());
            int lvl = 4;
            if(sem == 1 || sem == 2){
                lvl = 4;
            } else if(sem == 3 || sem == 4){
                lvl = 5;
            } else if(sem == 5 || sem == 6){
                lvl = 6;
            }

            if (firstName.isEmpty() || lastName.isEmpty() || emailAdd.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all of the fields.", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    String id = new Student().addNewStudent(firstName, lastName, emailAdd, courseShortName.trim(), lvl, sem);
                    JOptionPane.showMessageDialog(this, "You have been registered as a Student. \nYour id is : " + id, "Congratulations!!",
                        JOptionPane.INFORMATION_MESSAGE);
                    new StudentGUI().stdLandPanel();
                    dispose();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        cancel.addActionListener(ae -> {
            new InstructorGUI().insLandPanel();
            dispose();
        });

        JPanel regRightPanel = new JPanel(new GridLayout(1, 1));
        regRightPanel.setBorder(BorderFactory.createTitledBorder("Module Details"));

        JPanel regRightPanelData = new JPanel(new BorderLayout());
        JPanel crsePanel = new JPanel();
        JPanel modsPanel = new JPanel(new GridLayout(27, 3, 5, 10));
        adm.getModulesOf(courseShortName, crsePanel, modsPanel);
        regRightPanelData.add(crsePanel, BorderLayout.NORTH);
        regRightPanelData.add(modsPanel, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(regRightPanelData);

        regRightPanel.add(scrollPane);

        registerPanel.add(regLeftPanel);
        registerPanel.add(regRightPanel);

        setVisible(true);
        add(registerPanel);
    }

    public static void main(String[] args) {
        StudentGUI stdGUI = new StudentGUI();
        adm.getFromCourseFile();
        std.getStdDataFromFile();
        stdGUI.stdLandPanel();
    }

}
