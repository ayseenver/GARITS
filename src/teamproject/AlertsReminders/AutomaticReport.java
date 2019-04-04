/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.AlertsReminders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import teamproject.Databases.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Ayse
 */
public class AutomaticReport implements Runnable {

    Connection connection;
    Statement statement;
    ResultSet rs;
    LocalDate date;
    HashMap<String, Integer> soldMap = new HashMap<>();
    HashMap<String, Integer> usedMap = new HashMap<>();
    HashMap<String, Integer> orderMap = new HashMap<>();

    @Override
    public void run() {
        connect();
        CheckReportFrequency();
        closeConnection();
    }

    private void connect() {
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:GARITSDB.db");
            this.statement = connection.createStatement();
            this.statement.setQueryTimeout(30);  // set timeout to 30 sec.
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
    }

    private void CheckReportFrequency() {
        String line = null;
        String fileName = "reportFrequency.txt";
        String frequency = "";
        LocalDate date = LocalDate.now();
        try {
            // FileReader reads text files in the default encoding.
            File reportFrequency = new File(fileName);
            reportFrequency.createNewFile(); // if file already exists will do nothing 

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
            DailyReport();
        } else if (frequency.equals("weekly")) {
            WeeklyReport(date);
        } else if (frequency.equals("monthly")) {
            MonthlyReport(date);
        }
    }

    private void DailyReport() {
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
        generateDailyVehicleReport();
        generateDailyAverageReport();
        generateDailyStockReport();
        scheduler.scheduleAtFixedRate(new AutomaticBackups(), DelayInMinutes, OneDayMinutes, TimeUnit.MINUTES);
    }

