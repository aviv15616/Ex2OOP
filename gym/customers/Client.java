package gym.customers;

public class Client extends Person {
    public Client(String name, int balance, Gender gender, String birthdate, int id) {
        super(name, balance, gender, birthdate);
        this.id=id;
    }
    @Override
    public boolean equals(Object a){
        Client c1=(Client)a;
        return this.id==c1.getId();
    }

}