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
public class Database extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    public Database() {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        labelDatabase = new javax.swing.JLabel();
        buttonTransferData = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonTransferData1 = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelDatabase.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelDatabase.setText("Database");
        add(labelDatabase, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

        buttonTransferData.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonTransferData.setText("Restore");
        buttonTransferData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTransferDataActionPerformed(evt);
            }
        });
        add(buttonTransferData, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, 200, -1));

        buttonBack.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, -1));

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

        buttonTransferData1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonTransferData1.setText("Backup");
        buttonTransferData1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTransferData1ActionPerformed(evt);
            }
        });
        add(buttonTransferData1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, 200, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonTransferDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTransferDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonTransferDataActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonBackActionPerformed

    private void textFieldUserDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserDetailsActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonTransferData1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTransferData1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonTransferData1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonExit;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton buttonTransferData;
    private javax.swing.JButton buttonTransferData1;
    private javax.swing.JLabel labelDatabase;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
