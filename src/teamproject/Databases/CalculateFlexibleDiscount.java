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
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Ayse
 */
public class CalculateFlexibleDiscount implements Runnable {

    Statement statement;
    ResultSet rs;
    DB_ImplClass db = new DB_ImplClass();
    Connection connection;

    @Override
    public void run() {
        connect();
        CalculateFlexibleDiscount();
        closeConnection();
    }

    private void connect() {
        connection = db.connect();
    }

    private void CalculateFlexibleDiscount() {
        double totalCost = 0.0;
        double credit = 0.0;
        try {
            //get the account IDs of flexible discount holders who have invoices in the last month
            String sql = ("select accountID from customeraccount inner join customer on "
                    + "customer.ID = customeraccount.CustomerID where customerID in "
                    + "(select ID from customer where id in "
                    + "(select CustomerID from vehicle where registrationnumber in "
                    + "(select Vehicleregistrationnumber from job where jobID in "
                    + "(select JobjobID from "
                    + "(select invoice.*, job.totalCost from invoice "
                    + "inner join job on job.jobID = invoice.JobjobID where dateProduced <= date('now') "
                    + "and dateProduced >= date('now', '-1 month')))))) and accountID in "
                    + "(select customeraccountaccountid from discountplan where flexibleDiscountdiscountID is not null)");
            PreparedStatement ps = null;

            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        ArrayList<String> accountIDs = new ArrayList<String>();
        try {
            while (rs.next()) {
                String accountID = rs.getString("accountID");
                accountIDs.add(accountID);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        for (String s : accountIDs) {
            try {
                //get the all jobs in the last month for this accountID
                String sql = ("select * from job where jobid in "
                        + "(select jobID from job where vehicleregistrationnumber in "
                        + "(select registrationnumber from vehicle where customerID in "
                        + "(select ID from customer where ID in "
                        + "(select customerID from customeraccount where accountID = " + s + ")))) "
                        + "and (datecompleted <= date('now') and datecompleted >= date('now', '-1 month'))");
                PreparedStatement ps = null;

                try {
                    ps = connection.prepareStatement(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.rs = ps.executeQuery();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            //get the total value of all jobs for this account holder in the last month
            try {
                while (rs.next()) {
                    double price = Double.parseDouble(rs.getString("totalCost"));
                    totalCost += price;
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                //update "order value this month" to the total cost
                //get the flexible discount ID
                String sql = ("select * from customeraccount inner join discountplan on discountplan.CustomerAccountaccountID = customeraccount.accountID "
                        + "inner join flexiblediscount on flexiblediscount.discountID = discountplan.FlexibleDiscountdiscountID "
                        + "where accountID = " + s);
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

            String discountID = "";
            try {
                while (rs.next()) {
                    discountID = rs.getString("flexiblediscountdiscountID");
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            //get the discount plan for this customer
            try {
                String sql = ("select flexibands_flexiblediscount.*, flexiblediscount.orderValueThisMonth "
                        + "from flexibands_flexiblediscount inner join flexiblediscount on "
                        + "flexiblediscount.discountID = flexibands_flexiblediscount.FlexibleDiscountdiscountID "
                        + "where flexiblediscountdiscountID = " + discountID);
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

            //store each flexi band ID and it's percentage
            HashMap<String, Double> bandIDPercentage = new HashMap<>();
            try {
                while (rs.next()) {
                    String bandID = rs.getString("FlexiBandsbandID");
                    double percentage = Double.parseDouble(rs.getString("percentage"));
                    bandIDPercentage.put(bandID, percentage);
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            double percentage = 0.0;
            if (totalCost >= 0 && totalCost <= 1000) {
                percentage = bandIDPercentage.get("1");
            } else if (totalCost >= 1001 && totalCost <= 5000) {
                percentage = bandIDPercentage.get("2");
            } else if (totalCost >= 5001 && totalCost <= 10000) {
                percentage = bandIDPercentage.get("3");
            }

            credit = totalCost * (percentage / 100);

            try {
                //update "order value this month" to the total cost, and the credit
                String sql = ("update flexiblediscount set ordervaluethismonth = " + totalCost 
                        + ", credit = " + credit + " where discountID = " + discountID);
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
