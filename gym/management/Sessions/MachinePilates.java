package gym.management.Sessions;

import gym.customers.Client;
import gym.management.Instructor;

import java.util.ArrayList;

public class MachinePilates extends Session {
    public MachinePilates(String date,ForumType forum, Instructor instructor,int price,int maxCap){
        super(SessionType.MachinePilates, date, forum, instructor,price,maxCap);
    }
}
