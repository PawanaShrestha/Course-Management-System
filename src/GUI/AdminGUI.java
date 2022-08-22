package GUI;

import CourseManagementSystem.*;

import javax.swing.*;

import java.awt.*;

public class AdminGUI extends JFrame {
    public static AdminGUI ad = new AdminGUI();
    public static Admin adm = new Admin();

    AdminGUI self = this;
 
    JButton addBtn, editBtn, cancelBtn, dltBtn, activateBtn, makeRsltBtn, backBtn;

    JTextField nameOfCourse, courseShortName, mod1Code, mod1Name, mod2Code, mod2Name, mod3Code, mod3Name, mod4Code,
            mod4Name, mod5Code, mod5Name, mod6Code, mod6Name, mod7Code, mod7Name, mod8Code, mod8Name, mod9Code,
            mod9Name, mod10Code, mod10Name, mod11Code, mod11Name, mod12Code, mod12Name, mod13Code, mod13Name, mod14Code,
            mod14Name, mod15Code, mod15Name, mod16Code, mod16Name, mod17Code, mod17Name, mod18Code, mod18Name,
            mod19Code, mod19Name, mod20Code, mod20Name, mod21Code, mod21Name, mod22Code, mod22Name, mod23Code,
            mod23Name, mod24Code, mod24Name;

    void adLandPanel() {
        setTitle("Coursework");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(900, 700);
        setLocationRelativeTo(null);

        JPanel adLandPanel = new JPanel();
        adLandPanel.setLayout(new GridLayout(2, 1));

        JPanel topPanel = new JPanel(new GridLayout(1, 2));

        JPanel topLeftPanel = new JPanel(new GridLayout(4, 1));
        topLeftPanel.setBorder(BorderFactory.createTitledBorder("Available Courses"));
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
            topLeftPanel.add(new JLabel(i + 1 + ". " + adm.getAllCourses().get(i).getCourseName() + " ( "
                    + adm.getAllCourses().get(i).getCourseShortName() + " ) [ " + crseStatus + " ]"));
            courses[i] = adm.getAllCourses().get(i).getCourseShortName();
        }

        JPanel topRightPanel = new JPanel(new GridLayout(3, 1));
        topRightPanel.setBorder(BorderFactory.createTitledBorder("Choose your course"));
        topRightPanel.add(new JPanel());
        JComboBox<String> comboBox = new JComboBox<>(courses);
        topRightPanel.add(comboBox);

