package CourseManagementSystem;

public class Module {
    private String modCode;
    private String modName;
    private int sem;
    private int marks;

    public String getModCode() {
        return modCode; 
    }

    public String getModName() {
        return modName;
    }

    public int getSem(){
        return sem;
    }

    public int getMarks(){
        return marks;
    }

    // public void setModCode(String code){
    //     this.modCode = code;
    // }

    public void setModName(String name){
        this.modName = name;
    }

    // public void setSem(int sem){
    //     this.sem = sem;
    // }

    public void setMarks(int marks){
        this.marks = marks;
    }

    Module(String code, String name, int sem){
        this.modCode = code;
        this.modName = name;
        this.sem = sem;
    }

    Module(String code, String name, int sem, int marks){
        this.modCode = code;
        this.modName = name;
        this.sem = sem;
        this.marks = marks;
    }
}
