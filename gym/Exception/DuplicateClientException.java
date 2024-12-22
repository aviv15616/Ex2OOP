package gym.Exception;

public class DuplicateClientException extends Exception{
    public DuplicateClientException() {
        super("Client already exists");
    }
}
