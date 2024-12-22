package gym.customers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    private String name;
    private int balance;
    private Gender gender;
    private Date birthdate;

    // Constructor
    public Person(String name, int balance, Gender gender, String birthdate) {
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthdate = parseDate(birthdate);
    }

    // Method to parse birthdate from string to Date object
    private Date parseDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    // Method to display Person info
    public void displayInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Name: " + name);
        System.out.println("Salary: " + balance);
        System.out.println("Gender: " + gender);
        System.out.println("Birthdate: " + sdf.format(birthdate));
    }
}
