package schedulesystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.*;

public class ScheduleController
{
    private final ScheduleView view;
    private final ScheduleDatabase database;
    //private final JPanel currentPanel, previousPanel;
    
    public ScheduleController(ScheduleView view, ScheduleDatabase database) {
        this.view = view;
        this.database = database;
        this.view.addAdderListener(new AdderListener());
        this.view.addBrowseListener(new BrowseListener());
        //this.view.addBackListener(new BackListener());
    }
    
    public void register() {
        
    }
    
    public void drop() {
        
    }
    
    public boolean checkConflict() {
        return true;
    }
    
    public boolean checkCompletion() {
        return true;
    }
    
    class BrowseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            view.showBrowse();
        }
    }
    
    class AdderListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            view.showAdder();
        }
    }
    
    /*class BackListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            view.previousScreen(currentPanel, previousPanel);
        }
    }*/
}
