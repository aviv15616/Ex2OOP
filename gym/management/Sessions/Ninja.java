package gym.management.Sessions;

import gym.customers.Instructor;

/**
 * Represents a Ninja session at the gym.
 * This session has a fixed price and maximum capacity.
 */
public class Ninja extends Session {
    private final int price = 150;
    private final int maxCap = 5;

    /**
     * Constructor to create a new Ninja session with specific date, forum type, and instructor.
     *
     * @param date       The date and time of the session in the format "dd-MM-yyyy HH:mm".
     * @param forum      The forum type for the session (e.g., Female, Male, Seniors, All).
     * @param instructor The instructor conducting the session.
     * @param price      The price for the session (defaults to 150).
     * @param maxCap     The maximum capacity for the session (defaults to 5).
     */
    public Ninja(String date, ForumType forum, Instructor instructor, int price, int maxCap) {
        super(SessionType.Ninja, date, forum, instructor, price, maxCap);
    }

}

