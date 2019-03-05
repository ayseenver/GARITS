/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import teamproject.Customer_Account.Vehicle;

/**
 *
 * @author ahmetsesli
 */
public class CreateJobTask extends javax.swing.JPanel {
    private String username;
    private ResultSet rs;    
    private Statement statement;
    String[] taskArray;
    String[] requiredTaskArray;
    String[] bayArray;
    ArrayList<String> requiredTasks = new ArrayList<>();
    ArrayList<String> tasks = new ArrayList<>();
    private Connection connection;
    String bayType;
    String jobType;
    Job job;
    Vehicle v;
    
    /**
     * Creates new form NewJPanel
     */
    public CreateJobTask(String username, Vehicle v) {
        this.username = username;
        this.bayType = "MoT inspection";
        this.jobType = "Service";
        this.v = v;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        
        this.textFieldUserDetails.setText(username);
        EstablishConnection();
        
        try{
            this.rs = statement.executeQuery("select * from Task");
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          System.err.println(e.getMessage());
        }
        
        listAvailableTasks.removeAll();
        listRequiredTasks.removeAll();
        listAvailableBays.removeAll();
        
        UpdateTaskList();
        
        UpdateBayList();
        
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private String[] CreateTaskArray(ArrayList<String> tasks){
        String[] taskArray = new String[tasks.size()];
        taskArray = tasks.toArray(taskArray);
        return taskArray;
    }
    
    private String[] CreateRequiredTaskArray(ArrayList<String> tasks){
        String[] requiredTaskArray = new String[tasks.size()];
        requiredTaskArray = tasks.toArray(requiredTaskArray);
        return requiredTaskArray;
    }
    
    private String[] CreateBayArray(ArrayList<String> bays){
        String[] bayArray = new String[bays.size()];
        bayArray = bays.toArray(bayArray);
        return bayArray;
    }
    
    private void UpdateBayList(){
        listAvailableTasks.removeAll();
        ArrayList<String> bays = new ArrayList<>();
        //get all bays
        try{
            String sql = ("select * from Bay where type = '" + bayType) +"'" + ("and booked = 0");
            PreparedStatement ps = null;
            try {
            ps = connection.prepareStatement(sql);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            this.rs = ps.executeQuery();
            //this.rsV = statement.executeQuery("select * from Vehicle where Customername =");
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          System.err.println(e.getMessage());
        }
        
        //add bays to bay list
        try{
        while(rs.next())
          {
            // read the result set
            String bay = rs.getString("bayID") +": " + rs.getString("type");
            bays.add(bay);
          } 
        }
        catch(SQLException e){
        }
        
        bayArray = CreateBayArray(bays);
                
        listAvailableBays.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return bayArray.length; }
            public String getElementAt(int i) { return bayArray[i]; }
        });
    }
    
    private void UpdateTaskList(){
        listAvailableBays.removeAll();
        
        //add all tasks to task list
        try{
        while(rs.next())
          {
            // read the result set
            String task = rs.getString("description");
            tasks.add(task);
          } 
        }
        catch(SQLException e){
        }
        
        taskArray = CreateTaskArray(tasks);
                
        listAvailableTasks.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return taskArray.length; }
            public String getElementAt(int i) { return taskArray[i]; }
        });
    }
    
    
    private void EstablishConnection(){
        connection = null;
        try
        {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:GARITSDB.db");
            this.statement = connection.createStatement();
            this.statement.setQueryTimeout(30);  // set timeout to 30 sec.
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
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

        labelSelectTasks = new javax.swing.JLabel();
        createJobButton = new javax.swing.JButton();
        labelCreateJob = new javax.swing.JLabel();
        bayTypeCombo = new javax.swing.JComboBox<>();
        jScrollPane11 = new javax.swing.JScrollPane();
        listAvailableBays = new javax.swing.JList<>();
        labelAvailableBay = new javax.swing.JLabel();
        labelBayType = new javax.swing.JLabel();
        labelJobType = new javax.swing.JLabel();
        jobTypeCombo = new javax.swing.JComboBox<>();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        checkBoxYard = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listRequiredTasks = new javax.swing.JList<>();
        jScrollPane10 = new javax.swing.JScrollPane();
        listAvailableTasks = new javax.swing.JList<>();
        labelAvailableTasks = new javax.swing.JLabel();
        textFieldSearchJobs = new javax.swing.JTextField();
        buttonSearchTasks = new javax.swing.JButton();
        labelTasksRequired = new javax.swing.JLabel();
        removeTask = new javax.swing.JButton();
        addTask = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelSelectTasks.setFont(new java.awt.Font("Lucida Grande", 1, 48)); // NOI18N
        labelSelectTasks.setText("Select Tasks");
        add(labelSelectTasks, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, -1));

        createJobButton.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        createJobButton.setText("Create Job");
        createJobButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createJobButtonActionPerformed(evt);
            }
        });
        add(createJobButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 620, -1, -1));

        labelCreateJob.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelCreateJob.setText("Create Job");
        add(labelCreateJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));

        bayTypeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MoT inspection", "repair" }));
        bayTypeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayTypeComboActionPerformed(evt);
            }
        });
        add(bayTypeCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 610, -1, -1));

        listAvailableBays.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane11.setViewportView(listAvailableBays);

        add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 550, 220, 80));

        labelAvailableBay.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelAvailableBay.setText("Bay Available:");
        add(labelAvailableBay, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 520, -1, -1));

        labelBayType.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelBayType.setText("Bay Type:");
        add(labelBayType, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 580, -1, -1));

        labelJobType.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelJobType.setText("Job Type:");
        add(labelJobType, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 520, -1, -1));

        jobTypeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Service", "MoT", "Repair" }));
        jobTypeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jobTypeComboActionPerformed(evt);
            }
        });
        add(jobTypeCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 550, -1, -1));

        textFieldUserDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUserDetailsActionPerformed(evt);
            }
        });
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
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

        checkBoxYard.setText("Send to Yard ");
        checkBoxYard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxYardActionPerformed(evt);
            }
        });
        add(checkBoxYard, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 600, -1, -1));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listRequiredTasks.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane5.setViewportView(listRequiredTasks);

        jPanel6.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 76, 230, 220));

        listAvailableTasks.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane10.setViewportView(listAvailableTasks);

        jPanel6.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 79, 230, 220));

        labelAvailableTasks.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelAvailableTasks.setText("Available Tasks:");
        jPanel6.add(labelAvailableTasks, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 23, -1, -1));
        jPanel6.add(textFieldSearchJobs, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 160, -1));

        buttonSearchTasks.setText("Search");
        buttonSearchTasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchTasksActionPerformed(evt);
            }
        });
        jPanel6.add(buttonSearchTasks, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        labelTasksRequired.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelTasksRequired.setText("Tasks Required:");
        jPanel6.add(labelTasksRequired, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        removeTask.setText("<");
        removeTask.setActionCommand("addTask");
        removeTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeTaskActionPerformed(evt);
            }
        });
        jPanel6.add(removeTask, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, -1, -1));

        addTask.setText(">");
        addTask.setActionCommand("addTask");
        addTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTaskActionPerformed(evt);
            }
        });
        jPanel6.add(addTask, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, -1, -1));

        add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, 550, 320));
    }// </editor-fold>//GEN-END:initComponents

    private void createJobButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createJobButtonActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        
        f.dispose();
        new ConfirmJob(username, v);
    }//GEN-LAST:event_createJobButtonActionPerformed

    private void textFieldUserDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserDetailsActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        new CreateJobCustomer(username);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void checkBoxYardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxYardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxYardActionPerformed

    private void buttonSearchTasksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchTasksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchTasksActionPerformed

    private void removeTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTaskActionPerformed
        String selected = listRequiredTasks.getSelectedValue();
        
        requiredTasks.remove(selected);
        
        requiredTaskArray = CreateRequiredTaskArray(requiredTasks);
                
        listRequiredTasks.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return requiredTaskArray.length; }
            public String getElementAt(int i) { return requiredTaskArray[i]; }
        });
        
        tasks.add(selected);
        UpdateTaskList();
    }//GEN-LAST:event_removeTaskActionPerformed

    private void addTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTaskActionPerformed
        String selected = listAvailableTasks.getSelectedValue();
        
        requiredTasks.add(selected);
        
        requiredTaskArray = CreateRequiredTaskArray(requiredTasks);
                
        listRequiredTasks.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return requiredTaskArray.length; }
            public String getElementAt(int i) { return requiredTaskArray[i]; }
        });
        
        tasks.remove(selected);
        UpdateTaskList();
    }//GEN-LAST:event_addTaskActionPerformed

    private void bayTypeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayTypeComboActionPerformed
        bayType = bayTypeCombo.getSelectedItem().toString();
        System.out.println(bayType);
        UpdateBayList();
    }//GEN-LAST:event_bayTypeComboActionPerformed

    private void jobTypeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jobTypeComboActionPerformed
        jobType = jobTypeCombo.getSelectedItem().toString();
        System.out.println(jobType);
    }//GEN-LAST:event_jobTypeComboActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTask;
    private javax.swing.JComboBox<String> bayTypeCombo;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonSearchTasks;
    private javax.swing.JCheckBox checkBoxYard;
    private javax.swing.JButton createJobButton;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JComboBox<String> jobTypeCombo;
    private javax.swing.JLabel labelAvailableBay;
    private javax.swing.JLabel labelAvailableTasks;
    private javax.swing.JLabel labelBayType;
    private javax.swing.JLabel labelCreateJob;
    private javax.swing.JLabel labelJobType;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelSelectTasks;
    private javax.swing.JLabel labelTasksRequired;
    private javax.swing.JList<String> listAvailableBays;
    private javax.swing.JList<String> listAvailableTasks;
    private javax.swing.JList<String> listRequiredTasks;
    private javax.swing.JButton removeTask;
    private javax.swing.JTextField textFieldSearchJobs;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
