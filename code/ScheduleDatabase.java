package schedulesystem;

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

    private ArrayList<String> departments = new ArrayList<>();
    private ArrayList<Course> courses;
    private ArrayList<ArrayList<Course>> courses2 = new ArrayList<>();
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
            courses = new ArrayList<>();
            parseCourses();
        }
        catch(IOException | ClassNotFoundException e) {}
        divideCourses();
    }
    
    public int size() {
        return courses.size();
    }
    
    public int size(int i) {
        return courses2.get(i).size();
    }
    
    public Course get(int i) {
        return courses.get(i);
    }
    
    public Course get(int i, int j) {
        return courses2.get(i).get(j);
    }
    
    public ArrayList<String> getDepartments() {
        return departments;
    }
    
    private void parseCourses() throws FileNotFoundException, IOException {
        String regex = "([A-Z]+)\\s+(\\d\\d\\d\\d).*?(\\d)\\s+([A-Z/]+\\s+[A-Z/]*\\s*[A-Z/]*\\s*[A-Z/]*\\s*[A-Z/]*\\s*)\\s+(\\d).0\\s+(\\d+)-(\\d+)(?:N*)\\s+([A-Z]+\\s*[A-Z]*\\s*[A-Z]*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
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
                    if(!line.startsWith("(F)") && !line.contains("TBA") && !line.contains("LAB") && !line.contains("1-3")) {
                        matcher = pattern.matcher(line);
                        if(matcher.find())
                            courses.add(new Course(matcher.group(8), matcher.group(4), matcher.group(1), Integer.parseInt(matcher.group(6)), Integer.parseInt(matcher.group(7)), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(5))));
                    }
                }
            }
        }
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
    
    private void divideCourses() {
        for(Course c : courses) {
            if(!departments.contains(c.getDepartment())) {
                departments.add(c.getDepartment());
                courses2.add(new ArrayList<>());
            }
            courses2.get(departments.indexOf(c.getDepartment())).add(c);
        }
        System.out.println(courses2);
    }
}
