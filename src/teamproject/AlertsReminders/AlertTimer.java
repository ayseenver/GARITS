package teamproject.AlertsReminders;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import teamproject.Databases.Alert;

public class AlertTimer {

    public AlertTimer() {
        Alert();
    }
    
    private void Alert() {
        //call the alert class every 15 minutes
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(new Alert(), 0, 15, TimeUnit.MINUTES);
    }
     
}
