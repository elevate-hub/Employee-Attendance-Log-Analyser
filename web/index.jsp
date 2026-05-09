<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Attendance Web Analyzer</title>
</head>
<body>
<h1>Employee Attendance Web Analyzer</h1>
<p>Click a button to invoke the servlet and navigate to the next page.</p>
<form action="attendance" method="post">
    <button type="submit" name="action" value="load">Load Logs</button>
    <button type="submit" name="action" value="sort">Sort by Employee ID</button>
    <button type="submit" name="action" value="late">Show Late Logins</button>
    <button type="submit" name="action" value="insertForm">Insert Record</button>
</form>
</body>
</html>
