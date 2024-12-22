package gym.management;

import gym.Exception.*;
import gym.customers.Client;
import gym.customers.Gender;
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
    private boolean isCurrSecretary(){
        return gym.getSecretary().equals(this);
    }



    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException,
            SecretaryUnauthorizedException {
        if (!isCurrSecretary()) throw new SecretaryUnauthorizedException();

            Date currDate = new Date();  // Get current date

            // Get the person's birthdate
            Date birthdate = person.getBirthdate();

            // Calculate the person's age
            int age = calcDateDiff(birthdate, currDate);

            // If the person is under 18, return without doing anything
            if (age < 18) throw new InvalidAgeException();
            if(gym.clients.contains((Client) person))throw new DuplicateClientException();
            gym.clients.add((Client) person);
            System.out.println("Client registered successfully.");
            return (Client) person;
    }

    private int calcDateDiff(Date checkedDate, Date currDate) {
        // Create a Calendar instance to work with date manipulation
        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(checkedDate);

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

    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessions)throws SecretaryUnauthorizedException,DuplicateInstructorException {
        if (!isCurrSecretary()) throw new SecretaryUnauthorizedException();
        Instructor instructor = new Instructor(person, salary, sessions);
        if(gym.instructors.contains(instructor))throw new DuplicateInstructorException();
            gym.instructors.add(instructor);
            return instructor;
    }


    public void unregisterClient(Client c2) throws ClientNotRegisteredException,SecretaryUnauthorizedException {
        if(!(gym.clients.contains(c2)))throw new ClientNotRegisteredException();
        if (!isCurrSecretary()) throw new SecretaryUnauthorizedException();
            this.gym.clients.remove(c2);
    }

    public void registerClientToLesson(Client c1, Session s2)throws SecretaryUnauthorizedException,
            InvalidAgeException, InstructorNotQualifiedException,DuplicateClientException,FullSessionException
            ,SessionHasPastException,InvalidGenderException,InsufficientFundsException{
        if (!isCurrSecretary()) throw new SecretaryUnauthorizedException();
        Date sessionDate=s2.getDate();
        Date currDate= new Date();
        ForumType type=s2.getForum();
        Gender gender =c1.getGender();
        int sessionPrice=s2.getPrice();
        int clientAge=calcDateDiff(c1.getBirthdate(),currDate);
        int clientBalance=c1.getBalance();
        boolean isSenior= clientAge >= 65;
        if(!(s2.getRegistered().size()<s2.getMaxCap()))throw new FullSessionException();
        if(calcDateDiff(sessionDate,currDate)<0)throw new SessionHasPastException();
        if(type==ForumType.Male&&gender!=Gender.Male)throw new InvalidGenderException();
        if(type==ForumType.Female&&gender!=Gender.Female)throw new InvalidGenderException();
        if(type==ForumType.Seniors&&!isSenior)throw new InvalidAgeException();
        if(clientBalance<sessionPrice)throw new InsufficientFundsException();
        s2.addClient(c1);
    }

    public Session addSession(SessionType type, String date, ForumType forum, Instructor instructor) throws SecretaryUnauthorizedException,
            DuplicateSessionException,InstructorNotQualifiedException{
        // Check if the current user is the secretary
        if (!isCurrSecretary()) throw new SecretaryUnauthorizedException();
        Session s1 = SessionFactory.createSession(type, date, forum, instructor);
        // Check if the instructor supports the session type
        if (!instructor.getSessionTypes().contains(type)) throw new InstructorNotQualifiedException();
        if(gym.sessions.contains(s1)) throw new DuplicateSessionException();
            gym.sessions.add(s1);
            return s1;
    }
    public void paySalaries() {
    }

    public void printActions() {
    }
}
