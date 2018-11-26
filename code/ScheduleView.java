package schedulesystem;

import java.awt.*;
import javax.swing.*;

public class ScheduleView extends JFrame{

    private final JPanel main, mySchedule, browser, registered;
    private final JButton addCourse = new JButton("Add Course");
    private final JButton browseCourses = new JButton("Browse Courses");
    private final JButton viewSchedule = new JButton("View Schedule");
    private final JScrollBar scrollbar = new JScrollBar();
    private final JTable table = new JTable();
    private final JScrollPane scrollpane = new JScrollPane(table);
    
    
    public ScheduleView() {
        
        main = new JPanel();
        mySchedule = new JPanel();
        browser = new JPanel();
        registered = new JPanel();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);
        
        main.add(addCourse);
        main.add(browseCourses);
        main.add(viewSchedule);
        
        browser.add(scrollpane, BorderLayout.CENTER);
        
        this.add(main);
        this.setVisible(true);
    }
}
