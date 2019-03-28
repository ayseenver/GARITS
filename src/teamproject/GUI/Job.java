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
    String bayID = "";
    String[] bayArray;
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

        ListActualTasks();
        ListAllTasks();
        GetParts();
        ListAllParts();
        GetActualParts();
        ListUsedParts();
        UpdateBayList();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void GetParts() {
        try {
            String sql = ("select * from sparepart where vehicleType = "
                    + "(select model from Vehicle where registrationNumber = '" + vehicleReg + "') or vehicleType = 'all' "
                    + "or vehicleType = (select make from Vehicle where registrationNumber = '" + vehicleReg + "')"
                    + "and partID not in (select PartpartID from Job_Part_Record where JobjobID = '" + jobID + "')");
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
    }

    private void GetActualParts() {
        //get all actual tasks descriptions for this job
        try {
            String sql = ("SELECT job_part_record.quantity, SparePart.* "
                    + "FROM job_part_record "
                    + "INNER JOIN SparePart ON job_part_record.PartpartID=SparePart.partID "
                    + "where job_part_record.jobjobID = " + jobID);
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
    }

    private void ListAllParts() {
        listAvailableParts.removeAll();
        parts.clear();

        //get all parts not used on this job
        String sql;
        try {
            sql = ("select * from sparepart where partID not in "
                    + "(select partpartID from job_part_record where jobjobID = " + jobID + ")");
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

        //add all parts to available part list
        try {
            while (rs.next()) {
                // read the result set
                String part = rs.getString("partName") + ", Available: " + rs.getString("quantity");
                parts.add(part);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        partArray = CreateArray(parts);

        listAvailableParts.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return partArray.length;
            }

            public String getElementAt(int i) {
                return partArray[i];
            }
        });
    }

    private void ListUsedParts() {
        listPartsUsed.removeAll();
        usedParts.clear();

        //get all parts for this job
        String sql;
        try {
            sql = ("select * from job_part_record "
                    + "inner join sparepart on sparepart.partID = job_part_record.PartpartID "
                    + "where jobjobID = " + jobID);
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

        //add all parts to used part list
        try {
            while (rs.next()) {
                // read the result set
                String part = rs.getString("partName") + ", Quantity: " + rs.getString("quantity");
                usedParts.add(part);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        usedPartArray = CreateArray(usedParts);

        listPartsUsed.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return usedPartArray.length;
            }

            public String getElementAt(int i) {
                return usedPartArray[i];
            }
        });
    }

    private void UpdateUsedParts() {
        usedPartArray = CreateArray(usedParts);
        listPartsUsed.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return usedPartArray.length;
            }

            public String getElementAt(int i) {
                return usedPartArray[i];
            }
        });
    }

    private String[] CreateArray(ArrayList<String> tasks) {
        String[] newArray = new String[tasks.size()];
        newArray = tasks.toArray(newArray);
        return newArray;
    }

    private void UpdateBayList() {
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

    private void ListAllTasks() {
        listAvailableTasks.removeAll();
        tasks.clear();

        try {
            this.rs = statement.executeQuery("select * from Task where taskID not in "
                    + "(select tasktaskID from actual_task where jobjobid = " + jobID + ")");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //add all tasks to task list
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

        listAvailableTasks.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return taskArray.length;
            }

            public String getElementAt(int i) {
                return taskArray[i];
            }
        });
    }

    private void ListActualTasks() {
        listTasksCarriedOut.removeAll();
        actualTasks.clear();

        //get all actual tasks descriptions for this job
        try {
            String sql = ("select * from actual_task inner join task on task.taskID = actual_task.TasktaskID "
                    + "where jobjobId = " + jobID);
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

        //add all actual task descriptions to task list
        try {
            while (rs.next()) {
                // read the result set
                String actualTask = rs.getString("description");
                actualTasks.add(actualTask);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        actualTaskArray = CreateArray(actualTasks);

        listTasksCarriedOut.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return actualTaskArray.length;
            }

            public String getElementAt(int i) {
                return actualTaskArray[i];
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
        jScrollPane11 = new javax.swing.JScrollPane();
        listAvailableBays = new javax.swing.JList<>();
        labelAvailableBay = new javax.swing.JLabel();

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

        listAvailableBays.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane11.setViewportView(listAvailableBays);

        add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 610, 220, 80));

        labelAvailableBay.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelAvailableBay.setText("Bay Available:");
        add(labelAvailableBay, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 580, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchTasksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchTasksActionPerformed
        try {
            String sql = ("select * from Task where description LIKE '%"
                    + textFieldSearchTasks.getText() + "%'");
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

        tasks.clear();
        //add all tasks to task list
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

        listAvailableTasks.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return taskArray.length;
            }

            public String getElementAt(int i) {
                return taskArray[i];
            }
        });
    }//GEN-LAST:event_buttonSearchTasksActionPerformed

    private void buttonSearchPartsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchPartsActionPerformed
        try {
            String sql = ("select * from sparepart where partName LIKE '%"
                    + textFieldSearchParts.getText() + "%' or vehicleType LIKE '%"
                    + textFieldSearchParts.getText() + "%'");
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
        ListAllParts();
    }//GEN-LAST:event_buttonSearchPartsActionPerformed

    private void updateJobButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateJobButtonActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();

        //insert the new bayID
        bayID = listAvailableBays.getSelectedValue();
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
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        db.closeConnection(connection);
        new MainMenu(username);
    }//GEN-LAST:event_updateJobButtonActionPerformed

    private void buttonUpdateTaskTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateTaskTimeActionPerformed
        String sql = "";
        String hours = textFieldTime.getText();
        try {
            if (!hours.equals("")) {
                sql = ("update actual_task set actualHours = " + Double.parseDouble(hours) + " "
                        + "where JobjobID = " + jobID + " and TasktaskID = (select taskID from task where description = '"
                        + listTasksCarriedOut.getSelectedValue() + "')");
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_buttonUpdateTaskTimeActionPerformed

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

    private void buttonUpdateQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateQuantityActionPerformed
        String selected = listPartsUsed.getSelectedValue();
        String quantity = textFieldQuantity.getText();
        if (!(selected == null) && !(quantity.equals(""))) {
            int q = Integer.parseInt(quantity);
            String[] selectedParts = selected.split(", Quantity: ");
            String partName = selectedParts[0];
            int initialQuantity = Integer.parseInt(selectedParts[1]);

            String sql;
            try {
                sql = ("UPDATE Job_Part_Record "
                        + "SET quantity = " + q + " "
                        + "WHERE JobjobID = " + jobID + " "
                        + "and partpartID = (select partID from sparepart where partName = '" + partName + "')");
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

            textFieldQuantity.setText("");
            usedParts.set(usedParts.indexOf(selected), partName + ", Quantity: " + q);

            //update the quantity of this part in the database
            try {
                sql = ("select * from sparepart where partName = '" + partName + "'");
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

            int newQuantity = 0;
            try {
                while (rs.next()) {
                    int partQuantity = Integer.parseInt(rs.getString("quantity"));
                    newQuantity = partQuantity - q + initialQuantity;
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                sql = ("UPDATE sparePart "
                        + "SET quantity = " + newQuantity + " "
                        + "where partName = '" + partName + "'");
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
        }
        UpdateUsedParts();


    }//GEN-LAST:event_buttonUpdateQuantityActionPerformed

    private void sendYardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendYardButtonActionPerformed
        if (yardCheckBox.isSelected()) {
            try {
                String sql = ("update job set BaybayID = null where jobID = " + jobID);

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

            JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
            f.dispose();
            db.closeConnection(connection);
            new MainMenu(username);
        }
    }//GEN-LAST:event_sendYardButtonActionPerformed

    private void addTaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTaskButtonActionPerformed
        String selected = listAvailableTasks.getSelectedValue();

        if (selected != null) {
            //insert the actual tasks for this job
            String sql;
            try {
                sql = ("insert into Actual_Task(JobjobID, TasktaskID, actualHours, actualCost)"
                        + " values ((" + jobID + "), "
                        + "(select taskID from Task where description = '" + selected + "'), "
                        + "(select defaultHours from Task where description = '" + selected + "'), "
                        + "(select defaultCost from Task where description = '" + selected + "'))");
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
            ListAllTasks();
            ListActualTasks();
        }
    }//GEN-LAST:event_addTaskButtonActionPerformed

    private void removeTaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTaskButtonActionPerformed
        String selected = listTasksCarriedOut.getSelectedValue();

        //delete this task from the list of actual tasks
        if (selected != null) {
            String sql;
            try {
                sql = ("delete from Actual_Task"
                        + " where JobjobID = " + jobID + " "
                        + "and TasktaskID = (select taskID from Task where description = '" + selected + "')");
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
            ListAllTasks();
            ListActualTasks();
        }
    }//GEN-LAST:event_removeTaskButtonActionPerformed

    private void addPartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPartButtonActionPerformed
        String selected = listAvailableParts.getSelectedValue();
        if (selected != null) {
            String[] selectedParts = selected.split(", Available: ");
            String partName = selectedParts[0];

            //insert part into the parts used for this job
            String sql;
            try {
                sql = ("insert into Job_Part_Record(PartpartID, JobjobID, quantity)"
                        + " values ((select partID from sparepart where partName = '" + partName + "'), "
                        + "" + jobID + ", 1)");
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

            //update the quantity of this part in the database
            try {
                sql = ("select * from sparepart where partName = '" + partName + "'");
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

            int newQuantity = 0;
            try {
                while (rs.next()) {
                    int partQuantity = Integer.parseInt(rs.getString("quantity"));
                    newQuantity = partQuantity - 1;
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                sql = ("UPDATE sparePart "
                        + "SET quantity = " + newQuantity + " "
                        + "where partName = '" + partName + "'");
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
        }

        ListAllParts();
        ListUsedParts();
    }//GEN-LAST:event_addPartButtonActionPerformed

    private void removePartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removePartButtonActionPerformed
        String selected = listPartsUsed.getSelectedValue();
        if (selected != null) {
            String[] selectedParts = selected.split(", Quantity: ");
            String partName = selectedParts[0];
            int quantity = Integer.parseInt(selectedParts[1]);

            //delete part from job_part_record
            String sql;
            try {
                sql = ("delete from Job_Part_Record"
                        + " where JobjobID = " + jobID + " "
                        + "and PartpartID = (select partID from sparepart where partName = '" + partName + "')");
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

            //update the quantity of this part in the database
            try {
                sql = ("select * from sparepart where partName = '" + partName + "'");
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

            int newQuantity = 0;
            try {
                while (rs.next()) {
                    int partQuantity = Integer.parseInt(rs.getString("quantity"));
                    newQuantity = partQuantity + quantity;
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                sql = ("UPDATE sparePart "
                        + "SET quantity = " + newQuantity + " "
                        + "where partName = '" + partName + "'");
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
        }

        ListAllParts();
        ListUsedParts();
    }//GEN-LAST:event_removePartButtonActionPerformed

    private void jobCompletedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jobCompletedButtonActionPerformed
        String sql;

        try {
            connection.setAutoCommit(false);
            //get actual hours and cost of all the tasks
            double totalCost = 0.0d;
            double totalHours = 0.0d;
            try {
                sql = ("select * from Actual_Task where jobJobID = " + jobID);
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
                    totalHours += Double.parseDouble(rs.getString("actualHours"));
                    totalCost += Double.parseDouble(rs.getString("actualCost"));
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            //get the hourly rate for the mechanic assinged to this job, and multiply
            //that by the total hours spent.
            try {
                sql = ("select hourlyRate from mechanic where ID in (select MechanicID from job where jobID = " + jobID + ")");
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
                    double hourlyRate = Double.parseDouble(rs.getString("hourlyRate"));
                    totalCost *= hourlyRate;
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            //add the actual cost of all the parts
            try {
                sql = ("select (quantity * sellingPrice) from "
                        + "(SELECT job_part_record.quantity, SparePart.* "
                        + "FROM job_part_record INNER JOIN SparePart "
                        + "ON job_part_record.PartpartID=SparePart.partID "
                        + "where job_part_record.jobjobID = " + jobID + ")");
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
                    totalCost += Double.parseDouble(rs.getString("(quantity * sellingPrice)"));
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            //set completion date to today, and update the total hours and total cost
            try {
                sql = ("update job "
                        + "set dateCompleted = date('now'), totalCost = " + totalCost + ", "
                        + "totalHours = " + totalHours + ", status = 'Completed' where jobID = '" + jobID + "'");
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

            //unbook the bay from this job
            try {
                sql = ("select * from bay where bayID in (select baybayID from job where jobID = " + jobID + ")");
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

            String bayID = "";
            try {
                while (rs.next()) {
                    bayID = rs.getString("bayID");
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                if (!(bayID.isEmpty())) { //if the vehicle is in a bay
                    sql = ("update bay set booked = 0 where bayID = " + bayID);
                    PreparedStatement ps = null;
                    try {
                        ps = connection.prepareStatement(sql);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            //create an invoice in the database.
            try {
                sql = ("insert into Invoice(dateProduced, JobjobID, payLater)"
                        + " values (date('now'), "
                        + "" + jobID + ", 0)");
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

            //update the vehicle so if it's a service or MoT, move the due date 1 year back.
            try {
                sql = ("select type from job where jobID = " + jobID);
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

            if (jobType.equals("MoT")) {
                //next MoT date 1 year from now
                try {
                    sql = ("update vehicle set nextMOTDate = DATE('now', '+1 years') "
                            + "where registrationNumber = '" + vehicleReg + "'");
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
            } else if (jobType.equals("Service")) {
                //next service date 1 year from now
                try {
                    sql = ("update vehicle set nextServiceDate = DATE('now', '+1 years') "
                            + "where registrationNumber = '" + vehicleReg + "'");
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
            }

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            try {
                connection.rollback();
                connection.setAutoCommit(true); //just in case it had been set to false earlier.
            } catch (SQLException a) {
                System.err.println(a.getMessage());
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
        try {
            if (!cost.equals("")) {
                sql = ("update actual_task set actualCost = " + Double.parseDouble(cost) + " "
                        + "where JobjobID = " + jobID + " and TasktaskID = (select taskID from task where description = '"
                        + listTasksCarriedOut.getSelectedValue() + "')");
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ps.executeUpdate();
            }
        } catch (SQLException e) {
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
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JButton jobCompletedButton;
    private javax.swing.JLabel labelAvailableBay;
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
    private javax.swing.JList<String> listAvailableBays;
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

}
