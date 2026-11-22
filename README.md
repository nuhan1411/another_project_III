# Employee Management System

A desktop application built with Java Swing and MySQL for managing employee records, tracking personal and departmental details, and handling administrative tasks.

---

## Features

- **Splash Screen**: Animated loading screen on application startup.
- **Authentication**: Secure login module for administrators.
- **Dashboard**: Central navigation hub to access all core operations.
- **Add Employee**: Register new employee details including personal, educational, and professional data with an auto-generated unique Employee ID.
- **View / Search Employees**: Tabular employee directory with live search by Employee ID and printing capabilities.
- **Update Employee**: Modify editable employee records (contact, salary, designation, education) safely.
- **Remove Employee**: Look up and delete employee records with safety confirmation prompts.

---

## Tech Stack & Prerequisites

- **Java Development Kit (JDK)**: Version 11 or higher
- **Database**: MySQL Server (v5.7+ or v8.0+)
- **GUI Framework**: Java Swing & AWT
- **Libraries**:
  - `mysql-connector-j` (MySQL JDBC Driver)
  - `jcalendar` (Date chooser component)
  - Drop-in JDBC TableModel provider (built-in)

---

## Database Setup

1. Open your MySQL client (e.g., MySQL Workbench, phpMyAdmin, or MySQL CLI).
2. Execute the included `database.sql` script to create the database, tables, and seed initial admin credentials:
   ```bash
   mysql -u root -p < database.sql
   ```
   *Or copy and paste the contents of [database.sql](database.sql) into your MySQL client.*

### Default Admin Credentials:
- **Username:** `admin`
- **Password:** `admin123`

---

## Configuration

Database connection settings can be configured via environment variables (falling back to standard defaults if unset):

| Variable | Default Value | Description |
| :--- | :--- | :--- |
| `DB_HOST` | `localhost` | MySQL Server Host |
| `DB_PORT` | `3306` | MySQL Server Port |
| `DB_NAME` | `employeemanagement` | Database Name |
| `DB_USER` | `root` | Database Username |
| `DB_PASSWORD` | `rifat@194rain` | Database Password |

---

## How to Run

### Option 1: IntelliJ IDEA
1. Open the project folder in IntelliJ IDEA.
2. Ensure JDK 11+ is configured as the Project SDK (`File -> Project Structure -> Project`).
3. Add the required JARs (or reload via Maven) if running without Maven.
4. Run `Splash.java` (`src/employee/management/system/Splash.java`).

### Option 2: Apache Maven
```bash
# Build the project
mvn clean compile

# Launch the application
mvn exec:java
```

---

## Key Refinements & Fixes in this Release

- **Bug Fix in Update Operation:** Fixed father's name field mapping in `UpdateEmployee.java` where employee's name was erroneously overwriting father's name.
- **SQL Security & PreparedStatements:** Replaced vulnerable dynamic string concatenation with parameterized `PreparedStatement` across all database queries to prevent SQL syntax errors and injection vulnerabilities.
- **Double Alert UX Fix:** Fixed redundant duplicate success dialogs when adding a new employee.
- **Employee ID Space Trimming:** Fixed leading whitespace issue in auto-generated Employee IDs that caused query mismatches.
- **Deletion Safety:** Added confirmation dialog before deleting employee records in `RemoveEmployee.java`.
- **Window Management & Resource Cleanup:**
  - Added proper `dispose()` on window transitions to prevent native memory leaks.
  - Windows are centered dynamically across all monitor resolutions (`setLocationRelativeTo(null)`).
  - Proper resource closing (`Connection`, `PreparedStatement`, `ResultSet`) using try-with-resources.
- **Configurability:** Added environment variable support for database credentials so the app can run across various local environments without hardcoded password changes.
- **Database Schema Included:** Added [database.sql](database.sql) and default administrator credentials for instant setup.
- **Self-Contained TableModel Provider:** Included built-in `DbUtils` to eliminate external untracked `rs2xml.jar` compilation failures.
