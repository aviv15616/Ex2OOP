package gym.Exception;

public class SessionHasPastException extends Exception {
    public SessionHasPastException() {
        super("Session has already past");
    }
}
