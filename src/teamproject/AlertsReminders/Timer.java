package teamproject.AlertsReminders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import teamproject.Databases.AutomaticBackups;
import teamproject.Databases.AutomaticReminders;
import teamproject.Databases.CalculateFlexibleDiscount;

public class Timer {

    public Timer() {
        DailyReminders();
        CheckBackupFrequency();
        MonthlyReports();
        CalculateFlexibleDiscounts();
    }

    private void CheckBackupFrequency() {
        String line = null;
        String fileName = "backupFrequency.txt";
        String frequency = "";
        LocalDate date = LocalDate.now();
        try {
            // FileReader reads text files in the default encoding.
            File backupFrequency = new File(fileName);
            backupFrequency.createNewFile(); // if file already exists will do nothing 

            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(", ");
                frequency = parts[0]; //daily, weekly or monthly
                String sDate = parts[1]; //2019-03-21
                String[] dateParts = sDate.split("-");
                int year = Integer.parseInt(dateParts[0]);
                int day = Integer.parseInt(dateParts[2]);
                int month = Integer.parseInt(dateParts[1]);
                Month thisMonth = Month.JANUARY;
                if (month == 1) {
                    thisMonth = Month.JANUARY;
                } else if (month == 2) {
                    thisMonth = Month.FEBRUARY;
                } else if (month == 3) {
                    thisMonth = Month.MARCH;
                } else if (month == 4) {
                    thisMonth = Month.APRIL;
                } else if (month == 5) {
                    thisMonth = Month.MAY;
                } else if (month == 6) {
                    thisMonth = Month.JUNE;
                } else if (month == 7) {
                    thisMonth = Month.JULY;
                } else if (month == 8) {
                    thisMonth = Month.AUGUST;
                } else if (month == 9) {
                    thisMonth = Month.SEPTEMBER;
                } else if (month == 10) {
                    thisMonth = Month.OCTOBER;
                } else if (month == 11) {
                    thisMonth = Month.NOVEMBER;
                } else if (month == 12) {
                    thisMonth = Month.DECEMBER;
                }

                try {
                    date = LocalDate.of(year, thisMonth, day); // the date that was inputted
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }

        if (frequency.equals("daily")) {
            DailyBackups();
        } else if (frequency.equals("weekly")) {
            WeeklyBackups(date);
        } else if (frequency.equals("monthly")) {
            MonthlyBackups(date);
        }
    }

    private void DailyBackups() {
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
        scheduler.scheduleAtFixedRate(new AutomaticBackups(), DelayInMinutes, OneDayMinutes, TimeUnit.MINUTES);
    }

    private void WeeklyBackups(LocalDate date) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        LocalDate aDate = LocalDate.now(); // Current date or parsed date;

        long daysBetween = DAYS.between(date, aDate);
        if (daysBetween <= 7) {
            daysBetween = 7 - daysBetween;
        } else {
            daysBetween = daysBetween % 7; //get remainder
            daysBetween = 7 - daysBetween;
        }

        scheduler.scheduleAtFixedRate(new AutomaticBackups(), daysBetween, 7, TimeUnit.DAYS); //every 7 days

    }

    private void MonthlyBackups(LocalDate date) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        //schedule a backup to happen at the end of this month, and every 30 days thereafter.
        scheduler.scheduleAtFixedRate(new AutomaticBackups(), 31 - date.getDayOfMonth(), 30, TimeUnit.DAYS); //every 30 days (1 month)

    }

    private void MonthlyReports() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        //Every day call the automatic report method
        scheduler.scheduleAtFixedRate(new AutomaticReport(), 0, 1, TimeUnit.DAYS); //every 30 days (1 month)
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
        scheduler.scheduleAtFixedRate(new AutomaticReminders(), DelayInMinutes, OneDayMinutes, TimeUnit.MINUTES);
    }

    private void CalculateFlexibleDiscounts() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        //if it's the 1st, calculate the flexible discounts for the last month
        scheduler.scheduleAtFixedRate(new CalculateFlexibleDiscount(), 0, 1, TimeUnit.DAYS);
    }

}
