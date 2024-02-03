package assign03;

public class Student {
    private int studentID;
    private String studentName;

    public Student(String name,int id){
        this.studentName = name;
        this.studentID = id;
    }
    public String getName(){

        return this.studentName;
    }
    public int getID(){
        return this.studentID;
    }
    public void setID(int newID){
        this.studentID = newID;
    }
    public void setName(String newName){
        this.studentName = newName;
    }

}
