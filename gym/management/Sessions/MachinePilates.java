package gym.management.Sessions;

import gym.customers.Instructor;

/**
 * Represents a Machine Pilates session in the gym management system.
 * <p>
 * This class is a specialized type of {@link Session} for Machine Pilates,
 * which includes information about the session type, date, forum type,
 * assigned instructor, price, and maximum capacity.
 */
public class MachinePilates extends Session {
    /**
     * Creates a new Machine Pilates session with the specified details.
     *
     * @param date       The date of the session in the format "YYYY-MM-DD".
     * @param forum      The type of forum (e.g., Female, Male, Seniors, All).
     * @param instructor The instructor assigned to the session.
     * @param price      The price of the session.
     * @param maxCap     The maximum number of participants allowed in the session.
     */
    public MachinePilates(String date, ForumType forum, Instructor instructor, int price, int maxCap) {
        super(SessionType.MachinePilates, date, forum, instructor, price, maxCap);
    }
}
