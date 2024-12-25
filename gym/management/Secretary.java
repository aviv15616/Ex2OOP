package gym.management;

import gym.Exception.*;
import gym.customers.Client;
import gym.customers.Gender;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Sessions.*;
import gym.management.Sessions.SessionType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Represents a secretary in the gym management system. The secretary is responsible
 * for managing client registrations, scheduling sessions, sending notifications, and
 * maintaining gym operations.
 */
public class Secretary {
    private final Person person;
    private final int salary;
    private final Gym gym = Gym.getInstance();



    /**
     * Constructs a Secretary with the specified personal information and salary.
     *
     * @param person The personal information of the secretary.
     * @param salary The monthly salary of the secretary.
     */
    public Secretary(Person person, int salary) {
        this.person = person;
        this.salary = salary;
    }


    /**
     * Sends a notification to all clients registered for a specific session.
     *
     * @param s1      The session for which clients will be notified.
     * @param message The notification message to send.
     */
    public void notify(Session s1, String message) {
        for (Client c1 : gym.clients) {
            if (s1.getRegistered().contains(c1)) {
                c1.getNotifications().add(message);
            }
        }
        logAction("A message was sent to everyone registered for session " + s1.getType() + " on " + convertDateFormat(s1.getDate()) + " : " + message);
    }

    /**
     * Reverses the date format from DD-MM-YYYY to YYYY-MM-DD.
     *
     * @param date The date string to reverse.
     * @return The reversed date format string.
     * @throws IllegalArgumentException If the date is null, empty, or in an invalid format.
     */
    public String reverseDateFormat(String date) {
        if (date == null || date.isEmpty()) {
            throw new IllegalArgumentException("Input date cannot be null or empty");
        }

        // Split the date into parts
        String[] parts = date.split("-");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Input date must be in the format DD-MM-YYYY");
        }

