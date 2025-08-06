import java.util.*;
import java.io.*;
class Student{
    private int  ID;
    private String name;
    private double marks;

    public Student(int ID, String name, double marks){
        this.ID = ID;
        this.name = name;
        this.marks = marks;
    }
    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getMarks() {
        return marks;
    }
    public void setMarks(double marks) {
        this.marks = marks;
    }
    public String toString(){
        return "ID" +ID+ "| Name: "+name+ "| Marks: "+marks;
    }
}
public class Main {
    private static ArrayList<Student> students =  new ArrayList<>();
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int choice;
        do{
            System.out.println("-------STUDENT MANAGEMENT SYSTEM------");
            System.out.println("1. Add Student ");
            System.out.println("2. View Student ");
            System.out.println("3. Update Student ");
            System.out.println("4. Delete Student ");
            System.out.println("5. Exit ");
            System.out.print("Enter Your Choice : ");

            choice = input.nextInt();

            switch (choice){
                case 1: addStudent();
                break;
                case 2: viewStudent();
                break;
                case 3: updateStudent();
                break;
                case 4: deleteStudent();
                break;
                case 5: saveStudentToFile();
                    System.out.println("Exiting.....");
                break;
                default:
                    System.out.println("Invalid Choice!..");
            }
        }while (choice !=5 );
    }
    // create
    private static void addStudent(){
        System.out.print("Enter ID:");
        int ID = input.nextInt();
        input.nextLine();
        System.out.print("Enter Name: ");
        String name  = input.nextLine();
        System.out.print("Add Marks: ");
        double marks = input.nextDouble();

        students.add(new Student(ID,name,marks));
        System.out.println("Student add successfully! ");

    }
    // read
    private static void viewStudent(){
        if(students.isEmpty()){
            System.out.println("No student found ");
        }else {
            System.out.println("\n----Student List-----");
            for (int i = 0; i <= students.size(); i++) {
                Student s = students.get(i);
                System.out.println(s);
            }
        }
    }
    // Update
    private static void updateStudent(){
        System.out.print("Enter the Student ID to update: ");
        int ID = input.nextInt();
        boolean found = false;
        for(int i=0; i<=students.size(); i++){
            Student s = students.get(i);
            if(s.getID() == ID){
                input.nextLine();
                System.out.print("Enter new Name:");
                s.setName(input.nextLine());
                System.out.print("Enter new Marks: ");
                s.setMarks(input.nextDouble());
                System.out.println("Student Update Successfully!");
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Student not found! ");
        }
    }
    // delete
    private static void deleteStudent(){
        System.out.println("Enter the student ID to delete ");
        int id = input.nextInt();
        boolean removed = students.removeIf(s -> s.getID() == id);
        if(removed){
            System.out.println("student delete successfully ");
        }else{
            System.out.println("student not found !");
        }
    }
    // save student to file
    private static final String FILE_NAME = "students.dat";
    private static void saveStudentToFile(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            oos.writeObject(students);
        }catch (IOException e){
            System.out.println("Error saving data"+e.getMessage());
        }
    }
    // Load student form file
    private static void loadStudentFromFile(){
        File file = new File(FILE_NAME);
        if(!file.exists()){
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))){
            students = (ArrayList<Student>) ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Error loading data: "+e.getMessage());
        }
    }
}