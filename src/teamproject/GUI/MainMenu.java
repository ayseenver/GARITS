/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.GUI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import teamproject.Databases.DB_ImplClass;
import teamproject.AlertsReminders.AlertTimer;

/**
 *
 * @author ahmetsesli
 */
public class MainMenu extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    private ResultSet rs;
    Statement statement;
    Connection connection = null;
    DB_ImplClass db = new DB_ImplClass();
    private String roleName;
    private String username;

    public MainMenu(String username) {
        this.username = username;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();

        this.textFieldUserDetails.setText(username);
        connection = db.connect();
        statement = db.getStatement();

        frame.setVisible(true);
        GetRole();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void GetRole() {
        try {
            this.rs = statement.executeQuery("select * from User where deleted = 0");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            while (rs.next()) {
                // read the result set
                String user = rs.getString("username");

                //Code to get Role name from Databse
                if (username.equals(user)) {
                    this.rs = statement.executeQuery("select roleName from User where username = '" + username + "'");

                    roleName = rs.getString("roleName");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //Code To check Which roleName is selected 
        if (roleName.equals("receptionist")) {
            receptionist_menu();
        } else if (roleName.equals("mechanic")) {
            mechanic_menu();
        } else if (roleName.equals("admin")) {
            admin_menu();
        } else if (roleName.equals("franchisee")) {
            franchise_menu();
        } else if (roleName.equals("foreperson")) {
            foreperson_menu();
        }
    }

    // Receptionist View
    public void receptionist_menu() {

        this.buttonDatabase.setVisible(false);
        this.buttonUserAccount.setVisible(false);
        this.buttonReport.setVisible(false);
        this.buttonCustomers.setVisible(false);
        this.buttonMyJobs.setVisible(false);
        this.buttonAllocateJob.setVisible(false);
        this.buttonTaskList.setVisible(false);

        this.buttonCreateJob.setLocation(490, 260);
        this.buttonJobList.setLocation(490, 320);
        this.buttonInvoices.setLocation(490, 380);
        this.buttonReminders.setLocation(490, 440);
        this.buttonStockControl.setLocation(490, 500);

    }

    // Foreperson View 
    public void foreperson_menu() {
        this.buttonDatabase.setVisible(false);
        this.buttonUserAccount.setVisible(false);
        this.buttonCustomers.setVisible(false);
        this.buttonReport.setVisible(false);
        this.buttonTaskList.setVisible(false);

        this.buttonCreateJob.setLocation(490, 220);
        this.buttonAllocateJob.setLocation(490, 280);
        this.buttonJobList.setLocation(490, 340);
        this.buttonMyJobs.setLocation(490, 400);
        this.buttonInvoices.setLocation(490, 460);
        this.buttonStockControl.setLocation(490, 520);
        this.buttonReminders.setLocation(490, 580);

    }

    //Franchise View
    public void franchise_menu() {
        this.buttonDatabase.setVisible(false);
        this.buttonUserAccount.setVisible(false);
        this.buttonTaskList.setVisible(false);

        this.buttonCreateJob.setLocation(490, 140);
        this.buttonAllocateJob.setLocation(490, 195);
        this.buttonJobList.setLocation(490, 250);
        this.buttonMyJobs.setLocation(490, 305);
        this.buttonInvoices.setLocation(490, 360);
        this.buttonReminders.setLocation(490, 415);
        this.buttonStockControl.setLocation(490, 470);
        this.buttonCustomers.setLocation(490, 525);
        this.buttonReport.setLocation(490, 580);
        this.buttonTaskList.setLocation(490, 635);
        
        AlertTimer at = new AlertTimer();
    }

    //Mechanic View
    public void mechanic_menu() {

        this.buttonAllocateJob.setVisible(false);
        this.buttonInvoices.setVisible(false);
        this.buttonReminders.setVisible(false);
        this.buttonDatabase.setVisible(false);
        this.buttonUserAccount.setVisible(false);
        this.buttonStockControl.setVisible(false);
        this.buttonCreateJob.setVisible(false);
        this.buttonJobList.setVisible(false);
        this.buttonCustomers.setVisible(false);
        this.buttonReport.setVisible(false);
        this.buttonTaskList.setVisible(false);

        this.buttonMyJobs.setLocation(490, 375);

    }

    //Admin View
    public void admin_menu() {

        this.buttonAllocateJob.setVisible(false);
        this.buttonInvoices.setVisible(false);
        this.buttonReminders.setVisible(false);
        this.buttonStockControl.setVisible(false);
        this.buttonCreateJob.setVisible(false);
        this.buttonJobList.setVisible(false);
        this.buttonMyJobs.setVisible(false);
        this.buttonCustomers.setVisible(false);
        this.buttonReport.setVisible(false);
        this.buttonTaskList.setVisible(false);

        this.buttonDatabase.setLocation(490, 350);
        this.buttonUserAccount.setLocation(490, 410);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMainMenu = new javax.swing.JLabel();
        buttonCreateJob = new javax.swing.JButton();
        buttonReminders = new javax.swing.JButton();
        buttonStockControl = new javax.swing.JButton();
        buttonInvoices = new javax.swing.JButton();
        buttonMyJobs = new javax.swing.JButton();
        buttonAllocateJob = new javax.swing.JButton();
        buttonReport = new javax.swing.JButton();
        buttonJobList = new javax.swing.JButton();
        textFieldUserDetails = new javax.swing.JTextField();
        lblLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonUserAccount = new javax.swing.JButton();
        buttonCustomers = new javax.swing.JButton();
        buttonDatabase = new javax.swing.JButton();
        buttonTaskList = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(null);

        lblMainMenu.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        lblMainMenu.setText("Main Menu");
        add(lblMainMenu);
        lblMainMenu.setBounds(410, 40, 374, 92);

        buttonCreateJob.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonCreateJob.setText("Create Job");
        buttonCreateJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCreateJobActionPerformed(evt);
            }
        });
        add(buttonCreateJob);
        buttonCreateJob.setBounds(490, 160, 230, 41);

        buttonReminders.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonReminders.setText("View Reminders");
        buttonReminders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemindersActionPerformed(evt);
            }
        });
        add(buttonReminders);
        buttonReminders.setBounds(490, 460, 230, 41);

        buttonStockControl.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonStockControl.setText("Stock Control");
        buttonStockControl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStockControlActionPerformed(evt);
            }
        });
        add(buttonStockControl);
        buttonStockControl.setBounds(490, 520, 230, 41);

        buttonInvoices.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonInvoices.setText("Invoices");
        buttonInvoices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInvoicesActionPerformed(evt);
            }
        });
        add(buttonInvoices);
        buttonInvoices.setBounds(490, 400, 230, 41);

        buttonMyJobs.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonMyJobs.setText("My Jobs");
        buttonMyJobs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMyJobsActionPerformed(evt);
            }
        });
        add(buttonMyJobs);
        buttonMyJobs.setBounds(490, 340, 230, 41);

        buttonAllocateJob.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonAllocateJob.setText("Allocate Jobs");
        buttonAllocateJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAllocateJobActionPerformed(evt);
            }
        });
        add(buttonAllocateJob);
        buttonAllocateJob.setBounds(490, 220, 230, 41);

        buttonReport.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonReport.setText("All Reports");
        buttonReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReportActionPerformed(evt);
            }
        });
        add(buttonReport);
        buttonReport.setBounds(490, 640, 230, 41);

        buttonJobList.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonJobList.setText("Job List");
        buttonJobList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonJobListActionPerformed(evt);
            }
        });
        add(buttonJobList);
        buttonJobList.setBounds(490, 280, 230, 41);

        textFieldUserDetails.setEditable(false);
        textFieldUserDetails.setFocusable(false);
        textFieldUserDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUserDetailsActionPerformed(evt);
            }
        });
        add(textFieldUserDetails);
        textFieldUserDetails.setBounds(910, 10, 220, 30);

        lblLoggedIn.setText("Logged In as:");
        add(lblLoggedIn);
        lblLoggedIn.setBounds(820, 20, 66, 14);

        buttonExit.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonExit.setText("Logout");
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });
        add(buttonExit);
        buttonExit.setBounds(1140, 10, 120, 33);

        buttonUserAccount.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonUserAccount.setText("User Accounts");
        buttonUserAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUserAccountActionPerformed(evt);
            }
        });
        add(buttonUserAccount);
        buttonUserAccount.setBounds(800, 600, 230, 41);

        buttonCustomers.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonCustomers.setText("View Customers");
        buttonCustomers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCustomersActionPerformed(evt);
            }
        });
        add(buttonCustomers);
        buttonCustomers.setBounds(490, 580, 230, 41);

        buttonDatabase.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonDatabase.setText("Database");
        buttonDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDatabaseActionPerformed(evt);
            }
        });
        add(buttonDatabase);
        buttonDatabase.setBounds(800, 500, 230, 41);

        buttonTaskList.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonTaskList.setText("Task List");
        buttonTaskList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTaskListActionPerformed(evt);
            }
        });
        add(buttonTaskList);
        buttonTaskList.setBounds(800, 550, 230, 41);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCreateJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCreateJobActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new CreateJobCustomer(username);
    }//GEN-LAST:event_buttonCreateJobActionPerformed

    private void buttonRemindersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemindersActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new ReminderGUI(username);
    }//GEN-LAST:event_buttonRemindersActionPerformed

    private void buttonStockControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStockControlActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new StockControl(username);

    }//GEN-LAST:event_buttonStockControlActionPerformed

    private void buttonInvoicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInvoicesActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new Invoice(username);
    }//GEN-LAST:event_buttonInvoicesActionPerformed

    private void buttonMyJobsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMyJobsActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new MyJob(username);
    }//GEN-LAST:event_buttonMyJobsActionPerformed

    private void buttonAllocateJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAllocateJobActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new AllocateJob(username);
    }//GEN-LAST:event_buttonAllocateJobActionPerformed

    private void buttonReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReportActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new Report(username);

    }//GEN-LAST:event_buttonReportActionPerformed

    private void buttonJobListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonJobListActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new JobList(username);
    }//GEN-LAST:event_buttonJobListActionPerformed

    private void textFieldUserDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserDetailsActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new LogIn();
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonUserAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUserAccountActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new UserAccount(username);

    }//GEN-LAST:event_buttonUserAccountActionPerformed

    private void buttonCustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCustomersActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new CustomerList(username);
    }//GEN-LAST:event_buttonCustomersActionPerformed

    private void buttonDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDatabaseActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new Database(username);
    }//GEN-LAST:event_buttonDatabaseActionPerformed

    private void buttonTaskListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTaskListActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new TaskList(username);
    }//GEN-LAST:event_buttonTaskListActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAllocateJob;
    private javax.swing.JButton buttonCreateJob;
    private javax.swing.JButton buttonCustomers;
    private javax.swing.JButton buttonDatabase;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonInvoices;
    private javax.swing.JButton buttonJobList;
    private javax.swing.JButton buttonMyJobs;
    private javax.swing.JButton buttonReminders;
    private javax.swing.JButton buttonReport;
    private javax.swing.JButton buttonStockControl;
    private javax.swing.JButton buttonTaskList;
    private javax.swing.JButton buttonUserAccount;
    private javax.swing.JLabel lblLoggedIn;
    private javax.swing.JLabel lblMainMenu;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
