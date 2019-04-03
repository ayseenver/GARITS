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
import javax.swing.JOptionPane;
import teamproject.Databases.DB_ImplClass;

/**
 *
 * @author ahmetsesli
 */
public class ReminderGUI extends javax.swing.JPanel {

    private String username;
    Statement statement;
    Connection connection = null;
    DB_ImplClass db = new DB_ImplClass();
    ResultSet rs;
    String[] reminderArray;
    String selected;
    String type;
    String vehicle;
    String date;
    String reminderNo;

    /**
     * Creates new form NewJPanel
     */
    public ReminderGUI(String username) {
        this.username = username;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        this.textFieldUserDetails.setText(username);
        connection = db.connect();
        statement = db.getStatement();

        ShowAllReminders();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void ShowAllReminders() {
        //get all MoT reminders
        try {
            this.rs = statement.executeQuery("select * from VehicleReminder where type = 'MoT' and deleted = 0");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        ArrayList<String> reminders = new ArrayList<>();

        try {
            while (rs.next()) {
                // read the result set
                String reminder = "Type: " + rs.getString("type") + ", Vehicle: " + rs.getString("VehicleregistrationNumber") + ", "
                        + "Due: " + rs.getString("dueDate");
                reminders.add(reminder);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        if (!(reminders.isEmpty())) {
            reminders.add("\n");
        }

        //get all service reminders
        try {
            this.rs = statement.executeQuery("select * from VehicleReminder where type = 'Service' and deleted = 0");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            while (rs.next()) {
                // read the result set
                String reminder = "Type: " + rs.getString("type") + ", Vehicle: " + rs.getString("VehicleregistrationNumber") + ", "
                        + "Due: " + rs.getString("dueDate");
                reminders.add(reminder);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        if (!(reminders.isEmpty())) {
            reminders.add("\n");
        }

        //get all payment reminders
        try {
            System.out.println("select paymentReminder.reminderNumber, paymentReminder.InvoiceinvoiceNumber, "
                    + "invoice.dateProduced, job.jobID, job.totalCost, job.VehicleregistrationNumber "
                    + "from paymentreminder inner join invoice on "
                    + "Invoice.invoiceNumber = paymentReminder.InvoiceinvoiceNumber "
                    + "inner join job on job.jobID = invoice.JobjobID "
                    + "where deleted = 0 "
                    + "order by reminderNumber asc");
            this.rs = statement.executeQuery("select paymentReminder.reminderNumber, paymentReminder.InvoiceinvoiceNumber, "
                    + "invoice.dateProduced, job.jobID, job.totalCost, job.VehicleregistrationNumber "
                    + "from paymentreminder inner join invoice on "
                    + "Invoice.invoiceNumber = paymentReminder.InvoiceinvoiceNumber "
                    + "inner join job on job.jobID = invoice.JobjobID "
                    + "where deleted = 0 "
                    + "order by reminderNumber asc");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            while (rs.next()) {
                // read the result set
                String reminder = "Type: Payment, Vehicle: " + rs.getString("VehicleregistrationNumber")
                        + ", Invoice date: " + rs.getString("dateProduced")
                        + ", Reminder number: " + rs.getString("reminderNumber");
                reminders.add(reminder);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        reminderArray = CreateArray(reminders);

        listReminders.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return reminderArray.length;
            }

            public String getElementAt(int i) {
                return reminderArray[i];
            }
        });
    }

    private String[] CreateArray(ArrayList<String> tasks) {
        String[] newArray = new String[tasks.size()];
        newArray = tasks.toArray(newArray);
        return newArray;
    }

    private void SplitSelected() {
        if (listReminders.getSelectedValue() != null) {
            selected = listReminders.getSelectedValue();
            String[] parts = selected.split(", ");

            String[] typeParts = parts[0].split(": ");
            type = typeParts[1];

            String[] vehicleParts = parts[1].split(": ");
            vehicle = vehicleParts[1];

            String[] dateParts = parts[2].split(": ");
            date = dateParts[1];

            if (type.equals("Payment")) {
                String[] reminderParts = parts[3].split(": ");
                reminderNo = reminderParts[1];
            }
        } else {
            String mess = "Please select a reminder";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }

    private void SplitString(String s) {
        String[] parts = s.split(", ");

        String[] typeParts = parts[0].split(": ");
        type = typeParts[1];

        String[] vehicleParts = parts[1].split(": ");
        vehicle = vehicleParts[1];

        String[] dateParts = parts[2].split(": ");
        date = dateParts[1];

        if (type.equals("Payment")) {
            String[] reminderParts = parts[3].split(": ");
            reminderNo = reminderParts[1];
        }
    }

    private String CreateMoTReminder() {
        String result = "";

        result += "Reminder - MoT Test Due\n";
        result += "Vehicle registration no.: " + vehicle + "\tRenewal test date: " + date + "\n";
        result += "According to our records, the above vehicle is due to have its MoT certificate renewed on the date shown.\n";
        /*if customer is account holder
        result += "Account Holders customers such as yourself are assured of our prompt attention, "
                + "and we hope that you will use our services "
                + "on this occasion in order to have the necessary test carried out on your vehicle.\n";
         */
        return result;
    }

    private String CreateServiceReminder() {
        String result = "";

        result += "Reminder - Service Due\n";
        result += "Vehicle registration no.: " + vehicle + "\tService date: " + date + "\n";
        result += "According to our records, the above vehicle is due to be serviced on the date shown.\n";
        /*if customer is account holder
        result += "Account Holders customers such as yourself are assured of our prompt attention, "
                + "and we hope that you will use our services "
                + "on this occasion in order to have the necessary service carried out on your vehicle.\n";
         */
        return result;
    }

    private String CreatePaymentReminder() {
        try {
            this.rs = statement.executeQuery("select paymentReminder.reminderNumber, paymentReminder.InvoiceinvoiceNumber, "
                    + "invoice.dateProduced, job.jobID, job.totalCost, job.VehicleregistrationNumber "
                    + "from paymentreminder inner join invoice on "
                    + "Invoice.invoiceNumber = paymentReminder.InvoiceinvoiceNumber "
                    + "inner join job on job.jobID = invoice.JobjobID "
                    + "where VehicleregistrationNumber = '" + vehicle + "' and reminderNumber = " + reminderNo);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        String totalCost = "";
        String invoiceNumber = "";

        try {
            while (rs.next()) {
                try {
                    totalCost = rs.getString("totalCost");
                    invoiceNumber = rs.getString("InvoiceinvoiceNumber");
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        String result = "";

        if (reminderNo.equals("1")) {
            //invoice number 1
            result += "Reminder - Invoice number " + invoiceNumber + "\n";
            result += "Vehicle registration no.: " + vehicle + "\tTotal amount: " + totalCost + "\n";
            result += "According to our records, it appears that we have not yet received payment of the above invoice, "
                    + "which was posted to you on " + date + ", for work done on the vehicle(s) listed above.\n";
            result += "We would appreciate payment at your earliest convenience.\n";
            result += "If you have already sent a payment to us recently, please accept our apologies.\n";
        } else if (reminderNo.equals("2")) {
            //invoice number 2
            result += "Second reminder - Invoice number " + invoiceNumber + "\n";
            result += "Vehicle registration no.: " + vehicle + "\tTotal amount: " + totalCost + "\n";
            result += "It appears that we still have not yet received payment of the above invoice, "
                    + "which was posted to you on " + date + ", for work done on the vehicle(s) listed above, despite a reminder letter posted to you 1 month later.";
            result += "We would appreciate it if you would settle this invoice in full by return. \n";
            result += "If you have already sent a payment to us recently, please accept our apologies.\n";
        } else {
            //invoice number 3
            result += "Final reminder - Invoice number " + invoiceNumber + "\n";
            result += "Vehicle registration no.: " + vehicle + "\tTotal amount: " + totalCost + "\n";
            result += "Despite two reminders, it appears that we still have not yet received payment of the above invoice, "
                    + "which was posted to you on " + date + ", for work done on the vehicle(s) listed above.\n";
            result += "Unless you pay the outstanding amount in full within SEVEN DAYS, or contact us with proposals for repayment, "
                    + "we will have no option but to refer the matter to our solicitor.\n";
            result += "Please send payment immediately to avoid further action.\n";
        }

        return result;
    }

    private void Print() {
        String fileName = "reminder-" + type + "-" + vehicle + ".txt";

        String details = "";
        if (type.equals("MoT")) {
            details = CreateMoTReminder();
        } else if (type.equals("Service")) {
            details = CreateServiceReminder();
        } else if (type.equals("Payment")) {
            details = CreatePaymentReminder();
        }

        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(details);
            writer.close();
            String mess = "Printed sucessfully";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void PrintWithoutPopup() {
        String fileName = "reminder-" + type + "-" + vehicle + ".txt";

        String details = "";
        if (type.equals("MoT")) {
            details = CreateMoTReminder();
        } else if (type.equals("Service")) {
            details = CreateServiceReminder();
        } else if (type.equals("Payment")) {
            details = CreatePaymentReminder();
        }

        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(details);
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
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

        labelReminders = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listReminders = new javax.swing.JList<>();
        buttonPrintAll = new javax.swing.JButton();
        buttonPrint = new javax.swing.JButton();
        buttonPrintType = new javax.swing.JButton();
        buttonDismiss = new javax.swing.JButton();
        comboBoxType = new javax.swing.JComboBox<>();
        labelDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaDescription = new javax.swing.JTextArea();
        labelType = new javax.swing.JLabel();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelReminders.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelReminders.setText("Reminders");
        add(labelReminders, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, -1, -1));

        listReminders.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        listReminders.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        listReminders.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listRemindersValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listReminders);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 570, 420));

        buttonPrintAll.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonPrintAll.setText("Print All");
        buttonPrintAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrintAllActionPerformed(evt);
            }
        });
        add(buttonPrintAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 640, -1, -1));

        buttonPrint.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonPrint.setText("Print");
        buttonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrintActionPerformed(evt);
            }
        });
        add(buttonPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 640, -1, -1));

