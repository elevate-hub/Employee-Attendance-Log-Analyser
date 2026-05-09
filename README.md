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
│   └── Main.java
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
