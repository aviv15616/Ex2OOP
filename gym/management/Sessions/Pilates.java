package gym.management.Sessions;

import gym.customers.Instructor;

/**
 * Represents a Pilates session at the gym.
 * This session is created with a specific date, forum type, instructor, price, and maximum capacity.
 */
public class Pilates extends Session {
    /**
     * Constructor to create a new Pilates session.
     *
     * @param date       The date and time of the session in the format "dd-MM-yyyy HH:mm".
     * @param forum      The forum type for the session (e.g., Female, Male, Seniors, All).
     * @param instructor The instructor conducting the session.
     * @param price      The price for the session.
     * @param maxCap     The maximum capacity for the session.
     */
    public Pilates(String date, ForumType forum, Instructor instructor, int price, int maxCap) {
        super(SessionType.Pilates, date, forum, instructor, price, maxCap);
    }

}