        // Rearrange and join the parts
        return parts[2] + "-" + parts[1] + "-" + parts[0];
    }

    /**
     * Sends a notification to all clients registered for any session on a specific date.
     *
     * @param date    The date for which notifications will be sent.
     * @param message The notification message to send.
     */
    public void notify(String date, String message) {
        for (Client c1 : gym.clients) {
            for (Session session : gym.sessions) {
                if (session.getDate().substring(0, 10).equals(date)) {
                    if (session.getRegistered().contains(c1)) {
                        c1.getNotifications().add(message);
                    }
                }
            }
        }
        logAction("A message was sent to everyone registered for a session on " + reverseDateFormat(date) + " : " + message);

    }

    /**
     * Sends a notification to all gym clients.
     *
     * @param message The notification message to send.
     */
    public void notify(String message) {
        for (Client c1 : gym.clients) {
            c1.getNotifications().add(message);
        }
        logAction("A message was sent to all gym clients: " + message);
    }

    /**
     * Checks if the current instance is the secretary of the gym.
     *
     * @return {@code true} if this instance is the current secretary of the gym, {@code false} otherwise.
     */
    private boolean isCurrSecretary() {
        return gym.getSecretary().equals(this);
    }

    /**
     * Logs an action to the gym's action log.
     *
     * @param action The action to log.
     */
    protected void logAction(String action) {
        gym.getActionLog().add(action);
    }

    /**
     * Registers a new client to the gym.
     *
     * @param person The personal information of the client.
     * @return The registered Client object.
     * @throws InvalidAgeException      If the client is under 18 years old.
     * @throws DuplicateClientException If the client is already registered.
     */
    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        if (!isCurrSecretary()) throw new NullPointerException();
        String birthdate = person.getBirthdate();
        int age = Person.calcDateDiff(birthdate);

        if (age < 18) throw new InvalidAgeException("Error: Client must be at least 18 years old to register");
        Client client = new Client(person);
        if (gym.clients.contains(client)) throw new DuplicateClientException("Error: The client is already registered");
        gym.clients.add(client);
        logAction("Registered new client: " + person.getName());
        return client;
    }

    /**
     * Retrieves the person associated with this instance.
     *
     * @return The {@link Person} object associated with this instance.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Hires a new instructor for the gym.
     *
     * @param person   The personal information of the instructor.
     * @param salary   The hourly salary of the instructor.
     * @param sessions The session types the instructor is certified to teach.
     * @return The hired Instructor object.
     */
    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessions) {
        if (!isCurrSecretary()) throw new NullPointerException();
        Instructor instructor = new Instructor(person, salary, sessions);
        if (!gym.instructors.contains(instructor)) {
            gym.instructors.add(instructor);
            logAction("Hired new instructor: " + person.getName() + " with salary per hour: " + salary);
        }
        return instructor;
    }

    /**
     * Removes a client from the gym's client list.
     *
     * @param c2 The client to unregister.
     * @throws ClientNotRegisteredException If the client is not registered.
     */
    public void unregisterClient(Client c2) throws ClientNotRegisteredException {
        if (!isCurrSecretary()) throw new NullPointerException();
        if (!gym.clients.contains(c2))
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        gym.clients.remove(c2);
        logAction("Unregistered client: " + c2.getPerson().getName());
    }

    /**
     * Determines if a client can access a specific session based on various criteria.
     *
     * @param c1 The {@link Client} attempting to register for the session.
     * @param s2 The {@link Session} the client is trying to access.
     * @return {@code true} if the client meets all criteria to access the session,
     * {@code false} otherwise.
     */
    private boolean canAccessSession(Client c1, Session s2) {
        String sessionDate = s2.getDate();
        ForumType type = s2.getForum();
        Gender gender = c1.getPerson().getGender();
        int sessionPrice = s2.getPrice();
        int clientAge = Person.calcDateDiff(c1.getPerson().getBirthdate());
        int clientBalance = c1.getPerson().getBalance();
        boolean isSenior = clientAge >= 65;
        List<String> errors = new ArrayList<>();
        // Check if the session is in the future
        if (!isSessionInTheFuture(sessionDate)) {
            errors.add("Failed registration: Session is not in the future");
        }
        // Check if the client's gender matches the session's gender requirements
        if ((type == ForumType.Female && gender != Gender.Female) || (type == ForumType.Male && gender != Gender.Male)) {
            errors.add("Failed registration: Client's gender doesn't match the session's gender requirements");
        }
        // Check if the client is old enough for a seniors session
        if (type == ForumType.Seniors && !isSenior) {
            errors.add("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
        }
        // Check if the client has enough balance
        if (clientBalance < sessionPrice) {
            errors.add("Failed registration: Client doesn't have enough balance");
        }
        // Check for available spots in the session
        if (s2.getRegistered().size() == s2.getMaxCap()) {
            errors.add("Failed registration: No available spots for session");
        }
        if (!errors.isEmpty()) {
            for (String s : errors) logAction(s);
            return false;
        }
        return true;
    }

    /**
     * Registers a client for a specific session, if all conditions are met.
     *
     * @param c1 The {@link Client} attempting to register for the session.
     * @param s2 The {@link Session} the client wants to join.
     * @throws DuplicateClientException     If the client is already registered for the session.
     * @throws ClientNotRegisteredException If the client is not registered with the gym.
     * @throws NullPointerException         If the current user is not the gym's secretary.
     */
    public void registerClientToLesson(Client c1, Session s2) throws DuplicateClientException, ClientNotRegisteredException {
        if (!isCurrSecretary())
            throw new NullPointerException();// Ensure that the current instance is the gym's secretary
        if (!gym.clients.contains(c1)) // Check if the client is registered with the gym
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        if (s2.getRegistered().contains(c1))// Check if the client is already registered for the session
            throw new DuplicateClientException("Error: The client is already registered for this lesson");
        if (canAccessSession(c1, s2)) {// Check if the client meets all criteria for session access
            s2.addClient(c1);
            c1.getPerson().setBalance(c1.getPerson().getBalance() - s2.getPrice());
            setGymBalance(getGymBalance() + s2.getPrice());
            logAction("Registered client: " + c1.getPerson().getName() + " to session: " + s2.getType() + " on " + convertDateFormat(s2.getDate()) + " for price: " + s2.getPrice());// Log the registration details
        }
    }

    /**
     * Checks if a given session date is in the future.
     *
     * @param sessionDate The session date in the format "dd-MM-yyyy HH:mm".
     * @return {@code true} if the session date is in the future, {@code false} otherwise.
     */
    private boolean isSessionInTheFuture(String sessionDate) {
        try {
            // Define the input format for the session date (23-01-2025 10:00)
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

            // Parse the session date string to Date object
            Date parsedSessionDate = sdf.parse(sessionDate);

            // Get the current date and time
            Calendar currentCalendar = Calendar.getInstance();

            // Compare the parsed session date with the current date
            return parsedSessionDate.after(currentCalendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false in case of any error (e.g., parsing exception)
        }
    }

    /**
     * Updates the gym's balance. Only the current secretary can perform this action.
     *
     * @param balance The new balance to set for the gym.
     * @throws NullPointerException If the current user is not the gym's secretary.
     */
    public void setGymBalance(int balance) {
        if (!isCurrSecretary()) throw new NullPointerException();
        gym.balance = balance;
    }

    /**
     * Retrieves the current gym balance. Only the gym's secretary can access this information.
     *
     * @return The current balance of the gym.
     * @throws NullPointerException If the current user is not the gym's secretary.
     */
    public int getGymBalance() {
        if (!isCurrSecretary()) throw new NullPointerException();
        return gym.balance;
    }

    /**
     * Converts a date from one format ("dd-MM-yyyy HH:mm") to another format ("yyyy-MM-dd'T'HH:mm").
     *
     * @param inputDate The date string to be converted.
     * @return The date formatted as a string in the new format, or {@code null} if a parsing error occurs.
     * @throws NullPointerException If the input date string is null.
     */
    public static String convertDateFormat(String inputDate) {
        try {
            // Define the input format (23-01-2025 10:00)
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

            // Parse the input date string to a Date object
            Date date = inputFormat.parse(inputDate);

            // Define the output format (2025-01-23T10:00)
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

            // Return the formatted date as a string
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Return null in case of parsing error
        }
    }

    /**
     * Adds a new session to the gym's schedule if the instructor is qualified and the current user is the secretary.
     *
     * @param type       The type of the session to be created (e.g., MachinePilates, Yoga, etc.).
     * @param date       The date and time of the session in the format "dd-MM-yyyy HH:mm".
     * @param forum      The forum type for the session (e.g., Female, Male, Seniors, All).
     * @param instructor The instructor assigned to conduct the session.
     * @return The newly created {@link Session}.
     * @throws NullPointerException            If the current user is not the gym's secretary.
     * @throws InstructorNotQualifiedException If the instructor is not qualified to conduct the specified session type.
     */
    public Session addSession(SessionType type, String date, ForumType forum, Instructor instructor) throws InstructorNotQualifiedException {
        if (!isCurrSecretary()) throw new NullPointerException();
        Session session = SessionFactory.createSession(type, date, forum, instructor);

        if (!instructor.getSessionTypes().contains(type)) {
            throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");
        }
        if (!gym.sessions.contains(session)) {
            gym.sessions.add(session);
            logAction("Created new session: " + type + " on " + convertDateFormat(date) + " with instructor: " + instructor.getPerson().getName());
        }
        return session;
    }

    /**
     * Returns the salary of the person.
     *
     * @return The salary of the person (for example, the secretary).
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Pays salaries to all gym employees, including instructors and the secretary.
     */
    public void paySalaries() {
        for (Instructor i1 : gym.instructors) {
            int count = 0;
            for (Session s1 : gym.sessions) {
                if (s1.getInstructor().equals(i1)) count++;
            }
            int toAdd = count * i1.getSalary();
            int currInsBal = i1.getPerson().getBalance();
            i1.getPerson().setBalance(currInsBal + toAdd);
            setGymBalance(getGymBalance() - toAdd);
        }
        int toAddSec = getSalary();
        int currSecBal = gym.getSecretary().getPerson().getBalance();
        gym.getSecretary().getPerson().setBalance(currSecBal + toAddSec);
        setGymBalance(getGymBalance() - toAddSec);
        logAction("Salaries have been paid to all employees");
    }

    /**
     * Prints all logged actions.
     */
    public void printActions() {
        for (String action : gym.getActionLog()) {
            System.out.print(action + "\n"); // Use \n explicitly
        }
    }

    /**
     * Returns a string representation of the Secretary object, including personal details and salary.
     *
     * @return A string containing the Secretary's ID, name, gender, birthdate, age, balance, role, and salary.
     */
    @Override
    public String toString() {
        return "ID: " + this.person.getId() +
                " | Name: " + this.person.getName() +
                " | Gender: " + this.person.getGender() +
                " | Birthday: " + this.person.getBirthdate() +
                " | Age: " + Person.calcDateDiff(this.person.getBirthdate()) +
                " | Balance: " + this.person.getBalance() +
                " | Role: Secretary" +
                " | Salary per Month: " + this.salary + "\n"; // Explicit \n added
    }
}