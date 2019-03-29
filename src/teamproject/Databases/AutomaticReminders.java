/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.Databases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ayse
 */
public class AutomaticReminders implements Runnable {

    Statement statement;
    ResultSet rs;
    DB_ImplClass db = new DB_ImplClass();
    Connection connection;

    @Override
    public void run() {
        connect();
        checkDueMoT();
        checkDueService();
        checkOverduePayments();
        closeConnection();
    }

    private void connect() {
        connection = db.connect();
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

    private void checkOverduePayments() {
        //get all invoices overdue payment by 1 month
        try {
            this.rs = connection.createStatement().executeQuery("select * from invoice where JobjobID in "
                    + "(select jobID from job where dateCompleted > date('now', '-1 month') "
                    + "and jobID not in (select jobjobID from payment))");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            while (rs.next()) {
                //insert this job into the reminder table with reminder number 1
                try {
                    String sql = ("INSERT INTO paymentReminder(Invoiceinvoicenumber, reminderNumber, deleted) "
                            + "VALUES (" + rs.getString("invoiceNumber") + ", 1, 0)");
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

        //get all invoices overdue payment by 2 months
        try {
            this.rs = connection.createStatement().executeQuery("select * from invoice where JobjobID in "
                    + "(select jobID from job where dateCompleted > date('now', '-2 month') "
                    + "and jobID not in (select jobjobID from payment))");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            while (rs.next()) {
                //insert this job into the reminder table with reminder number 2
                try {
                    String sql = ("INSERT INTO paymentReminder(Invoiceinvoicenumber, reminderNumber, deleted) "
                            + "VALUES (" + rs.getString("invoiceNumber") + ", 2, 0)");
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

        //get all invoices overdue payment by 3 months
        try {
            this.rs = connection.createStatement().executeQuery("select * from invoice where JobjobID in "
                    + "(select jobID from job where dateCompleted > date('now', '-3 month') "
                    + "and jobID not in (select jobjobID from payment))");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            while (rs.next()) {
                //insert this job into the reminder table with reminder number 3
                try {
                    String sql = ("INSERT INTO paymentReminder(Invoiceinvoicenumber, reminderNumber, deleted) "
                            + "VALUES (" + rs.getString("invoiceNumber") + ", 3, 0) ");
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
        System.out.println("done");
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
