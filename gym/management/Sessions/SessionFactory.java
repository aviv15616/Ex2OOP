package gym.management.Sessions;

import gym.management.Instructor;

public class SessionFactory {
    public static Session createSession(SessionType type, String date, ForumType forum, Instructor instructor) {
        return switch (type) {
            case Pilates -> new Pilates(date, forum, instructor,60,30);
            case MachinePilates -> new MachinePilates(date, forum, instructor,80,10);
            case ThaiBoxing -> new ThaiBoxing(date, forum, instructor,100,20);
            case Ninja -> new Ninja(date, forum, instructor,150,5);
            default -> throw new IllegalArgumentException("Unsupported session type: " + type);
        };
    }

}