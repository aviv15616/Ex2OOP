package gym.management.Sessions;

import gym.customers.Instructor;

/**
 * Represents a Thai Boxing session.
 * <p>
 * This class extends the generic `Session` class and defines a specific type of session,
 * Thai Boxing, with predefined properties such as price and maximum capacity.
 */
public class ThaiBoxing extends Session {
    /**
     * Constructs a new Thai Boxing session.
     *
     * @param date       The date of the session in the format "dd-MM-yyyy".
     * @param forum      The forum type for the session (e.g., Female, Male, Seniors, All).
     * @param instructor The instructor conducting the session.
     * @param price      The price for attending the session.
     * @param maxCap     The maximum capacity of participants for the session.
     */
    public ThaiBoxing(String date, ForumType forum, Instructor instructor, int price, int maxCap) {
        super(SessionType.ThaiBoxing, date, forum, instructor, price, maxCap);
    }

}
