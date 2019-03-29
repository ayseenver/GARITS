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
import teamproject.Customer_Account.Customer;
import teamproject.Customer_Account.Vehicle;
import teamproject.Databases.DB_ImplClass;

/**
 *
 * @author ahmetsesli
 */
public class TaskList extends javax.swing.JPanel {

    private String username;
    private ResultSet rs;
    String[] taskArray;
    String[] defaultTaskArray;
    ArrayList<String> defaultTasks = new ArrayList<>();
    ArrayList<String> tasks = new ArrayList<>();
    String jobType;
    Job job;
    Vehicle v;
    Customer c;
    Statement statement;
    Connection connection = null;
    DB_ImplClass db = new DB_ImplClass();

    /**
     * Creates new form NewJPanel
     */
    public TaskList(String username) {
        this.username = username;
        this.jobType = "defaultServiceJob";
        this.v = v;
        this.c = c;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();

        this.textFieldUserDetails.setText(username);
        connection = db.connect();
        statement = db.getStatement();

        GetJobType();
        ShowAllTasks();
        ShowDefaultTasks();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private String[] CreateArray(ArrayList<String> tasks) {
        String[] newArray = new String[tasks.size()];
        newArray = tasks.toArray(newArray);
        return newArray;
    }

    private void GetJobType() {
        jobType = jobTypeCombo.getSelectedItem().toString();
        if (jobType.equalsIgnoreCase("Service")) {
            jobType = "defaultServiceJob";
        } else {
            jobType = "defaultMoTJob";
        }
    }

    private void ShowAllTasks() {
        try {
            String sql = ("select * from task where " + jobType + " = 0");
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

        listAllTasks.removeAll();
        tasks.clear();

        //add all parts to part list
        try {
            while (rs.next()) {
                // read the result set
                String task = rs.getString("description");
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        taskArray = CreateArray(tasks);

        listAllTasks.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return taskArray.length;
            }

            public String getElementAt(int i) {
                return taskArray[i];
            }
        });
    }

    private void ShowDefaultTasks() {
        try {
            String sql = ("select * from task where " + jobType + " = 1");
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

        listDefaultTasks.removeAll();
        defaultTasks.clear();

        //add all tasks to task list
        try {
            while (rs.next()) {
                // read the result set
                String task = rs.getString("description");
                defaultTasks.add(task);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        defaultTaskArray = CreateArray(defaultTasks);

        listDefaultTasks.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return defaultTaskArray.length;
            }

            public String getElementAt(int i) {
                return defaultTaskArray[i];
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

        doneButton = new javax.swing.JButton();
        labelCreateJob = new javax.swing.JLabel();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonLogout = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listDefaultTasks = new javax.swing.JList<>();
        jScrollPane10 = new javax.swing.JScrollPane();
        listAllTasks = new javax.swing.JList<>();
        labelAvailableTasks = new javax.swing.JLabel();
        textFieldSearchJobs = new javax.swing.JTextField();
        buttonSearchTasks = new javax.swing.JButton();
        labelTasksRequired = new javax.swing.JLabel();
        removeTask = new javax.swing.JButton();
        addTask = new javax.swing.JButton();
        jobTypeCombo = new javax.swing.JComboBox<>();
        labelJobType = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        doneButton.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        doneButton.setText("Done");
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });
        add(doneButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 620, -1, -1));

        labelCreateJob.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelCreateJob.setText("Task List");
        add(labelCreateJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, -1, -1));

        textFieldUserDetails.setEditable(false);
        textFieldUserDetails.setFocusable(false);
        add(textFieldUserDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 220, 30));

        labelLoggedIn.setText("Logged In as:");
        add(labelLoggedIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        buttonLogout.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonLogout.setText("Logout");
        buttonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogoutActionPerformed(evt);
            }
        });
        add(buttonLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, -1, -1));

        buttonBack.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listDefaultTasks.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane5.setViewportView(listDefaultTasks);

        jPanel6.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 230, 220));

        listAllTasks.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane10.setViewportView(listAllTasks);

        jPanel6.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 79, 230, 220));

        labelAvailableTasks.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelAvailableTasks.setText("All Tasks:");
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
        labelTasksRequired.setText("Default Tasks:");
        jPanel6.add(labelTasksRequired, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        removeTask.setText("<");
        removeTask.setActionCommand("addTask");
        removeTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeTaskActionPerformed(evt);
            }
        });
        jPanel6.add(removeTask, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, -1, -1));

        addTask.setText(">");
        addTask.setActionCommand("addTask");
        addTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTaskActionPerformed(evt);
            }
        });
        jPanel6.add(addTask, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, -1, -1));

        jobTypeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Service", "MoT" }));
        jobTypeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jobTypeComboActionPerformed(evt);
            }
        });
        jPanel6.add(jobTypeCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, -1, -1));

        labelJobType.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelJobType.setText("Job Type:");
        jPanel6.add(labelJobType, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, -1, -1));

        add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 580, 320));
    }// </editor-fold>//GEN-END:initComponents

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new MainMenu(username);
    }//GEN-LAST:event_doneButtonActionPerformed

    private void buttonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogoutActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new LogIn();
    }//GEN-LAST:event_buttonLogoutActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new MainMenu(username);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonSearchTasksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchTasksActionPerformed
        try {
            String sql = ("select * from Task where description LIKE '%"
                    + textFieldSearchJobs.getText() + "%'");
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

        listAllTasks.removeAll();
        tasks.clear();

        //add all parts to part list
        try {
            while (rs.next()) {
                // read the result set
                String task = rs.getString("description");
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        taskArray = CreateArray(tasks);

        listAllTasks.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return taskArray.length;
            }

            public String getElementAt(int i) {
                return taskArray[i];
            }
        });
    }//GEN-LAST:event_buttonSearchTasksActionPerformed

    private void jobTypeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jobTypeComboActionPerformed
        GetJobType();
        ShowAllTasks();
        ShowDefaultTasks();
    }//GEN-LAST:event_jobTypeComboActionPerformed

    private void removeTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTaskActionPerformed
        String selected = listDefaultTasks.getSelectedValue();

        if (!(selected == null)) {
            try {
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement("Update task set " + jobType + " = 0 where description = '" + selected + "'");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            ShowAllTasks();
            ShowDefaultTasks();
        } else {
            String mess = "Select a task to remove";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }//GEN-LAST:event_removeTaskActionPerformed

    private void addTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTaskActionPerformed
        String selected = listAllTasks.getSelectedValue();
        if (!(selected == null)) {
            try {
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement("Update task set " + jobType + " = 1 where description = '" + selected + "'");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            ShowAllTasks();
            ShowDefaultTasks();
        } else {
            String mess = "Select a task to add";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }//GEN-LAST:event_addTaskActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTask;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonLogout;
    private javax.swing.JButton buttonSearchTasks;
    private javax.swing.JButton doneButton;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JComboBox<String> jobTypeCombo;
    private javax.swing.JLabel labelAvailableTasks;
    private javax.swing.JLabel labelCreateJob;
    private javax.swing.JLabel labelJobType;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelTasksRequired;
    private javax.swing.JList<String> listAllTasks;
    private javax.swing.JList<String> listDefaultTasks;
    private javax.swing.JButton removeTask;
    private javax.swing.JTextField textFieldSearchJobs;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
