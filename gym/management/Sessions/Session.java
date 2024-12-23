package gym.management.Sessions;
import gym.customers.Client;
import gym.customers.Person;
import gym.management.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Session {
    private SessionType type;
    private ForumType forum;
    private String date;
    private ArrayList <Client> registered=new ArrayList<>();
    private int price;
    private int maxCap;
    private Instructor instructor;

    public Session(SessionType type,String date,ForumType forum, Instructor instructor,int price, int maxCap){
        this.type=type;
        this.date=date;
        this.forum=forum;
        this.instructor=instructor;
        this.price=price;
        this.maxCap=maxCap;


    }
    public Instructor getInstructor(){
        return instructor;
    }
    private Date parseDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }
    public SessionType getType(){
        return type;
    }
    public String getDate(){
        return date;
    }
    public ForumType getForum(){
        return forum;
    }
    public ArrayList<Client> getRegistered(){
        return registered;
    }
    public int getMaxCap() {
        return maxCap;
    }
    public int getPrice() {
        return price;
    }
    public void addClient(Client c1){
        registered.add(c1);
    }
    @Override
    public String toString(){
        String info="Session Type: "+type+" | Date: "+date+" | Forum: "+forum+" | Instructor: "+instructor.getPerson().getName()+" | Participants: "+registered.size()+"/"+maxCap;
        return info;
    }
}
