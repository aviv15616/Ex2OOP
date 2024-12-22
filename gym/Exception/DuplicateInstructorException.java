package gym.Exception;

public class DuplicateInstructorException extends Exception {
    public DuplicateInstructorException() {
        super("Instructor already exists");
    }
}
