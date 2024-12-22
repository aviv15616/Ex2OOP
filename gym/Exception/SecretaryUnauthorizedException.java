package gym.Exception;

public class SecretaryUnauthorizedException extends Exception {
    public SecretaryUnauthorizedException() {
        super("Secretary unauthorized for this action");
    }
}
