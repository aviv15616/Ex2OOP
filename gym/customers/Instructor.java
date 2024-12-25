/**
 * The Instructor class represents an instructor at the gym.
 * It contains details about the instructor's personal information, salary, and session types they are certified to teach.
 */
package gym.customers;

import gym.management.Sessions.SessionType;

import java.util.ArrayList;

/**
 * Class representing an instructor at the gym.
 */
public class Instructor {
    private final Person person;
    private final int salary;
    private ArrayList<SessionType> sessionTypes;

    /**
     * Constructs a new Instructor object.
     *
     * @param person       the personal information of the instructor
     * @param salary       the salary of the instructor per hour
     * @param sessionTypes the list of session types the instructor is certified to teach
     */
    public Instructor(Person person, int salary, ArrayList<SessionType> sessionTypes) {
        this.person = person;
        this.salary = salary;
        this.sessionTypes = sessionTypes;
    }

    /**
     * Retrieves the salary of the instructor.
     *
     * @return the salary of the instructor
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Retrieves the personal information of the instructor.
     *
     * @return the Person object associated with the instructor
     */
    public Person getPerson() {
        return person;
    }


    /**
     * Retrieves the session types the instructor is certified to teach.
     *
     * @return a list of SessionType objects
     */
    public ArrayList<SessionType> getSessionTypes() {
        return sessionTypes;
    }

    /**
     * Converts the list of session types to a string representation.
     *
     * @return a comma-separated string of session types
     */
    public String stringSessionTypes() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < sessionTypes.size(); i++) {
            SessionType currType = sessionTypes.get(i);
            if (i == sessionTypes.size() - 1) string.append(currType.toString());
            else {
                string.append(currType.toString()).append(", ");
            }
        }
        return string.toString();
    }

    /**
     * Checks if this instructor is equal to another object.
     * Two instructors are considered equal if their associated Person objects have the same ID.
     *
     * @param a the object to compare with this instructor
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object a) {
        Instructor i1 = (Instructor) a;
        return i1.getPerson().getId() == person.getId();
    }

    /**
     * Returns a string representation of the instructor, including their ID, name, gender,
     * birthdate, age, balance, role, salary, and certified session types.
     *
     * @return a string representation of the instructor
     */
    @Override
    public String toString() {
        String info = "ID: " + person.getId() + " | Name: " + person.getName() + " | Gender: " + person.getGender() + " | Birthday: " + person.getBirthdate() + " | Age: " + Person.calcDateDiff(person.getBirthdate()) + " | Balance: " + person.getBalance() +
                " | Role: Instructor | Salary per Hour: " + salary + " | Certified Classes: " + stringSessionTypes();
        return info;

    }
}
