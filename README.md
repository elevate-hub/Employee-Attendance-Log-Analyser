# Employee Attendance Log Analyzer

A simple Java Swing desktop application to load, parse, sort, and filter employee attendance logs. Built as a college mini project.

## Features
- `AttendanceLog` data model (employeeId, action, time)
- Parse log lines with `substring()` and `indexOf()`
- Store logs in an `ArrayList`
- Display logs in a `JTable`
- Sort logs by Employee ID using a `Comparator`
- Filter and show employees who logged in after 9:00 AM
- Buttons: **Load Logs**, **Sort by Employee ID**, **Show Late Logins**, **Reset Table**

## Requirements
- Java 17 or later (`java -version` to check)

## Project Structure
```
EmployeeAttendanceLogAnalyzer/
├── src/
│   ├── AttendanceLog.java
│   ├── AttendanceComparator.java
│   ├── AttendanceUI.java
│   ├── Main.java
│   ├── AttendanceServlet.java
│   └── DatabaseHelper.java
├── web/
│   ├── index.jsp
│   ├── logs.jsp
│   ├── insert.jsp
│   ├── result.jsp
│   └── WEB-INF/
│       └── web.xml
└── README.md
```

## Run in VS Code
1. Install the **Extension Pack for Java** (Microsoft).
2. Open the extracted `EmployeeAttendanceLogAnalyzer` folder in VS Code.
3. Open `src/Main.java` and click **Run** above the `main` method.

## Run from Terminal
```bash
cd EmployeeAttendanceLogAnalyzer
javac -d out src/*.java
java -cp out Main
```

## Web App Version
This repository now includes a simple Servlet/JSP web interface in the `web/` folder with JDBC insert support.

### Requirements for Web App
- A servlet container such as Apache Tomcat
- Java 17 or later
- SQLite JDBC driver jar (for example `sqlite-jdbc-3.41.2.1.jar`)

### Deploy the Web App
1. Compile the servlet classes and copy them to `web/WEB-INF/classes`.
   ```bash
   javac -d web/WEB-INF/classes -classpath "<TOMCAT_HOME>/lib/servlet-api.jar" src/*.java
   ```
2. Copy the contents of the `web/` folder into your servlet container's application directory.
3. Place the SQLite JDBC jar into `WEB-INF/lib/`.
4. Start Tomcat and navigate to `http://localhost:8080/<your-app-name>/`.

### What the Web App Does
- `index.jsp` provides buttons that submit to `AttendanceServlet`
- `AttendanceServlet` forwards to `logs.jsp`, `insert.jsp`, or `result.jsp`
- `DatabaseHelper` uses JDBC to insert attendance rows into `attendance.db`
- `result.jsp` shows the insert result and saved rows

> Note: the Swing desktop app still exists in `src/` and runs with `Main.java`.

## Expected Output
A window opens titled **Employee Attendance Log Analyzer** with an empty table and four buttons:
- **Load Logs** – fills the table with sample attendance entries.
- **Sort by Employee ID** – sorts rows alphabetically by Employee ID.
- **Show Late Logins** – shows only LOGIN entries after 9:00 AM (e.g., EMP103, EMP104, EMP105, EMP107).
- **Reset Table** – clears the table.

## Sample Log Format
```
EMP101 | LOGIN  | 08:45 AM
EMP103 | LOGIN  | 09:15 AM
```
"# Employee-Attendance-Log-Analyser" 
