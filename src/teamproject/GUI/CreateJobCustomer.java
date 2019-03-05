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
public class CreateJobCustomer extends javax.swing.JPanel {
    private String username;
    
    /**
     * Creates new form NewJPanel
     */
    
    public CreateJobCustomer(String username) {
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

        labelCreateJob = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelVehicleSelected = new javax.swing.JLabel();
        buttonNext = new javax.swing.JButton();
        labelPickCustomer = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        listCustomers = new javax.swing.JList<>();
        labelSelectCustomer = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        listVehicle = new javax.swing.JList<>();
        buttonSelectVehicle = new javax.swing.JButton();
        buttonSearchCustomer = new javax.swing.JButton();
        textFieldSearchCustomer = new javax.swing.JTextField();
        buttonFindVehicle = new javax.swing.JButton();
        textFieldVehicleSelected = new javax.swing.JTextField();
        labelSelectVehicle = new javax.swing.JLabel();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        buttonAddNewVehicle = new javax.swing.JButton();
        buttonNewCustomer = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(1280, 720));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelCreateJob.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelCreateJob.setText("Create Job");
        add(labelCreateJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, -1, -1));

        labelVehicleSelected.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelVehicleSelected.setText("Selected Vehicle:");
        add(labelVehicleSelected, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 610, -1, -1));

        buttonNext.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonNext.setText("Next");
        buttonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNextActionPerformed(evt);
            }
        });
        add(buttonNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 660, -1, -1));

        labelPickCustomer.setFont(new java.awt.Font("Lucida Grande", 1, 48)); // NOI18N
        labelPickCustomer.setText("Select Customer");
        add(labelPickCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, -1, -1));

        listCustomers.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listCustomers.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane9.setViewportView(listCustomers);

        add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 1140, 150));

        labelSelectCustomer.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelSelectCustomer.setText("Select Customer:");
        add(labelSelectCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        listVehicle.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listVehicle.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane8.setViewportView(listVehicle);

        add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, 1140, 130));

        buttonSelectVehicle.setText("Select Vehicle");
        buttonSelectVehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelectVehicleActionPerformed(evt);
            }
        });
        add(buttonSelectVehicle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 560, 200, -1));

        buttonSearchCustomer.setText("Search");
        buttonSearchCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchCustomerActionPerformed(evt);
            }
        });
        add(buttonSearchCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, -1, -1));
        add(textFieldSearchCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 150, -1));

        buttonFindVehicle.setText("Find Vehicles");
        buttonFindVehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFindVehicleActionPerformed(evt);
            }
        });
        add(buttonFindVehicle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 190, -1));
        add(textFieldVehicleSelected, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 630, 220, -1));

        labelSelectVehicle.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelSelectVehicle.setText("Select Vehicle:");
        add(labelSelectVehicle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, -1, -1));

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
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, -1));

        buttonAddNewVehicle.setText("Add New Vehicle");
        buttonAddNewVehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddNewVehicleActionPerformed(evt);
            }
        });
        add(buttonAddNewVehicle, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 400, -1, -1));

        buttonNewCustomer.setText("Add New Customer");
        buttonNewCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewCustomerActionPerformed(evt);
            }
        });
        add(buttonNewCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 190, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNextActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        new CreateJobTask(username);
    }//GEN-LAST:event_buttonNextActionPerformed

    private void buttonSelectVehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelectVehicleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSelectVehicleActionPerformed

    private void buttonSearchCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchCustomerActionPerformed

    private void buttonFindVehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFindVehicleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonFindVehicleActionPerformed

    private void textFieldUserDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserDetailsActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        new MainMenu(username);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonAddNewVehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddNewVehicleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAddNewVehicleActionPerformed

    private void buttonNewCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonNewCustomerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddNewVehicle;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonFindVehicle;
    private javax.swing.JButton buttonNewCustomer;
    private javax.swing.JButton buttonNext;
    private javax.swing.JButton buttonSearchCustomer;
    private javax.swing.JButton buttonSelectVehicle;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel labelCreateJob;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelPickCustomer;
    private javax.swing.JLabel labelSelectCustomer;
    private javax.swing.JLabel labelSelectVehicle;
    private javax.swing.JLabel labelVehicleSelected;
    private javax.swing.JList<String> listCustomers;
    private javax.swing.JList<String> listVehicle;
    private javax.swing.JTextField textFieldSearchCustomer;
    private javax.swing.JTextField textFieldUserDetails;
    private javax.swing.JTextField textFieldVehicleSelected;
    // End of variables declaration//GEN-END:variables
}
