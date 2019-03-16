/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ayse
 */
public class Automation implements Runnable {

    Connection connection;
    Statement statement;
    ResultSet rs;

    @Override
    public void run() {
        connect();
        checkDueMoT();
        checkDueService();
        backupDatabase();
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

    private void backupDatabase() {
        //backup
        try {
            connection.createStatement().executeUpdate("backup to database.db");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void checkDueMoT() {
        //get all vehicles with MoTs due in 7 days (5 working days)
        try {
            this.rs = connection.createStatement().executeQuery("select * from vehicle where nextMOTDate = date('now', '+7 day')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            while (rs.next()) {
                String regNo = rs.getString("registrationNumber");

                //insert this vehicle into the reminder table to store the reminder for its MoT
                try {
                    String sql = ("INSERT INTO VehicleReminder (type, VehicleregistrationNumber, dueDate, deleted) "
                            + "VALUES ('MoT', (select registrationNumber from vehicle where "
                            + "registrationNumber = '" + regNo + "'), "
                            + "'" + rs.getString("nextMoTDate") + "', 0)");
                    PreparedStatement ps = null;
                    try {
                        ps = connection.prepareStatement(sql);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void checkDueService() {
        //get all vehicles with services due in 7 days (5 working days)
        try {
            this.rs = connection.createStatement().executeQuery("select * from vehicle where nextServiceDate = date('now', '+7 day')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            while (rs.next()) {
                String regNo = rs.getString("registrationNumber");

                //insert this vehicle into the reminder table to store the reminder for its service
                try {
                    String sql = ("INSERT INTO VehicleReminder (type, VehicleregistrationNumber, dueDate, deleted) "
                            + "VALUES ('Service', (select registrationNumber from vehicle where "
                            + "registrationNumber = '" + regNo + "'), "
                            + "'" + rs.getString("nextServiceDate") + "', 0)");
                    PreparedStatement ps = null;
                    try {
                        ps = connection.prepareStatement(sql);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    private void checkOneMonthOverdue(){
        
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
