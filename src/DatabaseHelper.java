import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:attendance.db";

    static {
        try {
            initDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void initDatabase() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS attendance_logs (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "employee_id TEXT NOT NULL, " +
                    "action TEXT NOT NULL, " +
                    "time TEXT NOT NULL");
        }
    }

    public static void insertLog(AttendanceLog log) throws SQLException {
        String sql = "INSERT INTO attendance_logs(employee_id, action, time) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, log.getEmployeeId());
            stmt.setString(2, log.getAction());
            stmt.setString(3, log.getTime());
            stmt.executeUpdate();
        }
    }

    public static List<AttendanceLog> getAllLogs() throws SQLException {
        String sql = "SELECT employee_id, action, time FROM attendance_logs ORDER BY employee_id";
        List<AttendanceLog> logs = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                logs.add(new AttendanceLog(
                        rs.getString("employee_id"),
                        rs.getString("action"),
                        rs.getString("time")
                ));
            }
        }
        return logs;
    }
}
