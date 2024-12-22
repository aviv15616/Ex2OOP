package gym.management;
import gym.customers.*;
import gym.management.Sessions.Session;

import java.util.ArrayList;
import java.util.List;

public class Gym {
    private static Gym instance;
    private String name;
    private Secretary secretary;
    final List<Session> sessions = new ArrayList<>();
    final List<Client> clients = new ArrayList<>();
    final List<Instructor> instructors = new ArrayList<>();



    private Gym() {}

    public static Gym getInstance() {
        if (instance == null) instance = new Gym();
        return instance;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
    return this.name;
    }

    public void setSecretary(Person person, int salary) {
        this.secretary = new Secretary(person, salary);
    }

    public Secretary getSecretary() {
        return secretary;
    }

    @Override
    public String toString() {
        return null;
    }
}
