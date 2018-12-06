package schedulesystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

public class ScheduleView extends JFrame{

    private final ScheduleDatabase database;
    private final JPanel main, mySchedule, browser, registered, adder;
    public final JButton addCourse = new JButton("Add Course");
    public final JButton browseCourses = new JButton("Browse Courses");
    public final JButton viewSchedule = new JButton("Schedule");
    public final JButton registeredCourses = new JButton("Registered Courses");
    public final JButton register = new JButton("Register Course");
    public final JButton backToMain = new JButton("Back");
    public final JButton drop = new JButton("Drop");
    public final JButton search = new JButton("Search Course");
    public final JButton back = new JButton("Back");
    public final JButton link = new JButton("Course Page");
    public final JTextField dept, courseNum, sectionNum, filterField;
    private final JTable table, regTable;
    private TableModel model, regModel;
    private TableColumnModel columns, regColumns;
    public TableRowSorter<TableModel> tableFilter;
    private final JScrollPane scrollpane, regScrollPane;
    public User user = new User(this);


    
    public ScheduleView(ScheduleDatabase database) {
        this.database = database;

        this.setTitle("Scheduler");
        this.setLocationRelativeTo(null);
        
        main = new JPanel();
        mySchedule = new JPanel();
        browser = new JPanel();
        registered = new JPanel();
        adder = new JPanel();
        
        filterField = new JTextField(10);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);
        
        main.add(addCourse);
        main.add(browseCourses);
        main.add(viewSchedule);
        main.add(registeredCourses);
        
        model = createTable();
        table = new JTable(model);
        tableFilter = new TableRowSorter<>(model);
        table.setRowSorter(tableFilter);
        columns = createColumns(table);
        
        filterField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                String text = filterField.getText();
                
                if (text.trim().length() == 0)
                    tableFilter.setRowFilter(null);
                else
                    tableFilter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                String text = filterField.getText();
                
                if (text.trim().length() == 0)
                    tableFilter.setRowFilter(null);
                else
                    tableFilter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
            } 
        });

        table.setShowVerticalLines(false);
        table.setFont(new Font("Helvetica", Font.PLAIN, 16));
        table.setRowHeight(25);
        
        scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(800, 480));
        //browser.add(backToMain);
        browser.add(scrollpane);
        browser.add(register);
        browser.add(filterField);
        browser.add(link);

        JLabel textLabel1 = new JLabel("Department");
        dept = new JTextField(4);
        textLabel1.setLabelFor(dept);
        JLabel textLabel2 = new JLabel("Course Number");
        courseNum = new JTextField(4);
        textLabel2.setLabelFor(courseNum);
        JLabel textLabel3 = new JLabel("Section Number");
        sectionNum = new JTextField(3);
        textLabel3.setLabelFor(sectionNum);
        adder.add(textLabel1);
        adder.add(dept);
        adder.add(textLabel2);
        adder.add(courseNum);
        adder.add(textLabel3);
        adder.add(sectionNum);
        adder.add(search);
        adder.add(back);

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
    
    public void addAdderListener(ActionListener listenForAdder) {
        
        addCourse.addActionListener(listenForAdder);
    }
    
    public void addBackListener(ActionListener listenForBack) {
        
        back.addActionListener(listenForBack);
    }
    
    public void addMainListener(ActionListener listenForMain) {
        
        backToMain.addActionListener(listenForMain);
    }

    public void addRegisteredCoursesListener(ActionListener listenForRegisteredCourses){

        registeredCourses.addActionListener(listenForRegisteredCourses);
    }

    public void addDropListener (ActionListener listenForDrop){

        drop.addActionListener(listenForDrop);
    }
    
    public void addLinkListener (ActionListener listenForLink){
        link.addActionListener(listenForLink);
    }
    
    public void showBrowse() {
        
        main.setVisible(false);
        this.add(browser);
        this.setSize(900, 650);
        browser.setVisible(true);
        backToMain.setVisible(true);
    }
    
    public void showMain() {
        browser.setVisible(false);
        registered.setVisible(false);
        adder.setVisible(false);
        backToMain.setVisible(false);
        main.setVisible(true);
        this.setSize(600, 200);
    }
    
    public void showAdder() {
        main.setVisible(false);
        this.add(adder);
        this.setSize(600, 125);
        adder.setVisible(true);
    }

    public void showRegistered(){
        this.add(registered);
        main.setVisible(false);
        registered.setVisible(true);
        backToMain.setVisible(true);
        this.setSize(900, 600);
    }
    
    private TableModel createTable() {
        TableModel m = new DefaultTableModel() {
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
        
        return m;
    }

    private TableModel createRegTable() {
        TableModel m = new DefaultTableModel() {
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

        return m;
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
        return user.registered.get(row);
    }
    
    public void removeRowTable() {
        DefaultTableModel m = (DefaultTableModel)table.getModel();
        m.fireTableDataChanged();
    }
    
    public void removeRowRegTable() {
        DefaultTableModel m = (DefaultTableModel)regTable.getModel();
        m.fireTableDataChanged();
    }
    
    public void showMessage(String str) {
        JOptionPane.showMessageDialog(this.getContentPane(), str);
    }

}
