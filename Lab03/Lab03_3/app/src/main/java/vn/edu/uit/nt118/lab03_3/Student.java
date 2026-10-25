package vn.edu.uit.nt118.lab03_3;
public class Student {

    private long id;
    private String studentCode;
    private String name;
    private String className;
    private String phone;

    public Student(String studentCode, String name,
                   String className, String phone) {
        this.studentCode = studentCode;
        this.name = name;
        this.className = className;
        this.phone = phone;
    }

    public Student(long id, String studentCode, String name,
                   String className, String phone) {
        this(studentCode, name, className, phone);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public String getPhone() {
        return phone;
    }
}
