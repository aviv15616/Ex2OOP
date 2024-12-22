package gym.management.Sessions;
import gym.management.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Session {
    private SessionType type;
    private ForumType forum;
    private Date date;
    private Instructor instructor;
    public Session(SessionType type,String date,ForumType forum, Instructor instructor){
        this.type=type;
        this.date=parseDate(date);
        this.forum=forum;
        this.instructor=instructor;
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
}
