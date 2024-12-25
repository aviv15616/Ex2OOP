package gym.Exception;

/**
 * Exception thrown when an invalid age is encountered.
 * <p>
 * This exception is used to indicate that an operation or action was attempted
 * with an age value that is considered invalid (e.g., too young or too old for a session).
 */
public class InvalidAgeException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message, which is saved for later retrieval
     *                by the {@link Throwable#getMessage()} method.
     */
    public InvalidAgeException(String message) {
        super(message);
    }
}
