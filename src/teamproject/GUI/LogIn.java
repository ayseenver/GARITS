/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.GUI;

//import teamproject.GUI.MainMenu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import teamproject.AlertsReminders.PaymentAlertTimer;
import teamproject.AlertsReminders.StockAlertTimer;
import teamproject.AlertsReminders.Timer;
import teamproject.Databases.DB_ImplClass;

/**
 *
 * @author ahmetsesli
 */
public class LogIn extends javax.swing.JPanel {

    ResultSet rs;
    Statement statement;
    Connection connection = null;
    DB_ImplClass db = new DB_ImplClass();

    /**
     * Creates new form NewJPanel
     */
    public LogIn() {
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        connection = db.connect();
        statement = db.getStatement();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelUsername = new javax.swing.JLabel();
        labelGarits = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        textFieldUserName = new javax.swing.JTextField();
        buttonSignin = new javax.swing.JButton();
        buttonExit = new javax.swing.JButton();
        textFieldPassword = new javax.swing.JPasswordField();

        labelUsername.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelUsername.setText("Username:");

        labelGarits.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelGarits.setText("GARITS");

        labelPassword.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelPassword.setText("Password:");

        textFieldUserName.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        buttonSignin.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonSignin.setText("Sign In");
        buttonSignin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSigninActionPerformed(evt);
            }
        });

        buttonExit.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonExit.setText("Exit");
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelUsername))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSignin)
                    .addComponent(textFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 73, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelGarits)
                        .addGap(107, 107, 107))
                    .addComponent(buttonExit, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(buttonExit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelGarits)
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUsername)
                    .addComponent(textFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textFieldPassword)
                    .addComponent(labelPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addComponent(buttonSignin)
                .addContainerGap(99, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSigninActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSigninActionPerformed
        String username = this.textFieldUserName.getText().trim();
        String password = new String(this.textFieldPassword.getPassword());
        try {
            this.rs = statement.executeQuery("select * from User where deleted = 0");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }

        try {
            String user = "";
            String pass = "";
            while (rs.next()) {
                // read the result set
                user = rs.getString("username");
                pass = rs.getString("password");
                if (username.equals(user) && password.equals(pass)) {
                    if (rs.getString("roleName").equals("franchisee")) {
                        StockAlertTimer at = new StockAlertTimer();
                        PaymentAlertTimer pt = new PaymentAlertTimer();
                    }else if (rs.getString("roleName").equals("receptionist")) {
                        StockAlertTimer at = new StockAlertTimer();
                    }
                    JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
                    f.dispose();
                    db.closeConnection(connection);
                    Timer t = new Timer();
                    new MainMenu(username);
                }
            }
            String mess = "Incorrect details";
            JOptionPane.showMessageDialog(new JFrame(), mess);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_buttonSigninActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_buttonExitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonSignin;
    private javax.swing.JLabel labelGarits;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelUsername;
    private javax.swing.JPasswordField textFieldPassword;
    private javax.swing.JTextField textFieldUserName;
    // End of variables declaration//GEN-END:variables
}
