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
public class Invoice extends javax.swing.JPanel {
    private String username;

    /**
     * Creates new form NewJPanel
     */
    public Invoice(String username) {
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

        buttonSearchInvoices = new javax.swing.JButton();
        buttonPay = new javax.swing.JButton();
        labelInvoice = new javax.swing.JLabel();
        textFieldAmount = new javax.swing.JTextField();
        labelDetail = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listInvoices = new javax.swing.JList<>();
        buttonView = new javax.swing.JButton();
        buttonPrint = new javax.swing.JButton();
        labelAmount = new javax.swing.JLabel();
        comboxBoxPaymentType = new javax.swing.JComboBox<>();
        labelInvoices = new javax.swing.JLabel();
        labelPaymentType = new javax.swing.JLabel();
        textFieldSearchInvoices = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaInvoiceDetail = new javax.swing.JTextArea();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        buttonPrint1 = new javax.swing.JButton();
        labelCustomerInfomation = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonSearchInvoices.setText("Search");
        buttonSearchInvoices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchInvoicesActionPerformed(evt);
            }
        });
        add(buttonSearchInvoices, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, -1, -1));

        buttonPay.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonPay.setText("Pay");
        buttonPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPayActionPerformed(evt);
            }
        });
        add(buttonPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 630, -1, -1));

        labelInvoice.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelInvoice.setText("Invoices");
        add(labelInvoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));

        textFieldAmount.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        textFieldAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldAmountActionPerformed(evt);
            }
        });
        add(textFieldAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 630, 90, 30));

        labelDetail.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelDetail.setText("Detail:");
        add(labelDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, -1, -1));

        listInvoices.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listInvoices.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listInvoices);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 1160, 140));

        buttonView.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonView.setText("View");
        buttonView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonViewActionPerformed(evt);
            }
        });
        add(buttonView, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 340, -1, -1));

        buttonPrint.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonPrint.setText("Pay Later");
        buttonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrintActionPerformed(evt);
            }
        });
        add(buttonPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 630, -1, -1));

        labelAmount.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        labelAmount.setText("Amount:");
        add(labelAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 640, -1, -1));

        comboxBoxPaymentType.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        comboxBoxPaymentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(comboxBoxPaymentType, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 640, -1, -1));

        labelInvoices.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelInvoices.setText("Invoices:");
        add(labelInvoices, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        labelPaymentType.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        labelPaymentType.setText("Payment Type:");
        add(labelPaymentType, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 640, -1, -1));

        textFieldSearchInvoices.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        textFieldSearchInvoices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSearchInvoicesActionPerformed(evt);
            }
        });
        add(textFieldSearchInvoices, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 227, 30));

        textAreaInvoiceDetail.setColumns(20);
        textAreaInvoiceDetail.setRows(5);
        jScrollPane3.setViewportView(textAreaInvoiceDetail);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 1160, 240));

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
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, -1));

        buttonPrint1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonPrint1.setText("Print Invoice");
        buttonPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrint1ActionPerformed(evt);
            }
        });
        add(buttonPrint1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 630, -1, -1));

        labelCustomerInfomation.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        labelCustomerInfomation.setText("*Pay Later option needs to be configured");
        add(labelCustomerInfomation, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 640, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchInvoicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchInvoicesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchInvoicesActionPerformed

    private void buttonPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonPayActionPerformed

    private void textFieldAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldAmountActionPerformed

    private void buttonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonViewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonViewActionPerformed

    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonPrintActionPerformed

    private void textFieldSearchInvoicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchInvoicesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSearchInvoicesActionPerformed

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

    private void buttonPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrint1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonPrint1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonPay;
    private javax.swing.JButton buttonPrint;
    private javax.swing.JButton buttonPrint1;
    private javax.swing.JButton buttonSearchInvoices;
    private javax.swing.JButton buttonView;
    private javax.swing.JComboBox<String> comboxBoxPaymentType;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelAmount;
    private javax.swing.JLabel labelCustomerInfomation;
    private javax.swing.JLabel labelDetail;
    private javax.swing.JLabel labelInvoice;
    private javax.swing.JLabel labelInvoices;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelPaymentType;
    private javax.swing.JList<String> listInvoices;
    private javax.swing.JTextArea textAreaInvoiceDetail;
    private javax.swing.JTextField textFieldAmount;
    private javax.swing.JTextField textFieldSearchInvoices;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
