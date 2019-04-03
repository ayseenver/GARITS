/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.GUI;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import teamproject.Databases.DB_ImplClass;

/**
 *
 * @author ahmetsesli
 */
public class PartOrder extends javax.swing.JPanel {

    private String username;
    String vehicleReg;
    Statement statement;
    Connection connection = null;
    ResultSet rs;
    DB_ImplClass db = new DB_ImplClass();
    ArrayList<String> order = new ArrayList<>();

    /**
     * Creates new form NewJPanel
     */
    public PartOrder(String username, ArrayList<String> order) {
        this.username = username;
        this.order = order;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        this.textFieldUserDetails.setText(username);
        connection = db.connect();
        statement = db.getStatement();

        ShowOrderDetails();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void ShowOrderDetails() {
        for (String s : order) {
            textAreaPartsOrder.append(s + "\n");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelPartsOrder = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaPartsOrder = new javax.swing.JTextArea();
        buttonPrint = new javax.swing.JButton();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonConfirm = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelPartsOrder.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelPartsOrder.setText("Parts Order");
        add(labelPartsOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, -1, -1));

        textAreaPartsOrder.setColumns(20);
        textAreaPartsOrder.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        textAreaPartsOrder.setRows(5);
        jScrollPane1.setViewportView(textAreaPartsOrder);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 1180, 400));

        buttonPrint.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonPrint.setText("Print");
        buttonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrintActionPerformed(evt);
            }
        });
        add(buttonPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 570, -1, -1));

        textFieldUserDetails.setEditable(false);
        textFieldUserDetails.setFocusable(false);
        add(textFieldUserDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 220, 30));

        labelLoggedIn.setText("Logged In as:");
        add(labelLoggedIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        buttonExit.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonExit.setText("Logout");
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });
        add(buttonExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, -1, -1));

        buttonConfirm.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonConfirm.setText("Confirm Order");
        buttonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmActionPerformed(evt);
            }
        });
        add(buttonConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 640, -1, -1));

        buttonBack.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrintActionPerformed
        String fileName = "order-by-" + username + ".txt";
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(textAreaPartsOrder.getText());
            writer.close();
            String mess = "Printed sucessfully";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_buttonPrintActionPerformed

    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();

        //create a order record in the database.
        String sql;
        try {
            sql = ("insert into partOrder(orderNumber, date) values (null, date('now'))");
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        for (String s : order) {
            String[] parts = s.split(", Quantity: ");

            String partName = parts[0]; //Exhaust, complete box, Estate
            String[] nameParts = partName.split(", ");
            String vType;

            if (nameParts.length == 2) {
                partName = nameParts[0];
                vType = nameParts[1];
            } else {
                partName = nameParts[0] + ", " + nameParts[1];
                vType = nameParts[2];
            }

            String[] qParts = parts[1].split(", ");
            int quantity = Integer.parseInt(qParts[0]);

            try {
                sql = ("insert into sparePart_partOrder(SparePartpartID, PartOrderorderNumber, quantity)"
                        + " values ((select partID from sparepart where partName = '" + partName + "' and "
                        + "vehicleType = '" + vType + "'), "
                        + "(select orderNumber from partOrder where orderNumber = (select max(orderNumber) from partOrder))"
                        + ", " + quantity + ")");
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            //update the stock levels
            try {
                sql = ("UPDATE SparePart "
                        + "SET quantity = (select quantity from sparePart where partID in "
                        + "(select sparepartpartid from sparepart_partOrder where partorderordernumber = "
                        + "(select max(partorderordernumber) from sparepart_partorder)) "
                        + "and partName = '" + partName + "' and vehicleType = '" + vType + "') + " + quantity
                        + " WHERE partID = (select partID from sparePart where partID in "
                        + "(select sparepartpartid from sparepart_partOrder where partorderordernumber = "
                        + "(select max(ordernumber) from partorder)) "
                        + "and partName = '" + partName + "' and vehicleType = '" + vType + "')");
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

        }

        db.closeConnection(connection);
        new StockControl(username);
    }//GEN-LAST:event_buttonConfirmActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new LogIn();
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new StockControl(username, order);
    }//GEN-LAST:event_buttonBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonPrint;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelPartsOrder;
    private javax.swing.JTextArea textAreaPartsOrder;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
