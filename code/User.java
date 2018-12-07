package schedulesystem;

import java.util.ArrayList;

public class User {
    
    ScheduleView view;
    int hours = 0;
    ArrayList<Course> registered = new ArrayList<>();
    
    public User(ScheduleView view) {
        this.view = view;
    }

    public void registerCourse(Course course){
        registered.add(course);
        hours += course.getHours();
        view.showMessage("Course Registered");
    }

    public void dropCourse(Course course){
        registered.remove(course);
        hours -= course.getHours();
        view.showMessage("Course Dropped");
    }
    
    public int hours() {
        return hours;
    }
}