        buttonPrintType.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonPrintType.setText("Print Selected Type ");
        buttonPrintType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrintTypeActionPerformed(evt);
            }
        });
        add(buttonPrintType, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 640, -1, -1));

        buttonDismiss.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonDismiss.setText("Dismiss");
        buttonDismiss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDismissActionPerformed(evt);
            }
        });
        add(buttonDismiss, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 640, -1, -1));

        comboBoxType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MoT", "Service", "Payment" }));
        add(comboBoxType, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 640, -1, -1));

        labelDescription.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelDescription.setText("Description:");
        add(labelDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 170, -1, -1));

        textAreaDescription.setColumns(20);
        textAreaDescription.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        textAreaDescription.setLineWrap(true);
        textAreaDescription.setRows(5);
        jScrollPane1.setViewportView(textAreaDescription);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, 570, 420));

        labelType.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelType.setText("Type:");
        add(labelType, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 640, -1, -1));

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
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPrintAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrintAllActionPerformed
        for (int i = 0; i < listReminders.getModel().getSize(); i++) {
            String current = listReminders.getModel().getElementAt(i);
            if (current != null && (!(current.equals("\n")))) {
                SplitString(current);
                PrintWithoutPopup();
            }
        }
        String mess = "Printed sucessfully";
        JOptionPane.showMessageDialog(new JFrame(), mess);
    }//GEN-LAST:event_buttonPrintAllActionPerformed

    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrintActionPerformed
        if (listReminders.getSelectedValue() != null) {
            SplitSelected();
            Print();
        } else {
            String mess = "Select a reminder";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }//GEN-LAST:event_buttonPrintActionPerformed

    private void buttonPrintTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrintTypeActionPerformed
        for (int i = 0; i < listReminders.getModel().getSize(); i++) {
            String current = listReminders.getModel().getElementAt(i);
            if (current != null && (!(current.equals("\n")))) {
                SplitString(current);
                if (type.equals(comboBoxType.getSelectedItem().toString())) {
                    PrintWithoutPopup();
                }
            }
        }
        String mess = "Printed sucessfully";
        JOptionPane.showMessageDialog(new JFrame(), mess);
    }//GEN-LAST:event_buttonPrintTypeActionPerformed

    private void buttonDismissActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDismissActionPerformed
        SplitSelected();
        if (listReminders.getSelectedValue() != null) {

            String sql;
            try {
                if (type.equals("MoT") || type.equals("Service")) {
                    sql = ("update vehicleReminder "
                            + "set deleted = 1 "
                            + "where type = '" + type + "' and "
                            + "vehicleregistrationNumber = '" + vehicle + "' and "
                            + "dueDate = '" + date + "'");
                } else {

                    try {
                        this.rs = statement.executeQuery("select paymentReminder.reminderNumber, paymentReminder.InvoiceinvoiceNumber, "
                                + "invoice.dateProduced, job.jobID, job.totalCost, job.VehicleregistrationNumber "
                                + "from paymentreminder inner join invoice on "
                                + "Invoice.invoiceNumber = paymentReminder.InvoiceinvoiceNumber "
                                + "inner join job on job.jobID = invoice.JobjobID "
                                + "where VehicleregistrationNumber = '" + vehicle + "' and reminderNumber = " + reminderNo);
                    } catch (SQLException e) {
                        System.err.println(e.getMessage());
                    }

                    String invoiceNo = "";
                    try {
                        while (rs.next()) {
                            invoiceNo = rs.getString("invoiceinvoicenumber");
                        }
                    } catch (SQLException e) {
                        System.err.println(e.getMessage());
                    }

                    sql = ("update paymentReminder "
                            + "set deleted = 1 where "
                            + "reminderNumber = " + reminderNo + " and "
                            + "Invoiceinvoicenumber = " + invoiceNo);
                    System.out.println(sql);
                }

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
            ShowAllReminders();
        } else {
            String mess = "Select a reminder";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }//GEN-LAST:event_buttonDismissActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new MainMenu(username);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new LogIn();
    }//GEN-LAST:event_buttonExitActionPerformed

    private void listRemindersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listRemindersValueChanged

        if (listReminders.getSelectedValue() != null) {
            SplitSelected();
            if (type.equals("MoT")) {
                textAreaDescription.setText(CreateMoTReminder());
            } else if (type.equals("Service")) {
                textAreaDescription.setText(CreateServiceReminder());
            } else if (type.equals("Payment")) {
                textAreaDescription.setText(CreatePaymentReminder());
            }
        }
    }//GEN-LAST:event_listRemindersValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonDismiss;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonPrint;
    private javax.swing.JButton buttonPrintAll;
    private javax.swing.JButton buttonPrintType;
    private javax.swing.JComboBox<String> comboBoxType;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelReminders;
    private javax.swing.JLabel labelType;
    private javax.swing.JList<String> listReminders;
    private javax.swing.JTextArea textAreaDescription;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
