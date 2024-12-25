package gym.customers;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a client in the gym system.
 * <p>
 * A client is associated with a {@link Person} object and has a list of messages
 * in their message box, which can be used to store notifications or other information.
 */
public class Client {
    private Person person;
    private List<String> messageBox = new ArrayList<>();

    /**
     * Constructs a new Client with the given {@link Person}.
     *
     * @param person The Person object associated with the client.
     */
    public Client(Person person) {
        this.person = person;  // A single shared Person instance
    }

    /**
     * Returns a string representation of the client, including their personal details.
     *
     * @return A string containing the client's ID, name, gender, birthdate, age, and balance.
     */
    @Override
    public String toString() {
        String info = "ID: " + person.getId() + " | Name: " + person.getName() + " | Gender: " + person.getGender() + " | Birthday: " + person.getBirthdate() + " | Age: " + Person.calcDateDiff(person.getBirthdate()) + " | Balance: " + person.getBalance();
        return info;
    }

    /**
     * Compares this client to another object for equality.
     * <p>
     * The comparison is based on the unique ID of the associated {@link Person}.
     *
     * @param a The object to compare with this client.
     * @return true if the object is a client with the same ID; false otherwise.
     */
    @Override
    public boolean equals(Object a) {
        Client c1 = (Client) a;
        return c1.getPerson().getId() == person.getId();
    }

    /**
     * Returns the list of notifications/messages for the client.
     *
     * @return A list of strings containing the client's messages.
     */
    public List<String> getNotifications() {
        return messageBox;
    }

    /**
     * Returns the name of the client.
     *
     * @return The name of the client.
     */
    public String getName() {
        return person.getName();
    }

    /**
     * Returns the associated {@link Person} object for this client.
     *
     * @return The Person object representing the client.
     */
    public Person getPerson() {
        return this.person;
    }
}