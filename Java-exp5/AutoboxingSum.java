import java.util.*;

public class AutoboxingSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();
        
        System.out.println("Enter integers separated by spaces:");
        String input = sc.nextLine();
        String[] parts = input.trim().split("\\s+");
        
        // Parsing strings to Integers (autoboxing happens here)
        for (String part : parts) {
            Integer num = Integer.parseInt(part); // parseInt returns int, autoboxes to Integer
            numbers.add(num);
        }
        
        // Calculate sum (unboxing happens here)
        int sum = 0;
        for (Integer n : numbers) { // unboxes each Integer to int
            sum += n;
        }
        
        System.out.println("Sum of all integers: " + sum);
        sc.close();
    }
}
