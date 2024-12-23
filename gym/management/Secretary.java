package gym.management;

import gym.Exception.*;
import gym.customers.Client;
import gym.customers.Gender;
import gym.customers.Person;
import gym.management.Sessions.*;
import gym.management.Sessions.SessionType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Secretary {
    private final Person person;
    private final int salary;
    private final Gym gym = Gym.getInstance();


    public Secretary(Person person, int salary) {
        this.person = person;
        this.salary = salary;
    }

    private boolean isCurrSecretary() {
        return gym.getSecretary().equals(this);
    }

    /**
     * Logs an action to the action log.
     *
     * @param action The action to log.
     */
    private void logAction(String action) {
        gym.getActionLog().add(action);
    }

    private String dateToString(Date date) {
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        if (!isCurrSecretary()) throw new NullPointerException();
        String birthdate = person.getBirthdate();
        int age = Person.calcDateDiff(birthdate);

        if (age < 18) throw new InvalidAgeException("Error: Client must be at least 18 years old to register");
        Client client = new Client(person.getName(), person.getBalance(), person.getGender(), person.getBirthdate(), person.getId());
        if (gym.clients.contains(client)) throw new DuplicateClientException("Error: The client is already registered");
        gym.clients.add(client);
        logAction("Registered new client: " + person.getName());
        return client;
    }

    public Person getPerson() {
        return person;
    }

    private int calcDateDiff(Date checkedDate) {
        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(checkedDate);
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(new Date());

        int age = currentCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);
        if (currentCalendar.get(Calendar.MONTH) < birthCalendar.get(Calendar.MONTH) ||
                (currentCalendar.get(Calendar.MONTH) == birthCalendar.get(Calendar.MONTH) &&
                        currentCalendar.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }
        return age;
    }

    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessions) {
        if (!isCurrSecretary()) throw new NullPointerException();
        Instructor instructor = new Instructor(person, salary, sessions);
        if (!gym.instructors.contains(instructor)) {
            gym.instructors.add(instructor);
            logAction("Hired new instructor: " + person.getName() + " with salary per hour: " + salary);
        }
        return instructor;
    }

    public void unregisterClient(Client c2) throws ClientNotRegisteredException {
        if (!isCurrSecretary()) throw new NullPointerException();
        if (!gym.clients.contains(c2)) throw new ClientNotRegisteredException("Error: Client not registered");
        gym.clients.remove(c2);
        logAction("Unregistered client: " + c2.getName());
    }

    private boolean canAccessSession(Client c1, Session s2) {
        String sessionDate = s2.getDate();
        ForumType type = s2.getForum();
        Gender gender = c1.getGender();
        int sessionPrice = s2.getPrice();
        int clientAge = Person.calcDateDiff(c1.getBirthdate());
        int clientBalance = c1.getBalance();
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

    public void registerClientToLesson(Client c1, Session s2) throws DuplicateClientException, ClientNotRegisteredException {
        if (!isCurrSecretary()) throw new NullPointerException();
        if (!gym.clients.contains(c1))
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        if (s2.getRegistered().contains(c1))
            throw new DuplicateClientException("Error: The client is already registered for this lesson");
        if (canAccessSession(c1, s2)) {
            s2.addClient(c1);
            c1.setBalance(c1.getBalance() - s2.getPrice());
            setGymBalance(getGymBalance() + s2.getPrice());
            logAction("Registered client: " + c1.getName() + " to session: " + s2.getType() + " on " + convertDateFormat(s2.getDate()) + " for price: " + s2.getPrice());
        }
    }

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

    public void setGymBalance(int balance) {
        if (!isCurrSecretary()) throw new NullPointerException();
        gym.balance = balance;
    }

    public int getGymBalance() {
        if (!isCurrSecretary()) throw new NullPointerException();
        return gym.balance;
    }

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

    public Session addSession(SessionType type, String date, ForumType forum, Instructor instructor) throws InstructorNotQualifiedException {
        if (!isCurrSecretary()) throw new NullPointerException();
        Session session = SessionFactory.createSession(type, date, forum, instructor);

        if (!instructor.getSessionTypes().contains(type)) {
            throw new InstructorNotQualifiedException("Error: Instructor is not qualified for this session type.");
        }
        if (!gym.sessions.contains(session)) {
            gym.sessions.add(session);
            logAction("Created new session: " + type + " on " + convertDateFormat(date) + " with instructor: " + instructor.getPerson().getName());
        }
        return session;
    }
    public int getSalary(){
        return salary;
    }

    public void paySalaries() {
        for(Instructor i1: gym.instructors){
            int count=0;
            for(Session s1:gym.sessions){
                if(s1.getInstructor().equals(i1)) count++;
            }
            int toAdd=count*i1.getSalary();
            int currInsBal=i1.getPerson().getBalance();
            i1.getPerson().setBalance(currInsBal+toAdd);
            setGymBalance(getGymBalance()-toAdd);
        }
        int toAddSec=gym.getSecretary().salary;
        int currSecBal=gym.getSecretary().getPerson().getBalance();
        gym.getSecretary().getPerson().setBalance(currSecBal+toAddSec);
        setGymBalance(getGymBalance()-toAddSec);
        logAction("Salaries have been paid to all employees");
    }

    /**
     * Prints all logged actions.
     */
    public void printActions() {
        for (String action : gym.getActionLog()) {
            System.out.println(action);
        }
    }
@Override
    public String toString() {
        return "ID: " + this.person.getId() +
                " | Name: " + this.person.getName() +
                " | Gender: " + this.person.getGender() +
                " | Birthday: " + this.person.getBirthdate() +
                " | Age: " + Person.calcDateDiff(this.person.getBirthdate()) +
                " | Balance: " + this.person.getBalance() +
                " | Role: Secretary" +
                " | Salary per Month: " + this.salary;
    }
}
