
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MarksStorage {

    public static void saveToFile(List<Student> students, String filePath) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (Student s : students) {
                pw.println(s.getName() + "," + s.getSub1() + "," + s.getSub2() + "," + s.getSub3());
            }
        }
    }

    public static List<Student> loadFromFile(String filePath) throws IOException {
        List<Student> list = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) {
            return list; 
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4) continue;
                String name = parts[0];
                int m1 = Integer.parseInt(parts[1]);
                int m2 = Integer.parseInt(parts[2]);
                int m3 = Integer.parseInt(parts[3]);
                list.add(new Student(name, m1, m2, m3));
            }
        }
        return list;
    }
}
