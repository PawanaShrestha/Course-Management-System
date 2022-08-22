package CourseManagementSystem;

import java.util.List;

public class Course {
    private String courseName;
    private String courseShortName;
    private Boolean courseStatus;
    private final List<Module> courseMod;

    public String getCourseName() {
        return courseName;
    }

    public String getCourseShortName(){
        return courseShortName;
    }

    public void setCourseStatus(Boolean status) {
        this.courseStatus = status;
    }

    public Boolean getCourseStatus() {
        return courseStatus;
    }

    Course(String courseName, String shortCourseName, Boolean status, List<Module> mods) {
        this.courseName = courseName;
        this.courseShortName = shortCourseName;
        this.courseStatus = status;
        this.courseMod = mods;
    }

    public List<Module> getAllModules() {
        return this.courseMod;
    } 
}