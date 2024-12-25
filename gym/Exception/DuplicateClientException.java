package gym.Exception;

/**
 * Exception thrown when a duplicate client is encountered in the system.
 * <p>
 * This exception is used to indicate that an operation or action was attempted
 * involving a client who is already present or registered in the gym's system when they shouldn't be.
 */
public class DuplicateClientException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message, which is saved for later retrieval
     *                by the {@link Throwable#getMessage()} method.
     */
    public DuplicateClientException(String message) {
        super(message);
    }
}
