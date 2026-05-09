import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AttendanceUI extends JFrame {
    private final List<AttendanceLog> logs = new ArrayList<>();
    private final DefaultTableModel tableModel;
    private final JTable table;

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

    public AttendanceUI() {
        setTitle("Employee Attendance Log Analyzer");
        setSize(700, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        tableModel = new DefaultTableModel(new String[]{"Employee ID", "Action", "Time"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(26);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton loadBtn = new JButton("Load Logs");
        JButton sortBtn = new JButton("Sort by Employee ID");
        JButton lateBtn = new JButton("Show Late Logins");
        JButton resetBtn = new JButton("Reset Table");

        loadBtn.addActionListener(e -> loadLogs());
        sortBtn.addActionListener(e -> sortLogs());
        lateBtn.addActionListener(e -> showLateLogins());
        resetBtn.addActionListener(e -> resetTable());

        buttons.add(loadBtn);
        buttons.add(sortBtn);
        buttons.add(lateBtn);
        buttons.add(resetBtn);
        add(buttons, BorderLayout.SOUTH);

        JLabel title = new JLabel("Employee Attendance Log Analyzer", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(title, BorderLayout.NORTH);
    }

    private AttendanceLog parseLog(String line) {
        int first = line.indexOf("|");
        int second = line.indexOf("|", first + 1);
        String id = line.substring(0, first).trim();
        String action = line.substring(first + 1, second).trim();
        String time = line.substring(second + 1).trim();
        return new AttendanceLog(id, action, time);
    }

    private void loadLogs() {
        logs.clear();
        for (String line : SAMPLE_LOGS) {
            logs.add(parseLog(line));
        }
        refreshTable(logs);
    }

    private void sortLogs() {
        if (logs.isEmpty()) { loadLogs(); }
        Collections.sort(logs, new AttendanceComparator());
        refreshTable(logs);
    }

    private boolean isAfterNineAM(String time) {
        // time format: "HH:MM AM/PM"
        try {
            String[] parts = time.split(" ");
            String[] hm = parts[0].split(":");
            int h = Integer.parseInt(hm[0]);
            int m = Integer.parseInt(hm[1]);
            String period = parts[1].toUpperCase();
            if (period.equals("PM") && h != 12) h += 12;
            if (period.equals("AM") && h == 12) h = 0;
            return (h > 9) || (h == 9 && m > 0);
        } catch (Exception e) {
            return false;
        }
    }

    private void showLateLogins() {
        if (logs.isEmpty()) { loadLogs(); }
        List<AttendanceLog> late = new ArrayList<>();
        for (AttendanceLog log : logs) {
            if (log.getAction().equalsIgnoreCase("LOGIN") && isAfterNineAM(log.getTime())) {
                late.add(log);
            }
        }
        refreshTable(late);
    }

    private void resetTable() {
        logs.clear();
        tableModel.setRowCount(0);
    }

    private void refreshTable(List<AttendanceLog> data) {
        tableModel.setRowCount(0);
        for (AttendanceLog log : data) {
            tableModel.addRow(new Object[]{log.getEmployeeId(), log.getAction(), log.getTime()});
        }
    }
}
