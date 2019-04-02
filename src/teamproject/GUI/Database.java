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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import teamproject.Databases.DB_ImplClass;

/**
 *
 * @author ahmetsesli
 */
public class Database extends javax.swing.JPanel {

    private String username;
    Statement statement;
    Connection connection = null;
    DB_ImplClass db = new DB_ImplClass();

    /**
     * Creates new form NewJPanel
     */
    public Database(String username) {
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
        buttonBack = new javax.swing.JButton();
        labelLoggedIn = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        buttonBack1 = new javax.swing.JButton();
        labelLoggedIn1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        comboBoxBackup = new javax.swing.JComboBox<>();
        buttonBackup = new javax.swing.JButton();
        buttonRestore = new javax.swing.JButton();
        buttonExit = new javax.swing.JButton();
        labelDatabase = new javax.swing.JLabel();
        textFieldUserDetails = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonBack.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, -1));

        labelLoggedIn.setText("Logged In as:");
        add(labelLoggedIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonBack1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonBack1.setText("Back");
        buttonBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBack1ActionPerformed(evt);
            }
        });
        jPanel1.add(buttonBack1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, -1));

        labelLoggedIn1.setText("Logged In as:");
        jPanel1.add(labelLoggedIn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Automatic Backup Frequency:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 500, -1, 40));

        comboBoxBackup.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        comboBoxBackup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "daily", "weekly", "monthly" }));
        comboBoxBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxBackupActionPerformed(evt);
            }
        });
        jPanel1.add(comboBoxBackup, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 510, -1, 30));

        buttonBackup.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonBackup.setText("Backup Now");
        buttonBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackupActionPerformed(evt);
            }
        });
        jPanel1.add(buttonBackup, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, 200, -1));

        buttonRestore.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonRestore.setText("Restore");
        buttonRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRestoreActionPerformed(evt);
            }
        });
        jPanel1.add(buttonRestore, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 430, 200, -1));

        buttonExit.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonExit.setText("Logout");
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });
        jPanel1.add(buttonExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 0, -1, -1));

        labelDatabase.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelDatabase.setText("Database");
        jPanel1.add(labelDatabase, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, -1, -1));

        textFieldUserDetails.setEditable(false);
        textFieldUserDetails.setFocusable(false);
        jPanel1.add(textFieldUserDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 220, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRestoreActionPerformed
        db.Restore(connection);
    }//GEN-LAST:event_buttonRestoreActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new MainMenu(username);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new LogIn();
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackupActionPerformed
        db.Backup(connection);
        String message = "Database Updated";
        JOptionPane.showMessageDialog(new JFrame(), message);

    }//GEN-LAST:event_buttonBackupActionPerformed

    private void buttonBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBack1ActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new MainMenu(username);
    }//GEN-LAST:event_buttonBack1ActionPerformed

    private void comboBoxBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxBackupActionPerformed
        LocalDate today = LocalDate.now();
        String fileName = "backupFrequency.txt";
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(comboBoxBackup.getSelectedItem().toString() + ", " + today);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        JOptionPane.showMessageDialog(new JFrame(), "Frequency Updated!");
    }//GEN-LAST:event_comboBoxBackupActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonBack1;
    private javax.swing.JButton buttonBackup;
    private javax.swing.JButton buttonExit;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton buttonRestore;
    private javax.swing.JComboBox<String> comboBoxBackup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelDatabase;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelLoggedIn1;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
