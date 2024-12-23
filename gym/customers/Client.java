package gym.customers;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private Person person;
    private List<String> messageBox=new ArrayList<>();

    public Client(Person person) {
        this.person = person;  // A single shared Person instance
    }

    @Override
    public String toString(){
        String info="ID: "+person.getId()+" | Name: "+person.getName()+" | Gender: "+person.getGender()+" | Birthday: "+person.getBirthdate()+" | Age: "+Person.calcDateDiff(person.getBirthdate())+" | Balance: "+person.getBalance();
        return info;
    }
    @Override
    public boolean equals(Object a){
        Client c1= (Client)a;
        return c1.getPerson().getId()==person.getId();
    }
    public List <String> getNotifications(){
        return messageBox;
    }
    public String getName(){
        return person.getName();
    }


    public Person getPerson() {
        return this.person;
    }
}