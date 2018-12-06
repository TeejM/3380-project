package schedulesystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScheduleController
{
    private final ScheduleView view;
    private final ScheduleDatabase database;

    public ScheduleController(ScheduleView view, ScheduleDatabase database) {
        this.view = view;
        this.database = database;
        
        this.view.addBrowseListener(new Actions());
        this.view.addMainListener(new Actions());
        this.view.addRegisterListener(new Actions());
        this.view.addRegisteredCoursesListener(new Actions());
        this.view.addDropListener(new Actions());
    }
    
    public void register(Course course) {
        view.user.registerCourse(course);
    }
    
    public void drop(Course course) {
        view.user.dropCourse(course);
    }
    
    public boolean checkConflict() {
        return true;
    }
    
    public boolean checkCompletion() {
        return true;
    }

    public class Actions implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == view.browseCourses)
                view.showBrowse();

            if (ae.getSource() == view.backToMain)
                view.showMain();

            if (ae.getSource() == view.registeredCourses)
                view.showRegistered();

            if (ae.getSource() == view.register) {
                Course reg = view.getSelectedCourse();
                register(reg);
            }

            if (ae.getSource() == view.drop) {
                Course course = view.getSelectedDropCourse();
                drop(course);
                view.refreshRegistered();
            }
        }
    }
    
    /*class BrowseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            view.showBrowse();
        }
    }
    
    class MainListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            view.showMain();
        }
    }

    class RegisterListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource() == view.register)
                System.out.println("Class registered");
        }
    }*/
}
