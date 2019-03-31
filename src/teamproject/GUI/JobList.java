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
import javax.swing.JOptionPane;
import teamproject.Databases.DB_ImplClass;

/**
 *
 * @author ahmetsesli
 */
public class JobList extends javax.swing.JPanel {

    private int jobID;
    private String vehicleReg;
    private String username;
    String bayID = "";
    String[] bayArray;
    Statement statement;
    Connection connection = null;
    DB_ImplClass db = new DB_ImplClass();
    ResultSet rs;
    String[] jobArray;

    /**
     * Creates new form NewJPanel
     */
    public JobList(String username) {
        this.username = username;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();

        this.textFieldUserDetails.setText(username);
        connection = db.connect();
        statement = db.getStatement();
        try {
            this.rs = statement.executeQuery("select * from Job where status = '" + comboStatus.getSelectedItem().toString() + "'");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }

        ShowAllJobs();
        buttonAllocateBay.setVisible(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void ShowAllJobs() {

        listJobList.removeAll();
        ArrayList<String> jobs = new ArrayList<>();

        try {
            while (rs.next()) {
                // read the result set
                String job = "ID: " + rs.getString("jobID") + ", Vehicle: " + rs.getString("VehicleregistrationNumber")
                        + ", Job Type: " + rs.getString("type") + ", Booked on: " + rs.getString("dateBookedIn");
                jobs.add(job);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        jobArray = CreateArray(jobs);

        listJobList.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return jobArray.length;
            }

            public String getElementAt(int i) {
                return jobArray[i];
            }
        });
    }

    private void ShowBayList() {

        if (comboStatus.getSelectedItem().toString().equalsIgnoreCase("Completed")) {

            buttonAllocateBay.setVisible(false);
        } else {
            buttonAllocateBay.setVisible(true);
        }
        try {
            String sql = "select type from job where jobID = " + jobID;
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

        String jobType = "";
        try {
            while (rs.next()) {
                jobType = rs.getString("type");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        ArrayList<String> bays = new ArrayList<>();

        if (jobType.equals("MoT")) {
            //get all bays for this job type
            try {
                String sql = ("select * from Bay where type = 'MoT' and booked = 0");
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.rs = ps.executeQuery();
            } catch (SQLException e) {
                // if the error message is "out of memory",
                // it probably means no database file is found
                System.err.println(e.getMessage());
            }
        } else {
            //get all bays for this job type
            try {
                String sql = ("select * from Bay where type = 'Repair' and booked = 0");
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.rs = ps.executeQuery();
            } catch (SQLException e) {
                // if the error message is "out of memory",
                // it probably means no database file is found
                System.err.println(e.getMessage());
            }
        }
        //add bays to bay list
        try {
            while (rs.next()) {
                // read the result set
                String bay = rs.getString("bayID") + ": " + rs.getString("type");
                bays.add(bay);
            }
        } catch (SQLException e) {
        }

        bayArray = CreateArray(bays);

        listAvailableBays.setModel(new javax.swing.AbstractListModel<String>() {

            public int getSize() {
                return bayArray.length;
            }

            public String getElementAt(int i) {
                return bayArray[i];
            }
        }
        );
    }

    private String[] CreateArray(ArrayList<String> tasks) {
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

        buttonSearch = new javax.swing.JButton();
        labelJobList = new javax.swing.JLabel();
        textFieldSearch = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        listJobList = new javax.swing.JList<>();
        textFieldUserDetails = new javax.swing.JTextField();
        lblLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        buttonSelectJob = new javax.swing.JButton();
        comboStatus = new javax.swing.JComboBox<>();
        jScrollPane11 = new javax.swing.JScrollPane();
        listAvailableBays = new javax.swing.JList<>();
        labelAvailableBay = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaJobOverview = new javax.swing.JTextArea();
        buttonAllocateBay = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });
        add(buttonSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, -1, -1));

        labelJobList.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelJobList.setText("Job List");
        add(labelJobList, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, -1, -1));

        textFieldSearch.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        textFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSearchActionPerformed(evt);
            }
        });
        add(textFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 227, 30));

        listJobList.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listJobList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listJobList);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 1160, 230));

        textFieldUserDetails.setEditable(false);
        textFieldUserDetails.setFocusable(false);
        add(textFieldUserDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 220, 30));

        lblLoggedIn.setText("Logged In as:");
        add(lblLoggedIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

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

        buttonSelectJob.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonSelectJob.setText("Show Detail");
        buttonSelectJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelectJobActionPerformed(evt);
            }
        });
        add(buttonSelectJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, -1, -1));

        comboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Created", "Allocated", "Completed" }));
        comboStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboStatusActionPerformed(evt);
            }
        });
        add(comboStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 190, -1, -1));

        listAvailableBays.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane11.setViewportView(listAvailableBays);

        add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 510, 130, 130));

        labelAvailableBay.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelAvailableBay.setText("Bay Available:");
        add(labelAvailableBay, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 490, -1, -1));

        textAreaJobOverview.setColumns(20);
        textAreaJobOverview.setRows(5);
        jScrollPane1.setViewportView(textAreaJobOverview);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, 1010, 130));

        buttonAllocateBay.setText("Allocate to Bay");
        buttonAllocateBay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAllocateBayActionPerformed(evt);
            }
        });
        add(buttonAllocateBay, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 640, -1, -1));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel1.setText("Job Overview:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        try {
            String sql = ("select job.*, vehicle.*, customer.* from job "
                    + "inner join vehicle on vehicle.registrationNumber = job.VehicleregistrationNumber "
                    + "inner join customer on customer.ID = vehicle.CustomerID "
                    + "where registrationNumber like '%" + textFieldSearch.getText() + "%' "
                    + "or name like '%" + textFieldSearch.getText() + "%' and "
                    + "status = '" + comboStatus.getSelectedItem().toString() + "'");
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
        ShowAllJobs();
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void textFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSearchActionPerformed

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

    private void buttonSelectJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelectJobActionPerformed
        ShowBayList();
        String jobDetails = listJobList.getSelectedValue();
        String jobOverview;
        if (jobDetails != null) {
            String[] details = jobDetails.split(", ");

            String[] idParts = details[0].split(": ");
            jobID = Integer.parseInt(idParts[1]);

            String[] regParts = details[1].split(": ");
            vehicleReg = regParts[1];

            try {
                String sql = ("select v.RegistrationNumber, v.make, v.model,  c.name, j.datebookedIn , c.telephoneNumber "
                        + "from customer c, job j, Vehicle v where  v.registrationNumber is (select VehicleRegistrationNumber from Job where jobId = " + jobID
                        + ") and c.id = v.customerid and datebookedin is (select datebookedin from Job where jobID= " + jobID + ")");
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.rs = ps.executeQuery();
                while (rs.next()) {
                    jobOverview = "Vehicle Registraion No: " + rs.getString("registrationNumber") + '\t' + "Date Booked In: " + rs.getString("datebookedin")
                            + '\n' + "Make: " + rs.getString("make") + "\t\t" + "Model: " + rs.getString("model") + '\n'
                            + "Customer Name: " + rs.getString("name") + '\t' + "Tel.: " + rs.getString("telephoneNumber");
                    textAreaJobOverview.setText(jobOverview);
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            String mess = "Select a job";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }

    }//GEN-LAST:event_buttonSelectJobActionPerformed

    private void comboStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboStatusActionPerformed
        buttonAllocateBay.setVisible(false);
        try {
            this.rs = statement.executeQuery("select * from Job where status = '" + comboStatus.getSelectedItem().toString() + "'");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }

        ShowAllJobs();
    }//GEN-LAST:event_comboStatusActionPerformed

    private void buttonAllocateBayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAllocateBayActionPerformed
        //insert the new bayID
        bayID = listAvailableBays.getSelectedValue();
        String message = "Please Select a Job and Bay";
        try {
            if (!(bayID == null)) {
                String[] bayParts = bayID.split(": ");
                bayID = bayParts[0];
                int bayIDInt = Integer.parseInt(bayID);
                String sql = ("update job set baybayID = (select bayID from bay where bayID = " + bayIDInt + ") "
                        + "where jobID = " + jobID);
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ps.executeUpdate();

                //set the bay to booked
                sql = ("update bay set booked = 1 where bayID = " + bayIDInt);
                ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ps.executeUpdate();
                message = "Bay Allocated to Job";
            }
            JOptionPane.showMessageDialog(new JFrame(), message);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }//GEN-LAST:event_buttonAllocateBayActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAllocateBay;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JButton buttonSelectJob;
    private javax.swing.JComboBox<String> comboStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelAvailableBay;
    private javax.swing.JLabel labelJobList;
    private javax.swing.JLabel lblLoggedIn;
    private javax.swing.JList<String> listAvailableBays;
    private javax.swing.JList<String> listJobList;
    private javax.swing.JTextArea textAreaJobOverview;
    private javax.swing.JTextField textFieldSearch;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
