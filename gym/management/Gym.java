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
        System.out.print("Gym Name: " + this.name + "\n");
        System.out.print("Gym Secretary: " + secretary.toString());
        System.out.println("Gym Balance: " + this.balance + "\n");

        // Clients Data
        System.out.print("Clients Data:\n");
        for (Client client : clients) { // Assuming 'clients' is a list of Client objects
            System.out.print(client.toString() + "\n");
        }

        // Employees Data
        System.out.print("\nEmployees Data:\n");
        for (Instructor instructor : instructors) { // Assuming 'instructors' is a list of Instructor objects
            System.out.print(instructor.toString() + "\n");
        }
        System.out.print(secretary.toString() + "\n"); // Including secretary in employees list

        // Sessions Data
        System.out.print("Sessions Data:\n");
        for (int i = 0; i < sessions.size(); i++) { // Assuming 'sessions' is a list of Session objects
            if (i == sessions.size() - 1) {
                // Print the last session without a trailing blank line
                System.out.print(sessions.get(i).toString());
            } else {
                System.out.print(sessions.get(i).toString() + "\n");
            }
        }
        return "";
    }
}
