/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.GUI;

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
public class AllocateJob extends javax.swing.JPanel {
    private String username;
    Statement statement;
    Connection connection = null;
    DB_ImplClass db = new DB_ImplClass();
    ResultSet rs;
    String[] jobArray;
    String[] mechArray;
    int jobID;
    
    /**
     * Creates new form NewJPanel
     */
    public AllocateJob(String username) {
        this.username = username;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        
        this.textFieldUserDetails.setText(username);
        connection = db.connect();
        statement = db.getStatement();
        
        ShowUnallocatedJobs();
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void ShowUnallocatedJobs(){
        try{
            this.rs = statement.executeQuery("select * from Job where dateCompleted is null and MechanicID is null");
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          System.err.println(e.getMessage());
        }
        
        listUnallocatedJobs.removeAll();
        ArrayList<String> jobs = new ArrayList<>();
        
        try{
        while(rs.next())
          {
            // read the result set
            String job = "ID: " + rs.getString("jobID") + ", Vehicle: " + rs.getString("VehicleregistrationNumber")
                    + ", Booked on: " + rs.getString("dateBookedIn");
            jobs.add(job);
          } 
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        
        
        jobArray = CreateArray(jobs);
                
        listUnallocatedJobs.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return jobArray.length; }
            public String getElementAt(int i) { return jobArray[i]; }
        });  
    }
    
    private void ShowMechanics(){
        try{
            this.rs = statement.executeQuery("select * from user where username in (select Userusername from mechanic)");
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          System.err.println(e.getMessage());
        }
        
        listMechanics.removeAll();
        ArrayList<String> mechs = new ArrayList<>();
        
        try{
        while(rs.next())
          {
            // read the result set
            String mech = rs.getString("firstName") + " " + rs.getString("surname");
            mechs.add(mech);
          } 
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        
        
        mechArray = CreateArray(mechs);
                
        listMechanics.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return mechArray.length; }
            public String getElementAt(int i) { return mechArray[i]; }
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

        labelJobAllocation = new javax.swing.JLabel();
        labelSelectMechanic = new javax.swing.JLabel();
        labelNotAllocatedJobs = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listMechanics = new javax.swing.JList<>();
        buttonSelectJob = new javax.swing.JButton();
        buttonAllocateToMechanic = new javax.swing.JButton();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        listUnallocatedJobs = new javax.swing.JList<>();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelJobAllocation.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelJobAllocation.setText("Job Allocation");
        add(labelJobAllocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, -1, -1));

        labelSelectMechanic.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelSelectMechanic.setText("Select Mechanic:");
        add(labelSelectMechanic, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, -1, -1));

        labelNotAllocatedJobs.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelNotAllocatedJobs.setText("Unallocated Jobs:");
        add(labelNotAllocatedJobs, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        listMechanics.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane4.setViewportView(listMechanics);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 1160, 190));

        buttonSelectJob.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        buttonSelectJob.setText("Select Job");
        buttonSelectJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelectJobActionPerformed(evt);
            }
        });
        add(buttonSelectJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 400, -1, -1));

        buttonAllocateToMechanic.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        buttonAllocateToMechanic.setText("Allocate To Mechanic");
        buttonAllocateToMechanic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAllocateToMechanicActionPerformed(evt);
            }
        });
        add(buttonAllocateToMechanic, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 630, -1, -1));

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

        listUnallocatedJobs.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane7.setViewportView(listUnallocatedJobs);

        add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 1160, 210));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAllocateToMechanicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAllocateToMechanicActionPerformed
        //get the selected mechanic's first and last name
        String[] parts = listMechanics.getSelectedValue().split(" ");
        String firstName = parts[0];
        String lastName = parts[1];
        //update job with this mechanic's ID
        try{
            String sql = ("update job "
                    + "set MechanicID = (select ID from mechanic where Userusername = (select username from user where firstname = '" + firstName + "' "
                    + "and surname = '" + lastName + "')) "
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
        
        //go to main menu
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new MainMenu(username);
    }//GEN-LAST:event_buttonAllocateToMechanicActionPerformed

    private void textFieldUserDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserDetailsActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        db.closeConnection(connection);
        System.exit(0);
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        // TODO add your handling code here:
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new MainMenu(username);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonSelectJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelectJobActionPerformed
        if (listUnallocatedJobs.getSelectedValue() != null){
            String job = listUnallocatedJobs.getSelectedValue();
            String[] jobParts = job.split(", ");
            String[] idParts = jobParts[0].split(": ");
            jobID = Integer.parseInt(idParts[1]);
            System.out.println("ID: " + jobID);
            
            ShowMechanics();
        }
    }//GEN-LAST:event_buttonSelectJobActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAllocateToMechanic;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonSelectJob;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel labelJobAllocation;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelNotAllocatedJobs;
    private javax.swing.JLabel labelSelectMechanic;
    private javax.swing.JList<String> listMechanics;
    private javax.swing.JList<String> listUnallocatedJobs;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
