import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> list) {
        students.clear();
        if (list != null) {
            students.addAll(list);
        }
    }

    public Student searchStudent(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    public boolean deleteStudent(String name) {
        Student s = searchStudent(name);
        if (s != null) {
            students.remove(s);
            return true;
        }
        return false;
    }

    public double getClassAveragePercentage() {
        if (students.isEmpty()) return 0.0;
        double sum = 0;
        for (Student s : students) {
            sum += s.getPercentage();
        }
        return sum / students.size();
    }

    // 🔹 NEW: Get topper (highest percentage)
    public Student getTopper() {
        if (students.isEmpty()) return null;
        Student best = students.get(0);
        for (Student s : students) {
            if (s.getPercentage() > best.getPercentage()) {
                best = s;
            }
        }
        return best;
    }

    // 🔹 NEW: Pass / Fail counts
    public int getPassCount() {
        int count = 0;
        for (Student s : students) {
            if ("Pass".equalsIgnoreCase(s.getResult())) {
                count++;
            }
        }
        return count;
    }

    public int getFailCount() {
        int count = 0;
        for (Student s : students) {
            if ("Fail".equalsIgnoreCase(s.getResult())) {
                count++;
            }
        }
        return count;
    }
}
