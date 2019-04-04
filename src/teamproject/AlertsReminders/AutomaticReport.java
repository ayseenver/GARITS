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
import java.util.HashMap;

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
        GetTodaysDate();
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

    private void GetTodaysDate() {
        LocalDate date = LocalDate.now();
        
        if (date.getDayOfMonth() == 1) {
            //do this on the 1st of every month
            generateMonthlyVehicleReport();
            generateAverageReport();
            generateStockReport();
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

    private void generateAverageReport() {
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

    private void generateStockReport() {
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
