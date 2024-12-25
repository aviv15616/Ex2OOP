package gym.management;
import gym.customers.Instructor;
import gym.management.Sessions.*;

public class SessionFactory {
    /**
     * Creates a session of the specified type with the given parameters.
     * Based on the provided session type, the method creates an appropriate session object
     * (e.g., Pilates, Machine Pilates, Thai Boxing, Ninja).
     *
     * @param type       The type of session to create (e.g., Pilates, MachinePilates, ThaiBoxing, Ninja).
     * @param date       The date of the session in the format "dd-MM-yyyy".
     * @param forum      The forum type (e.g., Female, Male, Seniors, All).
     * @param instructor The instructor leading the session.
     * @return The created session object corresponding to the session type.
     * @throws IllegalArgumentException if an unsupported session type is provided.
     */
    protected static Session createSession(SessionType type, String date, ForumType forum, Instructor instructor) {
        return switch (type) {
            case Pilates -> new Pilates(date, forum, instructor, 60, 30);
            case MachinePilates -> new MachinePilates(date, forum, instructor, 80, 10);
            case ThaiBoxing -> new ThaiBoxing(date, forum, instructor, 100, 20);
            case Ninja -> new Ninja(date, forum, instructor, 150, 5);
            default -> throw new IllegalArgumentException("Unsupported session type: " + type);
        };
    }

}