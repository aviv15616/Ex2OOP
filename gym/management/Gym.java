/**
 * The Gym class represents a gym management system that handles clients, sessions, instructors, and financial balance.
 * It is implemented as a singleton to ensure only one instance of the gym exists.
 */
package gym.management;

import gym.customers.*;
import gym.management.Sessions.Session;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Main class for gym management. This class maintains a list of sessions, clients, instructors,
 * and other key entities of the gym. It also provides methods to manage these entities and their interactions.
 */
public class Gym {

    private static Gym instance; // Singleton instance of the Gym class
    public int balance;
    private String name;
    private Secretary secretary;
    final List<Session> sessions = new ArrayList<>();
    final List<Client> clients = new ArrayList<>();
    final List<Instructor> instructors = new ArrayList<>();
    protected final List<String> actionLog = new LinkedList<>(); // Stores logged actions

    /**
     * Private constructor to enforce the singleton pattern.
     */
    private Gym() {
    }

    /**
     * Provides the singleton instance of the Gym class.
     *
     * @return the singleton instance of the Gym class
     */
    public static Gym getInstance() {
        if (instance == null) instance = new Gym();
        return instance;
    }

    /**
     * Assigns a secretary to the gym.
     *
     * @param person the person who will act as the secretary
     * @param salary the salary of the secretary
     */
    public void setSecretary(Person person, int salary) {
        this.secretary = new Secretary(person, salary);
        secretary.logAction("A new secretary has started working at the gym: " + secretary.getPerson().getName());
    }

    /**
     * Retrieves the secretary of the gym.
     *
     * @return the secretary object
     */
    public Secretary getSecretary() {
        return secretary;
    }

    /**
     * Sets the name of the gym.
     *
     * @param name the name of the gym
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the action log.
     *
     * @return the list of logged actions
     */
    public List<String> getActionLog() {
        return actionLog;
    }

    /**
     * Returns a string representation of the gym, including its name, secretary, balance, clients,
     * instructors, and sessions.
     *
     * @return the string representation of the gym
     */
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
