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
                view.removeRowTable();
                register(reg);
                database.remove(reg);
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
