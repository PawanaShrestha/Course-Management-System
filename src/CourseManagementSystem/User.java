package CourseManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class User {
    public static TreeSet<InstructorDetails> insTreeSet = new TreeSet<InstructorDetails>();

    public static TreeSet<StudentDetails> stdTreeSet = new TreeSet<StudentDetails>();

    public static List<Course> Courses = new ArrayList<>();

    public TreeSet<InstructorDetails> getInsTreeSet(){
        return insTreeSet;
    }

    public TreeSet<StudentDetails> getStdTreeSet(){
        return stdTreeSet;
    }

    public void showInsOfMods(String modCode, JPanel panel){
        for(InstructorDetails insDet : insTreeSet){
            for(String mods : insDet.getModules()){
                if(mods.equals(modCode)){
                    // System.out.println(insDet.getInsFirstName() + " " + insDet.getInsLastName() + " " + insDet.getInsEmail() + " " + insDet.getCourse());
                    String name = insDet.getInsFirstName() + " " + insDet.getInsLastName();
                    JPanel instructors = new JPanel(new GridLayout(1, 3));
                    instructors.add(new JLabel(insDet.getInsId()));
                    instructors.add(new JLabel(name));
                    instructors.add(new JLabel(insDet.getInsEmail()));
                }
            }
        }
    } 

    public void showStdOfMods(String course, String modCode, JPanel panel){
        int sem = 0;
        for(Course crse: Courses){
            if(crse.getCourseShortName().equals(course)){
                for(Module mod: crse.getAllModules()){
                    if(mod.getModCode().equals(modCode)){
                        sem = mod.getSem(); 
                    }
                }
            }
        }

        for(StudentDetails stdDet : stdTreeSet){
            if(stdDet.getStdSem() == sem){
                String name = stdDet.getStdFirstName() + " " + stdDet.getStdLastName();
                JPanel stds = new JPanel(new GridLayout(1, 3));
                stds.add(new JLabel(stdDet.getStdId()));
                stds.add(new JLabel(name));
                stds.add(new JLabel(stdDet.getStdEmail()));
                panel.add(stds);
                // System.out.println(stdDet.getStdFirstName() + " " + stdDet.getStdLastName() + " " + stdDet.getStdEmail() + " Course: " + stdDet.getStdCourse() + " Level: " + stdDet.getStdLevel() + " Sem: " + stdDet.getStdSem());
            }
        }
    }    
}
