package schedulesystem;

import javax.swing.*;
import java.util.ArrayList;

public class User {
    
    ArrayList<Course> completed;
    ArrayList<Course> registered = new ArrayList<Course>();
    
    public User() {
        
    }

    public void registerCourse(Course course){
        registered.add(course);
        JOptionPane.showMessageDialog(null, "Course Registered");
    }

    public void dropCourse(Course course){
        registered.remove(course);
        JOptionPane.showMessageDialog(null, "Course Dropped");
    }
}
