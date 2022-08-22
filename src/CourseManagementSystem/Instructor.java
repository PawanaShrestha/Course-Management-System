package CourseManagementSystem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Instructor extends User {
    public String addNewInstructor(String fname, String lname, String email, String course, String[] modCodes) {
        getDataFromFile();
        int count = insTreeSet.size();
        String id = "INS-" + course + "-" + count;

        try {
            FileWriter file = new FileWriter("./Files/Instructors.txt", true);
            file.write(id + " " + fname + " " + lname + " " + course + " " + email + " " + modCodes[0] + " "
                    + modCodes[1] + " " + modCodes[2] + " " + modCodes[3] + "\n");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getDataFromFile();
        return id;
    }

    public void getDataFromFile() {  
        insTreeSet.clear();
        String id, fname, lname, email, course;

        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader("./Files/Instructors.txt"));
            while (sc.hasNextLine()) {
                String modCodes[] = new String[4];
                Scanner sca = new Scanner(sc.nextLine());
                while (sca.hasNext()) {
                    id = sca.next();
                    fname = sca.next();
                    lname = sca.next();
                    course = sca.next();
                    email = sca.next();
                    for (int i = 0; i < 4; i++) {
                        modCodes[i] = sca.next();
                    }
                    insTreeSet.add(new InstructorDetails(id, fname, lname, email, course, modCodes));
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No instructors until now");
        }

    }

    public String[] getInstructor(String id, JPanel panel) {
        Iterator<InstructorDetails> itr = insTreeSet.iterator();
        String mod[] = new String[4];
        while (itr.hasNext()) {
            InstructorDetails next = itr.next();
            if (next.getInsId().equals(id)) {
                for (int i = 0; i < 4; i++) {
                    if (next.getModules()[i].equals("null")) {
                        mod[i] = "";
                    } else {
                        mod[i] = next.getModules()[i];
                    }
                }
                String name = next.getInsFirstName() + " " + next.getInsLastName();
                panel.add(new JLabel());
                panel.add(new JLabel());
                panel.add(new JLabel("Instructor ID : "));
                panel.add(new JLabel(next.getInsId()));
                panel.add(new JLabel("Name : "));
                panel.add(new JLabel(name));
                panel.add(new JLabel("Email :"));
                panel.add(new JLabel(next.getInsEmail()));
                panel.add(new JLabel("Course :"));
                panel.add(new JLabel(next.getCourse()));
                panel.add(new JLabel("Modules Teaching :"));
                panel.add(new JLabel(mod[0]));
                // panel.add(new JLabel(next.getModules()[0]));
                panel.add(new JLabel());
                panel.add(new JLabel(mod[1]));
                // panel.add(new JLabel(next.getModules()[1]));
                panel.add(new JLabel());
                panel.add(new JLabel(mod[2]));
                // panel.add(new JLabel(next.getModules()[2]));
                panel.add(new JLabel());
                panel.add(new JLabel(mod[3]));
                // panel.add(new JLabel(next.getModules()[3]));
                panel.add(new JLabel());
            }
        }
        return mod;
    }

    public void showAllInsDetails() {
        Iterator<InstructorDetails> itr = insTreeSet.iterator();
        while (itr.hasNext()) {
            InstructorDetails next = itr.next();
            System.out.println(next.getInsId() + " " + next.getInsFirstName() + " " + next.getInsLastName() + " "
                    + next.getInsEmail() + " " + next.getCourse());
            System.out.println(next.getModules()[0] + " " + next.getModules()[1] + " " + next.getModules()[2] + " "
                    + next.getModules()[3]);
        }
    }

    public void giveMarks(String stdId, String modCode, int marks) {
        for (StudentDetails stdDet : stdTreeSet) {
            if (stdDet.getStdId().equals(stdId)) {
                for (Module mods : stdDet.getStdModules()) {
                    if (mods.getModCode().equals(modCode)) {
                        mods.setMarks(marks);
                    }
                }
            }
        }

        try {
            FileWriter file = new FileWriter("./Files/Marks.txt", true);
            file.write(stdId + " " + modCode + " " + marks + "\n");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
