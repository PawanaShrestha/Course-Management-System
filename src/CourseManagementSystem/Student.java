package CourseManagementSystem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Student extends User { 

    public Student(){
        getStdDataFromFile();
    }

    public String addNewStudent(String fname, String lname, String email, String course, int lvl, int sem)
            throws IOException {
        getStdDataFromFile();
        FileWriter file = null;
        int count = stdTreeSet.size();
        String id = "STD-" + course + "-" + count;
        try {
            file = new FileWriter("./Files/Students.txt", true);
            file.write(id + " " + fname + " " + lname + " " + email + " " + course + " " + lvl + " " + sem + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.close();
        }
        getStdDataFromFile();
        return id;
    }

    public void getStdDataFromFile() {
        stdTreeSet.clear();
        String id, fname, lname, email, course, marksId = "", marksModCode = "";
        int level, sem, marks = 0;
        Scanner sc = null, scMarks = null;
        try {
            sc = new Scanner(new FileReader("./Files/Students.txt"));
            while (sc.hasNextLine()) {
                Scanner sca = new Scanner(sc.nextLine());
                while (sca.hasNext()) {
                    id = sca.next();
                    fname = sca.next();
                    lname = sca.next();
                    email = sca.next();
                    course = sca.next();
                    level = Integer.parseInt(sca.next());
                    sem = Integer.parseInt(sca.next());
                    List<Module> mod = new ArrayList<Module>();

                    try {
                        scMarks = new Scanner(new FileReader("./Files/Marks.txt"));
                        while (scMarks.hasNextLine()) {
                            Scanner scaMarks = new Scanner(scMarks.nextLine());
                            while (scaMarks.hasNext()) {
                                marksId = scaMarks.next();
                                marksModCode = scaMarks.next();
                                marks = Integer.parseInt(scaMarks.next());
                                for (Course crse : Courses) {
                                    if (crse.getCourseShortName().equals(course)) {
                                        for (Module mods : crse.getAllModules()) {
                                            if (mods.getSem() == sem) {
                                                String modCode = mods.getModCode();
                                                String modName = mods.getModName();
                                                int semester = sem;
                                                int marksObtained = 0;
                                                if (id.equals(marksId) && marksModCode.equals(modCode)) {
                                                    marksObtained = marks;
                                                    mod.add(new Module(modCode, modName, semester, marksObtained));
                                                }
                                                
                                            }
                                        }
                                    }
                                }
                            }
                        
                        }
                        scMarks.close();
                    } catch (NullPointerException e) {

                    } catch (IOException e) {

                    }
                    stdTreeSet.add(new StudentDetails(id, fname, lname, email, course, level, sem, mod));
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("No such file.");
        }

    }

    // public void showAllStdDetails() {
    //     // using enhanced for loop
    //     for (StudentDetails stdDet : stdTreeSet) {
    //         System.out.println(stdDet.getStdId() + " " + stdDet.getStdFirstName() + " " + stdDet.getStdLastName() + " "
    //                 + stdDet.getStdEmail() + " Course: " + stdDet.getStdCourse() + " Level: " + stdDet.getStdLevel()
    //                 + " Sem: " + stdDet.getStdSem());
    //     }
    // }

    public String[] getStudent(String id, JPanel panel){
        String returnMods[] = new String[4];
        for (StudentDetails stdDet : stdTreeSet) {
            String course = stdDet.getStdCourse();
            int sem = stdDet.getStdSem();
            if(stdDet.getStdId().equals(id)){
                String mod[] = new String[4];
                int i = 0;
                for(Course crse: Courses){
                    if(crse.getCourseShortName().equals(course)){
                        List<Module> modules = crse.getAllModules();
                        for(Module mods : modules) {
                            if(mods.getSem() == sem){
                                mod[i] = mods.getModName() + " ( " + mods.getModCode() +  " )";
                                returnMods[i] = mods.getModCode();
                                i++;
                            }
                        }
                    }
                }

                String name = stdDet.getStdFirstName() + " " + stdDet.getStdLastName();
                panel.add(new JLabel());
                panel.add(new JLabel());
                panel.add(new JLabel("Student ID :"));
                panel.add(new JLabel(stdDet.getStdId()));
                panel.add(new JLabel("Name :"));
                panel.add(new JLabel(name));
                panel.add(new JLabel("Email :"));
                panel.add(new JLabel(stdDet.getStdEmail()));
                panel.add(new JLabel("Course :"));
                panel.add(new JLabel(stdDet.getStdCourse()));
                panel.add(new JLabel("Level :"));
                panel.add(new JLabel("" + stdDet.getStdLevel()));
                panel.add(new JLabel("Semester :"));
                panel.add(new JLabel("" + stdDet.getStdSem()));
                panel.add(new JLabel("Enrolled Modules: "));
                panel.add(new JLabel(mod[0]));
                panel.add(new JLabel());
                panel.add(new JLabel(mod[1]));
                panel.add(new JLabel());
                panel.add(new JLabel(mod[2]));
                panel.add(new JLabel());
                panel.add(new JLabel(mod[3]));
                panel.add(new JLabel());
            }
        }

        return returnMods;
    }

    // public void showStdModDetails() {
    //     for (StudentDetails stdDet : stdTreeSet) {
    //         // System.out.println(stdDet.getStdId() + " " + stdDet.getStdFirstName() + " " + stdDet.getStdLastName() + " "
    //                 + stdDet.getStdEmail() + " Course: " + stdDet.getStdCourse() + " Level: " + stdDet.getStdLevel()
    //                 + " Sem: " + stdDet.getStdSem());

    //         // to get the details of the module the student is studying
    //         for (Module mods : stdDet.getStdModules()) {
    //             System.out.println("Mod Code: " + mods.getModCode() + " Mod Name: " + mods.getModName() + " Marks: "
    //                     + mods.getMarks());
    //         }
    //     }
    // }

    public void viewResult(String stdId) {
        String id = "", fname = "", lname = "", email = "", course = "", modCode[] = new String[4], modName[] = new String[4], result = "";
        int lvl = 0, sem = 0, marks[] = new int[4];
        try {
            Scanner sc = new Scanner(new FileReader("./Files/" + stdId + ".txt"));
            while(sc.hasNextLine()){
                id = sc.nextLine();
                fname = sc.nextLine();
                lname = sc.nextLine();
                email = sc.nextLine();
                course = sc.nextLine();
                lvl = Integer.parseInt(sc.nextLine());
                sem = Integer.parseInt(sc.nextLine());
                for(int i=0; i<4; i++){
                    modCode[i] = sc.nextLine();
                    modName[i] = sc.nextLine();
                    marks[i] = Integer.parseInt(sc.nextLine());
                }
                result = sc.nextLine();
            }
            System.out.println("\n\n\t---------------   RESULT  ---------------");
            System.out.println("\t\t\t__________");
            System.out.println("\n\nName : " + fname + " " + lname);
            System.out.println("Std Id: " + id + "\t\t" + " Email : " + email);
            System.out.println("Course : " + course + " \t " + " Level : " + lvl + "\t" + "Semester : " + sem);
            System.out.println("\n-----Modules---------------------Marks-----\n");
            System.out.println(modCode[0] + " - " + modName[0] + "\t\t" + marks[0]);
            System.out.println(modCode[1] + " - " + modName[1] + "\t\t" + marks[1]);
            System.out.println(modCode[2] + " - " + modName[2] + "\t\t" + marks[2]);
            System.out.println(modCode[3] + " - " + modName[3] + "\t\t" + marks[3]);
            System.out.println("\n\nResult: " + result + "\n\n");
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Your result has not yet been made.");
        }

        
    }
}
