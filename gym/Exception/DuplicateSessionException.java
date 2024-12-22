package gym.Exception;

public class DuplicateSessionException extends Exception {
    public DuplicateSessionException() {
        super("Session already exists");
    }
}
