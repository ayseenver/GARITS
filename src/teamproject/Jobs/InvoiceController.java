package teamproject.Jobs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import teamproject.Customer_Account.Customer;
import teamproject.Customer_Account.Vehicle;
import teamproject.Databases.DB_ImplClass;

public class InvoiceController {

    private double VAT;
    private String invoiceNumber;
    private Date dateCreated;
    private int jobID;
    ResultSet rs;
    DB_ImplClass db = new DB_ImplClass();
    Connection connection;
    Statement statement;
    Vehicle v = new Vehicle();
    Customer c = new Customer();

    public InvoiceController(int jobID) {
        this.jobID = jobID;
        connection = db.connect();
        statement = db.getStatement();
    }

    private void GetJobDetails() {
        try {
            String sql = ("select * from job where jobID = " + jobID);
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String vehicleReg = "";
        try {
            while (rs.next()) {
                vehicleReg = rs.getString("Vehicleregistrationnumber");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //get the vehicle for this job
        try {
            String sql = ("select * from vehicle where registrationnumber = '" + vehicleReg + "'");
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String customerID = "";
        try {
            while (rs.next()) {
                v.setRegistrationNumber(rs.getString("registrationNumber"));
                v.setModel(rs.getString("model"));
                v.setMake(rs.getString("make"));
                customerID = rs.getString("customerID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //get the customer for this job
        try {
            String sql = ("select * from customer where id = " + customerID);
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (rs.next()) {
                c.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //get the invoice number for this job
        try {
            String sql = ("select * from invoice where jobjobid = " + jobID);
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (rs.next()) {
                invoiceNumber = rs.getString("invoiceNumber");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String GetInvoiceDetails() {
        String result = "";
        result += ("Dear " + c.getName() + "\n\n");
        result += ("Invoice number : " + invoiceNumber + "\n\n");
        result += ("Vehicle Registration No.: " + v.getRegistrationNumber() + "\n");
        result += ("Make: " + v.getMake() + "\n" + "Model: " + v.getModel() + "\n\n");
        result += ("Job number : " + jobID + "\n\n");
        result += ("Description of work: \n");

        //get all task descriptions for the actual tasks for this job
        try {
            String sql = ("select * from task where taskID in (select TasktaskID from actual_task where JobjobID = " + jobID + ")");
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int i = 1;
        try {
            while (rs.next()) {
                // read the result set. Get task description.
                String task = i + ") " + rs.getString("description");
                result += (task + "\n");
                ++i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //get all actual task hours for this job
        try {
            String sql = ("select totalHours from job where jobID = " + jobID);
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        double totalHours = 0;
        try {
            while (rs.next()) {
                // read the result set. Get task hours
                totalHours += Double.parseDouble(rs.getString("totalHours"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        result += ("\nParts used: \n");

        //get all parts used for this job
        try {
            String sql = ("select * from job_part_record inner join sparepart on sparepart.partID = job_part_record.PartpartID "
                    + "where jobjobid = " + jobID);
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        double totalPartsCost = 0;
        try {
            while (rs.next()) {
                int quantity = Integer.parseInt(rs.getString("quantity"));
                double sellingPrice = Double.parseDouble(rs.getString("sellingPrice"));
                // read the result set. Get part name description.
                String part = rs.getString("partName")
                        + ", £" + sellingPrice + ", quantity: " + quantity;
                totalPartsCost += (sellingPrice * quantity);
                result += (part + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        result += ("Total parts cost: £" + String.format("%.2f", totalPartsCost) + "\n");

        //get hourly rate for this mechanic
        try {
            String sql = ("select hourlyRate from mechanic where ID = (select MechanicID from job where jobID = " + jobID + ")");
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        double hourlyRate = 0;
        try {
            while (rs.next()) {
                hourlyRate = Double.parseDouble(rs.getString("hourlyRate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        double totalCost = ((hourlyRate * totalHours) + totalPartsCost); //excluding VAT
        result += ("\nTotal labour cost: £" + String.format("%.2f", (hourlyRate * totalHours)) + "\n");
        result += ("\nVAT: £" + String.format("%.2f", totalCost * 0.2));
        result += ("\nGrand total: £" + String.format("%.2f", totalCost * 1.2));
        return result;
    }

    public void printJobInvoice() {
        GetJobDetails();

        String fileName = "Invoice-for-job" + jobID + ".txt";
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(GetInvoiceDetails());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String mess = "Printed successfully";
        JOptionPane.showMessageDialog(new JFrame(), mess);
    }

}
