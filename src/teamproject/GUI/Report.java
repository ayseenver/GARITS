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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import teamproject.Databases.DB_ImplClass;

/**
 *
 * @author ahmetsesli
 */
public class Report extends javax.swing.JPanel {

    private String username;
    private ResultSet rs;
    private ResultSet rsU;
    private String previousPage;
    Statement statement;
    Connection connection = null;
    DB_ImplClass db = new DB_ImplClass();
    String selected;
    String[] mechArray;
    HashMap<String, Integer> soldMap = new HashMap<>();
    HashMap<String, Integer> usedMap = new HashMap<>();
    HashMap<String, Integer> orderMap = new HashMap<>();

    /**
     * Creates new form NewJPanel
     */
    public Report(String username, String previousPage) {
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

        SetPanel();
        ShowReportOption();
        GetRole();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void GetRole() {
        String roleName = "";
        try {
            this.rs = statement.executeQuery("select * from User where deleted = 0");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (rs.next()) {
                // read the result set
                String user = rs.getString("username");

                //Code to get Role name from Databse
                if (username.equals(user)) {
                    roleName = rs.getString("roleName");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Code To check Which roleName is selected 
        if (!(roleName.equals("franchisee"))) {
            reportLabel.setVisible(false);
            comboBoxReport.setVisible(false);
        }
    }

    private void ShowMechanics() {
        try {
            this.rs = statement.executeQuery("select * from user where username in (select Userusername from mechanic)");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }

        comboBoxMechanic.removeAllItems();
        ArrayList<String> mechs = new ArrayList<>();

        try {
            while (rs.next()) {
                // read the result set
                String mech = rs.getString("firstName") + " " + rs.getString("surname");
                mechs.add(mech);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        mechArray = CreateArray(mechs);

        comboBoxMechanic.addItem("None");

        for (String m : mechArray) {
            comboBoxMechanic.addItem(m);
        }
    }

    private String[] CreateArray(ArrayList<String> tasks) {
        String[] newArray = new String[tasks.size()];
        newArray = tasks.toArray(newArray);
        return newArray;
    }

    private void ShowReportOption() {
        try {
            this.rsU = statement.executeQuery("select roleName from user where username = '" + username + "'");

            String roleName = rsU.getString("roleName");
            comboBoxReportType.setSelectedItem("Stock control");
            if (roleName.equals("franchisee")) {
                comboBoxReportType.setEnabled(true);

            } else {
                comboBoxReportType.setEnabled(false);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    private void SetPanel() {
        selected = comboBoxReportType.getSelectedItem().toString();
        if (selected.equals("Monthly vehicle report")) {
            jLayeredPane.removeAll();
            jLayeredPane.add(monthlyVehiclePanel);
            jLayeredPane.repaint();
            jLayeredPane.revalidate();
        } else if (selected.equals("Average job time and price")) {
            jLayeredPane.removeAll();
            ShowMechanics();
            jLayeredPane.add(jobTypePanel);
            jLayeredPane.repaint();
            jLayeredPane.revalidate();
        } else if (selected.equals("Stock control")) {
            jLayeredPane.removeAll();
            jLayeredPane.repaint();
            jLayeredPane.revalidate();
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

        labelReports = new javax.swing.JLabel();
        buttonPrint = new javax.swing.JButton();
        buttonView = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaReport = new javax.swing.JTextArea();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn1 = new javax.swing.JLabel();
        textFieldTill = new javax.swing.JTextField();
        labelTill = new javax.swing.JLabel();
        textFieldFrom = new javax.swing.JTextField();
        labelFrom = new javax.swing.JLabel();
        labelPeriod = new javax.swing.JLabel();
        labelSelectType = new javax.swing.JLabel();
        comboBoxReportType = new javax.swing.JComboBox<>();
        jLayeredPane = new javax.swing.JLayeredPane();
        jobTypePanel = new javax.swing.JPanel();
        labelCustomerType = new javax.swing.JLabel();
        comboBoxMechanic = new javax.swing.JComboBox<>();
        labelJobType1 = new javax.swing.JLabel();
        comboBoxJobTypeJob = new javax.swing.JComboBox<>();
        monthlyVehiclePanel = new javax.swing.JPanel();
        labelCustomerType1 = new javax.swing.JLabel();
        comboBoxCustomerType = new javax.swing.JComboBox<>();
        labelJobType2 = new javax.swing.JLabel();
        comboBoxJobTypeVehicle = new javax.swing.JComboBox<>();
        reportLabel = new javax.swing.JLabel();
        comboBoxReport = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelReports.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelReports.setText("Reports");
        add(labelReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, -1));

        buttonPrint.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonPrint.setText("Print");
        buttonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrintActionPerformed(evt);
            }
        });
        add(buttonPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 620, -1, -1));

        buttonView.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonView.setText("View");
        buttonView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonViewActionPerformed(evt);
            }
        });
        add(buttonView, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 620, -1, -1));

        textAreaReport.setColumns(20);
        textAreaReport.setRows(5);
        jScrollPane1.setViewportView(textAreaReport);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 1180, 400));

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
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

