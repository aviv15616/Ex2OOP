package gym.customers;

public class Client {
    private Person person;

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


    public Person getPerson() {
        return this.person;
    }
}