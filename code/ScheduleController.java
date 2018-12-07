package schedulesystem;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
        this.view.addAdderListener(new Actions());
        this.view.addBackListener(new Actions());
        this.view.addLinkListener(new Actions());
        this.view.addCourseAddListener(new Actions());
    }
    
    public void register(Course course) {
        view.user.registerCourse(course);
    }
    
    public void drop(Course course) {
        view.user.dropCourse(course);
    }
    
    public boolean checkConflict(Course c1, Course c2) {
        
        String[] days = {"M", "T", "W", "TH", "F", "S"};
        boolean conflict = false;
        for(String s : days) {
            if(c1.getDay().contains(s) && c2.getDay().contains(s))
                conflict = true;
        }
        
        if(!conflict)
            return false;
        
        int c1start = c1.getStartTime(), c1end = c1.getEndTime();
        int c2start = c2.getStartTime(), c2end = c2.getEndTime();
        
        if(c1start < 730) {
            c1start += 1200;
            c1end += 1200;
        }
        if(c2start < 730) {
            c2start += 1200;
            c2end += 1200;
        }
        if(c1end > c2start && c1start < c2end)
            return true;
        if(c2end > c1start && c2start < c1end)
            return true;
        
        return false;
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
                boolean conflict = false;
                for(Course c : view.user.registered)
                    if(checkConflict(c, reg)) {
                        conflict = true;
                        break;
                    }
                if(!conflict) {
                    view.removeRowTable();
                    register(reg);
                    database.remove(reg);
                }
                else {
                    view.showMessage("Course not added: Time Conflict");
                }
            }

            if (ae.getSource() == view.drop) {
                Course course = view.getSelectedDropCourse();
                view.removeRowRegTable();
                drop(course);
                database.add(course);
            }
            
            if(ae.getSource() == view.addCourse) {
                view.showAdder();
            }
            
            if(ae.getSource() == view.back) {
                view.showMain();
            }
            
            if(ae.getSource() == view.link) {
                try {
                    Course course = view.getSelectedCourse();
                    URI uri = new URI("https://catalog.lsu.edu/search_advanced.php?cur_cat_oid=17&search_database=Search&search_db=Search&cpage=1&ecpage=1&ppage=1&spage=1&tpage=1&location=33&filter%5Bkeyword%5D=" + course.getDepartment() + "+" + course.getNumber());
                    if(Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().browse(uri);
                    }
                } catch (URISyntaxException | IOException ex) {}
            }
            
            if(ae.getSource() == view.manualAdd) {
                Course course = database.find(view.dept.getText(), Integer.parseInt(view.courseNum.getText()), Integer.parseInt(view.sectionNum.getText()));
                if (course == null)
                    view.showMessage("Course not found.");
                else {
                    boolean conflict = false;
                    for(Course c : view.user.registered)
                        conflict = checkConflict(c, course);
                    if(!conflict) {
                        view.removeRowTable();
                        register(course);
                        database.remove(course);
                    }
                    else {
                        view.showMessage("Course not added: Time Conflict");
                    }
                }
            }
        }
    }
}
