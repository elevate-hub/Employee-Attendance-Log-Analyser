<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert Attendance Record</title>
</head>
<body>
<h1>Insert Attendance Record</h1>
<form action="attendance" method="post">
    <input type="hidden" name="action" value="insertSubmit" />
    <div>
        <label>Employee ID: <input type="text" name="employeeId" required /></label>
    </div>
    <div>
        <label>Action: 
            <select name="attendanceAction" required>
                <option value="LOGIN">LOGIN</option>
                <option value="LOGOUT">LOGOUT</option>
            </select>
        </label>
    </div>
    <div>
        <label>Time: <input type="text" name="time" placeholder="HH:MM AM/PM" required /></label>
    </div>
    <div>
        <button type="submit">Insert</button>
        <a href="index.jsp">Cancel</a>
    </div>
</form>
</body>
</html>
