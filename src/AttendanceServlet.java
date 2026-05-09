import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet {
    private static final String[] SAMPLE_LOGS = {
            "EMP103 | LOGIN  | 09:15 AM",
            "EMP101 | LOGIN  | 08:45 AM",
            "EMP105 | LOGIN  | 10:05 AM",
            "EMP102 | LOGIN  | 08:55 AM",
            "EMP104 | LOGIN  | 09:30 AM",
            "EMP106 | LOGOUT | 06:00 PM",
            "EMP107 | LOGIN  | 09:01 AM",
            "EMP108 | LOGIN  | 07:50 AM"
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        switch (action) {
            case "load":
                req.setAttribute("logs", loadLogs());
                req.getRequestDispatcher("logs.jsp").forward(req, resp);
                break;
            case "sort":
                req.setAttribute("logs", sortLogs());
                req.getRequestDispatcher("logs.jsp").forward(req, resp);
                break;
            case "late":
                req.setAttribute("logs", showLateLogins());
                req.getRequestDispatcher("logs.jsp").forward(req, resp);
                break;
            case "insertForm":
                req.getRequestDispatcher("insert.jsp").forward(req, resp);
                break;
            case "insertSubmit":
                insertRecord(req);
                req.getRequestDispatcher("result.jsp").forward(req, resp);
                break;
            default:
                resp.sendRedirect("index.jsp");
        }
    }

    private List<AttendanceLog> parseSampleLogs() {
        List<AttendanceLog> logs = new ArrayList<>();
        for (String line : SAMPLE_LOGS) {
            logs.add(parseLog(line));
        }
        return logs;
    }

    private AttendanceLog parseLog(String line) {
        int first = line.indexOf("|");
        int second = line.indexOf("|", first + 1);
        String id = line.substring(0, first).trim();
        String action = line.substring(first + 1, second).trim();
        String time = line.substring(second + 1).trim();
        return new AttendanceLog(id, action, time);
    }

    private List<AttendanceLog> loadLogs() {
        return parseSampleLogs();
    }

    private List<AttendanceLog> sortLogs() {
        List<AttendanceLog> logs = parseSampleLogs();
        Collections.sort(logs, new AttendanceComparator());
        return logs;
    }

    private List<AttendanceLog> showLateLogins() {
        List<AttendanceLog> logs = parseSampleLogs();
        List<AttendanceLog> late = new ArrayList<>();
        for (AttendanceLog log : logs) {
            if (log.getAction().equalsIgnoreCase("LOGIN") && isAfterNineAM(log.getTime())) {
                late.add(log);
            }
        }
        return late;
    }

    private boolean isAfterNineAM(String time) {
        try {
            String[] parts = time.split(" ");
            String[] hm = parts[0].split(":");
            int h = Integer.parseInt(hm[0]);
            int m = Integer.parseInt(hm[1]);
            String period = parts[1].toUpperCase();
            if (period.equals("PM") && h != 12) {
                h += 12;
            }
            if (period.equals("AM") && h == 12) {
                h = 0;
            }
            return (h > 9) || (h == 9 && m > 0);
        } catch (Exception e) {
            return false;
        }
    }

    private void insertRecord(HttpServletRequest req) {
        String employeeId = req.getParameter("employeeId");
        String attendanceAction = req.getParameter("attendanceAction");
        String time = req.getParameter("time");
        String message;

        if (employeeId == null || attendanceAction == null || time == null
                || employeeId.isBlank() || attendanceAction.isBlank() || time.isBlank()) {
            message = "All fields are required to insert a record.";
        } else {
            AttendanceLog log = new AttendanceLog(employeeId.trim(), attendanceAction.trim(), time.trim());
            try {
                DatabaseHelper.insertLog(log);
                message = "Record successfully inserted into the database.";
                req.setAttribute("logs", DatabaseHelper.getAllLogs());
            } catch (SQLException e) {
                message = "Database error: " + e.getMessage();
            }
        }

        req.setAttribute("message", message);
    }
}
