package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;

public class Instructor {
    private Person person;
    private int salary;
    private ArrayList<SessionType> sessionTypes;

    public Instructor(Person person, int salary, ArrayList<SessionType> sessionTypes) {
        this.person = person;
        this.salary = salary;
        this.sessionTypes = sessionTypes;
    }

    public int getSalary() {
        return salary;
    }

    public Person getPerson() {
        return person;
    }


    public ArrayList<SessionType> getSessionTypes() {
        return sessionTypes;
    }
    public String stringSessionTypes(){
        String string="";
        for (int i = 0; i < sessionTypes.size(); i++) {
            SessionType currType=sessionTypes.get(i);
            if(i==sessionTypes.size()-1)string+=currType.toString();
            else {
                string += currType.toString() + ", " ;
            }
        }
        return string;
    }


    @Override
    public String toString() {
        String info = "ID: " + person.getId() + " | Name: " + person.getName() + " | Gender: " + person.getGender() + " | Birthday: " + person.getBirthdate() + " | Age: " + Person.calcDateDiff(person.getBirthdate()) + " | Balance: " + person.getBalance() +
                " | Role: Instructor | Salary per Hour: "+salary+" | Certified Classes: "+stringSessionTypes();
        return info;

    }
}
