package gym.management;

import gym.customers.*;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;

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


    private Gym() {
    }

    public static Gym getInstance() {
        if (instance == null) instance = new Gym();
        return instance;
    }

    public List<String> getActionLog() {
        return actionLog;
    }


    public void setSecretary(Person person, int salary) {
        this.secretary = new Secretary(person, salary);
        actionLog.add("A new secretary has started working at the gym: " + secretary.getPerson().getName());
    }

    public Secretary getSecretary() {
        return secretary;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();

        // Gym basic details
        info.append("Gym Name: ").append(this.name).append("\n")
                .append("Gym Secretary: ").append(secretary.toString()).append("\n")
                .append("Gym Balance: ").append(this.balance).append("\n\n");

        // Clients Data
        info.append("Clients Data:\n");
        for (Client client : clients) { // Assuming 'clients' is a list of Client objects
            info.append(client.toString()).append("\n");
        }
        info.append("\n");

        // Employees Data
        info.append("Employees Data:\n");
        for (Instructor instructor : instructors) { // Assuming 'instructors' is a list of Instructor objects
            info.append(instructor.toString()).append("\n");
        }
        info.append(secretary.toString()).append("\n\n"); // Including secretary in employees list

        // Sessions Data
        info.append("Sessions Data:\n");
        for (Session session : sessions) { // Assuming 'sessions' is a list of Session objects
            info.append(session.toString()).append("\n");
        }

        return info.toString();
    }

}
