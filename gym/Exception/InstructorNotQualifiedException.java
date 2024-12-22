package gym.Exception;

public class InstructorNotQualifiedException extends Exception{
    public InstructorNotQualifiedException() {
        super("Instructor is not qualified for this lesson");
    }
}