        topPanel.add(topLeftPanel);
        topPanel.add(topRightPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 4, 20, 20));
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        addBtn = new JButton("Add new course");
        editBtn = new JButton("Edit selected course");
        dltBtn = new JButton("Delete selected course");
        cancelBtn = new JButton("Cancel selected course");
        activateBtn = new JButton("Activate selected course");
        makeRsltBtn = new JButton("Make Result");
        backBtn = new JButton("Go Back to Main Panel");
        
        addBtn.addActionListener(ae -> {
            AdminGUI course = new AdminGUI();
            course.coursePanel();
            dispose();
        });

        editBtn.addActionListener(ae -> {
            String crseName = (String) comboBox.getSelectedItem();
            AdminGUI edit = new AdminGUI();
            edit.editPanel(crseName);
            dispose();
        });

        dltBtn.addActionListener(ae -> {
            int ans = JOptionPane.showConfirmDialog(self, "Are you sure you want to delete the course?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (ans == JOptionPane.YES_OPTION) {
                String crseName = (String) comboBox.getSelectedItem();
                adm.deleteCourse(crseName);
                AdminGUI adLand = new AdminGUI();
                dispose();
                adLand.adLandPanel();
            }
        });

        cancelBtn.addActionListener(ae -> {
            int ans = JOptionPane.showConfirmDialog(self, "Are you sure you want to cancel the course?",
                    "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
            if (ans == JOptionPane.YES_OPTION) {
                String crseName = (String) comboBox.getSelectedItem();
                adm.cancelCourse(crseName);
                dispose();
                AdminGUI adLand = new AdminGUI();
                adLand.adLandPanel();
            }
        });

        activateBtn.addActionListener(ae -> {
            int ans = JOptionPane.showConfirmDialog(self, "Are you sure you want to activate the course?",
                    "Confirm Activation", JOptionPane.YES_NO_OPTION);
            if (ans == JOptionPane.YES_OPTION) {
                String crseName = (String) comboBox.getSelectedItem();
                adm.activateCourse(crseName);
                dispose();
                AdminGUI adLand = new AdminGUI();
                adLand.adLandPanel();
            }
        });

        makeRsltBtn.addActionListener(ae -> {
            AdminGUI rslt = new AdminGUI();
            rslt.makeResult();
        });

        backBtn.addActionListener(ae -> {
            new MainGUI();
        });

        bottomPanel.add(addBtn);
        bottomPanel.add(editBtn);
        bottomPanel.add(dltBtn);
        bottomPanel.add(cancelBtn);
        bottomPanel.add(activateBtn);
        bottomPanel.add(makeRsltBtn);
        bottomPanel.add(new JLabel());
        bottomPanel.add(backBtn);

        adLandPanel.add(topPanel);
        adLandPanel.add(bottomPanel);

        setVisible(true);

        add(adLandPanel);
    }

    public void coursePanel() {
        setTitle("Coursework");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(900, 700);
        setLocationRelativeTo(null);

        JPanel coursePanel = new JPanel();
        coursePanel.setLayout(new BorderLayout());

        JPanel courseName = new JPanel(new FlowLayout(FlowLayout.LEADING));
        courseName.add(new JLabel("Course Name: "));
        nameOfCourse = new JTextField(45);
        nameOfCourse.setPreferredSize(new Dimension(50, 40));
        courseName.add(nameOfCourse);
        courseName.setPreferredSize(new Dimension(100, 50));
        courseName.add(new JLabel("Course Short Name: "));
        courseShortName = new JTextField(20);
        courseShortName.setPreferredSize(new Dimension(50, 20));
        courseName.add(courseShortName);
        coursePanel.add(courseName, BorderLayout.NORTH);

        JPanel modulesPanel = new JPanel();
        modulesPanel.setBorder(BorderFactory.createTitledBorder("Modules"));
        modulesPanel.setLayout(new GridLayout(2, 2, 5, 5));

        JPanel year1 = new JPanel(new GridLayout(1, 2, 2, 2));
        year1.setBorder(BorderFactory.createTitledBorder("Year 1"));

        JPanel sem1 = new JPanel(new GridLayout(9, 1));
        sem1.add(new JLabel("---Code---------Name---------------------"));

        sem1.add(new JLabel("Module 1"));
        JPanel mod1 = new JPanel();
        mod1Code = new JTextField(5);
        mod1Name = new JTextField(12);
        mod1.add(mod1Code);
        mod1.add(mod1Name);
        sem1.add(mod1);

        sem1.add(new JLabel("Module 2"));
        JPanel mod2 = new JPanel();
        mod2Code = new JTextField(5);
        mod2Name = new JTextField(12);
        mod2.add(mod2Code);
        mod2.add(mod2Name);
        sem1.add(mod2);

        sem1.add(new JLabel("Module 3"));
        JPanel mod3 = new JPanel();
        mod3Code = new JTextField(5);
        mod3Name = new JTextField(12);
        mod3.add(mod3Code);
        mod3.add(mod3Name);
        sem1.add(mod3);

        sem1.add(new JLabel("Module 4"));
        JPanel mod4 = new JPanel();
        mod4Code = new JTextField(5);
        mod4Name = new JTextField(12);
        mod4.add(mod4Code);
        mod4.add(mod4Name);
        sem1.add(mod4);

        JPanel sem2 = new JPanel(new GridLayout(9, 1));
        sem2.add(new JLabel("---Code---------Name---------------------"));

        sem2.add(new JLabel("Module 5"));
        JPanel mod5 = new JPanel();
        mod5Code = new JTextField(5);
        mod5Name = new JTextField(12);
        mod5.add(mod5Code);
        mod5.add(mod5Name);
        sem2.add(mod5);

        sem2.add(new JLabel("Module 6"));
        JPanel mod6 = new JPanel();
        mod6Code = new JTextField(5);
        mod6Name = new JTextField(12);
        mod6.add(mod6Code);
        mod6.add(mod6Name);
        sem2.add(mod6);

        sem2.add(new JLabel("Module 7"));
        JPanel mod7 = new JPanel();
        mod7Code = new JTextField(5);
        mod7Name = new JTextField(12);
        mod7.add(mod7Code);
        mod7.add(mod7Name);
        sem2.add(mod7);

        sem2.add(new JLabel("Module 8"));
        JPanel mod8 = new JPanel();
        mod8Code = new JTextField(5);
        mod8Name = new JTextField(12);
        mod8.add(mod8Code);
        mod8.add(mod8Name);
        sem2.add(mod8);

        JPanel year2 = new JPanel(new GridLayout(1, 2, 2, 2));
        year2.setBorder(BorderFactory.createTitledBorder("Year 2"));

        JPanel sem3 = new JPanel(new GridLayout(9, 1));
        sem3.add(new JLabel("---Code---------Name---------------------"));

        sem3.add(new JLabel("Module 9"));
        JPanel mod9 = new JPanel();
        mod9Code = new JTextField(5);
        mod9Name = new JTextField(12);
        mod9.add(mod9Code);
        mod9.add(mod9Name);
        sem3.add(mod9);

        sem3.add(new JLabel("Module 10"));
        JPanel mod10 = new JPanel();
        mod10Code = new JTextField(5);
        mod10Name = new JTextField(12);
        mod10.add(mod10Code);
        mod10.add(mod10Name);
        sem3.add(mod10);

        sem3.add(new JLabel("Module 11"));
        JPanel mod11 = new JPanel();
        mod11Code = new JTextField(5);
        mod11Name = new JTextField(12);
        mod11.add(mod11Code);
        mod11.add(mod11Name);
        sem3.add(mod11);

        sem3.add(new JLabel("Module 12"));
        JPanel mod12 = new JPanel();
        mod12Code = new JTextField(5);
        mod12Name = new JTextField(12);
        mod12.add(mod12Code);
        mod12.add(mod12Name);
        sem3.add(mod12);

        JPanel sem4 = new JPanel(new GridLayout(9, 1));
        sem4.add(new JLabel("---Code---------Name---------------------"));

        sem4.add(new JLabel("Module 13"));
        JPanel mod13 = new JPanel();
        mod13Code = new JTextField(5);
        mod13Name = new JTextField(12);
        mod13.add(mod13Code);
        mod13.add(mod13Name);
        sem4.add(mod13);

        sem4.add(new JLabel("Module 14"));
        JPanel mod14 = new JPanel();
        mod14Code = new JTextField(5);
        mod14Name = new JTextField(12);
        mod14.add(mod14Code);
        mod14.add(mod14Name);
        sem4.add(mod14);

        sem4.add(new JLabel("Module 15"));
        JPanel mod15 = new JPanel();
        mod15Code = new JTextField(5);
        mod15Name = new JTextField(12);
        mod15.add(mod15Code);
        mod15.add(mod15Name);
        sem4.add(mod15);

        sem4.add(new JLabel("Module 16"));
        JPanel mod16 = new JPanel();
        mod16Code = new JTextField(5);
        mod16Name = new JTextField(12);
        mod16.add(mod16Code);
        mod16.add(mod16Name);
        sem4.add(mod16);

        JPanel year3 = new JPanel(new GridLayout(1, 2, 2, 2));
        year3.setBorder(BorderFactory.createTitledBorder("Year 3"));

        JPanel sem5 = new JPanel(new GridLayout(9, 1));
        sem5.add(new JLabel("---Code---------Name---------------------"));

        sem5.add(new JLabel("Module 17"));
        JPanel mod17 = new JPanel();
        mod17Code = new JTextField(5);
        mod17Name = new JTextField(12);
        mod17.add(mod17Code);
        mod17.add(mod17Name);
        sem5.add(mod17);

        sem5.add(new JLabel("Module 18"));
        JPanel mod18 = new JPanel();
        mod18Code = new JTextField(5);
        mod18Name = new JTextField(12);
        mod18.add(mod18Code);
        mod18.add(mod18Name);
        sem5.add(mod18);

        sem5.add(new JLabel("Module 19 (Optional)"));
        JPanel mod19 = new JPanel();
        mod19Code = new JTextField(5);
        mod19Name = new JTextField(12);
        mod19.add(mod19Code);
        mod19.add(mod19Name);
        sem5.add(mod19);

        sem5.add(new JLabel("Module 20 (Optional)"));
        JPanel mod20 = new JPanel();
        mod20Code = new JTextField(5);
        mod20Name = new JTextField(12);
        mod20.add(mod20Code);
        mod20.add(mod20Name);
        sem5.add(mod20);

        JPanel sem6 = new JPanel(new GridLayout(9, 1));
        sem6.add(new JLabel("---Code---------Name---------------------"));

        sem6.add(new JLabel("Module 21"));
        JPanel mod21 = new JPanel();
        mod21Code = new JTextField(5);
        mod21Name = new JTextField(12);
        mod21.add(mod21Code);
        mod21.add(mod21Name);
        sem6.add(mod21);

        sem6.add(new JLabel("Module 22"));
        JPanel mod22 = new JPanel();
        mod22Code = new JTextField(5);
        mod22Name = new JTextField(12);
        mod22.add(mod22Code);
        mod22.add(mod22Name);
        sem6.add(mod22);

        sem6.add(new JLabel("Module 23 (Optional)"));
        JPanel mod23 = new JPanel();
        mod23Code = new JTextField(5);
        mod23Name = new JTextField(12);
        mod23.add(mod23Code);
        mod23.add(mod23Name);
        sem6.add(mod23);

        sem6.add(new JLabel("Module 24 (Optional)"));
        JPanel mod24 = new JPanel();
        mod24Code = new JTextField(5);
        mod24Name = new JTextField(12);
        mod24.add(mod24Code);
        mod24.add(mod24Name);
        sem6.add(mod24);

        year1.add(sem1);
        year1.add(sem2);
        year2.add(sem3);
        year2.add(sem4);
        year3.add(sem5);
        year3.add(sem6);

        JPanel actions = new JPanel(new GridLayout(2, 1, 5, 5));
        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");

        confirm.addActionListener(ae -> {

            try {
                String crseName, crseShortName;
                String courseModCodes[] = new String[24], courseModNames[] = new String[24];
                // ArrayList<String> courseModCodes = new ArrayList<>();
                // ArrayList<String> courseModNames = new ArrayList<>();

                crseName = nameOfCourse.getText();
                crseShortName = courseShortName.getText();

                courseModCodes[0] = mod1Code.getText();
                courseModCodes[1] = mod2Code.getText();
                courseModCodes[2] = mod3Code.getText();
                courseModCodes[3] = mod4Code.getText();
                courseModCodes[4] = mod5Code.getText();
                courseModCodes[5] = mod6Code.getText();
                courseModCodes[6] = mod7Code.getText();
                courseModCodes[7] = mod8Code.getText();
                courseModCodes[8] = mod9Code.getText();
                courseModCodes[9] = mod10Code.getText();
                courseModCodes[10] = mod11Code.getText();
                courseModCodes[11] = mod12Code.getText();
                courseModCodes[12] = mod13Code.getText();
                courseModCodes[13] = mod14Code.getText();
                courseModCodes[14] = mod15Code.getText();
                courseModCodes[15] = mod16Code.getText();
                courseModCodes[16] = mod17Code.getText();
                courseModCodes[17] = mod18Code.getText();
                courseModCodes[18] = mod19Code.getText();
                courseModCodes[19] = mod20Code.getText();
                courseModCodes[20] = mod21Code.getText();
                courseModCodes[21] = mod22Code.getText();
                courseModCodes[22] = mod23Code.getText();
                courseModCodes[23] = mod24Code.getText();

                courseModNames[0] = mod1Name.getText();
                courseModNames[1] = mod2Name.getText();
                courseModNames[2] = mod3Name.getText();
                courseModNames[3] = mod4Name.getText();
                courseModNames[4] = mod5Name.getText();
                courseModNames[5] = mod6Name.getText();
                courseModNames[6] = mod7Name.getText();
                courseModNames[7] = mod8Name.getText();
                courseModNames[8] = mod9Name.getText();
                courseModNames[9] = mod10Name.getText();
                courseModNames[10] = mod11Name.getText();
                courseModNames[11] = mod12Name.getText();
                courseModNames[12] = mod13Name.getText();
                courseModNames[13] = mod14Name.getText();
                courseModNames[14] = mod15Name.getText();
                courseModNames[15] = mod16Name.getText();
                courseModNames[16] = mod17Name.getText();
                courseModNames[17] = mod18Name.getText();
                courseModNames[18] = mod19Name.getText();
                courseModNames[19] = mod20Name.getText();
                courseModNames[20] = mod21Name.getText();
                courseModNames[21] = mod22Name.getText();
                courseModNames[22] = mod23Name.getText();
                courseModNames[23] = mod24Name.getText();

                Boolean flag = true;

                for (int i = 0; i < 24; i++) {
                    if (courseModCodes[i].isEmpty() || courseModNames[i].isEmpty()) {
                        JOptionPane.showMessageDialog(self, "Please fill all of the fields.", "Warning",
                                JOptionPane.WARNING_MESSAGE);
                        flag = false;
                        break;
                    } 
                }

                if(flag) {
                    int ans = JOptionPane.showConfirmDialog(self, "Are you sure you want to add a new Course?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (ans == JOptionPane.YES_OPTION) {
                        adm.writeToFile(crseName, crseShortName, true, courseModCodes, courseModNames);
                        cancel.doClick();
                    }
                    
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(self, "Please fill all of the fields.", "Warning",
                        JOptionPane.WARNING_MESSAGE);
                e.printStackTrace();
            }
        });

        cancel.addActionListener(ae -> {
            ad.adLandPanel();
            dispose();
        });
        actions.add(confirm);
        actions.add(cancel);

        modulesPanel.add(year1);
        modulesPanel.add(year2);
        modulesPanel.add(year3);
        modulesPanel.add(actions);

        coursePanel.add(modulesPanel, BorderLayout.CENTER);

        setVisible(true);

        add(coursePanel);
    }

    public void editPanel(String course) {
        setTitle("Coursework");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(900, 700);
        setLocationRelativeTo(null);

        JPanel editPanel = new JPanel(new GridLayout(1, 2, 20, 20));

        JPanel leftEditPanel = new JPanel(new GridLayout(3, 1));
        leftEditPanel.setBorder(BorderFactory.createTitledBorder("Edit Module"));
        leftEditPanel.add(new JPanel());
        JPanel editingPanel = new JPanel(new GridLayout(5, 2));
        editingPanel.add(new JLabel("Old Module Code :"));
        JTextField oldModCode = new JTextField(20);
        editingPanel.add(oldModCode);
        editingPanel.add(new JLabel());
        editingPanel.add(new JLabel());
        editingPanel.add(new JLabel("New Module Name : "));
        JTextField newModName = new JTextField(20);
        editingPanel.add(newModName);
        editingPanel.add(new JLabel());
        editingPanel.add(new JLabel());
        JButton confirmBtn = new JButton("Confirm");
        JButton cancelBtn = new JButton("Cancel");

        confirmBtn.addActionListener(ae -> {
            try {
                String oldModuleCode = oldModCode.getText();
                String newModuleName = newModName.getText();
                if (oldModuleCode.isEmpty() || newModuleName.isEmpty()) {
                    JOptionPane.showMessageDialog(self, "Please fill all of the fields.", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    adm.editModule(course, oldModuleCode, newModuleName);
                    JOptionPane.showMessageDialog(self, "The module has been edited");
                    ad.adLandPanel();
                    dispose();
                }
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(self, "Please fill all of the fields.", "Warning",
                        JOptionPane.WARNING_MESSAGE);
                e.printStackTrace();
            } catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(self,
                        "The module Code you entered could not be found. Please check and try again.",
                        "Module Code Not Found", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        cancelBtn.addActionListener(ae -> {
            ad.adLandPanel();
            dispose();
        });

        editingPanel.add(confirmBtn);
        editingPanel.add(cancelBtn);
        leftEditPanel.add(editingPanel);
        leftEditPanel.add(new JPanel());

        JPanel rightEditPanel = new JPanel(new GridLayout(1, 1));
        rightEditPanel.setBorder(BorderFactory.createTitledBorder("Module Details"));

        JPanel rightEditPanelData = new JPanel(new BorderLayout());
        JPanel crsePanel = new JPanel();
        JPanel modsPanel = new JPanel(new GridLayout(27, 3, 5, 10));
        adm.getModulesOf(course, crsePanel, modsPanel);
        rightEditPanelData.add(crsePanel, BorderLayout.NORTH);
        rightEditPanelData.add(modsPanel, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(rightEditPanelData);

        rightEditPanel.add(scrollPane);

        editPanel.add(leftEditPanel);
        editPanel.add(rightEditPanel);

        setVisible(true);

        add(editPanel);
    }

    public void makeResult(){
        setTitle("Coursework");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel resultPanel = new JPanel(new GridLayout(2, 1));
        resultPanel.setBorder(BorderFactory.createTitledBorder("Make Student Result"));
        JPanel std = new JPanel(new GridLayout(1, 2));
        std.add(new JLabel("Student ID"));
        JTextField id = new JTextField(30);
        std.add(id);
        JButton generateBtn = new JButton("Generate Result");

        generateBtn.addActionListener(ae -> {
            String stdId = id.getText();
                if(stdId.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Please enter a student ID", "Missing Student ID", JOptionPane.OK_OPTION);
                } else {
                    adm.makeResult(stdId);
                    JOptionPane.showMessageDialog(this, "The result has been stored in a file with the Student ID as the file name.", "Result Generated", JOptionPane.OK_OPTION);
                    dispose();
                }
            
        });        

        resultPanel.add(std);
        resultPanel.add(generateBtn);
        setVisible(true);
        add(resultPanel);
    }

    public static void main(String[] args) {
        // new AdminGUI();
        adm.getFromCourseFile();
        ad.adLandPanel();
    }
}
