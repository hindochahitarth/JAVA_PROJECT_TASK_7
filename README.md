
---

# Employee Database App (Java JDBC)

## About

This Project demonstrates how to connect Java with a **MySQL/PostgreSQL database** using **JDBC** and perform basic **CRUD (Create, Read, Update, Delete)** operations.

---

## Features

* Connects to a relational database using JDBC.
* Add new employees.
* View all employees.
* Update employee salary.
* Delete employees by ID.
* Menu-driven console interface.

---

## Tools and Technologies

* Java (JDK 8 or higher)
* MySQL/PostgreSQL
* JDBC Driver
* VS Code / IntelliJ / Eclipse

---

## Database Setup (MySQL Example)

```sql
CREATE DATABASE employee_db;

USE employee_db;

CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    department VARCHAR(100),
    salary DOUBLE
);
```

---

## How to Run

1. Clone or download the project.

2. Add the **JDBC driver (mysql-connector-j.jar)** to your classpath.

    * If using Maven, add the dependency in `pom.xml`.

3. Update database URL, username, and password in `EmployeeDBApp.java`:

   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
   private static final String USER = "root";      // your DB username
   private static final String PASSWORD = "";  // your DB password
   ```

4. Compile and run:

   ```bash
   javac EmployeeDBApp.java
   java EmployeeDBApp
   ```

5. Use the menu to perform CRUD operations.

---

## Example Menu

```
=== Employee Database Menu ===
1. Add Employee
2. View Employees
3. Update Salary
4. Delete Employee
5. Exit
```

---

