package gym.customers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents a person (client or instructor) in the gym system.
 * <p>
 * The class stores personal information such as name, balance, gender, and birthdate,
 * as well as providing methods for calculating age and managing the person's details.
 */
public class Person {
    private final String name;
    private int balance;
    private final Gender gender;
    private final String birthdate;
    private static int nextId = 1111; // Start ID sequence
    public int id;

    /**
     * Constructs a new Person object with the given details.
     *
     * @param name      The name of the person.
     * @param balance   The balance associated with the person.
     * @param gender    The gender of the person (Male/Female).
     * @param birthdate The birthdate of the person in "dd-MM-yyyy" format.
     */
    public Person(String name, int balance, Gender gender, String birthdate) {
        this.id = nextId++; // Assign unique ID and increment
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    // Getters and setters
    public String getName() {
        return name;
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

    public String getBirthdate() {
        return birthdate;
    }

    public int getId() {
        return id;
    }

    /**
     * Calculates the age of the person based on the birthdate.
     *
     * @param birthdate The person's birthdate in "dd-MM-yyyy" format.
     * @return The person's age in years or -1 if there's an error parsing the date.
     */
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


    /**
     * Returns a string representation of the person, including their ID, name, gender, birthdate, age, and balance.
     *
     * @return A string containing the person's information.
     */

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Gender: " + gender + " | Birthday: " + birthdate + " | Age: " + calcDateDiff(birthdate) + " | Balance: " + balance;
    }


}
