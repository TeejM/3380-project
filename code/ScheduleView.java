package schedulesystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class ScheduleView extends JFrame{

    private final ScheduleDatabase database;
    private final JPanel main, mySchedule, browser, registered;
    private final JButton addCourse = new JButton("Add Course");
    private final JButton browseCourses = new JButton("Browse Courses");
    private final JButton viewSchedule = new JButton("Schedule");
    private final JButton registeredCourses = new JButton("Registered Courses");
    private final JButton register = new JButton("Register Course");
    private final JTable table;
    private TableModel model;
    private TableColumnModel columns;
    private final JScrollPane scrollpane;
    
    
    public ScheduleView(ScheduleDatabase database) {
        
        this.database = database;
        
        this.setTitle("Scheduler");
        this.setLocationRelativeTo(null);
        
        main = new JPanel();
        mySchedule = new JPanel();
        browser = new JPanel();
        registered = new JPanel();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);
        
        main.add(addCourse);
        main.add(browseCourses);
        main.add(viewSchedule);
        main.add(registeredCourses);
        
        model = createTable();
        table = new JTable(model);
        columns = createColumns(table);

        table.setShowVerticalLines(false);
        table.setFont(new Font("Helvetica", Font.PLAIN, 16));
        table.setRowHeight(25);
        
        scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(800, 480));
        browser.add(scrollpane);
        browser.add(register);
        
        this.add(main);
        
        this.setVisible(true);
    }
    
    public void addBrowseListener(ActionListener listenForBrowse) {
        
        browseCourses.addActionListener(listenForBrowse);
    }
    
    public void showBrowse() {
        
        main.setVisible(false);
        this.add(browser);
        this.setSize(900, 600);
        browser.setVisible(true);
    }
    
    private TableModel createTable() {
        TableModel model = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return 50;
            }

            @Override
            public int getColumnCount() {
                return 9;
            }

            @Override
            public Object getValueAt(int row, int col) {
                Course course = database.get(row);
                switch (col) {
                    case 0: return course.getDepartment();
                    case 1: return course.getNumber();
                    case 2: return course.getSection();
                    case 3: return course.getTitle();
                    case 4: return course.getHours();
                    case 5: return course.getStartTime();
                    case 6: return course.getEndTime();
                    case 7: return course.getDay();
                    case 8: return course.getInstructor();
                }
                
                return "";
            }
        };
        
        return model;
    }
    
    private TableColumnModel createColumns(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();
        
        columnModel.getColumn(0).setPreferredWidth(15);
        columnModel.getColumn(1).setPreferredWidth(15);
        columnModel.getColumn(2).setPreferredWidth(10);
        columnModel.getColumn(3).setPreferredWidth(200);
        columnModel.getColumn(4).setPreferredWidth(10);
        columnModel.getColumn(5).setPreferredWidth(25);
        columnModel.getColumn(6).setPreferredWidth(25);
        columnModel.getColumn(7).setPreferredWidth(10);
        columnModel.getColumn(8).setPreferredWidth(80);
        
        columnModel.getColumn(0).setHeaderValue("DEPT");
        columnModel.getColumn(1).setHeaderValue("NO");
        columnModel.getColumn(2).setHeaderValue("SEC");
        columnModel.getColumn(3).setHeaderValue("TITLE");
        columnModel.getColumn(4).setHeaderValue("HRS");
        columnModel.getColumn(5).setHeaderValue("START");
        columnModel.getColumn(6).setHeaderValue("END");
        columnModel.getColumn(7).setHeaderValue("DAYS");
        columnModel.getColumn(8).setHeaderValue("INSTRUCTOR");
        
        return columnModel;
    }
}