        textFieldUserDetails.setEditable(false);
        textFieldUserDetails.setFocusable(false);
        add(textFieldUserDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 220, 30));

        labelLoggedIn1.setText("Logged In as:");
        add(labelLoggedIn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));
        add(textFieldTill, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 190, 100, 30));

        labelTill.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelTill.setText("Till");
        add(labelTill, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 200, -1, -1));
        add(textFieldFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 190, 100, 30));

        labelFrom.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelFrom.setText("From");
        add(labelFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 200, -1, -1));

        labelPeriod.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelPeriod.setText("Period(yyyy-mm-dd):");
        add(labelPeriod, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 160, -1, -1));

        labelSelectType.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelSelectType.setText("Select Type of Report:");
        add(labelSelectType, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        comboBoxReportType.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        comboBoxReportType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monthly vehicle report", "Average job time and price", "Stock control" }));
        comboBoxReportType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxReportTypeActionPerformed(evt);
            }
        });
        add(comboBoxReportType, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, -1, 30));

        labelCustomerType.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelCustomerType.setText("Select mechanic: ");
        jobTypePanel.add(labelCustomerType);

        comboBoxMechanic.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        comboBoxMechanic.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None" }));
        jobTypePanel.add(comboBoxMechanic);

        labelJobType1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelJobType1.setText("Job Type:");
        jobTypePanel.add(labelJobType1);

        comboBoxJobTypeJob.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        comboBoxJobTypeJob.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Overall", "MoT", "Service", "Repair" }));
        jobTypePanel.add(comboBoxJobTypeJob);

        jLayeredPane.add(jobTypePanel);
        jobTypePanel.setBounds(80, 90, 370, 80);

        labelCustomerType1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelCustomerType1.setText("Customer Type:");
        monthlyVehiclePanel.add(labelCustomerType1);

        comboBoxCustomerType.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        comboBoxCustomerType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Casual", "Account holder" }));
        monthlyVehiclePanel.add(comboBoxCustomerType);

        labelJobType2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelJobType2.setText("Job Type:");
        monthlyVehiclePanel.add(labelJobType2);

        comboBoxJobTypeVehicle.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        comboBoxJobTypeVehicle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Overall", "MoT", "Service", "Repair" }));
        monthlyVehiclePanel.add(comboBoxJobTypeVehicle);

        jLayeredPane.add(monthlyVehiclePanel);
        monthlyVehiclePanel.setBounds(80, 90, 370, 80);

        add(jLayeredPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 520, 170));

        reportLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        reportLabel.setText("Automatic Report Frequency:");
        add(reportLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, 40));

        comboBoxReport.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        comboBoxReport.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "daily", "weekly", "monthly" }));
        comboBoxReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxReportActionPerformed(evt);
            }
        });
        add(comboBoxReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, -1, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrintActionPerformed
        if (textAreaReport.getText().isEmpty()) {
            String mess = "Click 'view' first";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
            Date aDate = new Date();
            String[] dateParts = aDate.toString().split(" ");
            String date = dateParts[0] + dateParts[1] + dateParts[2];

            String fileName = selected + date + "report.txt";
            try {
                PrintWriter writer = new PrintWriter(fileName, "UTF-8");
                writer.println(textAreaReport.getText());
                writer.close();
                String mess = "Printed successfully";
                JOptionPane.showMessageDialog(new JFrame(), mess);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_buttonPrintActionPerformed

    private void buttonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonViewActionPerformed
        if (!(textFieldFrom.getText().isEmpty() && textFieldTill.getText().isEmpty())) {
            textAreaReport.setText("");

            if (selected.equals("Monthly vehicle report")) {
                String customerType = comboBoxCustomerType.getSelectedItem().toString();
                String jobType = comboBoxJobTypeVehicle.getSelectedItem().toString();
                try {
                    String sql = "";
                    if (jobType.equals("Overall")) {
                        //all the jobs from casual customers
                        if (customerType.equals("Casual")) {
                            sql = ("select * from job "
                                    + "where VehicleregistrationNumber in "
                                    + "(select registrationNumber from vehicle where CustomerID in "
                                    + "(select ID from customer where ID not in "
                                    + "(select CustomerID from customerAccount))) "
                                    + "and (dateBookedIn BETWEEN '" + textFieldFrom.getText() + "' AND '" + textFieldTill.getText() + "')");
                        } else { //all the jobs from account holders
                            sql = ("select * from job "
                                    + "where VehicleregistrationNumber in "
                                    + "(select registrationNumber from vehicle where CustomerID in "
                                    + "(select ID from customer where ID in "
                                    + "(select CustomerID from customerAccount))) "
                                    + "and (dateBookedIn BETWEEN '" + textFieldFrom.getText() + "' "
                                    + "AND '" + textFieldTill.getText() + "')");
                        }
                    } else {
                        //all the jobs of this type from casual customers
                        if (customerType.equals("Casual")) {
                            sql = ("select * from job "
                                    + "where VehicleregistrationNumber in "
                                    + "(select registrationNumber from vehicle where CustomerID in "
                                    + "(select ID from customer where ID not in "
                                    + "(select CustomerID from customerAccount))) "
                                    + "and (dateBookedIn BETWEEN '" + textFieldFrom.getText() + "' "
                                    + "AND '" + textFieldTill.getText() + "') "
                                    + "and type = '" + jobType + "'");
                        } else { //all the jobs of this type from account holders
                            sql = ("select * from job "
                                    + "where VehicleregistrationNumber in "
                                    + "(select registrationNumber from vehicle where CustomerID in "
                                    + "(select ID from customer where ID in "
                                    + "(select CustomerID from customerAccount))) "
                                    + "and (dateBookedIn BETWEEN '" + textFieldFrom.getText() + "' "
                                    + "AND '" + textFieldTill.getText() + "') "
                                    + "and type = '" + jobType + "'");
                        }
                    }
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

                try {
                    while (rs.next()) {
                        String job = "JobID: " + rs.getString("jobID") + ", Vehicle: " + rs.getString("VehicleregistrationNumber");
                        textAreaReport.append(job + "\n");
                        jobs.add(job);
                    }
                    String text = textAreaReport.getText();
                    textAreaReport.setText("Booked in " + jobs.size() + " vehicles. Details: \n\n" + text);
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }

            } else if (selected.equals("Average job time and price")) {
                String mechanic = comboBoxMechanic.getSelectedItem().toString();

                String jobType = comboBoxJobTypeJob.getSelectedItem().toString();
                String sql;

                try {
                    //get average time and price for every job
                    if (mechanic.equals("None")) {
                        if (jobType.equals("Overall")) {
                            sql = ("select avg(totalCost), avg(totalHours) from job "
                                    + "where dateBookedIn BETWEEN '" + textFieldFrom.getText() + "' "
                                    + "AND '" + textFieldTill.getText() + "' and "
                                    + "dateCompleted is not null");
                        } else {
                            sql = ("select avg(totalCost), avg(totalHours) from job where type = '" + jobType + "' "
                                    + "and dateBookedIn BETWEEN '" + textFieldFrom.getText() + "' "
                                    + "AND '" + textFieldTill.getText() + "' and "
                                    + "dateCompleted is not null");
                        }
                    } else {//get average time and price for this mechanic
                        //get the mechanic name
                        String[] mechanicParts = mechanic.split(" ");
                        String firstName = mechanicParts[0];
                        String lastName = mechanicParts[1];
                        mechanic = firstName + " " + lastName;

                        if (jobType.equals("Overall")) {
                            sql = ("select avg(totalCost), avg(totalHours) from job "
                                    + "where MechanicID in "
                                    + "(select ID from mechanic where Userusername in "
                                    + "(select username from user where firstName = '" + firstName + "' and surname = '" + lastName + "')) "
                                    + "and dateBookedIn BETWEEN '" + textFieldFrom.getText() + "' "
                                    + "AND '" + textFieldTill.getText() + "' and "
                                    + "dateCompleted is not null");
                        } else {
                            sql = ("select avg(totalCost), avg(totalHours) from job "
                                    + "where MechanicID in "
                                    + "(select ID from mechanic where Userusername in "
                                    + "(select username from user where firstName = '" + firstName + "' and surname = '" + lastName + "')) "
                                    + "and dateBookedIn BETWEEN '" + textFieldFrom.getText() + "' "
                                    + "AND '" + textFieldTill.getText() + "' and "
                                    + "type = '" + jobType + "' and "
                                    + "dateCompleted is not null");
                        }
                    }
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

                try {
                    while (rs.next()) {
                        String result = "For jobs completed in this date range\n"
                                + "\nAverage hours: " + rs.getString("avg(totalHours)")
                                + "\nAverage cost (not including VAT): " + rs.getString("avg(totalCost)")
                                + "\nMechanic name: " + mechanic;
                        textAreaReport.append(result + "\n");
                    }
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            } else if (selected.equals("Stock control")) {
                //get all of the parts sold during this time frame.
                try {
                    String sql = "SELECT sparepartpartID, sum(quantity) FROM invoice_sparePart "
                            + "INNER JOIN invoice ON "
                            + "invoice.invoiceNumber = invoice_sparePart.invoiceinvoicenumber "
                            + "where dateProduced between '" + textFieldFrom.getText() + "' "
                            + "and '" + textFieldTill.getText() + "' "
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
                            + "where dateCompleted between '" + textFieldFrom.getText() + "' "
                            + "and '" + textFieldTill.getText() + "'"
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
                            + "where date between '" + textFieldFrom.getText() + "' and '" + textFieldTill.getText() + "' "
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

                try {
                    double initialCost = 0;
                    double stockCost = 0;
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
                        initialCost += initialQuantity * (Double.parseDouble(rs.getString("costPrice")));
                        stockCost += newQuantity * (Double.parseDouble(rs.getString("costPrice")));

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
                        textAreaReport.append(result + "\n");
                    }
                    textAreaReport.append("Total inital cost: " + initialCost + "\n");
                    textAreaReport.append("Total stock cost: " + stockCost + "\n");
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        } else {
            String mess = "Select the dates";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }//GEN-LAST:event_buttonViewActionPerformed

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
        if (previousPage.equalsIgnoreCase("StockControl")) {
            new StockControl(username);
        } else {
            new MainMenu(username);
        }
    }//GEN-LAST:event_buttonBackActionPerformed

    private void comboBoxReportTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxReportTypeActionPerformed
        SetPanel();
    }//GEN-LAST:event_comboBoxReportTypeActionPerformed

    private void comboBoxReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxReportActionPerformed
        LocalDate today = LocalDate.now();
        String fileName = "reportFrequency.txt";
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(comboBoxReport.getSelectedItem().toString() + ", " + today);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        JOptionPane.showMessageDialog(new JFrame(), "Frequency Updated!");
    }//GEN-LAST:event_comboBoxReportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonPrint;
    private javax.swing.JButton buttonView;
    private javax.swing.JComboBox<String> comboBoxCustomerType;
    private javax.swing.JComboBox<String> comboBoxJobTypeJob;
    private javax.swing.JComboBox<String> comboBoxJobTypeVehicle;
    private javax.swing.JComboBox<String> comboBoxMechanic;
    private javax.swing.JComboBox<String> comboBoxReport;
    private javax.swing.JComboBox<String> comboBoxReportType;
    private javax.swing.JLayeredPane jLayeredPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jobTypePanel;
    private javax.swing.JLabel labelCustomerType;
    private javax.swing.JLabel labelCustomerType1;
    private javax.swing.JLabel labelFrom;
    private javax.swing.JLabel labelJobType1;
    private javax.swing.JLabel labelJobType2;
    private javax.swing.JLabel labelLoggedIn1;
    private javax.swing.JLabel labelPeriod;
    private javax.swing.JLabel labelReports;
    private javax.swing.JLabel labelSelectType;
    private javax.swing.JLabel labelTill;
    private javax.swing.JPanel monthlyVehiclePanel;
    private javax.swing.JLabel reportLabel;
    private javax.swing.JTextArea textAreaReport;
    private javax.swing.JTextField textFieldFrom;
    private javax.swing.JTextField textFieldTill;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
