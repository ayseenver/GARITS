/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject;

/**
 *
 * @author ahmetsesli
 */
public class AllocateJob extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    public AllocateJob() {
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

        labelJobAllocation = new javax.swing.JLabel();
        buttonRemoveFromMechanic = new javax.swing.JButton();
        labelSelectMechanic = new javax.swing.JLabel();
        labelNotAllocatedJobs = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listMechanics = new javax.swing.JList<>();
        labelAllocatedJobs = new javax.swing.JLabel();
        buttonAddToList = new javax.swing.JButton();
        buttonAllocatedToMechanic = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        listAllocatedJobs = new javax.swing.JList<>();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        listNotAllocatedJobs1 = new javax.swing.JList<>();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelJobAllocation.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelJobAllocation.setText("Job Allocation");
        add(labelJobAllocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, -1, -1));

        buttonRemoveFromMechanic.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        buttonRemoveFromMechanic.setText("Remove Job from Mechanic");
        buttonRemoveFromMechanic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveFromMechanicActionPerformed(evt);
            }
        });
        add(buttonRemoveFromMechanic, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 640, -1, -1));

        labelSelectMechanic.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelSelectMechanic.setText("Select Mechanic:");
        add(labelSelectMechanic, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, -1));

        labelNotAllocatedJobs.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelNotAllocatedJobs.setText("Not allocated Jobs:");
        add(labelNotAllocatedJobs, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        listMechanics.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listMechanics.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(listMechanics);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 1160, 120));

        labelAllocatedJobs.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelAllocatedJobs.setText("Allocated Jobs:");
        add(labelAllocatedJobs, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, -1, -1));

        buttonAddToList.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        buttonAddToList.setText("Select Job");
        buttonAddToList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddToListActionPerformed(evt);
            }
        });
        add(buttonAddToList, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 300, -1, -1));

        buttonAllocatedToMechanic.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        buttonAllocatedToMechanic.setText("Allocate To Mechanic");
        buttonAllocatedToMechanic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAllocatedToMechanicActionPerformed(evt);
            }
        });
        add(buttonAllocatedToMechanic, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 470, -1, -1));

        listAllocatedJobs.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listAllocatedJobs.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(listAllocatedJobs);

        add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 520, 1160, 120));

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

        listNotAllocatedJobs1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listNotAllocatedJobs1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane7.setViewportView(listNotAllocatedJobs1);

        add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 1160, 120));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRemoveFromMechanicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveFromMechanicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRemoveFromMechanicActionPerformed

    private void buttonAllocatedToMechanicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAllocatedToMechanicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAllocatedToMechanicActionPerformed

    private void textFieldUserDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserDetailsActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonAddToListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddToListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAddToListActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddToList;
    private javax.swing.JButton buttonAllocatedToMechanic;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonRemoveFromMechanic;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel labelAllocatedJobs;
    private javax.swing.JLabel labelJobAllocation;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelNotAllocatedJobs;
    private javax.swing.JLabel labelSelectMechanic;
    private javax.swing.JList<String> listAllocatedJobs;
    private javax.swing.JList<String> listMechanics;
    private javax.swing.JList<String> listNotAllocatedJobs1;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
