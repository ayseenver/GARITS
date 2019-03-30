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
import javax.swing.JOptionPane;
import teamproject.Databases.DB_ImplClass;

/**
 *
 * @author ahmetsesli
 */
public class MyJob extends javax.swing.JPanel {

    private String username;
    ArrayList<String> assignedJobs = new ArrayList<>();
    String[] jobArray;
    ResultSet rs;
    String id;
    String selectedJob;
    Statement statement;
    Connection connection = null;
    DB_ImplClass db = new DB_ImplClass();

    /**
     * Creates new form NewJPanel
     */
    public MyJob(String username) {
        this.username = username;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();

        this.textFieldUserDetails.setText(username);
        connection = db.connect();
        statement = db.getStatement();

        ShowAssignedJobs();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private String[] CreateArray(ArrayList<String> tasks) {
        String[] newArray = new String[tasks.size()];
        newArray = tasks.toArray(newArray);
        return newArray;
    }

    private void ShowAssignedJobs() {
        try {
            String sql = ("select ID from Mechanic where Userusername = '" + username) + "'";
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.rs = ps.executeQuery();
            id = rs.getString("ID");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            String sql = ("select * from Job where MechanicID = '" + id) + "' and dateCompleted is null";
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
                // read the result set
                String job = "Job ID: " + rs.getString("jobID") + ", Vehicle reg: " + rs.getString("VehicleregistrationNumber") + ", Booked in: " + rs.getString("dateBookedIn");
                assignedJobs.add(job);
            }
        } catch (SQLException e) {
        }

        jobArray = CreateArray(assignedJobs);

        listAssignedJobs.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return jobArray.length;
            }

            public String getElementAt(int i) {
                return jobArray[i];
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonSearchJobs = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listAssignedJobs = new javax.swing.JList<>();
        labelMyJobs = new javax.swing.JLabel();
        textFieldSearchJobs = new javax.swing.JTextField();
        labelAssignedJobs = new javax.swing.JLabel();
        buttonViewJob = new javax.swing.JButton();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonSearchJobs.setText("Search");
        buttonSearchJobs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchJobsActionPerformed(evt);
            }
        });
        add(buttonSearchJobs, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, -1, -1));

        listAssignedJobs.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listAssignedJobs.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listAssignedJobs);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 1160, 430));

        labelMyJobs.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelMyJobs.setText("My Jobs");
        add(labelMyJobs, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, -1, -1));

        textFieldSearchJobs.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        textFieldSearchJobs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSearchJobsActionPerformed(evt);
            }
        });
        add(textFieldSearchJobs, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 227, 30));

        labelAssignedJobs.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelAssignedJobs.setText("Assigned Jobs:");
        add(labelAssignedJobs, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        buttonViewJob.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonViewJob.setText("View Job");
        buttonViewJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonViewJobActionPerformed(evt);
            }
        });
        add(buttonViewJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 640, -1, -1));

        textFieldUserDetails.setEditable(false);
        textFieldUserDetails.setFocusable(false);
        textFieldUserDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUserDetailsActionPerformed(evt);
            }
        });
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
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchJobsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchJobsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchJobsActionPerformed

    private void textFieldSearchJobsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchJobsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSearchJobsActionPerformed

    private void buttonViewJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonViewJobActionPerformed
        if (listAssignedJobs.getSelectedValue() != null) {
            selectedJob = listAssignedJobs.getSelectedValue();
            String[] parts = selectedJob.split(", ");

            String[] jobParts = parts[0].split(": ");
            int jobID = Integer.parseInt(jobParts[1]);
            String[] regParts = parts[1].split(": ");
            String vehicleReg = regParts[1];

            JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
            f.dispose();
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            db.closeConnection(connection);
            new Job(username, jobID, vehicleReg);
        } else {
            String mess = "Select a job";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }//GEN-LAST:event_buttonViewJobActionPerformed

    private void textFieldUserDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserDetailsActionPerformed

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
        new MainMenu(username);
    }//GEN-LAST:event_buttonBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonSearchJobs;
    private javax.swing.JButton buttonViewJob;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAssignedJobs;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelMyJobs;
    private javax.swing.JList<String> listAssignedJobs;
    private javax.swing.JTextField textFieldSearchJobs;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
