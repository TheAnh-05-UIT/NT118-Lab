package vn.edu.uit.nt118.lab02_3;

public class EmployeePartTime extends Employee {

    public EmployeePartTime() {
        super();
    }

    public EmployeePartTime(String id, String name) {
        super(id, name);
    }

    @Override
    public double tinhLuong() {
        return 150.0;
    }

    @Override
    public String toString() {
        return super.toString()
                + ">PartTime=" + tinhLuong();
    }
}
