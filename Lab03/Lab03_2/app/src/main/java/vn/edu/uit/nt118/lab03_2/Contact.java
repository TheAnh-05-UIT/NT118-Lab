package vn.edu.uit.nt118.lab03_2;


public class Contact {
    private int id;
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Contact(int id, String name, String phoneNumber) {
        this(name, phoneNumber);
        this.id = id;
    }

    public int getID() { return id; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setName(String name) { this.name = name; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
