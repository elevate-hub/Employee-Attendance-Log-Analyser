public class AttendanceLog {
    private String employeeId;
    private String action;
    private String time;

    public AttendanceLog(String employeeId, String action, String time) {
        this.employeeId = employeeId;
        this.action = action;
        this.time = time;
    }

    public String getEmployeeId() { return employeeId; }
    public String getAction() { return action; }
    public String getTime() { return time; }

    @Override
    public String toString() {
        return employeeId + " | " + action + " | " + time;
    }
}
