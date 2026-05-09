<%@ page import="java.util.List" %>
<%@ page import="AttendanceLog" %>
<%
    String message = (String) request.getAttribute("message");
    List<AttendanceLog> logs = (List<AttendanceLog>) request.getAttribute("logs");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert Result</title>
</head>
<body>
<h1>Insert Result</h1>
<p><strong><%= message != null ? message : "No message available." %></strong></p>
<% if (logs != null && !logs.isEmpty()) { %>
    <h2>Current Database Records</h2>
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
<% } %>
<p><a href="index.jsp">Back to Home</a></p>
</body>
</html>
