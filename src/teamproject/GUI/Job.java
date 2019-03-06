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
import javax.swing.JFrame;

/**
 *
 * @author ahmetsesli
 */
public class Job extends javax.swing.JPanel {
    private final String username;   
    ResultSet rs;
    Statement statement;
    ArrayList<String> tasks = new ArrayList<>();
    ArrayList<String> actualTasks = new ArrayList<>();
    ArrayList<String> parts = new ArrayList<>();
    ArrayList<String> usedParts = new ArrayList<>();
    String[] taskArray;
    String[] actualTaskArray;
    String[] partArray;
    String[] usedPartArray;
    Connection connection;
    int jobID;
    String vehicleReg;
    
    /**
     * Creates new form NewJPanel
     */
    public Job(String username, int jobID, String vehicleReg) {
        this.username = username;
        this.jobID = jobID;
        this.vehicleReg = vehicleReg;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        
        this.textFieldUserDetails.setText(username);
        EstablishConnection();
        
        GetTasks();
        ListAllTasks();
        GetActualTasks();
        ListActualTasks();
        GetParts();
        ListAllParts();
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void GetTasks(){
        try{
            this.rs = statement.executeQuery("select * from Task where description not in "
                    + "(select description from Task where taskID = " 
                    + "(select TasktaskID from Actual_Task where jobJobID = " + jobID + "))");
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
    }
    
    private void GetParts(){        
        try{
            String sql = ("select * from sparepart where vehicleType = "
                    + "(select model from Vehicle where registrationNumber = '" + vehicleReg + "')");
            PreparedStatement ps = null;
            try {
            ps = connection.prepareStatement(sql);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            this.rs = ps.executeQuery();
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }        
    }
    
    private void ListAllParts(){
        listAvailableParts.removeAll();
        
        //add all parts to part list
        try{
        while(rs.next())
          {
            // read the result set
            String part = rs.getString("partName");
            parts.add(part);
          } 
        }
        catch(SQLException e){
        }
        
        partArray = CreateArray(parts);
                
        listAvailableParts.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return partArray.length; }
            public String getElementAt(int i) { return partArray[i]; }
        });
    }
    
