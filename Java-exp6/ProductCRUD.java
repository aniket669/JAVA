import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    private static final String URL = "jdbc:mysql://localhost:3306/companydb";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false); // Enable manual control

            int choice;
            do {
                System.out.println("\n--- Product Management ---");
                System.out.println("1. Add Product");
                System.out.println("2. View Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> addProduct(conn, sc);
                    case 2 -> viewProducts(conn);
                    case 3 -> updateProduct(conn, sc);
                    case 4 -> deleteProduct(conn, sc);
                    case 5 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice");
                }

            } while (choice != 5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sc.close();
    }

    private static void addProduct(Connection conn, Scanner sc) {
        String sql = "INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            System.out.print("Product Name: ");
            String name = sc.nextLine();
            System.out.print("Price: ");
            double price = sc.nextDouble();
            System.out.print("Quantity: ");
            int qty = sc.nextInt();
            sc.nextLine();

            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, qty);
            ps.executeUpdate();
            conn.commit();
            System.out.println("Product added successfully.");
        } catch (SQLException e) {
            rollbackConn(conn);
        }
    }

    private static void viewProducts(Connection conn) {
        String sql = "SELECT * FROM Product";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("%d | %s | %.2f | %d%n",
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateProduct(Connection conn, Scanner sc) {
        String sql = "UPDATE Product SET Price = ?, Quantity = ? WHERE ProductID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            System.out.print("Enter Product ID to update: ");
            int id = sc.nextInt();
            System.out.print("New Price: ");
            double price = sc.nextDouble();
            System.out.print("New Quantity: ");
            int qty = sc.nextInt();
            sc.nextLine();

            ps.setDouble(1, price);
            ps.setInt(2, qty);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();
            if (rows > 0) conn.commit();
            System.out.println("Product updated successfully.");
        } catch (SQLException e) {
            rollbackConn(conn);
        }
    }

    private static void deleteProduct(Connection conn, Scanner sc) {
        String sql = "DELETE FROM Product WHERE ProductID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            System.out.print("Enter Product ID to delete: ");
            int id = sc.nextInt();
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) conn.commit();
            System.out.println("Product deleted successfully.");
        } catch (SQLException e) {
            rollbackConn(conn);
        }
    }

    private static void rollbackConn(Connection conn) {
        try {
            conn.rollback();
            System.out.println("Transaction rolled back due to error.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
              }
