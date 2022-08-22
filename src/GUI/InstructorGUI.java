package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import CourseManagementSystem.Admin;
import CourseManagementSystem.Course;
import CourseManagementSystem.Instructor;
import CourseManagementSystem.InstructorDetails;
import CourseManagementSystem.Student;
import CourseManagementSystem.StudentDetails;
import CourseManagementSystem.User;

public class InstructorGUI extends JFrame {
    public static Admin adm = new Admin();

    public static Instructor ins = new Instructor();

    public static Student std = new Student();

    InstructorGUI(){
        ins.getDataFromFile();
    }

    public void insLandPanel() {
        setTitle("Coursework");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(900, 700);
        setLocationRelativeTo(null);

        JPanel insLandPanel = new JPanel(new GridLayout(2, 2));

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
                InstructorGUI notRegis = new InstructorGUI();
                notRegis.insRegisterPanel(course);
                dispose();
            } else {
                String course = (String) comboBox.getSelectedItem();
                InstructorGUI regis = new InstructorGUI();
                regis.insLogInPanel(course);
                dispose();
            }
        });

        returnBtn.addActionListener(ae -> {
            new MainGUI();
        });

        insLandPanel.add(registrationDet);
        insLandPanel.add(coursesPanel);
        insLandPanel.add(selectCrsePanel);
        insLandPanel.add(actionsPanel);

        setVisible(true);
        add(insLandPanel);
    }

    public void insRegisterPanel(String courseShortName) {
        setTitle("Coursework");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        JPanel registerPanel = new JPanel(new GridLayout(1, 2));

        JPanel regLeftPanel = new JPanel(new GridLayout(10, 2));
        regLeftPanel.setBorder(BorderFactory.createTitledBorder("Instructor Details"));
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
        regLeftPanel.add(new JLabel("Modules Teaching (Enter Codes): "));
        JTextField modCode1 = new JTextField(20);
        regLeftPanel.add(modCode1);
        regLeftPanel.add(new JLabel());
        JTextField modCode2 = new JTextField(20);
        regLeftPanel.add(modCode2);
        regLeftPanel.add(new JLabel());
        JTextField modCode3 = new JTextField(20);
        regLeftPanel.add(modCode3);
        regLeftPanel.add(new JLabel());
        JTextField modCode4 = new JTextField(20);
        regLeftPanel.add(modCode4);
        regLeftPanel.add(new JLabel());
        regLeftPanel.add(new JLabel());
        JButton register = new JButton("Register");
        JButton cancel = new JButton("Cancel");
        regLeftPanel.add(register);
        regLeftPanel.add(cancel);

        register.addActionListener(ae -> {
            String mods[] = new String[4];
            mods[0] = modCode1.getText();
            mods[1] = modCode2.getText();
            mods[2] = modCode3.getText();
            mods[3] = modCode4.getText();
            String firstName = fname.getText();
            String lastName = lname.getText();
            String emailAdd = email.getText();

            for (int i = 0; i < 4; i++) {
                if (mods[i].isEmpty()) {
                    mods[i] = "null";
                }
            }

            if (firstName.isEmpty() || lastName.isEmpty() || emailAdd.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all of the fields.", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                String id = new Instructor().addNewInstructor(firstName, lastName, emailAdd, courseShortName.trim(), mods);
                JOptionPane.showMessageDialog(this, "You have been registered as an Instructor. \n Your ID is " + id, "Congratulations!!",
                        JOptionPane.INFORMATION_MESSAGE);
                new InstructorGUI().insLandPanel();
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

    public void insLogInPanel(String course) {
        setTitle("Instrcutor Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel insLogInPanel = new JPanel(new GridLayout(4, 1));
        insLogInPanel.setBorder(BorderFactory.createTitledBorder("Instructor Login Panel"));
        insLogInPanel.add(new JLabel("Enter your Instructor Code:"));
        JTextField insID = new JTextField();
        insLogInPanel.add(insID);
        insLogInPanel.add(new JLabel());
        JButton logInBtn = new JButton("Log IN");
        insLogInPanel.add(logInBtn);
        
        logInBtn.addActionListener(ae -> {
            String id = insID.getText();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter your instructor code.");
            } else {
                new Instructor();
                Boolean flag = true;
                Iterator<InstructorDetails> itr = User.insTreeSet.iterator();
                while(itr.hasNext()){
                    if(itr.next().getInsId().equals(id)){
                        new InstructorGUI().instructorPanel(course, id);
                        flag = false;
                        dispose();
                    } 
                }
                if(flag){
                    JOptionPane.showMessageDialog(this, "The ID you entered could not be found. Please check and try again. The Instructor IDs are in the Instructor file in the Files folder.", "ID not found", JOptionPane.OK_OPTION);
                }
            }
        });

        setVisible(true);
        add(insLogInPanel);
    }

    public void instructorPanel(String courseShortName, String id){
        setTitle("Coursework");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        JPanel insPanel = new JPanel(new GridLayout(1, 2));

        JPanel leftPanel = new JPanel(new GridLayout(2, 1));

        JPanel insDet = new JPanel(new GridLayout(10, 2));
        insDet.setBorder(BorderFactory.createTitledBorder("Instructor Details"));
        String mods[] = ins.getInstructor(id, insDet);

        JPanel actionsPanel = new JPanel(new GridLayout(4, 1));
        actionsPanel.add(new JLabel());
        
        JPanel marksPanel = new JPanel(new GridLayout(1, 2, 0, 10));
        marksPanel.setBorder(BorderFactory.createTitledBorder("Give Marks to Students"));
        JComboBox<String> modsCombo = new JComboBox<>(mods);
        JButton giveMarks = new JButton("Give Marks");


        giveMarks.addActionListener(ae -> {
            String modCode = (String)modsCombo.getSelectedItem();
            giveMarksPanel(modCode);

        });




        marksPanel.add(modsCombo);
        marksPanel.add(giveMarks);
        actionsPanel.add(marksPanel);

        actionsPanel.add(new JLabel());

        JButton returnBtn = new JButton("Return to Main Panel");
        actionsPanel.add(returnBtn);

        returnBtn.addActionListener(ae -> {
            new MainGUI();
            dispose();
        });

        JPanel stdDet = new JPanel(new GridLayout(4, 1));
        stdDet.setBorder(BorderFactory.createTitledBorder("Students Details"));
        
        JPanel mod1st = new JPanel(new GridLayout(1, 1));
        mod1st.setBorder(BorderFactory.createTitledBorder(mods[0]));
        JPanel mod1stStd = new JPanel(new GridLayout(5, 1));
        JPanel mod1stStdPanel = new JPanel(new GridLayout(1, 3));
        mod1stStdPanel.add(new JLabel("Student ID"));
        mod1stStdPanel.add(new JLabel("Student Name"));
        mod1stStdPanel.add(new JLabel("Email"));
        mod1stStd.add(mod1stStdPanel);
        new User().showStdOfMods(courseShortName, mods[0], mod1stStd);
        JScrollPane spMod1st = new JScrollPane(mod1stStd);
        mod1st.add(spMod1st);

        JPanel mod2nd = new JPanel(new GridLayout(1, 1));
        mod2nd.setBorder(BorderFactory.createTitledBorder(mods[1]));
        JPanel mod2ndStd = new JPanel(new GridLayout(5, 1));
        JPanel mod2ndStdPanel = new JPanel(new GridLayout(1, 3));
        mod2ndStdPanel.add(new JLabel("Student ID"));
        mod2ndStdPanel.add(new JLabel("Student Name"));
        mod2ndStdPanel.add(new JLabel("Email"));
        mod2ndStd.add(mod2ndStdPanel);
        new User().showStdOfMods(courseShortName, mods[1], mod2ndStd);
        JScrollPane spMod2nd = new JScrollPane(mod2ndStd);
        mod2nd.add(spMod2nd);

        JPanel mod3rd = new JPanel(new GridLayout(1, 1));
        mod3rd.setBorder(BorderFactory.createTitledBorder(mods[2]));
        JPanel mod3rdStd = new JPanel(new GridLayout(5, 1));
        JPanel mod3rdStdPanel = new JPanel(new GridLayout(1, 3));
        mod3rdStdPanel.add(new JLabel("Student ID"));
        mod3rdStdPanel.add(new JLabel("Student Name"));
        mod3rdStdPanel.add(new JLabel("Email"));
        mod3rdStd.add(mod3rdStdPanel);
        new User().showStdOfMods(courseShortName, mods[2], mod3rdStd);
        JScrollPane spMod3rd = new JScrollPane(mod3rdStd);
        mod3rd.add(spMod3rd);

        JPanel mod4th = new JPanel(new GridLayout(1, 1));
        mod4th.setBorder(BorderFactory.createTitledBorder(mods[3]));
        JPanel mod4thStd = new JPanel(new GridLayout(5, 1));
        JPanel mod4thStdPanel = new JPanel(new GridLayout(1, 3));
        mod4thStdPanel.add(new JLabel("Student ID"));
        mod4thStdPanel.add(new JLabel("Student Name"));
        mod4thStdPanel.add(new JLabel("Email"));
        mod4thStd.add(mod4thStdPanel);
        new User().showStdOfMods(courseShortName, mods[3], mod4thStd);
        JScrollPane spMod4th = new JScrollPane(mod4thStd);
        mod4th.add(spMod4th);
        
        

        stdDet.add(mod1st);
        stdDet.add(mod2nd);
        stdDet.add(mod3rd);
        stdDet.add(mod4th);

        leftPanel.add(insDet);
        leftPanel.add(actionsPanel);
        insPanel.add(leftPanel);
        insPanel.add(stdDet);

        setVisible(true);
        add(insPanel);
    }


    public void giveMarksPanel(String modCode){
        setTitle("Give Marks");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        JPanel giveMarksPanel = new JPanel(new GridLayout(5, 1));

        int sem = 0;
        for(Course crse: adm.getAllCourses()){
            for(CourseManagementSystem.Module mod : crse.getAllModules()) {
                if(mod.getModCode().equals(modCode)){
                    sem = mod.getSem(); 
                }
            }
        }

        for(StudentDetails stdDet : new User().getStdTreeSet()){
            if(stdDet.getStdSem() == sem){
                
                String name = stdDet.getStdFirstName() + " " + stdDet.getStdLastName();
                JPanel stds = new JPanel(new GridLayout(1, 4));
                stds.add(new JLabel(stdDet.getStdId()));
                stds.add(new JLabel(name));
                JTextField marks = new JTextField(20);
                stds.add(marks);
                JPanel panel = new JPanel();
                panel.add(stds);
                JButton mark = new JButton("Mark");
                giveMarksPanel.add(panel);

                mark.addActionListener(ae -> {
                    int markGiven = Integer.parseInt(marks.getText()) ;
                    ins.giveMarks(stdDet.getStdId(), modCode, markGiven);
                    JOptionPane.showMessageDialog(this, "Marks Given");
                });
                // System.out.println(stdDet.getStdFirstName() + " " + stdDet.getStdLastName() + " " + stdDet.getStdEmail() + " Course: " + stdDet.getStdCourse() + " Level: " + stdDet.getStdLevel() + " Sem: " + stdDet.getStdSem());
            }
        }

        setVisible(true);
        add(giveMarksPanel);
    }

    public static void main(String[] args) {
        new Student().getStdDataFromFile();
        adm.getFromCourseFile();
        ins.getDataFromFile();
        InstructorGUI insGUI = new InstructorGUI();
        insGUI.insLandPanel();
    }
}
