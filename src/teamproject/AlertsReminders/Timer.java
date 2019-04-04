package teamproject.AlertsReminders;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import teamproject.Databases.AutomaticReminders;
import teamproject.Databases.CalculateFlexibleDiscount;

public class Timer {

    public Timer() {
        DailyReminders();
        RunBackups();
        RunReports();
        CalculateFlexibleDiscounts();
    }

    private void RunBackups() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Date aDate = new Date();// Current date or parsed date;
        Calendar with = Calendar.getInstance();
        with.setTime(aDate);
        int hour = with.get(Calendar.HOUR_OF_DAY);
        int Minutes = with.get(Calendar.MINUTE);

        int MinutesPassed12AM = hour * 60 + Minutes;
        int MinutesAt8AM = (11 * 60) + 27;
        int OneDayMinutes = 24 * 60;
        long DelayInMinutes;

        if (MinutesPassed12AM <= MinutesAt8AM) {
            DelayInMinutes = MinutesAt8AM - MinutesPassed12AM;
        } else {
            DelayInMinutes = OneDayMinutes - (MinutesPassed12AM - MinutesAt8AM);
        }
        //Every day call the automatic backup method
        scheduler.scheduleAtFixedRate(new AutomaticBackups(), DelayInMinutes, OneDayMinutes, TimeUnit.MINUTES);
    }

    private void RunReports() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Date aDate = new Date();// Current date or parsed date;
        Calendar with = Calendar.getInstance();
        with.setTime(aDate);
        int hour = with.get(Calendar.HOUR_OF_DAY);
        int Minutes = with.get(Calendar.MINUTE);

        int MinutesPassed12AM = hour * 60 + Minutes;
        int MinutesAt8AM = (11 * 60) + 27;
        int OneDayMinutes = 24 * 60;
        long DelayInMinutes;

        if (MinutesPassed12AM <= MinutesAt8AM) {
            DelayInMinutes = MinutesAt8AM - MinutesPassed12AM;
        } else {
            DelayInMinutes = OneDayMinutes - (MinutesPassed12AM - MinutesAt8AM);
        }

        //Every day call the automatic report method
        scheduler.scheduleAtFixedRate(new AutomaticReport(), DelayInMinutes, OneDayMinutes, TimeUnit.MINUTES);
    }

    private void DailyReminders() {
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
        if (MinutesPassed12AM <= MinutesAt8AM) {
            DelayInMinutes = MinutesAt8AM - MinutesPassed12AM;
        } else {
            DelayInMinutes = OneDayMinutes - (MinutesPassed12AM - MinutesAt8AM);
        }
        scheduler.scheduleAtFixedRate(new AutomaticReminders(), 0, OneDayMinutes, TimeUnit.MINUTES);
    }

    private void CalculateFlexibleDiscounts() {
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

        if (MinutesPassed12AM <= MinutesAt8AM) {
            DelayInMinutes = MinutesAt8AM - MinutesPassed12AM;
        } else {
            DelayInMinutes = OneDayMinutes - (MinutesPassed12AM - MinutesAt8AM);
        }
        //if it's the 1st, calculate the flexible discounts for the last month
        scheduler.scheduleAtFixedRate(new CalculateFlexibleDiscount(), DelayInMinutes, OneDayMinutes, TimeUnit.MINUTES);
    }

}
