package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.management.Sessions.*;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Secretary {
    private Person person;
    private int salary;

    private Gym gym=Gym.getInstance();

    public Secretary(Person person, int salary) {
        this.person = person;
        this.salary = salary;
    }

    ;

    public Client registerClient(Person person) {
        Date currDate = new Date();  // Get current date

        // Get the person's birthdate
        Date birthdate = person.getBirthdate();

        // Calculate the person's age
        int age = calculateAge(birthdate, currDate);

        // If the person is under 18, return without doing anything
        if (age < 18) {
            System.out.println("Client is under 18. Registration not allowed.");//Exception needed
            return null;
        }
        gym.clients.add((Client) person);

        // Otherwise, proceed with registration logic (for example, save the client info)
        System.out.println("Client registered successfully.");
        return (Client) person;
        // Your registration logic goes here
    }

    private int calculateAge(Date birthdate, Date currDate) {
        // Create a Calendar instance to work with date manipulation
        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(birthdate);

        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(currDate);

        // Calculate age by comparing birth year with current year
        int age = currentCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);

        // Adjust age if the birthday hasn't occurred yet this year
        if (currentCalendar.get(Calendar.MONTH) < birthCalendar.get(Calendar.MONTH) ||
                (currentCalendar.get(Calendar.MONTH) == birthCalendar.get(Calendar.MONTH) &&
                        currentCalendar.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH))) {
            age--; // Person hasn't had their birthday yet this year
        }

        return age;
    }

    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessions) {
        Instructor instructor= new Instructor(person,salary,sessions);
        gym.instructors.add(instructor);
        return instructor;
    }


    public void unregisterClient(Client c2) {
        this.gym.clients.remove(c2);
    }

    public void registerClientToLesson(Client c1, Session s2) {
    }

    public Session addSession(SessionType type, String date, ForumType forum, Instructor instructor) {
        Session s1=new Session(type, date, forum, instructor);
        gym.sessions.add(s1);
        return s1;
    }

    public void paySalaries() {
    }

    public void printActions() {
    }
}
