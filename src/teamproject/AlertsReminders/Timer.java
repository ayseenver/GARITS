package teamproject.AlertsReminders;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import teamproject.Databases.Automation;
import teamproject.Databases.Alert;
import teamproject.Databases.CalculateFlexibleDiscount;

public class Timer {

    public Timer() {
        Automate();
        CalculateFlexibleDiscounts(LocalDate.now());
        //Alert();
    }

    private void Automate() {
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
        scheduler.scheduleAtFixedRate(new Automation(), DelayInMinutes, OneDayMinutes, TimeUnit.MINUTES);
    }

    private void CalculateFlexibleDiscounts(LocalDate date) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        //if it's the 1st, calculate the flexible discounts for the last month
        if (date.getDayOfMonth() == 1) {
            scheduler.scheduleAtFixedRate(new CalculateFlexibleDiscount(), 0, 30, TimeUnit.DAYS);
        }
    }

    /*
    private void Alert() {
        //call the alert class every 15 minutes
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(new Alert(), 0, 15, TimeUnit.MINUTES);
    }
     */
}
