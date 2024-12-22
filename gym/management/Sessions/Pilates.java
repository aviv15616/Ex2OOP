package gym.management.Sessions;

import gym.management.Instructor;

public class Pilates extends Session {
    public Pilates(String date,ForumType forum, Instructor instructor,int price,int maxCap){
        super(SessionType.Pilates, date, forum, instructor,price,maxCap);
    }

}