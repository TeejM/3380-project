package schedulesystem;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScheduleDatabase {

    private ArrayList<Course> courses;
    private FileInputStream fileIn;
    private ObjectInputStream in;
    private FileOutputStream fileOut;
    private ObjectOutputStream out;
    private File file = new File("database.ser");
    
    public ScheduleDatabase() throws IOException {
        try {
            fileIn = new FileInputStream(file);
            in = new ObjectInputStream(fileIn);
            courses = (ArrayList<Course>)in.readObject();
            in.close();
            fileIn.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("Database not found");
            courses = new ArrayList<>();
            parseCourses();
        }
        catch(IOException | ClassNotFoundException e) {}
    }
    
    public int size() {
        return courses.size();
    }
    
    public Course get(int i) {
        try {
            courses.get(i);
        }
        catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Please select a class");
        }
        return courses.get(i);
    }
    
    public void add(Course course) {
        courses.add(course);
    }
    
    public void remove(Course course) {
        courses.remove(course);
    }
    
    public ArrayList<Course> getCourses() {
        return courses;
    }
    
    public Course find(String dept, int num, int sec) {
        for (Course c : courses) {
            if(c.getDepartment().equals(dept))
                if(c.getNumber() == num)
                    if(c.getSection() == sec)
                        return c;
        }
        return null;
    }
    
   private void parseCourses() throws FileNotFoundException, IOException {
        String regex = "([A-Z]+)\\s+(\\d\\d\\d\\d).*?(\\d)\\s+([A-Z/]+\\s+[A-Z/]*\\s*[A-Z/]*\\s*[A-Z/]*\\s*[A-Z/]*\\s*)\\s+(\\d).0\\s+(\\d+)-(\\d+)(?:N*)\\s+([A-Z]+\\s*[A-Z]*\\s*[A-Z]*)";
        String regex2 = "(?:(F)).*?([A-Z]+)\\s+(\\d\\d\\d\\d).*?(\\d)\\s+([A-Z/]+\\s+[A-Z/]*\\s*[A-Z/]*\\s*[A-Z/]*\\s*[A-Z/]*\\s*)\\s+(\\d).0\\s+(\\d+)-(\\d+)(?:N*)\\s+([A-Z]+\\s*[A-Z]*\\s*[A-Z]*)";
        Pattern pattern = Pattern.compile(regex);
        Pattern pattern2 = Pattern.compile(regex);
        Matcher matcher, matcher2;
        String line;
        ArrayList<String> files = new ArrayList<>();
        
        File folder = new File("C:/Users/TJ/Downloads/htmls/");
        for(File fileEntry : folder.listFiles()) {
            if(fileEntry.isDirectory()) {
                for(String htmlfile : fileEntry.list()) {
                    if(htmlfile.equals("REGVCOF2.html")) {
                        files.add(fileEntry.toString() + "/" + htmlfile);
                    }
                }
            }
        }
        for(String a : files) {
            try (BufferedReader br = new BufferedReader(new FileReader(new File(a)))) {
                while((line = br.readLine()) != null) {
                    if(line.contains("&amp;"))
                        line = line.replace("&amp;","AND");
                    if(!line.contains("TBA") && !line.contains("LAB") && !line.contains("1-3")) {
                        if(line.contains("(F)")) {
                            matcher2 = pattern2.matcher(line);
                            if(matcher2.find())
                                courses.add(new Course(matcher2.group(8), matcher2.group(4), matcher2.group(1), Integer.parseInt(matcher2.group(6)), Integer.parseInt(matcher2.group(7)), Integer.parseInt(matcher2.group(3)), Integer.parseInt(matcher2.group(2)), Integer.parseInt(matcher2.group(5))));
                        }
                        else {
                            matcher = pattern.matcher(line);
                            if(matcher.find())
                                courses.add(new Course(matcher.group(8), matcher.group(4), matcher.group(1), Integer.parseInt(matcher.group(6)), Integer.parseInt(matcher.group(7)), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(5))));
                        }
                    }
                }
            }
        }
        System.out.println(courses.size());
        try {
            fileOut = new FileOutputStream(file);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(courses);
            out.close();
            fileOut.close();
        }
        catch(FileNotFoundException e) {}
        catch(IOException e){}
    }
}
