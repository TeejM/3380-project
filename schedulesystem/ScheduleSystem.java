package schedulesystem;

public class ScheduleSystem {

    public static void main(String[] args)
    {
        ScheduleDatabase data = new ScheduleDatabase();
        ScheduleView view = new ScheduleView(data);
        ScheduleController controller = new ScheduleController(view, data);
        User user = new User();
        
        data.add(new Course("Mukhopadhyay", "TTh", "Object Oriented Design", "CSC", 430, 600, 1, 3380, 3));
        data.add(new Course("Chatzopoulos", "MWF", "Astronomy of the Stars", "ASTR", 1230, 130, 1, 1102, 3));
        data.add(new Course("Duncan", "TTh", "Advanced Data Structures", "CSC", 900, 1030, 1, 3102, 3));
        data.add(new Course("Knapp", "MWF", "Engineering Statistics", "IE", 830, 930, 1, 3302, 3));
        data.add(new Course("Miller", "MW", "Introduction to Cyber Security", "CSC", 500, 630, 1, 2700, 3));
        data.add(new Course("Mukhopadhyay", "TTh", "Object Oriented Design", "CSC", 430, 600, 1, 3380, 3));
        data.add(new Course("Chatzopoulos", "MWF", "Astronomy of the Stars", "ASTR", 1230, 130, 1, 1102, 3));
        data.add(new Course("Duncan", "TTh", "Advanced Data Structures", "CSC", 900, 1030, 1, 3102, 3));
        data.add(new Course("Knapp", "MWF", "Engineering Statistics", "IE", 830, 930, 1, 3302, 3));
        data.add(new Course("Miller", "MW", "Introduction to Cyber Security", "CSC", 500, 630, 1, 2700, 3));
        data.add(new Course("Mukhopadhyay", "TTh", "Object Oriented Design", "CSC", 430, 600, 1, 3380, 3));
        data.add(new Course("Chatzopoulos", "MWF", "Astronomy of the Stars", "ASTR", 1230, 130, 1, 1102, 3));
        data.add(new Course("Duncan", "TTh", "Advanced Data Structures", "CSC", 900, 1030, 1, 3102, 3));
        data.add(new Course("Knapp", "MWF", "Engineering Statistics", "IE", 830, 930, 1, 3302, 3));
        data.add(new Course("Miller", "MW", "Introduction to Cyber Security", "CSC", 500, 630, 1, 2700, 3));
        data.add(new Course("Mukhopadhyay", "TTh", "Object Oriented Design", "CSC", 430, 600, 1, 3380, 3));
        data.add(new Course("Chatzopoulos", "MWF", "Astronomy of the Stars", "ASTR", 1230, 130, 1, 1102, 3));
        data.add(new Course("Duncan", "TTh", "Advanced Data Structures", "CSC", 900, 1030, 1, 3102, 3));
        data.add(new Course("Knapp", "MWF", "Engineering Statistics", "IE", 830, 930, 1, 3302, 3));
        data.add(new Course("Miller", "MW", "Introduction to Cyber Security", "CSC", 500, 630, 1, 2700, 3));
        data.add(new Course("Mukhopadhyay", "TTh", "Object Oriented Design", "CSC", 430, 600, 1, 3380, 3));
        data.add(new Course("Chatzopoulos", "MWF", "Astronomy of the Stars", "ASTR", 1230, 130, 1, 1102, 3));
        data.add(new Course("Duncan", "TTh", "Advanced Data Structures", "CSC", 900, 1030, 1, 3102, 3));
        data.add(new Course("Knapp", "MWF", "Engineering Statistics", "IE", 830, 930, 1, 3302, 3));
        data.add(new Course("Miller", "MW", "Introduction to Cyber Security", "CSC", 500, 630, 1, 2700, 3));
        data.add(new Course("Mukhopadhyay", "TTh", "Object Oriented Design", "CSC", 430, 600, 1, 3380, 3));
        data.add(new Course("Chatzopoulos", "MWF", "Astronomy of the Stars", "ASTR", 1230, 130, 1, 1102, 3));
        data.add(new Course("Duncan", "TTh", "Advanced Data Structures", "CSC", 900, 1030, 1, 3102, 3));
        data.add(new Course("Knapp", "MWF", "Engineering Statistics", "IE", 830, 930, 1, 3302, 3));
        data.add(new Course("Miller", "MW", "Introduction to Cyber Security", "CSC", 500, 630, 1, 2700, 3));
        data.add(new Course("Mukhopadhyay", "TTh", "Object Oriented Design", "CSC", 430, 600, 1, 3380, 3));
        data.add(new Course("Chatzopoulos", "MWF", "Astronomy of the Stars", "ASTR", 1230, 130, 1, 1102, 3));
        data.add(new Course("Duncan", "TTh", "Advanced Data Structures", "CSC", 900, 1030, 1, 3102, 3));
        data.add(new Course("Knapp", "MWF", "Engineering Statistics", "IE", 830, 930, 1, 3302, 3));
        data.add(new Course("Miller", "MW", "Introduction to Cyber Security", "CSC", 500, 630, 1, 2700, 3));
        data.add(new Course("Mukhopadhyay", "TTh", "Object Oriented Design", "CSC", 430, 600, 1, 3380, 3));
        data.add(new Course("Chatzopoulos", "MWF", "Astronomy of the Stars", "ASTR", 1230, 130, 1, 1102, 3));
        data.add(new Course("Duncan", "TTh", "Advanced Data Structures", "CSC", 900, 1030, 1, 3102, 3));
        data.add(new Course("Knapp", "MWF", "Engineering Statistics", "IE", 830, 930, 1, 3302, 3));
        data.add(new Course("Miller", "MW", "Introduction to Cyber Security", "CSC", 500, 630, 1, 2700, 3));
        data.add(new Course("Mukhopadhyay", "TTh", "Object Oriented Design", "CSC", 430, 600, 1, 3380, 3));
        data.add(new Course("Chatzopoulos", "MWF", "Astronomy of the Stars", "ASTR", 1230, 130, 1, 1102, 3));
        data.add(new Course("Duncan", "TTh", "Advanced Data Structures", "CSC", 900, 1030, 1, 3102, 3));
        data.add(new Course("Knapp", "MWF", "Engineering Statistics", "IE", 830, 930, 1, 3302, 3));
        data.add(new Course("Miller", "MW", "Introduction to Cyber Security", "CSC", 500, 630, 1, 2700, 3));
        data.add(new Course("Mukhopadhyay", "TTh", "Object Oriented Design", "CSC", 430, 600, 1, 3380, 3));
        data.add(new Course("Chatzopoulos", "MWF", "Astronomy of the Stars", "ASTR", 1230, 130, 1, 1102, 3));
        data.add(new Course("Duncan", "TTh", "Advanced Data Structures", "CSC", 900, 1030, 1, 3102, 3));
        data.add(new Course("Knapp", "MWF", "Engineering Statistics", "IE", 830, 930, 1, 3302, 3));
        data.add(new Course("Miller", "MW", "Introduction to Cyber Security", "CSC", 500, 630, 1, 2700, 3));
        data.add(new Course("Mukhopadhyay", "TTh", "Object Oriented Design", "CSC", 430, 600, 1, 3380, 3));
        data.add(new Course("Chatzopoulos", "MWF", "Astronomy of the Stars", "ASTR", 1230, 130, 1, 1102, 3));
        data.add(new Course("Duncan", "TTh", "Advanced Data Structures", "CSC", 900, 1030, 1, 3102, 3));
        data.add(new Course("Knapp", "MWF", "Engineering Statistics", "IE", 830, 930, 1, 3302, 3));
        data.add(new Course("Miller", "MW", "Introduction to Cyber Security", "CSC", 500, 630, 1, 2700, 3));
        data.add(new Course("Mukhopadhyay", "TTh", "Object Oriented Design", "CSC", 430, 600, 1, 3380, 3));
        data.add(new Course("Chatzopoulos", "MWF", "Astronomy of the Stars", "ASTR", 1230, 130, 1, 1102, 3));
        data.add(new Course("Duncan", "TTh", "Advanced Data Structures", "CSC", 900, 1030, 1, 3102, 3));
        data.add(new Course("Knapp", "MWF", "Engineering Statistics", "IE", 830, 930, 1, 3302, 3));
        data.add(new Course("Miller", "MW", "Introduction to Cyber Security", "CSC", 500, 630, 1, 2700, 3));
        
    }
}
