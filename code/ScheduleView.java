package schedulesystem;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;

public class ScheduleView extends JFrame{

    private final ScheduleDatabase database;
    private final JPanel main, mySchedule, browser, registered;
    public final JButton addCourse = new JButton("Add Course");
    public final JButton browseCourses = new JButton("Browse Courses");
    public final JButton viewSchedule = new JButton("Schedule");
    public final JButton registeredCourses = new JButton("Registered Courses");
    public final JButton register = new JButton("Register Course");
    public final JButton backToMain = new JButton("Back");
    public final JButton drop = new JButton("Drop");
    private final JTable table;
    private JTable regTable;
    private TableModel model, regModel;
    private TableColumnModel columns, regColumns;
    private final JScrollPane scrollpane;
    private JScrollPane regScrollPane;
    public User user = new User();


    
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
        //browser.add(backToMain);
        browser.add(scrollpane);
        browser.add(register);



        regModel = createRegTable();
        regTable = new JTable(regModel);
        regColumns = createColumns(regTable);

        regTable.setShowVerticalLines(false);
        regTable.setFont(new Font("Helvetica", Font.PLAIN, 16));
        regTable.setRowHeight(25);

        regScrollPane = new JScrollPane(regTable);
        regScrollPane.setPreferredSize(new Dimension(800, 480));

        registered.add(backToMain);
        registered.add(regScrollPane);
        registered.add(drop);
        registered.setVisible(true);


        this.setLayout(new BorderLayout());
        this.add(main, BorderLayout.CENTER);
        this.add(backToMain, BorderLayout.SOUTH);

        this.setVisible(true);
        backToMain.setVisible(false);
    }

    public void addRegisterListener(ActionListener listenForRegister){

        register.addActionListener(listenForRegister);
    }

    public void addBrowseListener(ActionListener listenForBrowse) {
        
        browseCourses.addActionListener(listenForBrowse);
    }
    
    public void addMainListener(ActionListener listenForMain) {
        
        backToMain.addActionListener(listenForMain);
    }

    public void addRegisteredCoursesListener(ActionListener listenForRegisteredCourses){

        registeredCourses.addActionListener(listenForRegisteredCourses);
    }

    public void addDropListener (ActionListener listenforDrop){

        drop.addActionListener(listenforDrop);
    }
    
    public void showBrowse() {
        
        main.setVisible(false);
        this.add(browser);
        this.setSize(900, 600);
        browser.setVisible(true);
        backToMain.setVisible(true);
    }
    
    public void showMain() {
        browser.setVisible(false);
        registered.setVisible(false);
        backToMain.setVisible(false);
        main.setVisible(true);
        this.setSize(600, 200);
    }

    public void showRegistered(){
        this.add(registered);
        main.setVisible(false);
        registered.setVisible(true);
        backToMain.setVisible(true);
        this.setSize(900, 600);
    }
    
    private TableModel createTable() {
        TableModel model = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return database.size();
            }

            @Override
            public int getColumnCount() {
                return 8;
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
                }
                
                return "";
            }
        };
        
        return model;
    }

    private TableModel createRegTable() {
        TableModel model = new AbstractTableModel() {
            @Override
            public int getRowCount() { return user.registered.size(); }

            @Override
            public int getColumnCount() { return 8; }

            @Override
            public Object getValueAt(int row, int col) {
                Course course = user.registered.get(row);

                switch (col) {
                    case 0: return course.getDepartment();
                    case 1: return course.getNumber();
                    case 2: return course.getSection();
                    case 3: return course.getTitle();
                    case 4: return course.getHours();
                    case 5: return course.getStartTime();
                    case 6: return course.getEndTime();
                    case 7: return course.getDay();
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
        
        columnModel.getColumn(0).setHeaderValue("DEPT");
        columnModel.getColumn(1).setHeaderValue("NO");
        columnModel.getColumn(2).setHeaderValue("SEC");
        columnModel.getColumn(3).setHeaderValue("TITLE");
        columnModel.getColumn(4).setHeaderValue("HRS");
        columnModel.getColumn(5).setHeaderValue("START");
        columnModel.getColumn(6).setHeaderValue("END");
        columnModel.getColumn(7).setHeaderValue("DAYS");
        
        return columnModel;
    }

    public Course getSelectedCourse(){
        int row = table.getSelectedRow();
        return database.get(row);
    }

    public Course getSelectedDropCourse(){
        int row = regTable.getSelectedRow();
        return database.get(row);
    }

    public void refreshRegistered(){
        regTable.removeRow(5);
    }

}
