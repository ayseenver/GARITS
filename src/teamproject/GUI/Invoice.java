/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.GUI;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import teamproject.Customer_Account.Customer;
import teamproject.Customer_Account.Vehicle;
import teamproject.Databases.DB_ImplClass;
import teamproject.Jobs.InvoiceController;

/**
 *
 * @author ahmetsesli
 */
public class Invoice extends javax.swing.JPanel {

    private String username;
    private String previousPage;
    Statement statement;
    Connection connection = null;
    DB_ImplClass db = new DB_ImplClass();
    ResultSet rs;
    ResultSet rsC;
    ResultSet rsV;
    Customer c = new Customer();
    Vehicle v = new Vehicle();
    private ResultSet rsP;
    String[] invoiceArray;
    String jobNumber;
    String invoiceNumber;

    /**
     * Creates new form NewJPanel
     */
    public Invoice(String username, String previousPage) {
        this.username = username;
        this.previousPage = previousPage;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        this.textFieldUserDetails.setText(username);
        connection = db.connect();
        statement = db.getStatement();

        ShowAllInvoices();
        buttonPayLater.setVisible(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void GetJobAndInvoiceNumber() {
        String selected = listInvoices.getSelectedValue();
        if (selected != null) {
            String[] parts = selected.split(", ");
            String[] idParts = parts[0].split(": ");
            invoiceNumber = idParts[1];
            try {
                String[] jobParts = parts[1].split(": ");
                jobNumber = jobParts[1];
            } catch (Exception e) {
                jobNumber = "";
            }
        } else {
            String mess = "Select an invoice";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }

    private void getJobVehicle() {

        try {
            String sql = ("select * from Vehicle where registrationNumber = (select vehicleregistrationNumber from job where jobID = " + 50 + ")");
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rsV = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            while (rsV.next()) {
                try {
                    v.setMake(rsV.getString("make"));
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }

                try {
                    v.setModel(rsV.getString("model"));
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }

                try {
                    v.setRegistrationNumber(rsV.getString("registrationNumber"));
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void GetCustomerDetails() {

        try {
            String sql = ("select * from Customer where id = (select customerID from vehicle where registrationNumber= '" + v.getRegistrationNumber() + "') ");
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.rsC = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            while (rsC.next()) {
                try {
                    c.setName(rsC.getString("name"));
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String GetPartInvoiceDetails() {
        GetJobAndInvoiceNumber();
        String result = "";
        result += ("Invoice number: " + invoiceNumber + "\n\n");
        result += ("Parts sold: \n");

        //get all part IDs on this invoice
        try {
            String sql = ("SELECT Invoice_SparePart.InvoiceinvoiceNumber, Invoice_SparePart.SparePartpartID, Invoice_SparePart.quantity, SparePart.* "
                    + "FROM Invoice_SparePart "
                    + "INNER JOIN SparePart ON Invoice_SparePart.SparePartpartID=SparePart.partID "
                    + "where Invoice_SparePart.InvoiceinvoiceNumber = " + invoiceNumber);
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
                // read the result set. Get part name and quantity
                result += rs.getString("partName") + ", Quantity: " + rs.getString("quantity") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        result += "\n";

        //get total selling price for all of these parts
        try {
            String sql = ("select sum(sellingPrice * quantity) from "
                    + "(SELECT Invoice_SparePart.InvoiceinvoiceNumber, Invoice_SparePart.SparePartpartID, Invoice_SparePart.quantity, SparePart.* "
                    + "FROM Invoice_SparePart "
                    + "INNER JOIN SparePart ON Invoice_SparePart.SparePartpartID=SparePart.partID "
                    + "where Invoice_SparePart.InvoiceinvoiceNumber = " + invoiceNumber + ")");
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

        Double sellingPrice = 0.00;
        try {
            while (rs.next()) {
                // read the result set. Get part name description.
                sellingPrice = Double.parseDouble(rs.getString("sum(sellingPrice * quantity)"));
                result += ("Total cost: £" + String.format("%.2f", sellingPrice) + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        double totalCost = sellingPrice;
        result += ("\nGrand total: £" + String.format("%.2f", totalCost * 1.2) + "\n");
        return result;
    }

    private void ShowAllInvoices() {
        //get all unpaid invoices for jobs
        try {
            this.rs = statement.executeQuery("select * from Invoice where invoiceNumber not in (select invoiceNumber from payment) "
                    + "and JobjobID is not null and payLater = 0");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace();
        }

        ArrayList<String> invoices = new ArrayList<>();

        try {
            while (rs.next()) {
                // read the result set
                String invoice = "Invoice Number: " + rs.getString("invoiceNumber") + ", Job ID: " + rs.getString("JobjobID");
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //get all invoices for part sales
        try {
            this.rs = statement.executeQuery("select * from Invoice where invoicenumber in (select invoiceinvoicenumber from "
                    + "invoice_sparepart) and invoiceNumber not in (select invoiceNumber from payment)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (rs.next()) {
                // read the result set
                String invoice = "Invoice Number: " + rs.getString("invoiceNumber") + ", Part sale";
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        invoiceArray = CreateArray(invoices);

        listInvoices.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return invoiceArray.length;
            }

            public String getElementAt(int i) {
                return invoiceArray[i];
            }
        });
    }

    private String[] CreateArray(ArrayList<String> tasks) {
        String[] newArray = new String[tasks.size()];
        newArray = tasks.toArray(newArray);
        return newArray;
    }

    private void StandardPayment() {
        //create a payment record in the database.
        try {
            String sql = ("insert into Payment(paymentType, invoiceNumber)"
                    + " values ( '" + comboxBoxPaymentType.getSelectedItem().toString() + "', "
                    + invoiceNumber + ")");
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ShowAllInvoices();
    }

    private void FixedDiscount(String fixedID) {
        try {
            String sql = ("select * from fixeddiscount where discountID = " + fixedID);
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        double percentage = 0.0;
        try {
            while (rs.next()) {
                percentage = Double.parseDouble(rs.getString("percentage"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //reduce the total cost of the job by this amount
        //get the total cost first
        try {
            String sql = ("select totalCost from job where jobID = " + jobNumber);
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        double totalCost = 0.0;
        try {
            while (rs.next()) {
                totalCost = Double.parseDouble(rs.getString("totalCost"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //calculate the new cost
        double newCost = totalCost * (1 - (percentage * 0.01));

        System.out.println("Total cost: " + totalCost);
        System.out.println("Discount: " + (1 - (percentage * 0.01)) + "%");
        System.out.println("Cost after discount: " + newCost);
        //update the cost in the job table
        try {
            String sql = ("update job set totalCost = " + newCost + " where jobID = " + jobNumber);
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //make a standard payment
        StandardPayment();

    }

    private void VariableDiscount(String variableID) {
        try {
            String sql = ("select * from variableDiscount where discountID = " + variableID);
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //get the percentage discount for parts
        double partPercentage = 0.0;
        try {
            while (rs.next()) {
                partPercentage = Double.parseDouble(rs.getString("SparePartpercentage"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //a map storing the taskID and discount percentage
        HashMap<String, String> taskDiscount = new HashMap<>();

        //get all task IDs for this job
        try {
            String sql = ("select TasktaskID from actual_task where jobjobID = " + jobNumber);
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //put all IDs in an array list
        ArrayList<String> taskIDs = new ArrayList<>();
        try {
            while (rs.next()) {
                String ID = rs.getString("TasktaskID");
                taskIDs.add(ID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //get the price for each task. Add it to total
        double totalTaskPrice = 0.0;
        for (String s : taskIDs) {
            try {
                String sql = ("select * from actual_task where tasktaskID = " + s + " and jobjobID = " + jobNumber);
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                rs = ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                while (rs.next()) {
                    double price = Double.parseDouble(rs.getString("actualCost"));
                    totalTaskPrice += price;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //loop through the ID list and get the corresponding discount %
        for (String s : taskIDs) {
            try {
                String sql = ("select * from task_variablediscount where tasktaskID = " + s
                        + " and variableDiscountdiscountID = " + variableID);
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                rs = ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                while (rs.next()) {
                    String percentage = rs.getString("percentage");
                    taskDiscount.put(s, percentage); //taskID, percentage
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //now we have all of the tasks and their percentages
        //get all of the parts associated with this job
        try {
            String sql = ("select * from job_part_record where jobjobID = " + jobNumber);
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //put all part IDs in an array lit
        ArrayList<String> partIDs = new ArrayList<>();
        try {
            while (rs.next()) {
                String ID = rs.getString("PartpartID");
                partIDs.add(ID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //loop through the part IDs, get the price for each one and add to total
        double totalPartCost = 0.0;
        for (String s : partIDs) {
            try {
                String sql = ("select * from sparepart where partID = " + s);
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                rs = ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                while (rs.next()) {
                    double sellingPrice = Double.parseDouble(rs.getString("sellingPrice"));
                    totalPartCost += sellingPrice;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Total Part Cost: " + totalPartCost);

        //now has: total part cost, tasks and their percentages
        //get the total cost for this job
        try {
            String sql = ("select * from job where jobID = " + jobNumber);
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        double totalCost = 0.0;
        try {
            while (rs.next()) {
                totalCost = Double.parseDouble(rs.getString("totalCost"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //take away the cost of parts
        totalCost -= totalPartCost;
        totalPartCost = totalPartCost * (1 - (partPercentage / 100));

        //System.out.println("Total Part cost after discount: " + totalPartCost);

        //this is the total cost after discounting parts
        totalCost += totalPartCost;
        //System.out.println("Total cost after part discount: " + totalCost);
        //now discount the tasks
        //first take away the price of all the tasks
        totalCost -= totalTaskPrice;

        //then get each task and its discount percentage
        //HashMap<String, String> taskDiscount = new HashMap<>();
        double newTaskPrice = 0.0; // the total price of tasks with discount applied

        Iterator it = taskDiscount.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            try {
                String sql = ("select * from actual_task where jobjobID = " + jobNumber
                        + " and tasktaskID = " + pair.getKey());
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                rs = ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                while (rs.next()) {
                    double originalPrice = Double.parseDouble(rs.getString("actualCost"));
                    double percentage = Double.parseDouble(pair.getValue() + "");
                    newTaskPrice += originalPrice * (1 - (percentage / 100));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        totalCost += newTaskPrice;
        System.out.println("Total cost after discount: " + totalCost);

        //update the total cost of the job, taking into account the discounted price
        try {
            String sql = ("update job set totalCost = " + totalCost + " where jobID = " + jobNumber);
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        StandardPayment();
    }

    private void FlexibleDiscount(String flexibleID, String accountID) {
        //get the job price
        try {
            String sql = ("select totalCost from job where jobID = " + jobNumber);
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Double totalCost = 0.0;
        try {
            while (rs.next()) {
                totalCost = Double.parseDouble(rs.getString("totalCost"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Total cost: " + totalCost);
        //get the amount to be deducted for orders for this customer
        try {
            String sql = ("select toBeDeducted from flexibleDiscount where discountID = " + flexibleID);
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Double credit = 0.0;
        try {
            while (rs.next()) {
                credit = Double.parseDouble(rs.getString("toBeDeducted"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Amount to be decuducted: " + credit);
        if (credit <= totalCost) {
            totalCost -= credit;
            credit = 0.0;
        } else {
            credit = credit - totalCost;
            totalCost = 0.0;
        }
        System.out.println("Total after discount: " + totalCost);
        //set the customer's credit to 0
        try {
            String sql = ("update flexibleDiscount set toBeDeducted = " + credit + " where discountID = " + flexibleID);
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //update the total cost of the job, taking into account the credit.
        try {
            String sql = ("update job set totalCost = " + totalCost + " where jobID = " + jobNumber);
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        StandardPayment();
    }

    private void CheckAccountHolder() {
        String sql;
        //see if customer is account holder for a normal job 
        try {
            sql = ("select job.jobID, job.VehicleregistrationNumber, invoice.invoiceNumber, vehicle.CustomerID, "
                    + "customer.name, customeraccount.accountID, customerAccount.configuredPayLater, "
                    + "discountplan.* from job "
                    + "inner join invoice on job.jobID = invoice.JobjobID "
                    + "inner join vehicle on vehicle.registrationNumber = job.VehicleregistrationNumber "
                    + "inner join customer on customer.ID = vehicle.CustomerID "
                    + "inner join customeraccount on customeraccount.CustomerID = customer.ID "
                    + "inner join discountplan on discountplan.CustomerAccountaccountID = customeraccount.accountID "
                    + "where jobID = " + jobNumber);
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void ShowPayLaterCustomer() {
        buttonPayLater.setVisible(false);
        try {
            this.rsP = connection.createStatement().executeQuery("select configuredPayLater from CustomerAccount where customerID = "
                    + "(select ID from Customer where ID = (select customerID from vehicle where registrationNumber = "
                    + "(select VehicleRegistrationNumber from Job where jobID = " + jobNumber + " )))");

            String configuredPayLater = "";
            try {
                while (rs.next()) {
                    configuredPayLater = rsP.getString("ConfiguredPayLater");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (configuredPayLater.equals("1")) {
                buttonPayLater.setVisible(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void PrintInvoice() {
        String selected = listInvoices.getSelectedValue();
        if (selected != null) {
            GetJobAndInvoiceNumber();
            String details;
            if (!jobNumber.isEmpty()) {
                InvoiceController i = new InvoiceController(Integer.parseInt(jobNumber), connection);
                i.printJobInvoice();
            } else {
                details = GetPartInvoiceDetails();
                String fileName = "Invoice-number-" + invoiceNumber + ".txt";
                if (listInvoices.getSelectedValue() != null) {
                    try {
                        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
                        writer.println(details);
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String mess = "Printed successfully";
                    JOptionPane.showMessageDialog(new JFrame(), mess);
                }
            }

        } else {
            String mess = "Select an invoice";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonPay = new javax.swing.JButton();
        labelInvoice = new javax.swing.JLabel();
        labelDetail = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listInvoices = new javax.swing.JList<>();
        buttonPayLater = new javax.swing.JButton();
        comboxBoxPaymentType = new javax.swing.JComboBox<>();
        labelInvoices = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaInvoiceDetail = new javax.swing.JTextArea();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        buttonPrintInvoice = new javax.swing.JButton();
        labelPaymentType1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonPay.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonPay.setText("Pay");
        buttonPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPayActionPerformed(evt);
            }
        });
        add(buttonPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 630, -1, -1));

        labelInvoice.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelInvoice.setText("Invoices");
        add(labelInvoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));

        labelDetail.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelDetail.setText("Detail:");
        add(labelDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, -1, -1));

        listInvoices.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listInvoices.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listInvoicesValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listInvoices);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 1160, 170));

        buttonPayLater.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonPayLater.setText("Pay Later");
        buttonPayLater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPayLaterActionPerformed(evt);
            }
        });
        add(buttonPayLater, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 630, -1, -1));

        comboxBoxPaymentType.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        comboxBoxPaymentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "cash", "card" }));
        add(comboxBoxPaymentType, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 630, -1, 30));

        labelInvoices.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelInvoices.setText("Invoices:");
        add(labelInvoices, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        textAreaInvoiceDetail.setColumns(20);
        textAreaInvoiceDetail.setRows(5);
        jScrollPane3.setViewportView(textAreaInvoiceDetail);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 1160, 240));

        textFieldUserDetails.setEditable(false);
        textFieldUserDetails.setFocusable(false);
        add(textFieldUserDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 220, 30));

        labelLoggedIn.setText("Logged In as:");
        add(labelLoggedIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        buttonExit.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonExit.setText("Logout");
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });
        add(buttonExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, -1, -1));

        buttonBack.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, -1));

        buttonPrintInvoice.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonPrintInvoice.setText("Print Invoice");
        buttonPrintInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrintInvoiceActionPerformed(evt);
            }
        });
        add(buttonPrintInvoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 630, -1, -1));

        labelPaymentType1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelPaymentType1.setText("Payment Type:");
        add(labelPaymentType1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 630, -1, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPayActionPerformed
        String selected = listInvoices.getSelectedValue();

        if (selected != null) {
            GetJobAndInvoiceNumber();
            if (!jobNumber.isEmpty()) { //is a job, not a part sale
                CheckAccountHolder();
                String accountID = "";
                String fixedID = "";
                String flexibleID = "";
                String variableID = "";
                try {
                    while (rs.next()) {
                        accountID = rs.getString("CustomerAccountaccountID");
                        fixedID = rs.getString("FixedDiscountdiscountID");
                        flexibleID = rs.getString("FlexibleDiscountdiscountID");
                        variableID = rs.getString("VariableDiscountdiscountID");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                if (accountID.equals("")) {
                    //this customer is not an account holder.
                    //proceed to standard payment (no discount)
                    StandardPayment();
                } else {
                    //check for fixed discount
                    if (fixedID == null) {
                        //no fixed discount
                    } else {
                        //fixed discount
                        FixedDiscount(fixedID);
                    }

                    //check for variable discount
                    if (variableID == null) {
                        //no variable discount
                    } else {
                        //variable discount
                        VariableDiscount(variableID);
                    }

                    //check for flexible discount
                    if (flexibleID == null) {
                        //no flexible discount
                    } else {
                        //flexible discount
                        FlexibleDiscount(flexibleID, accountID);
                    }
                }
            } else {//is a part sale, not a job
                StandardPayment();
            }
        } else {
            String mess = "Select an invoice";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }//GEN-LAST:event_buttonPayActionPerformed

    private void buttonPayLaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPayLaterActionPerformed
        String selected = listInvoices.getSelectedValue();
        if (selected != null) {
            String[] parts = selected.split(", ");
            String invoicePart = parts[0];
            String[] invoiceSplit = invoicePart.split(": ");
            String invoiceNumber = invoiceSplit[1];
            try {
                String sql = ("select invoice.*, job.jobID, vehicle.registrationNumber, "
                        + "customer.name, customeraccount.accountID, customeraccount.configuredPayLater "
                        + "from invoice inner join job on job.jobID = "
                        + "invoice.JobjobID inner join vehicle on job.VehicleregistrationNumber = "
                        + "vehicle.registrationNumber inner join customer on vehicle.CustomerID = customer.ID "
                        + "inner join customeraccount on customeraccount.CustomerID = customer.id "
                        + "where invoiceNumber = " + invoiceNumber);
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                rs = ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            boolean done = false;
            try {
                while (rs.next()) {
                    String accountID = rs.getString("accountID");
                    String payLater = rs.getString("configuredPayLater");
                    if (accountID != null && payLater.equals("1")) {
                        try {
                            String sql = ("update invoice set payLater = 1 where invoiceNumber = " + invoiceNumber);
                            PreparedStatement ps = null;
                            try {
                                ps = connection.prepareStatement(sql);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            ps.executeUpdate();
                            done = true;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        ShowAllInvoices();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (done == false) {
                String mess = "Pay later not configured for this customer";
                JOptionPane.showMessageDialog(new JFrame(), mess);
            }

        } else {
            String mess = "Select an invoice";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }//GEN-LAST:event_buttonPayLaterActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new LogIn();
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        if (previousPage.equalsIgnoreCase("PartSale")) {
            new PartSale(username);
        } else {
            new MainMenu(username);
        }
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonPrintInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrintInvoiceActionPerformed
        PrintInvoice();
    }//GEN-LAST:event_buttonPrintInvoiceActionPerformed

    private void listInvoicesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listInvoicesValueChanged
        textAreaInvoiceDetail.setText("");
        String selected = listInvoices.getSelectedValue();

        if (selected != null) {
            GetJobAndInvoiceNumber();
            getJobVehicle();
            GetCustomerDetails();
            if (!jobNumber.isEmpty()) {
                InvoiceController i = new InvoiceController(Integer.parseInt(jobNumber), connection);
                textAreaInvoiceDetail.setText(i.GetInvoiceDetails());
                ShowPayLaterCustomer();
            } else {
                textAreaInvoiceDetail.append(GetPartInvoiceDetails());
            }
        }
    }//GEN-LAST:event_listInvoicesValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonPay;
    private javax.swing.JButton buttonPayLater;
    private javax.swing.JButton buttonPrintInvoice;
    private javax.swing.JComboBox<String> comboxBoxPaymentType;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelDetail;
    private javax.swing.JLabel labelInvoice;
    private javax.swing.JLabel labelInvoices;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelPaymentType1;
    private javax.swing.JList<String> listInvoices;
    private javax.swing.JTextArea textAreaInvoiceDetail;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
