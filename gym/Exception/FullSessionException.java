package gym.Exception;

public class FullSessionException extends Exception{
    public FullSessionException() {
        super("Session is full");
    }
}
