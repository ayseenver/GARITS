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
public class ConfirmJob extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    public ConfirmJob() {

        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
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

        buttonEditJob = new javax.swing.JButton();
        labelConfirmDetails = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaAccountHolder = new javax.swing.JTextArea();
        buttonCreateJob = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        listTasksRequired = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaJobDetail = new javax.swing.JTextArea();
        labelTasksRequired = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        textAreaCustomerDetails = new javax.swing.JTextArea();
        labelAccountHolder = new javax.swing.JLabel();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        labelCustomerDetails1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonEditJob.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonEditJob.setText(" Edit Job");
        buttonEditJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditJobActionPerformed(evt);
            }
        });
        add(buttonEditJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 620, 150, -1));

        labelConfirmDetails.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelConfirmDetails.setText("Are the details correct?");
        add(labelConfirmDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        textAreaAccountHolder.setColumns(20);
        textAreaAccountHolder.setRows(5);
        jScrollPane2.setViewportView(textAreaAccountHolder);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 540, 610, 110));

        buttonCreateJob.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonCreateJob.setText("Confirm Job");
        buttonCreateJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCreateJobActionPerformed(evt);
            }
        });
        add(buttonCreateJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 650, -1, -1));

        listTasksRequired.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listTasksRequired.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane11.setViewportView(listTasksRequired);

        add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 540, 220));

        textAreaJobDetail.setColumns(20);
        textAreaJobDetail.setRows(5);
        jScrollPane3.setViewportView(textAreaJobDetail);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 1180, 90));

        labelTasksRequired.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelTasksRequired.setText("Tasks Required:");
        add(labelTasksRequired, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, -1));

        textAreaCustomerDetails.setColumns(20);
        textAreaCustomerDetails.setRows(5);
        jScrollPane4.setViewportView(textAreaCustomerDetails);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 280, 610, 220));

        labelAccountHolder.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelAccountHolder.setText("Account holder Details: ");
        add(labelAccountHolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 520, -1, -1));

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

        labelCustomerDetails1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelCustomerDetails1.setText("Customer and Vehicle Details:");
        add(labelCustomerDetails1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 250, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEditJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditJobActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonEditJobActionPerformed

    private void buttonCreateJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCreateJobActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        MainMenu mainMenu = new MainMenu();
    }//GEN-LAST:event_buttonCreateJobActionPerformed

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
    private javax.swing.JButton buttonCreateJob;
    private javax.swing.JButton buttonEditJob;
    private javax.swing.JButton buttonExit;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelAccountHolder;
    private javax.swing.JLabel labelConfirmDetails;
    private javax.swing.JLabel labelCustomerDetails1;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelTasksRequired;
    private javax.swing.JList<String> listTasksRequired;
    private javax.swing.JTextArea textAreaAccountHolder;
    private javax.swing.JTextArea textAreaCustomerDetails;
    private javax.swing.JTextArea textAreaJobDetail;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