    private void WeeklyReport(LocalDate date) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        if (date.getDayOfWeek().toString().equals("MONDAY")) {
            generateWeeklyVehicleReport();
            generateWeeklyAverageReport();
            generateWeeklyStockReport();
            scheduler.scheduleAtFixedRate(new AutomaticBackups(), 0, 7, TimeUnit.DAYS); //every 7 days
        }

    }

    private void MonthlyReport(LocalDate date) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        if (date.getDayOfMonth() == 1) {
            //do this on the 1st of every month
            generateMonthlyVehicleReport();
            generateMonthlyAverageReport();
            generateMonthlyStockReport();
            scheduler.scheduleAtFixedRate(new AutomaticBackups(), 0, 1, TimeUnit.DAYS); //check every day
        }
    }

    private void generateDailyVehicleReport() {
        //vehicles booked in report, overall
        //all the jobs between today and one month ago
        try {
            String sql = ("select * from job where dateBookedIn BETWEEN date('now') AND date('now', '-1 day')");

            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        ArrayList<String> jobs = new ArrayList<>();

        //create a txt file for this month's report
        LocalDate today = LocalDate.now();
        String fileName = "dailyVehicleReport" + today + ".txt";
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            String text = "";
            try {
                while (rs.next()) {
                    String job = "JobID: " + rs.getString("jobID") + ", Vehicle: " + rs.getString("VehicleregistrationNumber");
                    writer.println(job + "\n");
                    text += job + "\n";
                    jobs.add(job);
                }
                writer.println("Booked in " + jobs.size() + " vehicles. \nDetails: \n\n" + text);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateDailyAverageReport() {
        //Average time and price for last month's completed jobs
        try {
            String sql = ("select avg(totalCost), avg(totalHours) from job "
                    + "where dateBookedIn BETWEEN date('now') AND date('now', '-1 day') "
                    + "and dateCompleted is not null");

            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //create a txt file for this month's report
        LocalDate today = LocalDate.now();
        String fileName = "dailyAverageReport" + today + ".txt";
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            try {
                while (rs.next()) {
                    String result = "For jobs completed in this date range\n"
                            + "\nAverage hours: " + rs.getString("avg(totalHours)")
                            + "\nAverage cost (not including VAT): " + rs.getString("avg(totalCost)");
                    writer.println(result);
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateDailyStockReport() {
        try {
            String sql = "SELECT sparepartpartID, sum(quantity) FROM invoice_sparePart "
                    + "INNER JOIN invoice ON "
                    + "invoice.invoiceNumber = invoice_sparePart.invoiceinvoicenumber "
                    + "where dateProduced BETWEEN date('now') AND date('now', '-1 day') "
                    + "group by sparepartpartID";

            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        soldMap.clear();

        //add all sold parts and their quantities to a map
        try {
            while (rs.next()) {
                int quantity;
                if (rs.getString("sum(quantity)") == null) {
                    quantity = 0;
                } else {
                    quantity = Integer.parseInt(rs.getString("sum(quantity)"));
                }
                soldMap.put(rs.getString("sparepartpartID"), quantity);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //get all of the parts used on jobs completed during this time frame.
        try {
            String sql = "SELECT PartpartID, sum(quantity) FROM job_part_record "
                    + "INNER JOIN job ON job.jobID = job_part_record.jobJobID "
                    + "where dateCompleted BETWEEN date('now') AND date('now', '-1 day') "
                    + "group by PartpartID";
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        usedMap.clear();

        //add all used parts and their quantities to a map
        try {
            while (rs.next()) {
                int quantity;
                if (rs.getString("sum(quantity)") == null) {
                    quantity = 0;
                } else {
                    quantity = Integer.parseInt(rs.getString("sum(quantity)"));
                }
                usedMap.put(rs.getString("PartpartID"), quantity);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //get all of the parts ordered during this time frame.
        try {
            String sql = "SELECT SparePartpartID, sum(quantity), date "
                    + "FROM sparepart_partorder "
                    + "inner join partOrder on sparePart_partOrder.PartOrderorderNumber = partOrder.orderNumber "
                    + "where date BETWEEN date('now') AND date('now', '-1 day') "
                    + "group by sparePartpartID";
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        orderMap.clear();

        //add all ordered parts and their quantities to a map
        try {
            while (rs.next()) {
                int quantity;
                if (rs.getString("sum(quantity)") == null) {
                    quantity = 0;
                } else {
                    quantity = Integer.parseInt(rs.getString("sum(quantity)"));
                }
                usedMap.put(rs.getString("SparePartpartID"), quantity);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //get all of the parts
        try {
            String sql = "select * from sparePart";

            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        LocalDate today = LocalDate.now();
        String fileName = "dailyStockReport" + today + ".txt";
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            try {
                while (rs.next()) {
                    String partID = rs.getString("partID");
                    int newQuantity = Integer.parseInt(rs.getString("quantity"));

                    int usedQuantity;
                    String used = usedMap.get(partID) + "";
                    if (used.equals("null")) {
                        usedQuantity = 0;
                    } else {
                        usedQuantity = usedMap.get(partID);
                    }

                    int soldQuantity;
                    String sold = usedMap.get(partID) + "";
                    if (sold.equals("null")) {
                        soldQuantity = 0;
                    } else {
                        soldQuantity = usedMap.get(partID);
                    }

                    int orderQuantity;
                    String order = usedMap.get(partID) + "";
                    if (order.equals("null")) {
                        orderQuantity = 0;
                    } else {
                        orderQuantity = usedMap.get(partID);
                    }

                    int initialQuantity = (newQuantity + usedQuantity + soldQuantity) - orderQuantity;

                    String result = "Part name: " + rs.getString("partName") + "\n"
                            + "Part ID: " + rs.getString("partID") + "\n"
                            + "Manufacturer: " + rs.getString("Manufacturername") + "\n"
                            + "Vehicle type: " + rs.getString("vehicleType") + "\n"
                            + "Price: £" + rs.getString("costPrice") + "\n"
                            + "Initial stock level: " + initialQuantity + "\n"
                            + "Initial cost: " + (initialQuantity * (Double.parseDouble(rs.getString("costPrice")))) + "\n"
                            + "Used: " + usedQuantity + "\n"
                            + "Sold: " + soldQuantity + "\n"
                            + "Delivery: " + orderQuantity + "\n"
                            + "New stock level: " + newQuantity + "\n"
                            + "Stock cost: £" + (newQuantity * (Double.parseDouble(rs.getString("costPrice")))) + "\n"
                            + "Low stock threshold: " + rs.getString("threshold") + "\n";
                    writer.println(result);
                }
                writer.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateWeeklyVehicleReport() {
        //vehicles booked in report, overall
        //all the jobs between today and one month ago
        try {
            String sql = ("select * from job where dateBookedIn BETWEEN date('now') AND date('now', '-1 week')");

            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        ArrayList<String> jobs = new ArrayList<>();

        //create a txt file for this month's report
        LocalDate today = LocalDate.now();
        String fileName = "weeklyVehicleReport" + today + ".txt";
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            String text = "";
            try {
                while (rs.next()) {
                    String job = "JobID: " + rs.getString("jobID") + ", Vehicle: " + rs.getString("VehicleregistrationNumber");
                    writer.println(job + "\n");
                    text += job + "\n";
                    jobs.add(job);
                }
                writer.println("Booked in " + jobs.size() + " vehicles. \nDetails: \n\n" + text);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateWeeklyAverageReport() {
        //Average time and price for last month's completed jobs
        try {
            String sql = ("select avg(totalCost), avg(totalHours) from job "
                    + "where dateBookedIn BETWEEN date('now') AND date('now', '-1 week') "
                    + "and dateCompleted is not null");

            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //create a txt file for this month's report
        LocalDate today = LocalDate.now();
        String fileName = "weeklyAverageReport" + today + ".txt";
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            try {
                while (rs.next()) {
                    String result = "For jobs completed in this date range\n"
                            + "\nAverage hours: " + rs.getString("avg(totalHours)")
                            + "\nAverage cost (not including VAT): " + rs.getString("avg(totalCost)");
                    writer.println(result);
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateWeeklyStockReport() {
        try {
            String sql = "SELECT sparepartpartID, sum(quantity) FROM invoice_sparePart "
                    + "INNER JOIN invoice ON "
                    + "invoice.invoiceNumber = invoice_sparePart.invoiceinvoicenumber "
                    + "where dateProduced BETWEEN date('now') AND date('now', '-1 week') "
                    + "group by sparepartpartID";

            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        soldMap.clear();

        //add all sold parts and their quantities to a map
        try {
            while (rs.next()) {
                int quantity;
                if (rs.getString("sum(quantity)") == null) {
                    quantity = 0;
                } else {
                    quantity = Integer.parseInt(rs.getString("sum(quantity)"));
                }
                soldMap.put(rs.getString("sparepartpartID"), quantity);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //get all of the parts used on jobs completed during this time frame.
        try {
            String sql = "SELECT PartpartID, sum(quantity) FROM job_part_record "
                    + "INNER JOIN job ON job.jobID = job_part_record.jobJobID "
                    + "where dateCompleted BETWEEN date('now') AND date('now', '-1 week') "
                    + "group by PartpartID";
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        usedMap.clear();

        //add all used parts and their quantities to a map
        try {
            while (rs.next()) {
                int quantity;
                if (rs.getString("sum(quantity)") == null) {
                    quantity = 0;
                } else {
                    quantity = Integer.parseInt(rs.getString("sum(quantity)"));
                }
                usedMap.put(rs.getString("PartpartID"), quantity);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //get all of the parts ordered during this time frame.
        try {
            String sql = "SELECT SparePartpartID, sum(quantity), date "
                    + "FROM sparepart_partorder "
                    + "inner join partOrder on sparePart_partOrder.PartOrderorderNumber = partOrder.orderNumber "
                    + "where date BETWEEN date('now') AND date('now', '-1 week') "
                    + "group by sparePartpartID";
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        orderMap.clear();

        //add all ordered parts and their quantities to a map
        try {
            while (rs.next()) {
                int quantity;
                if (rs.getString("sum(quantity)") == null) {
                    quantity = 0;
                } else {
                    quantity = Integer.parseInt(rs.getString("sum(quantity)"));
                }
                usedMap.put(rs.getString("SparePartpartID"), quantity);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //get all of the parts
        try {
            String sql = "select * from sparePart";

            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        LocalDate today = LocalDate.now();
        String fileName = "weeklyStockReport" + today + ".txt";
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            try {
                while (rs.next()) {
                    String partID = rs.getString("partID");
                    int newQuantity = Integer.parseInt(rs.getString("quantity"));

                    int usedQuantity;
                    String used = usedMap.get(partID) + "";
                    if (used.equals("null")) {
                        usedQuantity = 0;
                    } else {
                        usedQuantity = usedMap.get(partID);
                    }

                    int soldQuantity;
                    String sold = usedMap.get(partID) + "";
                    if (sold.equals("null")) {
                        soldQuantity = 0;
                    } else {
                        soldQuantity = usedMap.get(partID);
                    }

                    int orderQuantity;
                    String order = usedMap.get(partID) + "";
                    if (order.equals("null")) {
                        orderQuantity = 0;
                    } else {
                        orderQuantity = usedMap.get(partID);
                    }

                    int initialQuantity = (newQuantity + usedQuantity + soldQuantity) - orderQuantity;

                    String result = "Part name: " + rs.getString("partName") + "\n"
                            + "Part ID: " + rs.getString("partID") + "\n"
                            + "Manufacturer: " + rs.getString("Manufacturername") + "\n"
                            + "Vehicle type: " + rs.getString("vehicleType") + "\n"
                            + "Price: £" + rs.getString("costPrice") + "\n"
                            + "Initial stock level: " + initialQuantity + "\n"
                            + "Initial cost: " + (initialQuantity * (Double.parseDouble(rs.getString("costPrice")))) + "\n"
                            + "Used: " + usedQuantity + "\n"
                            + "Sold: " + soldQuantity + "\n"
                            + "Delivery: " + orderQuantity + "\n"
                            + "New stock level: " + newQuantity + "\n"
                            + "Stock cost: £" + (newQuantity * (Double.parseDouble(rs.getString("costPrice")))) + "\n"
                            + "Low stock threshold: " + rs.getString("threshold") + "\n";
                    writer.println(result);
                }
                writer.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateMonthlyVehicleReport() {
        //vehicles booked in report, overall
        //all the jobs between today and one month ago
        try {
            String sql = ("select * from job where dateBookedIn BETWEEN date('now') AND date('now', '-1 month')");

            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        ArrayList<String> jobs = new ArrayList<>();

        //create a txt file for this month's report
        LocalDate today = LocalDate.now();
        String fileName = "monthlyVehicleReport" + today + ".txt";
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            String text = "";
            try {
                while (rs.next()) {
                    String job = "JobID: " + rs.getString("jobID") + ", Vehicle: " + rs.getString("VehicleregistrationNumber");
                    writer.println(job + "\n");
                    text += job + "\n";
                    jobs.add(job);
                }
                writer.println("Booked in " + jobs.size() + " vehicles. \nDetails: \n\n" + text);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateMonthlyAverageReport() {
        //Average time and price for last month's completed jobs
        try {
            String sql = ("select avg(totalCost), avg(totalHours) from job "
                    + "where dateBookedIn BETWEEN date('now') AND date('now', '-1 month') "
                    + "and dateCompleted is not null");

            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //create a txt file for this month's report
        LocalDate today = LocalDate.now();
        String fileName = "monthlyAverageReport" + today + ".txt";
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            try {
                while (rs.next()) {
                    String result = "For jobs completed in this date range\n"
                            + "\nAverage hours: " + rs.getString("avg(totalHours)")
                            + "\nAverage cost (not including VAT): " + rs.getString("avg(totalCost)");
                    writer.println(result);
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateMonthlyStockReport() {
        try {
            String sql = "SELECT sparepartpartID, sum(quantity) FROM invoice_sparePart "
                    + "INNER JOIN invoice ON "
                    + "invoice.invoiceNumber = invoice_sparePart.invoiceinvoicenumber "
                    + "where dateProduced BETWEEN date('now') AND date('now', '-1 month') "
                    + "group by sparepartpartID";

            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        soldMap.clear();

        //add all sold parts and their quantities to a map
        try {
            while (rs.next()) {
                int quantity;
                if (rs.getString("sum(quantity)") == null) {
                    quantity = 0;
                } else {
                    quantity = Integer.parseInt(rs.getString("sum(quantity)"));
                }
                soldMap.put(rs.getString("sparepartpartID"), quantity);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //get all of the parts used on jobs completed during this time frame.
        try {
            String sql = "SELECT PartpartID, sum(quantity) FROM job_part_record "
                    + "INNER JOIN job ON job.jobID = job_part_record.jobJobID "
                    + "where dateCompleted BETWEEN date('now') AND date('now', '-1 month') "
                    + "group by PartpartID";
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        usedMap.clear();

        //add all used parts and their quantities to a map
        try {
            while (rs.next()) {
                int quantity;
                if (rs.getString("sum(quantity)") == null) {
                    quantity = 0;
                } else {
                    quantity = Integer.parseInt(rs.getString("sum(quantity)"));
                }
                usedMap.put(rs.getString("PartpartID"), quantity);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //get all of the parts ordered during this time frame.
        try {
            String sql = "SELECT SparePartpartID, sum(quantity), date "
                    + "FROM sparepart_partorder "
                    + "inner join partOrder on sparePart_partOrder.PartOrderorderNumber = partOrder.orderNumber "
                    + "where date BETWEEN date('now') AND date('now', '-1 month') "
                    + "group by sparePartpartID";
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        orderMap.clear();

        //add all ordered parts and their quantities to a map
        try {
            while (rs.next()) {
                int quantity;
                if (rs.getString("sum(quantity)") == null) {
                    quantity = 0;
                } else {
                    quantity = Integer.parseInt(rs.getString("sum(quantity)"));
                }
                usedMap.put(rs.getString("SparePartpartID"), quantity);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //get all of the parts
        try {
            String sql = "select * from sparePart";

            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        LocalDate today = LocalDate.now();
        String fileName = "monthlyStockReport" + today + ".txt";
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            try {
                while (rs.next()) {
                    String partID = rs.getString("partID");
                    int newQuantity = Integer.parseInt(rs.getString("quantity"));

                    int usedQuantity;
                    String used = usedMap.get(partID) + "";
                    if (used.equals("null")) {
                        usedQuantity = 0;
                    } else {
                        usedQuantity = usedMap.get(partID);
                    }

                    int soldQuantity;
                    String sold = usedMap.get(partID) + "";
                    if (sold.equals("null")) {
                        soldQuantity = 0;
                    } else {
                        soldQuantity = usedMap.get(partID);
                    }

                    int orderQuantity;
                    String order = usedMap.get(partID) + "";
                    if (order.equals("null")) {
                        orderQuantity = 0;
                    } else {
                        orderQuantity = usedMap.get(partID);
                    }

                    int initialQuantity = (newQuantity + usedQuantity + soldQuantity) - orderQuantity;

                    String result = "Part name: " + rs.getString("partName") + "\n"
                            + "Part ID: " + rs.getString("partID") + "\n"
                            + "Manufacturer: " + rs.getString("Manufacturername") + "\n"
                            + "Vehicle type: " + rs.getString("vehicleType") + "\n"
                            + "Price: £" + rs.getString("costPrice") + "\n"
                            + "Initial stock level: " + initialQuantity + "\n"
                            + "Initial cost: " + (initialQuantity * (Double.parseDouble(rs.getString("costPrice")))) + "\n"
                            + "Used: " + usedQuantity + "\n"
                            + "Sold: " + soldQuantity + "\n"
                            + "Delivery: " + orderQuantity + "\n"
                            + "New stock level: " + newQuantity + "\n"
                            + "Stock cost: £" + (newQuantity * (Double.parseDouble(rs.getString("costPrice")))) + "\n"
                            + "Low stock threshold: " + rs.getString("threshold") + "\n";
                    writer.println(result);
                }
                writer.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void closeConnection() {
        //close connection
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