    private void ListUsedParts(){
        listPartsUsed.removeAll();
        
        //add all parts to used part list
        try{
        while(rs.next())
          {
            // read the result set
            String part = rs.getString("partName");
            usedParts.add(part);
          } 
        }
        catch(SQLException e){
        }
        
        usedPartArray = CreateArray(usedParts);
                
        listPartsUsed.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return usedPartArray.length; }
            public String getElementAt(int i) { return usedPartArray[i]; }
        });
    }
    
    private void GetActualTasks(){
        //get all actual tasks descriptions for this job
        try{
            String sql = ("select description from Task where taskID = "
                    + "(select TasktaskID from Actual_Task where jobJobID = " + jobID + ")");
            PreparedStatement ps = null;
            try {
            ps = connection.prepareStatement(sql);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            this.rs = ps.executeQuery();
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
    }
    
    private void ListAllTasks(){
        listAvailableTasks.removeAll();
        
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
        
        taskArray = CreateArray(tasks);
                
        listAvailableTasks.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return taskArray.length; }
            public String getElementAt(int i) { return taskArray[i]; }
        });
    }
    
    private void ListActualTasks(){
        listTasksCarriedOut.removeAll();
        
        //add all actual task descriptions to task list
        try{
        while(rs.next())
          {
            // read the result set
            String actualTask = rs.getString("description");
            actualTasks.add(actualTask);
          } 
        }
        catch(SQLException e){
        }        
        
        actualTaskArray = CreateArray(actualTasks);
                
        listTasksCarriedOut.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return actualTaskArray.length; }
            public String getElementAt(int i) { return actualTaskArray[i]; }
        });
    }
    
    
    private String[] CreateArray(ArrayList<String> tasks){
        String[] newArray = new String[tasks.size()];
        newArray = tasks.toArray(newArray);
        return newArray;
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

        labelJob = new javax.swing.JLabel();
        labelAvailableParts = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaJobDetails = new javax.swing.JTextArea();
        labelPartsUsed = new javax.swing.JLabel();
        buttonCompleted = new javax.swing.JButton();
        panelPart = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        listAvailableParts = new javax.swing.JList<>();
        textFieldSearchParts = new javax.swing.JTextField();
        buttonSearchParts = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        listPartsUsed = new javax.swing.JList<>();
        textFieldPartsUsedQuanity = new javax.swing.JTextField();
        labelPartsUsedQuantity = new javax.swing.JLabel();
        buttonUpdatePartsUsed = new javax.swing.JButton();
        addPartButton = new javax.swing.JButton();
        removePartButton = new javax.swing.JButton();
        panelTask = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listTasksCarriedOut = new javax.swing.JList<>();
        jScrollPane10 = new javax.swing.JScrollPane();
        listAvailableTasks = new javax.swing.JList<>();
        textFieldTime = new javax.swing.JTextField();
        labelTime = new javax.swing.JLabel();
        buttonUpdateTaskTime = new javax.swing.JButton();
        labelTasksCarriedOut = new javax.swing.JLabel();
        labelAvailableTasks = new javax.swing.JLabel();
        textFieldSearchTasks = new javax.swing.JTextField();
        buttonSearchTasks = new javax.swing.JButton();
        addTaskButton = new javax.swing.JButton();
        removeTaskButton = new javax.swing.JButton();
        buttonInfromCustomer = new javax.swing.JButton();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        lblAvailableParts1 = new javax.swing.JLabel();
        buttonRequestPartsList = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelJob.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelJob.setText("Job");
        add(labelJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(583, 0, -1, -1));

        labelAvailableParts.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelAvailableParts.setText("Available Parts:");
        add(labelAvailableParts, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, -1, -1));

        textAreaJobDetails.setColumns(20);
        textAreaJobDetails.setRows(5);
        jScrollPane2.setViewportView(textAreaJobDetails);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 1180, 100));

        labelPartsUsed.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelPartsUsed.setText("Parts Used:");
        add(labelPartsUsed, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 260, -1, -1));

        buttonCompleted.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonCompleted.setText("Job Completed");
        buttonCompleted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCompletedActionPerformed(evt);
            }
        });
        add(buttonCompleted, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 660, -1, -1));

        panelPart.setBackground(new java.awt.Color(204, 204, 204));
        panelPart.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listAvailableParts.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane9.setViewportView(listAvailableParts);

        panelPart.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 75, 226, 204));
        panelPart.add(textFieldSearchParts, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, -1));

        buttonSearchParts.setText("Search");
        buttonSearchParts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchPartsActionPerformed(evt);
            }
        });
        panelPart.add(buttonSearchParts, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        listPartsUsed.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane8.setViewportView(listPartsUsed);

        panelPart.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 79, 230, 200));
        panelPart.add(textFieldPartsUsedQuanity, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 285, 33, -1));

        labelPartsUsedQuantity.setText("Update Quantity:");
        panelPart.add(labelPartsUsedQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, -1, -1));

        buttonUpdatePartsUsed.setText("Update");
        buttonUpdatePartsUsed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdatePartsUsedActionPerformed(evt);
            }
        });
        panelPart.add(buttonUpdatePartsUsed, new org.netbeans.lib.awtextra.AbsoluteConstraints(451, 285, -1, -1));

        addPartButton.setText(">");
        addPartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPartButtonActionPerformed(evt);
            }
        });
        panelPart.add(addPartButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, -1, -1));

        removePartButton.setText("<");
        removePartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removePartButtonActionPerformed(evt);
            }
        });
        panelPart.add(removePartButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, -1, -1));

        add(panelPart, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 240, 550, 320));

        panelTask.setBackground(new java.awt.Color(204, 204, 204));
        panelTask.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listTasksCarriedOut.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane5.setViewportView(listTasksCarriedOut);

        panelTask.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 76, 230, 200));

        listAvailableTasks.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane10.setViewportView(listAvailableTasks);

        panelTask.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 79, 230, 200));
        panelTask.add(textFieldTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(391, 285, 33, -1));

        labelTime.setText("Update Time:");
        panelTask.add(labelTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(296, 290, -1, -1));

        buttonUpdateTaskTime.setText("Update");
        buttonUpdateTaskTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateTaskTimeActionPerformed(evt);
            }
        });
        panelTask.add(buttonUpdateTaskTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(448, 285, -1, -1));

        labelTasksCarriedOut.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelTasksCarriedOut.setText("Tasks carried out:");
        panelTask.add(labelTasksCarriedOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 23, -1, -1));

        labelAvailableTasks.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelAvailableTasks.setText("Available Tasks:");
        panelTask.add(labelAvailableTasks, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 23, -1, -1));
        panelTask.add(textFieldSearchTasks, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 160, -1));

        buttonSearchTasks.setText("Search");
        buttonSearchTasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchTasksActionPerformed(evt);
            }
        });
        panelTask.add(buttonSearchTasks, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        addTaskButton.setText(">");
        addTaskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTaskButtonActionPerformed(evt);
            }
        });
        panelTask.add(addTaskButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, -1, -1));

        removeTaskButton.setText("<");
        removeTaskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeTaskButtonActionPerformed(evt);
            }
        });
        panelTask.add(removeTaskButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, -1, -1));

        add(panelTask, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 550, 320));

        buttonInfromCustomer.setText("Inform Customer about cost");
        buttonInfromCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInfromCustomerActionPerformed(evt);
            }
        });
        add(buttonInfromCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 210, -1));

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

        lblAvailableParts1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        lblAvailableParts1.setText("Available Parts:");
        add(lblAvailableParts1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, -1, -1));

        buttonRequestPartsList.setText("Send Vehicle to Yard");
        buttonRequestPartsList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRequestPartsListActionPerformed(evt);
            }
        });
        add(buttonRequestPartsList, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 590, 170, -1));

        jCheckBox1.setText("Confirm Send Vehicle to Yard");
        add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 590, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchTasksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchTasksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchTasksActionPerformed

    private void buttonSearchPartsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchPartsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchPartsActionPerformed

    private void buttonCompletedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCompletedActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        new MainMenu(username);
    }//GEN-LAST:event_buttonCompletedActionPerformed

    private void buttonUpdateTaskTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateTaskTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonUpdateTaskTimeActionPerformed

    private void buttonInfromCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInfromCustomerActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        new MainMenu(username);
    }//GEN-LAST:event_buttonInfromCustomerActionPerformed

    private void textFieldUserDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserDetailsActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        new MainMenu(username);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonUpdatePartsUsedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdatePartsUsedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonUpdatePartsUsedActionPerformed

    private void buttonRequestPartsListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRequestPartsListActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        new MainMenu(username);
    }//GEN-LAST:event_buttonRequestPartsListActionPerformed

    private void addTaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTaskButtonActionPerformed
        String selected = listAvailableTasks.getSelectedValue();
        actualTasks.add(selected);
        actualTaskArray = CreateArray(actualTasks);
                
        listTasksCarriedOut.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return actualTaskArray.length; }
            public String getElementAt(int i) { return actualTaskArray[i]; }
        });
        
        tasks.remove(selected);
        ListAllTasks();
    }//GEN-LAST:event_addTaskButtonActionPerformed

    private void removeTaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTaskButtonActionPerformed
        String selected = listTasksCarriedOut.getSelectedValue(); 
        actualTasks.remove(selected);
        actualTaskArray = CreateArray(actualTasks);
                
        listAvailableTasks.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return actualTaskArray.length; }
            public String getElementAt(int i) { return actualTaskArray[i]; }
        });
        
        tasks.add(selected);
        ListAllTasks();
        ListActualTasks();
    }//GEN-LAST:event_removeTaskButtonActionPerformed

    private void addPartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPartButtonActionPerformed
        String selected = listAvailableParts.getSelectedValue();
        usedParts.add(selected);
        usedPartArray = CreateArray(usedParts);
                
        listPartsUsed.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return usedPartArray.length; }
            public String getElementAt(int i) { return usedPartArray[i]; }
        });
        
        parts.remove(selected);
        ListAllParts();
    }//GEN-LAST:event_addPartButtonActionPerformed

    private void removePartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removePartButtonActionPerformed
        String selected = listPartsUsed.getSelectedValue(); 
        usedParts.remove(selected);
        usedPartArray = CreateArray(usedParts);
                
        listPartsUsed.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return usedPartArray.length; }
            public String getElementAt(int i) { return usedPartArray[i]; }
        });
        
        parts.add(selected);
        ListAllParts();
        ListUsedParts();
    }//GEN-LAST:event_removePartButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPartButton;
    private javax.swing.JButton addTaskButton;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonCompleted;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonInfromCustomer;
    private javax.swing.JButton buttonRequestPartsList;
    private javax.swing.JButton buttonSearchParts;
    private javax.swing.JButton buttonSearchTasks;
    private javax.swing.JButton buttonUpdatePartsUsed;
    private javax.swing.JButton buttonUpdateTaskTime;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel labelAvailableParts;
    private javax.swing.JLabel labelAvailableTasks;
    private javax.swing.JLabel labelJob;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelPartsUsed;
    private javax.swing.JLabel labelPartsUsedQuantity;
    private javax.swing.JLabel labelTasksCarriedOut;
    private javax.swing.JLabel labelTime;
    private javax.swing.JLabel lblAvailableParts1;
    private javax.swing.JList<String> listAvailableParts;
    private javax.swing.JList<String> listAvailableTasks;
    private javax.swing.JList<String> listPartsUsed;
    private javax.swing.JList<String> listTasksCarriedOut;
    private javax.swing.JPanel panelPart;
    private javax.swing.JPanel panelTask;
    private javax.swing.JButton removePartButton;
    private javax.swing.JButton removeTaskButton;
    private javax.swing.JTextArea textAreaJobDetails;
    private javax.swing.JTextField textFieldPartsUsedQuanity;
    private javax.swing.JTextField textFieldSearchParts;
    private javax.swing.JTextField textFieldSearchTasks;
    private javax.swing.JTextField textFieldTime;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
