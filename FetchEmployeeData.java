import java.sql.*;

public class FetchEmployeeData {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/companydb"; // Example DB
        String user = "root";
        String password = "your_password";
        String query = "SELECT EmpID, Name, Salary FROM Employee";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Connected successfully. Fetching Employee Data:");
            while (rs.next()) {
                int empId = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");
                System.out.printf("EmpID: %d, Name: %s, Salary: %.2f%n", empId, name, salary);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
