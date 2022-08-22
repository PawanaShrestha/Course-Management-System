package CourseManagementSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Admin extends User{

    public List<Course> getAllCourses() {
        return Courses;
    }

    public void cancelCourse(String shortName) {
        int indexToCancel = 0;
        for (Iterator<Course> itr = Courses.iterator(); itr.hasNext(); indexToCancel++) {
            if (itr.next().getCourseShortName().equals(shortName)) {
                break;
            }
        }
        Courses.get(indexToCancel).setCourseStatus(false);

        File f = new File("./Files/Courses.txt");
        f.delete();

        makeCoursesFile();
    }
 
    public void activateCourse(String shortName) {
        int indexToActivate = 0;
        for (Iterator<Course> itr = Courses.iterator(); itr.hasNext(); indexToActivate++) {
            if (itr.next().getCourseShortName().equals(shortName)) {
                break;
            }
        }
        Courses.get(indexToActivate).setCourseStatus(true);

        File f = new File("./Files/Courses.txt");
        f.delete();

        makeCoursesFile();
    }

    public void deleteCourse(String shortName) {
        int indexToDelete = 0;
        for (Iterator<Course> itr = Courses.iterator(); itr.hasNext(); indexToDelete++) {
            if (itr.next().getCourseShortName().equals(shortName)) {
                break;
            }
        }
        Courses.remove(indexToDelete);
        String name = Courses.get(indexToDelete).getCourseName();
        String fileName = "./Files/" + name + ".txt";
        File f = new File(fileName);
        f.delete();

        makeCoursesFile();
    }

    public void editModule(String courseShort, String code, String newName) {
        int indexToEdit = 0;
        int courseToEditFrom = 0;
        
            for (Iterator<Course> itr = Courses.iterator(); itr.hasNext(); courseToEditFrom++) {
                if (itr.next().getCourseShortName().equals(courseShort)) {
                    break;
                }
            }
    
            String course = Courses.get(courseToEditFrom).getCourseName();
    
            for (Iterator<Module> modToEdit = Courses.get(courseToEditFrom).getAllModules().iterator(); modToEdit
                    .hasNext(); indexToEdit++) {
                if (modToEdit.next().getModCode().equals(code)) {
                    break;
                }
            }
    
            // Courses.get(courseToEditFrom).getAllModules().get(indexToEdit).setModCode(newCode);
            Courses.get(courseToEditFrom).getAllModules().get(indexToEdit).setModName(newName);
    
            try {
                writeInFile(course);
            } catch (IOException e) {
                e.printStackTrace();
            }
        
        
    }

    public void getModulesOf(String courseShortName, JPanel panel1, JPanel panel2) {
        int courseIndex = 0;
        for (Iterator<Course> itr = Courses.iterator(); itr.hasNext(); courseIndex++) {
            if (itr.next().getCourseShortName().equals(courseShortName)) {
                break;
            }
        }

        List<Module> courseMod = Courses.get(courseIndex).getAllModules();
        // System.out.println("Course: " + Courses.get(courseIndex).getCourseName());
        // for (Module m : courseMod) {
        //     System.out.println("Code: " + m.getModCode() + " Name: " + m.getModName() + " Semester: " + m.getSem());
        // }
        panel1.add(new JLabel(Courses.get(courseIndex).getCourseName() + " ( " + Courses.get(courseIndex).getCourseShortName() + " )"));
        panel2.add(new JLabel());
        panel2.add(new JLabel());
        panel2.add(new JLabel());
        panel2.add(new JLabel("Code"));
        panel2.add(new JLabel("Name"));
        panel2.add(new JLabel("Semester"));
        panel2.add(new JLabel());
        panel2.add(new JLabel());
        panel2.add(new JLabel());
        for (Module m : courseMod) {
            panel2.add(new JLabel(m.getModCode()));
            panel2.add(new JLabel(m.getModName()));
            panel2.add(new JLabel(""+m.getSem()));
        }
    }

    public void viewAllCourses() {
        List<Course> courses = this.getAllCourses();
        for (Course c : courses) {
            System.out.println("Course Name: " + c.getCourseName() + " (" + c.getCourseShortName() + ")");
            System.out.println("Course Status: " + c.getCourseStatus());
            List<Module> mods = c.getAllModules();
            for (Module m : mods) {
                System.out.println("Code: " + m.getModCode() + " Name: " + m.getModName() + " Semester: " + m.getSem());
            }
        }
    }

    public void viewCoursesForSelection() {
        List<Course> courses = this.getAllCourses();
        int i = 1;
        for (Course c : courses) { 
            String status;
            if (c.getCourseStatus()) {
                status = "Available";
            } else {
                status = "Cancelled";
            }
            System.out.println(i + ". " + c.getCourseName() + " ( " + c.getCourseShortName() + " ) [ " + status + " ]");
            i++;
        }
    }

    public void writeInFile(String courseName) throws IOException {
        int courseIndex = 0;
        for (Iterator<Course> itr = Courses.iterator(); itr.hasNext(); courseIndex++) {
            if (itr.next().getCourseName().equals(courseName)) {
                break;
            }
        }

        List<Module> courseMod = Courses.get(courseIndex).getAllModules();

        FileWriter file = null;
        try {
            String fileName = courseName + ".txt";
            file = new FileWriter("./Files/" + fileName);
            for (Module m : courseMod) {
                file.write(m.getSem() + "\n" + m.getModCode() + "\n" + m.getModName() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.close();
        }

        getFromCourseFile();
    }

    public void writeToFile(String courseName, String shortCourseName, Boolean status, String modCodes[],
            String modNames[]) {
        FileWriter file = null;
        int sem[] = new int[24];
        String fileName = courseName + ".txt";
        try {
            file = new FileWriter("./Files/" + fileName);
            for (int i = 0; i < 24; i++) {
                if (i < 4) {
                    sem[i] = 1;
                } else if (i < 8) {
                    sem[i] = 2;
                } else if (i < 12) {
                    sem[i] = 3;
                } else if (i < 16) {
                    sem[i] = 4;
                } else if (i < 20) {
                    sem[i] = 5;
                } else {
                    sem[i] = 6;
                }
                file.write(sem[i] + "\n" + modCodes[i] + "\n" + modNames[i] + "\n");
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        addNewCourse(courseName, shortCourseName, status, modCodes, modNames, sem);
        makeCoursesFile();
    }

    public void makeCoursesFile() {
        String status;
        try {
            FileWriter courseFile = new FileWriter("./Files/Courses.txt");
            for (Course c : Courses) {
                if (c.getCourseStatus()) {
                    status = "true";
                } else {
                    status = "false";
                }
                courseFile.write(c.getCourseName() + "\n" + c.getCourseShortName() + "\n" + status + "\n");
            }
            courseFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getFromCourseFile();
    }

    public void storeFromFile(String crseName, String shortCourseName, Boolean status) {
        String courseName = crseName;
        String modCodes[] = new String[24], modNames[] = new String[24];
        int[] sem = new int[24];
        String fileName = crseName + ".txt";
        Scanner sc = null;

        try {
            sc = new Scanner(new FileReader("./Files/" + fileName));
            int i = 0;
            while (sc.hasNextLine()) {
                sem[i] = Integer.parseInt(sc.nextLine());
                modCodes[i] = sc.nextLine();
                modNames[i] = sc.nextLine();
                i++;
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("No such file.");
        }

        addNewCourse(courseName, shortCourseName, status, modCodes, modNames, sem);
    }

    public void addNewCourse(String name, String shortCourseName, Boolean status, String moduleCodes[],
            String moduleNames[], int sem[]) {
        List<Module> courseModules = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            Module courseMods = new Module(moduleCodes[i], moduleNames[i], sem[i]);
            courseModules.add(courseMods);
        }

        Courses.add(new Course(name, shortCourseName, status, courseModules));
    }

    public void getFromCourseFile() {
        Courses.clear();
        List<String> courses = new ArrayList<>();
        List<String> shortCourseName = new ArrayList<>();
        List<Boolean> status = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new FileReader("./Files/Courses.txt"));
            while (sc.hasNextLine()) {
                courses.add(sc.nextLine());
                shortCourseName.add(sc.nextLine());
                status.add(Boolean.parseBoolean(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("There are no courses yet.");
        }

        for (int n = 0; n < courses.size(); n++) {
            storeFromFile(courses.get(n), shortCourseName.get(n), status.get(n));
        }
    }

    public void makeResult(String stdId) {
        int result = 0;
        try {
            
            for (StudentDetails stdDet : stdTreeSet) {
                if (stdDet.getStdId().equals(stdId)) {
                    FileWriter file = new FileWriter("./Files/" + stdId + ".txt");
                    file.write(stdDet.getStdId() + "\n");
                    file.write(stdDet.getStdFirstName() + "\n");
                    file.write(stdDet.getStdLastName() + "\n");
                    file.write(stdDet.getStdEmail() + "\n");
                    file.write(stdDet.getStdCourse() + "\n");
                    file.write(stdDet.getStdLevel() + "\n");
                    file.write(stdDet.getStdSem() + "\n");

                    for (Module mods : stdDet.getStdModules()) {
                        file.write(mods.getModCode() + "\n");
                        file.write(mods.getModName() + "\n");
                        file.write(mods.getMarks() + "\n");
                        if (mods.getMarks() > 40) {
                            result++;
                        }
                    }

                    if (result > 2) {
                        file.write("Pass");
                    } else {
                        file.write("Fail");
                    }
                    file.close();
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            // JOptionPane.showMessageDialog(this, "The student ID you entered could not be found. Please check and try again", "Student ID not found", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}