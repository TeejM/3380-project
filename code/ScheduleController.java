package schedulesystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScheduleController
{
    private final ScheduleView view;
    private final ScheduleDatabase database;
    
    public ScheduleController(ScheduleView view, ScheduleDatabase database) {
        this.view = view;
        this.database = database;
        
        this.view.addBrowseListener(new BrowseListener());
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
}
