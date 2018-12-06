package schedulesystem;

import java.util.ArrayList;

public class User {
    
    ScheduleView view;
    ArrayList<Course> registered = new ArrayList<>();
    
    public User(ScheduleView view) {
        this.view = view;
    }

    public void registerCourse(Course course){
        registered.add(course);
        view.showMessage("Course Registered");
    }

    public void dropCourse(Course course){
        registered.remove(course);
        view.showMessage("Course Dropped");
    }
}
