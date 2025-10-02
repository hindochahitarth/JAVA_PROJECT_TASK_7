package com;

import java.sql.*;
import java.util.Scanner;

public class EmployeeDBApp {


    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection conn;

    // Constructor - Establish DB connection
    public EmployeeDBApp() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add Employee
    public void addEmployee(String name, String email, String department, double salary) {
        String sql = "INSERT INTO employee (name, email, department, salary) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, department);
            stmt.setDouble(4, salary);
            stmt.executeUpdate();
            System.out.println("Employee added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View Employees
    public void viewEmployees() {
        String sql = "SELECT * FROM employee";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Email: %s | Dept: %s | Salary: %.2f%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("department"),
                        rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update Employee Salary
    public void updateEmployeeSalary(int id, double newSalary) {
        String sql = "UPDATE employee SET salary=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, newSalary);
            stmt.setInt(2, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Salary updated successfully");
            else System.out.println("Employee not found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Employee
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Employee deleted successfully");
            else System.out.println("Employee not found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main Menu
    public static void main(String[] args) {
        EmployeeDBApp app = new EmployeeDBApp();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Employee Database Menu ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Salary");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.next();
                    System.out.print("Email: ");
                    String email = sc.next();
                    System.out.print("Department: ");
                    String dept = sc.next();
                    System.out.print("Salary: ");
                    double sal = sc.nextDouble();
                    app.addEmployee(name, email, dept, sal);
                    break;

                case 2:
                    app.viewEmployees();
                    break;

                case 3:
                    System.out.print("Employee ID: ");
                    int id = sc.nextInt();
                    System.out.print("New Salary: ");
                    double newSal = sc.nextDouble();
                    app.updateEmployeeSalary(id, newSal);
                    break;

                case 4:
                    System.out.print("Employee ID: ");
                    int delId = sc.nextInt();
                    app.deleteEmployee(delId);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
