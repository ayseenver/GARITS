package teamproject.AlertsReminders;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import teamproject.Databases.DB_ImplClass;
import teamproject.Databases.DatabaseBackup;

public class Timer {

    public Timer() {
        ScheduleBackup();
    }

    private void ScheduleBackup() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Date aDate = new Date();// Current date or parsed date;

        Calendar with = Calendar.getInstance();
        with.setTime(aDate);
        int hour = with.get(Calendar.HOUR_OF_DAY);
        int Minutes = with.get(Calendar.MINUTE);

        int MinutesPassed12AM = hour * 60 + Minutes;
        int MinutesAt8AM = (8 * 60);
        int OneDayMinutes = 24 * 60;
        long DelayInMinutes;
        
        if (MinutesPassed12AM <= MinutesAt8AM){
            DelayInMinutes = MinutesAt8AM - MinutesPassed12AM;
        }else{
            DelayInMinutes = OneDayMinutes - (MinutesPassed12AM - MinutesAt8AM);
        }
        scheduler.scheduleAtFixedRate(new DatabaseBackup(), DelayInMinutes, OneDayMinutes, TimeUnit.MINUTES);
    }

}
