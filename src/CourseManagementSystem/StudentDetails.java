package CourseManagementSystem;

import java.util.List;

public class StudentDetails implements Comparable<StudentDetails> {
    private String stdId;
    private String stdFirstName;
    private String stdLastName;
    private String stdEmail;
    private String stdCourse;
    private int stdLevel;
    private int stdSem;
    private List<Module> stdModules;

    StudentDetails(String id, String fname, String lname, String email, String course, int lvl, int sem, List<Module> mods) {
        this.stdId = id;
        this.stdFirstName = fname;
        this.stdLastName = lname;
        this.stdEmail = email; 
        this.stdCourse = course;
        this.stdLevel = lvl;
        this.stdSem = sem;
        this.stdModules = mods;
    }

    // public void setStdId(String stdId){
    //     this.stdId = stdId;
    // }

    // public void setStdFirstName(String firstName) {
    //     this.stdFirstName = firstName;
    // }

    // public void setStdLastName(String lastName) {
    //     this.stdLastName = lastName;
    // }

    // public void setStdEmail(String stdEmail) {
    //     this.stdEmail = stdEmail;
    // }

    // public void setStdCourse(String stdCourse) {
    //     this.stdCourse = stdCourse;
    // }

    // public void setStdLevel(int stdLevel) {
    //     this.stdLevel = stdLevel;
    // }

    // public void setStdSem(int stdSem) {
    //     this.stdSem = stdSem;
    // }

    // public void setStdModules(List<Module> stdModules){
    //     this.stdModules = stdModules;
    // }

    public String getStdId(){
        return stdId;
    }

    public String getStdFirstName() {
        return stdFirstName; 
    }

    public String getStdLastName() {
        return stdLastName;
    }

    public String getStdEmail() {
        return stdEmail;
    }

    public String getStdCourse() {
        return stdCourse;
    }

    public int getStdLevel() {
        return stdLevel;
    }

    public int getStdSem() {
        return stdSem;
    }

    public List<Module> getStdModules(){
        return stdModules;
    }

	public int compareTo(StudentDetails std){
        return stdId.compareTo(std.stdId);
    }    
}
