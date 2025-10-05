import java.io.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private int studentID;
    private String name;
    private double grade;

    public Student(int studentID, String name, double grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Name: " + name + ", Grade: " + grade;
    }
}

public class StudentSerialization {
    public static void main(String[] args) {
        String filename = "student.ser";

        // Step 1 - Create Student object
        Student s = new Student(123, "Alice Johnson", 89.5);

        // Step 2 - Serialize object
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(s);
            System.out.println("Object Serialized Successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Step 3 - Deserialize object
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Student deserialized = (Student) ois.readObject();
            System.out.println("Object Deserialized Successfully!");
            System.out.println(deserialized);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    }
