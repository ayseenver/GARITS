/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.GUI;

import javax.swing.JFrame;

/**
 *
 * @author ahmetsesli
 */
public class CreateJobTask extends javax.swing.JPanel {
    private String username;

    /**
     * Creates new form NewJPanel
     */
    public CreateJobTask(String username) {
        this.username = username;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        
        this.textFieldUserDetails.setText(username);
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        jButton16 = new javax.swing.JButton();
        labelCreateJob = new javax.swing.JLabel();
        comboBoxBayType = new javax.swing.JComboBox<>();
        jScrollPane11 = new javax.swing.JScrollPane();
        listAvailableBay = new javax.swing.JList<>();
        labelAvailableBay = new javax.swing.JLabel();
        labelBayType = new javax.swing.JLabel();
        labelJobType = new javax.swing.JLabel();
        comboBoxJobType = new javax.swing.JComboBox<>();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        checkBoxYard = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listTasksCarriedOut = new javax.swing.JList<>();
        buttonMoveTaskToCarriedOut = new javax.swing.JButton();
        buttonMoveTaskToAvailable = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        listAvailableTasks = new javax.swing.JList<>();
        labelAvailableTasks = new javax.swing.JLabel();
        textFieldSearchJobs = new javax.swing.JTextField();
        buttonSearchTasks = new javax.swing.JButton();
        labelTasksRequired = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelSelectTasks.setFont(new java.awt.Font("Lucida Grande", 1, 48)); // NOI18N
        labelSelectTasks.setText("Select Tasks");
        add(labelSelectTasks, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, -1));

        jButton16.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jButton16.setText("Create Job");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 620, -1, -1));

        labelCreateJob.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelCreateJob.setText("Create Job");
        add(labelCreateJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));

        comboBoxBayType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(comboBoxBayType, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 600, -1, -1));

        listAvailableBay.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listAvailableBay.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane11.setViewportView(listAvailableBay);

        add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 550, 120, 80));

        labelAvailableBay.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelAvailableBay.setText("Bay Available:");
        add(labelAvailableBay, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 520, -1, -1));

        labelBayType.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelBayType.setText("Bay Type:");
        add(labelBayType, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 580, -1, -1));

        labelJobType.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelJobType.setText("Job Type:");
        add(labelJobType, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 520, -1, -1));

        comboBoxJobType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(comboBoxJobType, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 540, -1, -1));

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
        add(checkBoxYard, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 600, -1, -1));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listTasksCarriedOut.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listTasksCarriedOut.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(listTasksCarriedOut);

        jPanel6.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 76, 230, 220));

        buttonMoveTaskToCarriedOut.setText(">");
        buttonMoveTaskToCarriedOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMoveTaskToCarriedOutActionPerformed(evt);
            }
        });
        jPanel6.add(buttonMoveTaskToCarriedOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 172, 30, -1));

        buttonMoveTaskToAvailable.setText("<");
        buttonMoveTaskToAvailable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMoveTaskToAvailableActionPerformed(evt);
            }
        });
        jPanel6.add(buttonMoveTaskToAvailable, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 132, 30, -1));

        listAvailableTasks.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listAvailableTasks.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
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

        add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, 550, 320));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        new ConfirmJob(username);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void textFieldUserDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserDetailsActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonBackActionPerformed

    private void checkBoxYardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxYardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxYardActionPerformed

    private void buttonMoveTaskToCarriedOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMoveTaskToCarriedOutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonMoveTaskToCarriedOutActionPerformed

    private void buttonMoveTaskToAvailableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMoveTaskToAvailableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonMoveTaskToAvailableActionPerformed

    private void buttonSearchTasksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchTasksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchTasksActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonMoveTaskToAvailable;
    private javax.swing.JButton buttonMoveTaskToCarriedOut;
    private javax.swing.JButton buttonSearchTasks;
    private javax.swing.JCheckBox checkBoxYard;
    private javax.swing.JComboBox<String> comboBoxBayType;
    private javax.swing.JComboBox<String> comboBoxJobType;
    private javax.swing.JButton jButton16;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelAvailableBay;
    private javax.swing.JLabel labelAvailableTasks;
    private javax.swing.JLabel labelBayType;
    private javax.swing.JLabel labelCreateJob;
    private javax.swing.JLabel labelJobType;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelSelectTasks;
    private javax.swing.JLabel labelTasksRequired;
    private javax.swing.JList<String> listAvailableBay;
    private javax.swing.JList<String> listAvailableTasks;
    private javax.swing.JList<String> listTasksCarriedOut;
    private javax.swing.JTextField textFieldSearchJobs;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
