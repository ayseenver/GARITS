/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.GUI;

/**
 *
 * @author ahmetsesli
 */
public class MyJobs extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    public MyJobs() {
        initComponents();
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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 1160, 380));

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
        add(buttonViewJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 590, -1, -1));

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
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchJobsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchJobsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchJobsActionPerformed

    private void textFieldSearchJobsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchJobsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSearchJobsActionPerformed

    private void buttonViewJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonViewJobActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonViewJobActionPerformed

    private void textFieldUserDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserDetailsActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        // TODO add your handling code here:
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
