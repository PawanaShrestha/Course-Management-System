package CourseManagementSystem;

public class InstructorDetails implements Comparable<InstructorDetails>{
    private String insId;
    private String insFirstName;
    private String insLastName;
    private String insEmail;
    private String course;
    private String modules[]; 

    InstructorDetails(String id, String insfName, String inslName, String email, String course, String modCodes[]){
        this.insId = id;
        this.insFirstName = insfName;
        this.insLastName = inslName;
        this.insEmail = email;
        this.course = course;
        this.modules = modCodes;
    }

    // public void setInsId(String insId){
    //     this.insId = insId;
    // }

    // public void setInsFirstName(String insFirstName) {
    //     this.insFirstName = insFirstName;
    // }

    // public void setInsLastName(String insLastName) {
    //     this.insLastName = insLastName;
    // }

    // public void setInsEmail(String insEmail) {
    //     this.insEmail = insEmail;
    // }

    // public void setCourse(String course) {
    //     this.course = course;
    // }

    // public void setModules(String modules[]) {
    //     this.modules = modules;
    // }

    public String getInsId(){
        return insId;
    }

    public String getInsFirstName() {
        return insFirstName;
    }

    public String getInsLastName() {
        return insLastName;
    }

    public String getInsEmail() {
        return insEmail;
    }

    public String getCourse() {
        return course;
    }

    public String[] getModules() {
        return modules;
    }

    public int compareTo(InstructorDetails ins){
        return insId.compareTo(ins.insId);
    }

}