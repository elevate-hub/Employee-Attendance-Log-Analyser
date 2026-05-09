import java.util.Comparator;

public class AttendanceComparator implements Comparator<AttendanceLog> {
    @Override
    public int compare(AttendanceLog a, AttendanceLog b) {
        return a.getEmployeeId().compareTo(b.getEmployeeId());
    }
}
