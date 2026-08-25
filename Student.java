public class Student {
    private String name;
    private int sub1;
    private int sub2;
    private int sub3;

    public Student(String name, int sub1, int sub2, int sub3) {
        this.name = name;
        this.sub1 = sub1;
        this.sub2 = sub2;
        this.sub3 = sub3;
    }

    public String getName() {
        return name;
    }

    public int getSub1() {
        return sub1;
    }

    public int getSub2() {
        return sub2;
    }

    public int getSub3() {
        return sub3;
    }

    public int getTotal() {
        return sub1 + sub2 + sub3;
    }

    public double getPercentage() {
        return getTotal() / 3.0;
    }

    public String getGrade() {
        double per = getPercentage();
        if (per >= 75) return "A";
        else if (per >= 60) return "B";
        else if (per >= 50) return "C";
        else if (per >= 40) return "D";
        else return "F";
    }

    public String getResult() {
        return (getPercentage() >= 40) ? "Pass" : "Fail";
    }

    @Override
    public String toString() {
        return name
                + " | Marks: [" + sub1 + ", " + sub2 + ", " + sub3 + "]"
                + " | Total: " + getTotal()
                + " | %: " + String.format("%.2f", getPercentage())
                + " | Grade: " + getGrade()
                + " | " + getResult();
    }
}
