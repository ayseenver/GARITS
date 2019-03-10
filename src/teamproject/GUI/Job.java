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
import teamproject.Databases.DB_ImplClass;

/**
 *
 * @author ahmetsesli
 */
public class Job extends javax.swing.JPanel {
    private final String username;   
    ResultSet rs;
    ArrayList<String> tasks = new ArrayList<>();
    ArrayList<String> actualTasks = new ArrayList<>();
    ArrayList<String> parts = new ArrayList<>();
    ArrayList<String> usedParts = new ArrayList<>();
    String[] taskArray;
    String[] actualTaskArray;
    String[] partArray;
    String[] usedPartArray;
    int jobID;
    String vehicleReg;
    Statement statement;
    Connection connection = null;
    DB_ImplClass db = new DB_ImplClass();
    
    
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
        connection = db.connect();
        statement = db.getStatement();
        
        GetTasks();
        ListAllTasks();
        GetActualTasks();
        ListActualTasks();
        GetParts();
        ListAllParts();
        GetActualParts();
        ListUsedParts();
        
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
    
    private void GetParts(){        
        try{
            String sql = ("select * from sparepart where vehicleType = (select model from Vehicle where registrationNumber = '" + vehicleReg + "') "
                    + "and partID not in (select PartpartID from Job_Part_Record where JobjobID = '" + jobID + "')");
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
    
    private void GetActualParts(){
        //get all actual tasks descriptions for this job
        try{
            String sql = ("select partName from sparepart where partID in (select PartpartID from Job_Part_Record where JobjobID = '" + jobID + "')");
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
        updateJobButton = new javax.swing.JButton();
        panelPart = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        listAvailableParts = new javax.swing.JList<>();
        textFieldSearchParts = new javax.swing.JTextField();
        buttonSearchParts = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        listPartsUsed = new javax.swing.JList<>();
        textFieldQuantity = new javax.swing.JTextField();
        labelPartsUsedQuantity = new javax.swing.JLabel();
        buttonUpdateQuantity = new javax.swing.JButton();
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
        buttonUpdateTaskCost = new javax.swing.JButton();
        textFieldCost = new javax.swing.JTextField();
        labelTime1 = new javax.swing.JLabel();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        lblAvailableParts1 = new javax.swing.JLabel();
        sendYardButton = new javax.swing.JButton();
        yardCheckBox = new javax.swing.JCheckBox();
        jobCompletedButton = new javax.swing.JButton();

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

        updateJobButton.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        updateJobButton.setText("Update Job");
        updateJobButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateJobButtonActionPerformed(evt);
            }
        });
        add(updateJobButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 660, -1, -1));

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
        panelPart.add(textFieldQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 285, 33, -1));

        labelPartsUsedQuantity.setText("Update Quantity:");
        panelPart.add(labelPartsUsedQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, -1, -1));

        buttonUpdateQuantity.setText("Update");
        buttonUpdateQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateQuantityActionPerformed(evt);
            }
        });
        panelPart.add(buttonUpdateQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(451, 285, -1, -1));

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
        panelTask.add(textFieldTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, 33, -1));

        labelTime.setText("Update Time:");
        panelTask.add(labelTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, -1, -1));

        buttonUpdateTaskTime.setText("Update");
        buttonUpdateTaskTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateTaskTimeActionPerformed(evt);
            }
        });
        panelTask.add(buttonUpdateTaskTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 280, -1, 20));

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

        buttonUpdateTaskCost.setText("Update");
        buttonUpdateTaskCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateTaskCostActionPerformed(evt);
            }
        });
        panelTask.add(buttonUpdateTaskCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 300, -1, 20));
        panelTask.add(textFieldCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 33, -1));

        labelTime1.setText("Update Cost:");
        panelTask.add(labelTime1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, -1, -1));

        add(panelTask, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 550, 320));
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

        sendYardButton.setText("Send Vehicle to Yard");
        sendYardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendYardButtonActionPerformed(evt);
            }
        });
        add(sendYardButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 590, 170, -1));

        yardCheckBox.setText("Confirm Send Vehicle to Yard");
        add(yardCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 590, -1, -1));

        jobCompletedButton.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jobCompletedButton.setText("Job Completed");
        jobCompletedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jobCompletedButtonActionPerformed(evt);
            }
        });
        add(jobCompletedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 660, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchTasksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchTasksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchTasksActionPerformed

    private void buttonSearchPartsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchPartsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchPartsActionPerformed

    private void updateJobButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateJobButtonActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new MainMenu(username);
    }//GEN-LAST:event_updateJobButtonActionPerformed

    private void buttonUpdateTaskTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateTaskTimeActionPerformed
        String sql = "";
        String hours = textFieldTime.getText();
        try{
            if(!hours.equals("")){
                sql = ("update actual_task set actualHours = " + Double.parseDouble(hours) + " "
                        + "where JobjobID = " + jobID + " and TasktaskID = (select taskID from task where description = '" 
                        + listTasksCarriedOut.getSelectedValue() + "')");
            PreparedStatement ps = null;
            try {
            ps = connection.prepareStatement(sql);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            ps.executeUpdate();
            }         
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_buttonUpdateTaskTimeActionPerformed

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

    private void buttonUpdateQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateQuantityActionPerformed
        String selected = listPartsUsed.getSelectedValue();
        String quantity = textFieldQuantity.getText();
        if(!(selected==null) && !(quantity.equals(""))){
            int q = Integer.parseInt(quantity);
            String sql;

            try{
                sql = ("UPDATE Job_Part_Record "
                        + "SET quantity = " + q + " "
                        + "WHERE PartpartID = (select partID from sparepart where vehicleType = "
                        + "(select model from Vehicle where registrationNumber = '" + vehicleReg + "')"
                        + "and partName = '" + selected + "')"
                        + " AND JobjobID = " + jobID);
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } 
                catch (Exception e) {
                    e.printStackTrace();
                }
                ps.executeUpdate();
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }     
            textFieldQuantity.setText("");
        }
    }//GEN-LAST:event_buttonUpdateQuantityActionPerformed

    private void sendYardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendYardButtonActionPerformed
        if (yardCheckBox.isSelected()){
        try{
            String sql = ("update job set BaybayID = null where jobID = " + jobID);
            
            PreparedStatement ps = null;
            try {
            ps = connection.prepareStatement(sql);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
            
            JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
            f.dispose();
            db.closeConnection(connection);
            new MainMenu(username); 
        }
    }//GEN-LAST:event_sendYardButtonActionPerformed

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
        
        //insert the actual tasks for this job
        String sql;
        try{
            sql = ("insert into Actual_Task(JobjobID, TasktaskID, actualHours, actualCost)"
                    + " values (("+jobID+"), "
                    + "(select taskID from Task where description = '" + selected + "'), "
                    + "(select defaultHours from Task where description = '" + selected + "'), "
                    + "(select defaultCost from Task where description = '" + selected + "'))");
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }      
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
        
        //delete this task from the list of actal tasks
        String sql;
        try{
            sql = ("delete from Actual_Task"
                    + " where JobjobID = "+jobID+" "
                    + "and TasktaskID = (select taskID from Task where description = '" + selected + "')");
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }  
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
        
        //insert part into the parts used for this job
        String sql;
        try{
            sql = ("insert into Job_Part_Record(PartpartID, JobjobID, quantity)"
                    + " values ((select partID from sparepart where partName = '" + selected + "'), "
                    + "" + jobID + ", 1)");
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        } 
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
        
        //delete part from job_part_record
        String sql;
        try{
            sql = ("delete from Job_Part_Record"
                    + " where JobjobID = "+jobID+" "
                    + "and PartpartID = (select partID from sparepart where partName = '" + selected + "')");
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }  
    }//GEN-LAST:event_removePartButtonActionPerformed

    private void jobCompletedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jobCompletedButtonActionPerformed
        String sql;
        int initialQuantity = 0;
        int usedQuantity = 0;
        
        for (String s : usedParts){
            
            //get quantity of inital parts
            try{
                sql = ("select * from sparePart where partName = '" + s + "' "
                        + "and vehicleType = (select model from vehicle where registrationNumber = '" + vehicleReg + "')");
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } 
                catch (Exception e) {
                    e.printStackTrace();
                }
                rs = ps.executeQuery();
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }
            
            try{
            while(rs.next())
              {
                // read the result set
                initialQuantity = Integer.parseInt(rs.getString("quantity"));
              } 
            }
            catch(SQLException e){
                System.err.println(e.getMessage());
            }
            
            //get quantity of used parts
            try{
                sql = ("select quantity from job_part_record where PartpartID = "
                        + "(select partID from sparePart where partName = '" + s + "' "
                        + "and vehicleType = (select model from vehicle where registrationNumber = '" + vehicleReg + "'))");
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } 
                catch (Exception e) {
                    e.printStackTrace();
                }
                rs = ps.executeQuery();
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }
            
            try{
            while(rs.next())
              {
                // read the result set
                usedQuantity = Integer.parseInt(rs.getString("quantity"));
              } 
            }
            catch(SQLException e){
                System.err.println(e.getMessage());
            }
        
        //update the quantity in the stock ledger
         try{
             sql = ("UPDATE SparePart "
                     + "SET quantity = " + (initialQuantity - usedQuantity) 
                     + " WHERE partID = (select partID from sparePart where partName = '" + s 
                     + "' and vehicleType = (select model from vehicle where registrationNumber = '" 
                     + vehicleReg + "'))");
             PreparedStatement ps = null;
             try {
             ps = connection.prepareStatement(sql);
             } 
             catch (Exception e) {
                 e.printStackTrace();
             }
             ps.executeUpdate();
         }
         catch(SQLException e)
         {
           System.err.println(e.getMessage());
         }

         //set completion date to today
         try{
             sql = ("update job "
                     + "set dateCompleted = date('now') "
                     + "where jobID = '" +  jobID + "'");
             PreparedStatement ps = null;
             try {
             ps = connection.prepareStatement(sql);
             } 
             catch (Exception e) {
                 e.printStackTrace();
             }
             ps.executeUpdate();
         }
         catch(SQLException e)
         {
           System.err.println(e.getMessage());
         }

         //create an invoice in the database.
         try{
             sql = ("insert into Invoice(dateProduced, JobjobID)"
                     + " values (date('now'), "
                     + "" + jobID + ")");
             PreparedStatement ps = null;
             try {
                 ps = connection.prepareStatement(sql);
             } 
             catch (Exception e) {
                 e.printStackTrace();
             }
             ps.executeUpdate();
         }
         catch(SQLException e)
         {
             System.err.println(e.getMessage());
         } 
        }

        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new MainMenu(username);
    }//GEN-LAST:event_jobCompletedButtonActionPerformed

    private void buttonUpdateTaskCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateTaskCostActionPerformed
        String sql = "";
        String cost = textFieldCost.getText();
        try{
            if(!cost.equals("")){
                sql = ("update actual_task set actualCost = " + Double.parseDouble(cost) + " "
                        + "where JobjobID = " + jobID + " and TasktaskID = (select taskID from task where description = '" 
                        + listTasksCarriedOut.getSelectedValue() + "')");
            PreparedStatement ps = null;
            try {
            ps = connection.prepareStatement(sql);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            ps.executeUpdate();
            }         
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_buttonUpdateTaskCostActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPartButton;
    private javax.swing.JButton addTaskButton;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonSearchParts;
    private javax.swing.JButton buttonSearchTasks;
    private javax.swing.JButton buttonUpdateQuantity;
    private javax.swing.JButton buttonUpdateTaskCost;
    private javax.swing.JButton buttonUpdateTaskTime;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JButton jobCompletedButton;
    private javax.swing.JLabel labelAvailableParts;
    private javax.swing.JLabel labelAvailableTasks;
    private javax.swing.JLabel labelJob;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelPartsUsed;
    private javax.swing.JLabel labelPartsUsedQuantity;
    private javax.swing.JLabel labelTasksCarriedOut;
    private javax.swing.JLabel labelTime;
    private javax.swing.JLabel labelTime1;
    private javax.swing.JLabel lblAvailableParts1;
    private javax.swing.JList<String> listAvailableParts;
    private javax.swing.JList<String> listAvailableTasks;
    private javax.swing.JList<String> listPartsUsed;
    private javax.swing.JList<String> listTasksCarriedOut;
    private javax.swing.JPanel panelPart;
    private javax.swing.JPanel panelTask;
    private javax.swing.JButton removePartButton;
    private javax.swing.JButton removeTaskButton;
    private javax.swing.JButton sendYardButton;
    private javax.swing.JTextArea textAreaJobDetails;
    private javax.swing.JTextField textFieldCost;
    private javax.swing.JTextField textFieldQuantity;
    private javax.swing.JTextField textFieldSearchParts;
    private javax.swing.JTextField textFieldSearchTasks;
    private javax.swing.JTextField textFieldTime;
    private javax.swing.JTextField textFieldUserDetails;
    private javax.swing.JButton updateJobButton;
    private javax.swing.JCheckBox yardCheckBox;
    // End of variables declaration//GEN-END:variables

    void setType(String jobType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
