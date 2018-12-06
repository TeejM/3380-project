package schedulesystem;

import java.io.IOException;

public class ScheduleSystem {

    public static void main(String[] args) throws IOException
    {
        ScheduleDatabase data = new ScheduleDatabase();
        ScheduleView view = new ScheduleView(data);
        ScheduleController controller = new ScheduleController(view, data);
    }
}
