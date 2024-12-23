package gym.management;
import gym.customers.*;
import gym.management.Sessions.Session;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Gym {
    private static Gym instance;
    public int balance;
    private String name;

    private Secretary secretary;
    final List<Session> sessions = new ArrayList<>();
    final List<Client> clients = new ArrayList<>();
    final List<Instructor> instructors = new ArrayList<>();
    private final List<String> actionLog = new LinkedList<>(); // Stores logged actions



    private Gym() {}

    public static Gym getInstance() {
        if (instance == null) instance = new Gym();
        return instance;
    }
    public List<String> getActionLog(){
        return actionLog;
    }



    public void setSecretary(Person person, int salary) {
        this.secretary = new Secretary(person, salary);
        actionLog.add("A new secretary has started working at the gym: "+secretary.getPerson().getName());
    }

    public Secretary getSecretary() {
        return secretary;
    }

    public void setName(String name) {
        this.name=name;
    }
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("Gym Name: ").append(this.name).append("\n");
        info.append("Gym Secretary: ").append(secretary.displayInfo()).append("\n");
        info.append("Gym Balance: ").append(this.balance);
        return info.toString();
    }
}
