package gym.management.Sessions;

import gym.customers.Client;
import gym.customers.Instructor;


import java.util.ArrayList;

/**
 * Represents a gym session, which can have a specific type, forum, date, price, capacity, and instructor.
 * The session allows clients to register and attend.
 */
public class Session {
    private final SessionType type;
    private final ForumType forum;
    private final String date;
    private final ArrayList<Client> registered = new ArrayList<>();
    private final int price;
    private final int maxCap;
    private final Instructor instructor;

    /**
     * Constructor to create a new session with the specified type, date, forum, instructor, price, and max capacity.
     *
     * @param type       The type of the session (e.g., Pilates, Yoga).
     * @param date       The date of the session in the format "dd-MM-yyyy".
     * @param forum      The forum type for the session (e.g., Female, Male, Seniors, All).
     * @param instructor The instructor conducting the session.
     * @param price      The price of the session.
     * @param maxCap     The maximum capacity for the session.
     */
    protected Session(SessionType type, String date, ForumType forum, Instructor instructor, int price, int maxCap) {
        this.type = type;
        this.date = date;
        this.forum = forum;
        this.instructor = instructor;
        this.price = price;
        this.maxCap = maxCap;

    }

    /**
     * Returns the instructor assigned to this session.
     *
     * @return The instructor of this session.
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Returns the type of the session.
     *
     * @return The type of the session (e.g., Pilates, Yoga).
     */
    public SessionType getType() {
        return type;
    }

    /**
     * Returns the date of the session.
     *
     * @return The date of the session as a string in the format "dd-MM-yyyy".
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the forum type of the session.
     *
     * @return The forum type of the session (e.g., Female, Male, Seniors, All).
     */
    public ForumType getForum() {
        return forum;
    }

    /**
     * Returns the list of clients who have registered for the session.
     *
     * @return An ArrayList of clients registered for the session.
     */
    public ArrayList<Client> getRegistered() {
        return registered;
    }

    /**
     * Returns the maximum capacity of the session.
     *
     * @return The maximum number of participants allowed in the session.
     */
    public int getMaxCap() {
        return maxCap;
    }

    /**
     * Returns the price of the session.
     *
     * @return The price for attending the session.
     */
    public int getPrice() {
        return price;
    }


    /**
     * Provides a string representation of the session, including key details like type, date, forum, instructor, and participant count.
     *
     * @return A string representation of the session.
     */
    @Override
    public String toString() {
        String info = "Session Type: " + type + " | Date: " + date + " | Forum: " + forum + " | Instructor: " + instructor.getPerson().getName() + " | Participants: " + registered.size() + "/" + maxCap;
        return info;
    }
}
