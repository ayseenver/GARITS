/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.GUI;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import teamproject.Databases.DB_ImplClass;

/**
 *
 * @author ahmetsesli
 */
public class SetFrequency extends javax.swing.JPanel {

    private String username;
    Statement statement;
    Connection connection = null;
    DB_ImplClass db = new DB_ImplClass();

    /**
     * Creates new form NewJPanel
     */
    public SetFrequency(String username) {
        this.username = username;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();

        this.textFieldUserDetails.setText(username);
        connection = db.connect();
        statement = db.getStatement();

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        labelDatabase = new javax.swing.JLabel();
        buttonBack = new javax.swing.JButton();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        comboBoxBackup = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        comboBoxReport = new javax.swing.JComboBox<>();
        buttonDoneBackup = new javax.swing.JButton();
        buttonDoneReport = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelDatabase.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelDatabase.setText("Configure Frequency");
        add(labelDatabase, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, -1, -1));

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Backups");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, -1, -1));

        comboBoxBackup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "daily", "weekly", "monthly" }));
        add(comboBoxBackup, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 330, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Reports");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 360, -1, -1));

        comboBoxReport.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "daily", "weekly", "monthly" }));
        add(comboBoxReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 390, -1, -1));

        buttonDoneBackup.setText("Done");
        buttonDoneBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDoneBackupActionPerformed(evt);
            }
        });
        add(buttonDoneBackup, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 330, -1, -1));

        buttonDoneReport.setText("Done");
        buttonDoneReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDoneReportActionPerformed(evt);
            }
        });
        add(buttonDoneReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 390, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new MainMenu(username);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void textFieldUserDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserDetailsActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        db.closeConnection(connection);
        System.exit(0);
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonDoneBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDoneBackupActionPerformed
        LocalDate today = LocalDate.now();
        String fileName = "backupFrequency.txt";
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(comboBoxBackup.getSelectedItem().toString() + ", " + today);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        JOptionPane.showMessageDialog(new JFrame(), "Done!");
    }//GEN-LAST:event_buttonDoneBackupActionPerformed

    private void buttonDoneReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDoneReportActionPerformed
        Date today = Calendar.getInstance().getTime();
        String fileName = "reportFrequency.txt";
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(comboBoxReport.getSelectedItem().toString() + "\n" + today);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        JOptionPane.showMessageDialog(new JFrame(), "Done!");
    }//GEN-LAST:event_buttonDoneReportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonDoneBackup;
    private javax.swing.JButton buttonDoneReport;
    private javax.swing.JButton buttonExit;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> comboBoxBackup;
    private javax.swing.JComboBox<String> comboBoxReport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelDatabase;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
