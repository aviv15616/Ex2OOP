package gym.Exception;

/**
 * Exception thrown when an instructor is not qualified to conduct a session.
 * <p>
 * This exception is used to indicate that an operation or action was attempted
 * with an instructor who does not meet the necessary qualifications to lead a session.
 */
public class InstructorNotQualifiedException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message, which is saved for later retrieval
     *                by the {@link Throwable#getMessage()} method.
     */
    public InstructorNotQualifiedException(String message) {
        super(message);
    }
}
