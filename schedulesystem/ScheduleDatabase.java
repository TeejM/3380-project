package schedulesystem;

import java.io.File;
import java.util.ArrayList;

public class ScheduleDatabase {

    private final ArrayList<Course> courses = new ArrayList<>();
    private File file;
    
    public ScheduleDatabase() {
        
    }
    
    public void add(Course course) {
        if(!courses.contains(course))
            courses.add(course);
    }
    
    public void remove(Course course) {
        if(courses.contains(course))
            courses.remove(course);
    }
    
    public int size() {
        return courses.size();
    }
    
    public Course get(int i) {
        return courses.get(i);
    }
}
