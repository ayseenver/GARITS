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
public class Report extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    public Report() {
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

        labelReports = new javax.swing.JLabel();
        buttonPrint = new javax.swing.JButton();
        buttonView = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaReport = new javax.swing.JTextArea();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        textFieldUserDetails1 = new javax.swing.JTextField();
        labelLoggedIn1 = new javax.swing.JLabel();
        textFieldTillYear = new javax.swing.JTextField();
        textFieldTillMonth = new javax.swing.JTextField();
        textFieldTillDay = new javax.swing.JTextField();
        labelTill = new javax.swing.JLabel();
        textFieldFromYear = new javax.swing.JTextField();
        textFieldFromMonth = new javax.swing.JTextField();
        textFieldFromDay = new javax.swing.JTextField();
        labelSelectMechanic = new javax.swing.JLabel();
        labelFrom = new javax.swing.JLabel();
        labelPeriod = new javax.swing.JLabel();
        comboBoxMechanic = new javax.swing.JComboBox<>();
        labelSelectType = new javax.swing.JLabel();
        comboBoxReportType = new javax.swing.JComboBox<>();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelReports.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelReports.setText("Reports");
        add(labelReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, -1));

        buttonPrint.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonPrint.setText("Print");
        buttonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrintActionPerformed(evt);
            }
        });
        add(buttonPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 620, -1, -1));

        buttonView.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonView.setText("View");
        buttonView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonViewActionPerformed(evt);
            }
        });
        add(buttonView, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 620, -1, -1));

        textAreaReport.setColumns(20);
        textAreaReport.setRows(5);
        jScrollPane1.setViewportView(textAreaReport);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 1180, 400));

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

        textFieldUserDetails1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUserDetails1ActionPerformed(evt);
            }
        });
        add(textFieldUserDetails1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 220, 30));

        labelLoggedIn1.setText("Logged In as:");
        add(labelLoggedIn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        textFieldTillYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldTillYearActionPerformed(evt);
            }
        });
        add(textFieldTillYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 190, 50, 30));

        textFieldTillMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldTillMonthActionPerformed(evt);
            }
        });
        add(textFieldTillMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 190, 30, 30));

        textFieldTillDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldTillDayActionPerformed(evt);
            }
        });
        add(textFieldTillDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 190, 30, 30));

        labelTill.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelTill.setText("Till");
        add(labelTill, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 200, -1, -1));

        textFieldFromYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldFromYearActionPerformed(evt);
            }
        });
        add(textFieldFromYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 190, 50, 30));

        textFieldFromMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldFromMonthActionPerformed(evt);
            }
        });
        add(textFieldFromMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 190, 30, 30));

        textFieldFromDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldFromDayActionPerformed(evt);
            }
        });
        add(textFieldFromDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 190, 30, 30));

        labelSelectMechanic.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelSelectMechanic.setText("Select Mechanic");
        add(labelSelectMechanic, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, -1, -1));

        labelFrom.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelFrom.setText("From");
        add(labelFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 200, -1, -1));

        labelPeriod.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelPeriod.setText("Period(dd/mm/yyyy):");
        add(labelPeriod, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 160, -1, -1));

        comboBoxMechanic.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        comboBoxMechanic.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(comboBoxMechanic, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 180, -1, 40));

        labelSelectType.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelSelectType.setText("Select Type of Report:");
        add(labelSelectType, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        comboBoxReportType.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        comboBoxReportType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(comboBoxReportType, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, -1, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrintActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_buttonPrintActionPerformed

    private void buttonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonViewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonViewActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonBackActionPerformed

    private void textFieldUserDetails1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserDetails1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserDetails1ActionPerformed

    private void textFieldTillYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldTillYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldTillYearActionPerformed

    private void textFieldTillMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldTillMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldTillMonthActionPerformed

    private void textFieldTillDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldTillDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldTillDayActionPerformed

    private void textFieldFromYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldFromYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldFromYearActionPerformed

    private void textFieldFromMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldFromMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldFromMonthActionPerformed

    private void textFieldFromDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldFromDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldFromDayActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonPrint;
    private javax.swing.JButton buttonView;
    private javax.swing.JComboBox<String> comboBoxMechanic;
    private javax.swing.JComboBox<String> comboBoxReportType;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFrom;
    private javax.swing.JLabel labelLoggedIn1;
    private javax.swing.JLabel labelPeriod;
    private javax.swing.JLabel labelReports;
    private javax.swing.JLabel labelSelectMechanic;
    private javax.swing.JLabel labelSelectType;
    private javax.swing.JLabel labelTill;
    private javax.swing.JTextArea textAreaReport;
    private javax.swing.JTextField textFieldFromDay;
    private javax.swing.JTextField textFieldFromMonth;
    private javax.swing.JTextField textFieldFromYear;
    private javax.swing.JTextField textFieldTillDay;
    private javax.swing.JTextField textFieldTillMonth;
    private javax.swing.JTextField textFieldTillYear;
    private javax.swing.JTextField textFieldUserDetails1;
    // End of variables declaration//GEN-END:variables
}
