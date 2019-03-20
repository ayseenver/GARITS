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
import javax.swing.JFrame;
import teamproject.Databases.DB_ImplClass;

/**
 *
 * @author ahmetsesli
 */
public class Invoice extends javax.swing.JPanel {

    private String username;
    Statement statement;
    Connection connection = null;
    DB_ImplClass db = new DB_ImplClass();
    ResultSet rs;
    String[] invoiceArray;
    String jobNumber;
    String invoiceNumber;

    /**
     * Creates new form NewJPanel
     */
    public Invoice(String username) {
        this.username = username;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();

        this.textFieldUserDetails.setText(username);
        connection = db.connect();
        statement = db.getStatement();

        ShowAllInvoices();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void GetJobAndInvoiceNumber() {
        String[] parts = listInvoices.getSelectedValue().split(", ");
        String[] idParts = parts[0].split(": ");
        invoiceNumber = idParts[1];

        try {
            String[] jobParts = parts[1].split(": ");
            jobNumber = jobParts[1];
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private String GetJobInvoiceDetails() {
        GetJobAndInvoiceNumber();
        String result = "";
        result += ("Invoice number : " + invoiceNumber + "\n");
        result += ("Job number : " + jobNumber + "\n\n");
        result += ("Description of work: \n");

        //get all task descriptions for the actual tasks for this job
        try {
            String sql = ("select * from task where taskID in (select TasktaskID from actual_task where JobjobID = " + jobNumber + ")");
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

        int i = 1;
        try {
            while (rs.next()) {
                // read the result set. Get task description.
                String task = i + ") " + rs.getString("description");
                result += (task + "\n");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //get all actual task hours for this job
        try {
            String sql = ("select totalHours from job where jobID = " + jobNumber);
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

        double totalHours = 0;
        try {
            // read the result set. Get task hours
            totalHours += Double.parseDouble(rs.getString("totalHours"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        result += ("\nParts used: \n");

        //get all parts used for this job
        try {
            String sql = ("select * from sparePart where partID in (select PartpartID from job_part_record where JobjobID = " + jobNumber + ")");
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

        double totalPartsCost = 0;
        try {
            while (rs.next()) {
                // read the result set. Get part name description.
                String part = rs.getString("partName") + ", £" + Double.parseDouble(rs.getString("sellingPrice"));
                totalPartsCost += Double.parseDouble(rs.getString("sellingPrice"));
                result += (part + "\n");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        result += ("Total parts cost: £" + totalPartsCost + "\n");

        //get hourly rate for this mechanic
        try {
            String sql = ("select hourlyRate from mechanic where ID = (select MechanicID from job where jobID = " + jobNumber + ")");
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

        double hourlyRate = 0;
        try {
            while (rs.next()) {
                hourlyRate = Double.parseDouble(rs.getString("hourlyRate"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        double totalCost = ((hourlyRate * totalHours) + totalPartsCost); //excluding VAT
        result += ("\nTotal labour cost: £" + (hourlyRate * totalHours) + "\n");
        result += ("\nVAT: " + totalCost * 0.2);
        result += ("\nGrand total: £" + totalCost * 1.2 + "\n");
        return result;
    }

    private String GetPartInvoiceDetails() {
        GetJobAndInvoiceNumber();
        String result = "";
        result += ("Invoice number : " + invoiceNumber + "\n\n");
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
            System.err.println(e.getMessage());
        }

        try {
            while (rs.next()) {
                // read the result set. Get part name and quantity
                result += rs.getString("partName") + ", Quantity: " + rs.getString("quantity");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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
            System.err.println(e.getMessage());
        }

        Double sellingPrice = 0.00;
        try {
            while (rs.next()) {
                // read the result set. Get part name description.
                sellingPrice = Double.parseDouble(rs.getString("sum(sellingPrice * quantity)"));
                result += ("Total cost: £" + sellingPrice + "\n");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        double totalCost = sellingPrice;
        result += ("\nGrand total: £" + totalCost * 1.2 + "\n");
        return result;
    }

    private void ShowAllInvoices() {
        //get all unpaid invoices for jobs
        try {
            this.rs = statement.executeQuery("select * from Invoice where JobjobID not in (select JobjobID from payment) "
                    + "and JobjobID is not null and payLater = 0");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }

        ArrayList<String> invoices = new ArrayList<>();

        try {
            while (rs.next()) {
                // read the result set
                String invoice = "Invoice Number: " + rs.getString("invoiceNumber") + ", Job ID: " + rs.getString("JobjobID");
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        invoices.add("\n");

        //get all invoices for part sales
        try {
            this.rs = statement.executeQuery("select * from Invoice where JobjobID is null");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            while (rs.next()) {
                // read the result set
                String invoice = "Invoice Number: " + rs.getString("invoiceNumber") + ", Part sale";
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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
            String sql = ("insert into Payment(paymentType, JobjobID)"
                    + " values ( '" + comboxBoxPaymentType.getSelectedItem().toString() + "', "
                    + "(select jobID from job where jobID = " + jobNumber + "))");
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
            System.err.println(e.getMessage());
        }

        double percentage = 0.0;
        try {
            while (rs.next()) {
                percentage = Double.parseDouble(rs.getString("percentage"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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
            System.err.println(e.getMessage());
        }

        double totalCost = 0.0;
        try {
            while (rs.next()) {
                totalCost = Double.parseDouble(rs.getString("totalCost"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //calculate the new cost
        double newCost = totalCost * (1 - (percentage * 0.01));

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
            System.err.println(e.getMessage());
        }

        //make a standard payment
        StandardPayment();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonSearchInvoices = new javax.swing.JButton();
        buttonPay = new javax.swing.JButton();
        labelInvoice = new javax.swing.JLabel();
        labelDetail = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listInvoices = new javax.swing.JList<>();
        buttonView = new javax.swing.JButton();
        buttonPayLater = new javax.swing.JButton();
        comboxBoxPaymentType = new javax.swing.JComboBox<>();
        labelInvoices = new javax.swing.JLabel();
        labelPaymentType = new javax.swing.JLabel();
        textFieldSearchInvoices = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaInvoiceDetail = new javax.swing.JTextArea();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        buttonPrintInvoice = new javax.swing.JButton();
        labelCustomerInfomation = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonSearchInvoices.setText("Search");
        buttonSearchInvoices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchInvoicesActionPerformed(evt);
            }
        });
        add(buttonSearchInvoices, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, -1, -1));

        buttonPay.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonPay.setText("Pay");
        buttonPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPayActionPerformed(evt);
            }
        });
        add(buttonPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 637, -1, -1));

        labelInvoice.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelInvoice.setText("Invoices");
        add(labelInvoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));

        labelDetail.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelDetail.setText("Detail:");
        add(labelDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, -1, -1));

        listInvoices.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane2.setViewportView(listInvoices);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 1160, 140));

        buttonView.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonView.setText("View");
        buttonView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonViewActionPerformed(evt);
            }
        });
        add(buttonView, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 340, -1, -1));

        buttonPayLater.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonPayLater.setText("Pay Later");
        buttonPayLater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPayLaterActionPerformed(evt);
            }
        });
        add(buttonPayLater, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 630, -1, -1));

        comboxBoxPaymentType.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        comboxBoxPaymentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "cash", "card" }));
        add(comboxBoxPaymentType, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 640, -1, -1));

        labelInvoices.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelInvoices.setText("Invoices:");
        add(labelInvoices, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        labelPaymentType.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        labelPaymentType.setText("Payment Type:");
        add(labelPaymentType, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 640, -1, -1));

        textFieldSearchInvoices.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        textFieldSearchInvoices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSearchInvoicesActionPerformed(evt);
            }
        });
        add(textFieldSearchInvoices, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 227, 30));

        textAreaInvoiceDetail.setColumns(20);
        textAreaInvoiceDetail.setRows(5);
        jScrollPane3.setViewportView(textAreaInvoiceDetail);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 1160, 240));
        add(textFieldUserDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 220, 30));

        labelLoggedIn.setText("Logged In as:");
        add(labelLoggedIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        buttonExit.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonExit.setText("Exit");
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });
        add(buttonExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 0, -1, -1));

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

        labelCustomerInfomation.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        labelCustomerInfomation.setText("*Pay Later option needs to be configured");
        add(labelCustomerInfomation, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 640, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchInvoicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchInvoicesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchInvoicesActionPerformed

    private void buttonPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPayActionPerformed
        GetJobAndInvoiceNumber();
        String sql;
        //see if customer is account holder
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
            System.err.println(e.getMessage());
        }

        String accountID = "";
        String fixedID = "";
        try {
            while (rs.next()) {
                accountID = rs.getString("CustomerAccountaccountID");
                fixedID = rs.getString("FixedDiscountdiscountID");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        if (accountID.equals("")) {
            //this customer is not an account holder.
            //proceed to standard payment (no discount) 
            StandardPayment();
        } else {
            //check for fixed discount
            if (fixedID.equals("")) {
                //no fixed discount
            } else {
                //fixed discount
                FixedDiscount(fixedID);
            }
        }

        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new MainMenu(username);
    }//GEN-LAST:event_buttonPayActionPerformed

    private void buttonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonViewActionPerformed
        textAreaInvoiceDetail.setText("");

        GetJobAndInvoiceNumber();
        if (!jobNumber.equals("null")) {
            textAreaInvoiceDetail.append(GetJobInvoiceDetails());
        } else {
            textAreaInvoiceDetail.append(GetPartInvoiceDetails());
        }
    }//GEN-LAST:event_buttonViewActionPerformed

    private void buttonPayLaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPayLaterActionPerformed
        String[] parts = listInvoices.getSelectedValue().split(", ");
        String invoicePart = parts[0];
        String[] invoiceSplit = invoicePart.split(": ");
        String invoiceNumber = invoiceSplit[1];
        try {
            String sql = ("update invoice set payLater = 1 where invoiceNumber = " + invoiceNumber);
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

        ShowAllInvoices();
    }//GEN-LAST:event_buttonPayLaterActionPerformed

    private void textFieldSearchInvoicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchInvoicesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSearchInvoicesActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        db.closeConnection(connection);
        System.exit(0);
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new MainMenu(username);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonPrintInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrintInvoiceActionPerformed
        GetJobAndInvoiceNumber();
        String details;
        if (!jobNumber.equals("null")) {
            details = GetJobInvoiceDetails();
        } else {
            details = GetPartInvoiceDetails();
        }

        String fileName = "Invoice-number-" + invoiceNumber + ".txt";
        if (listInvoices.getSelectedValue() != null) {
            try {
                PrintWriter writer = new PrintWriter(fileName, "UTF-8");
                writer.println(details);
                writer.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
            f.dispose();
            db.closeConnection(connection);
            new MainMenu(username);
        }
    }//GEN-LAST:event_buttonPrintInvoiceActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonPay;
    private javax.swing.JButton buttonPayLater;
    private javax.swing.JButton buttonPrintInvoice;
    private javax.swing.JButton buttonSearchInvoices;
    private javax.swing.JButton buttonView;
    private javax.swing.JComboBox<String> comboxBoxPaymentType;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelCustomerInfomation;
    private javax.swing.JLabel labelDetail;
    private javax.swing.JLabel labelInvoice;
    private javax.swing.JLabel labelInvoices;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelPaymentType;
    private javax.swing.JList<String> listInvoices;
    private javax.swing.JTextArea textAreaInvoiceDetail;
    private javax.swing.JTextField textFieldSearchInvoices;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
