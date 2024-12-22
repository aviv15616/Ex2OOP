package gym.management;

import gym.customers.Person;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;

public class Instructor{
    private Person person;
    private int salary;
    private ArrayList<SessionType> sessionTypes;
    public Instructor(Person person, int salary, ArrayList<SessionType> sessionTypes){
        this.person=person;
        this.salary=salary;
        this.sessionTypes=sessionTypes;
    }
    public ArrayList<SessionType> getSessionTypes(){
        return sessionTypes;
    }

}
