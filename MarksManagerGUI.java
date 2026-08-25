import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MarksManagerGUI extends JFrame {

    private StudentManager manager = new StudentManager();

    private JTextField nameField;
    private JTextField sub1Field;
    private JTextField sub2Field;
    private JTextField sub3Field;
    private JTextField searchField;

    private DefaultListModel<String> listModel;
    private JList<String> studentList;
    private JLabel avgLabel;

    public MarksManagerGUI() {
        setTitle("Marks Manager (GUI)");
        setSize(900, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        initComponents();
    }

    private void initComponents() {
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Marks"));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Subject 1 Marks:"));
        sub1Field = new JTextField();
        inputPanel.add(sub1Field);

        inputPanel.add(new JLabel("Subject 2 Marks:"));
        sub2Field = new JTextField();
        inputPanel.add(sub2Field);

        inputPanel.add(new JLabel("Subject 3 Marks:"));
        sub3Field = new JTextField();
        inputPanel.add(sub3Field);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        JButton addButton    = new JButton("Add / Update");
        JButton viewButton   = new JButton("View All");
        JButton deleteButton = new JButton("Delete");
        JButton clearButton  = new JButton("Clear Fields");
        JButton saveButton   = new JButton("Save to File");
        JButton loadButton   = new JButton("Load from File");
        JButton topperButton = new JButton("Show Topper");
        JButton statsButton  = new JButton("Pass/Fail Stats");

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(topperButton);
        buttonPanel.add(statsButton);

        // ---- Search panel ----
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search"));

        searchPanel.add(new JLabel("Search by Name:"));
        searchField = new JTextField(15);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        listModel = new DefaultListModel<>();
        studentList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(studentList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Students & Marks"));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        avgLabel = new JLabel("Class Average Percentage: 0.00");
        bottomPanel.add(avgLabel);

        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.WEST);

        addButton.addActionListener(e -> addStudentAction());
        viewButton.addActionListener(e -> refreshStudentList());
        deleteButton.addActionListener(e -> deleteStudentAction());
        clearButton.addActionListener(e -> clearFields());
        searchButton.addActionListener(e -> searchStudentAction());
        saveButton.addActionListener(e -> saveToFileAction());
        loadButton.addActionListener(e -> loadFromFileAction());
        topperButton.addActionListener(e -> showTopperAction());
        statsButton.addActionListener(e -> showStatsAction());
    }

    private void addStudentAction() {
        String name  = nameField.getText().trim();
        String m1Text = sub1Field.getText().trim();
        String m2Text = sub2Field.getText().trim();
        String m3Text = sub3Field.getText().trim();

        if (name.isEmpty() || m1Text.isEmpty() || m2Text.isEmpty() || m3Text.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill all fields.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int m1, m2, m3;
        try {
            m1 = Integer.parseInt(m1Text);
            m2 = Integer.parseInt(m2Text);
            m3 = Integer.parseInt(m3Text);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Marks must be numbers.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (m1 < 0 || m1 > 100 || m2 < 0 || m2 > 100 || m3 < 0 || m3 > 100) {
            JOptionPane.showMessageDialog(this,
                    "Marks must be between 0 and 100.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student existing = manager.searchStudent(name);
        if (existing != null) {
            manager.deleteStudent(name); 
        }

        Student s = new Student(name, m1, m2, m3);
        manager.addStudent(s);
        JOptionPane.showMessageDialog(this,
                "Student marks saved successfully!");

        refreshStudentList();
        clearFields();
    }

    private void refreshStudentList() {
        listModel.clear();
        for (Student s : manager.getStudents()) {
            listModel.addElement(s.toString());
        }
        double avg = manager.getClassAveragePercentage();
        avgLabel.setText("Class Average Percentage: " + String.format("%.2f", avg));
    }

    private void deleteStudentAction() {
        String name = searchField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Enter a name in the search box to delete.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean deleted = manager.deleteStudent(name);
        if (deleted) {
            JOptionPane.showMessageDialog(this, "Student deleted.");
            refreshStudentList();
        } else {
            JOptionPane.showMessageDialog(this, "Student not found.");
        }
    }

    private void searchStudentAction() {
        String name = searchField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Enter a name to search.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student s = manager.searchStudent(name);
        if (s != null) {
            String msg = "Name: " + s.getName()
                    + "\nMarks: " + s.getSub1() + ", " + s.getSub2() + ", " + s.getSub3()
                    + "\nTotal: " + s.getTotal()
                    + "\nPercentage: " + String.format("%.2f", s.getPercentage())
                    + "\nGrade: " + s.getGrade()
                    + "\nResult: " + s.getResult();
            JOptionPane.showMessageDialog(this,
                    msg,
                    "Search Result",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Student not found.",
                    "Search Result",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void saveToFileAction() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            try {
                MarksStorage.saveToFile(manager.getStudents(), path);
                JOptionPane.showMessageDialog(this,
                        "Saved to file:\n" + path);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error saving file: " + ex.getMessage(),
                        "File Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadFromFileAction() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            try {
                List<Student> list = MarksStorage.loadFromFile(path);
                manager.setStudents(list);
                refreshStudentList();
                JOptionPane.showMessageDialog(this,
                        "Loaded " + list.size() + " records from:\n" + path);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error loading file: " + ex.getMessage(),
                        "File Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void showTopperAction() {
        Student topper = manager.getTopper();
        if (topper == null) {
            JOptionPane.showMessageDialog(this,
                    "No records to find topper.",
                    "Topper",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String msg = "Topper: " + topper.getName()
                + "\nTotal: " + topper.getTotal()
                + "\nPercentage: " + String.format("%.2f", topper.getPercentage())
                + "\nGrade: " + topper.getGrade()
                + "\nResult: " + topper.getResult();
        JOptionPane.showMessageDialog(this,
                msg,
                "Topper",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // 🔹 NEW: Show pass/fail stats
    private void showStatsAction() {
        int pass = manager.getPassCount();
        int fail = manager.getFailCount();
        int total = manager.getStudents().size();
        String msg = "Total Students: " + total
                + "\nPass: " + pass
                + "\nFail: " + fail;
        JOptionPane.showMessageDialog(this,
                msg,
                "Pass/Fail Statistics",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields() {
        nameField.setText("");
        sub1Field.setText("");
        sub2Field.setText("");
        sub3Field.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MarksManagerGUI().setVisible(true));
    }
}
