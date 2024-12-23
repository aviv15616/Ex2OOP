package gym.customers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Person {
    private String name;
    private int balance;
    private Gender gender;
    private String birthdate;
    private static int nextId = 1111; // Start ID sequence
    public int id;

    // Constructor
    public Person(String name, int balance, Gender gender, String birthdate) {
        this.id = nextId++; // Assign unique ID and increment
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthdate = birthdate;
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

    public String getBirthdate() {
        return birthdate;
    }
    public static int calcDateDiff(String birthdate) {
        try {
            // Define the date format for the input birthdate string
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            // Parse the birthdate string into a Date object
            Date birthDate = sdf.parse(birthdate);

            // Get the current date
            Calendar currentDate = Calendar.getInstance();

            // Get the current date's year, month, and day
            int currentYear = currentDate.get(Calendar.YEAR);
            int currentMonth = currentDate.get(Calendar.MONTH) + 1; // Calendar.MONTH is 0-based
            int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);

            // Set birthdate to calendar to get birth year, month, and day
            Calendar birthCalendar = Calendar.getInstance();
            birthCalendar.setTime(birthDate);
            int birthYear = birthCalendar.get(Calendar.YEAR);
            int birthMonth = birthCalendar.get(Calendar.MONTH) + 1;
            int birthDay = birthCalendar.get(Calendar.DAY_OF_MONTH);

            // Calculate age based on year, month, and day
            int age = currentYear - birthYear;

            // If the current month is before the birth month, or if it's the birth month but before the birth day
            if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
                age--; // Subtract 1 from age if the birthday hasn't occurred yet this year
            }

            return age;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // Return -1 if there's an error parsing the date
        }
    }


    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    // Method to display Person info

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Gender: " + gender + " | Birthday: " + birthdate + " | Age: " + calcDateDiff(birthdate) + " | Balance: " + balance;
    }

    public int getId(){
        return id;
    }
}
