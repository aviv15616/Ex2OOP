package gym.management.Sessions;

public class SessionType {
    public static final SessionType Pilates = new SessionType("Pilates");
    public static final SessionType MachinePilates = new SessionType("MachinePilates");
    public static final SessionType ThaiBoxing = new SessionType("ThaiBoxing");
    public static final SessionType Ninja = new SessionType("Ninja");
    private final String name;

    private SessionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
