import java.util.*;

class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return String.format("Employee{name='%s', age=%d, salary=%.2f}", name, age, salary);
    }
}

public class LambdaEmployeeSort {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", 25, 55000));
        employees.add(new Employee("Charlie", 30, 70000));
        employees.add(new Employee("Bob", 22, 48000));

        System.out.println("Original List:");
        employees.forEach(System.out::println);

        // Sort by name alphabetically
        employees.sort((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()));
        System.out.println("\nSorted by Name:");
        employees.forEach(System.out::println);

        // Sort by age ascending
        employees.sort(Comparator.comparingInt(Employee::getAge));
        System.out.println("\nSorted by Age:");
        employees.forEach(System.out::println);

        // Sort by salary descending
        employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        System.out.println("\nSorted by Salary (Descending):");
        employees.forEach(System.out::println);
    }
}
