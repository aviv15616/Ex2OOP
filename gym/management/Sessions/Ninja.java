    package gym.management.Sessions;

    import gym.management.Instructor;
        public class Ninja extends Session {
            private final int price=150;
            private final int maxCap=5;
            public Ninja(String date,ForumType forum, Instructor instructor,int price,int maxCap){
                super(SessionType.Pilates, date, forum, instructor,price,maxCap);
            }

        }

