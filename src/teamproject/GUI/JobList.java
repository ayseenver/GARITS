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
public class JobList extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    public JobList() {
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

        buttonSearch = new javax.swing.JButton();
        labelJobList = new javax.swing.JLabel();
        textFieldSearch = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        listJobList = new javax.swing.JList<>();
        buttonCustomerAgreed = new javax.swing.JButton();
        textFieldUserDetails = new javax.swing.JTextField();
        lblLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        labelAvailableBay = new javax.swing.JLabel();
        labelBayType = new javax.swing.JLabel();
        comboBoxBayAvailable = new javax.swing.JComboBox<>();
        comboBoxBayType = new javax.swing.JComboBox<>();
        jButton16 = new javax.swing.JButton();

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

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 1160, 400));

        buttonCustomerAgreed.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonCustomerAgreed.setText("Customer Agreed changes");
        buttonCustomerAgreed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCustomerAgreedActionPerformed(evt);
            }
        });
        add(buttonCustomerAgreed, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 620, -1, -1));

        textFieldUserDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUserDetailsActionPerformed(evt);
            }
        });
        add(textFieldUserDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 220, 30));

        lblLoggedIn.setText("Logged In as:");
        add(lblLoggedIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

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
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, -1));

        labelAvailableBay.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelAvailableBay.setText("Bay Available:");
        add(labelAvailableBay, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 620, -1, -1));

        labelBayType.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelBayType.setText("Bay Type:");
        add(labelBayType, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 620, -1, -1));

        comboBoxBayAvailable.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(comboBoxBayAvailable, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 620, -1, -1));

        comboBoxBayType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(comboBoxBayType, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 620, -1, -1));

        jButton16.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jButton16.setText("Allocate Bay");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 620, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void textFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSearchActionPerformed

    private void buttonCustomerAgreedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCustomerAgreedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCustomerAgreedActionPerformed

    private void textFieldUserDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserDetailsActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        MainMenu mainMenuFranchisee = new MainMenu();
    }//GEN-LAST:event_buttonBackActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonCustomerAgreed;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JComboBox<String> comboBoxBayAvailable;
    private javax.swing.JComboBox<String> comboBoxBayType;
    private javax.swing.JButton jButton16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelAvailableBay;
    private javax.swing.JLabel labelBayType;
    private javax.swing.JLabel labelJobList;
    private javax.swing.JLabel lblLoggedIn;
    private javax.swing.JList<String> listJobList;
    private javax.swing.JTextField textFieldSearch;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
