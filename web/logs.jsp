<%@ page import="java.util.List" %>
<%@ page import="AttendanceLog" %>
<%
    List<AttendanceLog> logs = (List<AttendanceLog>) request.getAttribute("logs");
    if (logs == null) {
        logs = new java.util.ArrayList<>();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Attendance Logs</title>
</head>
<body>
<h1>Attendance Logs</h1>
<table border="1" cellpadding="6" cellspacing="0">
    <tr>
        <th>Employee ID</th>
        <th>Action</th>
        <th>Time</th>
    </tr>
    <% for (AttendanceLog log : logs) { %>
        <tr>
            <td><%= log.getEmployeeId() %></td>
            <td><%= log.getAction() %></td>
            <td><%= log.getTime() %></td>
        </tr>
    <% } %>
</table>
<p><a href="index.jsp">Back to Home</a></p>
</body>
</html>
