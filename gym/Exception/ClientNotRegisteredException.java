package gym.Exception;

/**
 * Exception thrown when a client is not registered in the gym system.
 * <p>
 * This exception is used to indicate that an operation or action was attempted
 * involving a client who is not registered in the gym's system.
 */
public class ClientNotRegisteredException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message, which is saved for later retrieval
     *                by the {@link Throwable#getMessage()} method.
     */
    public ClientNotRegisteredException(String message) {
        super(message);
    }
}
