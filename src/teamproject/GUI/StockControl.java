/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.GUI;

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
public class StockControl extends javax.swing.JPanel {

    private String username;
    Statement statement;
    Connection connection = null;
    DB_ImplClass db = new DB_ImplClass();
    ResultSet rs;
    ResultSet rsU;
    String[] partArray;
    String[] lowPartArray;
    String[] partOrder;
    ArrayList<String> order = new ArrayList<>();

    /**
     * Creates new form NewJPanel
     */
    public StockControl(String username) {
        this.username = username;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();

        this.textFieldUserDetails.setText(username);
        connection = db.connect();
        statement = db.getStatement();

        try {
            this.rs = statement.executeQuery("select * from sparepart");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }

        ShowAllParts();
        ShowLowParts();
        ShowThreshold();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public StockControl(String username, ArrayList<String> order) {
        this(username);
        this.order = order;
        UpdateOrder();
    }

    private void ShowThreshold() {
        try {
            this.rsU = statement.executeQuery("select roleName from user where username = '" + username + "'");

            String roleName = rsU.getString("roleName");

            if (!roleName.equals("receptionist")) {
                buttonConfigureThreshold.setVisible(true);
                textFieldConfigureThreshold.setVisible(true);
            } else {
                buttonConfigureThreshold.setVisible(false);
                textFieldConfigureThreshold.setVisible(false);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    private void ShowAllParts() {

        ArrayList<String> parts = new ArrayList<>();

        try {
            while (rs.next()) {
                // read the result set
                String part = rs.getString("partName") + ", " + rs.getString("vehicleType") + ", Quantity: " + rs.getString("quantity") + ", Threshold: " + rs.getString("threshold");
                parts.add(part);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        partArray = CreateArray(parts);

        listStock.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return partArray.length;
            }

            public String getElementAt(int i) {
                return partArray[i];
            }
        });
    }

    private void ShowLowParts() {
        try {
            this.rs = statement.executeQuery("select * from sparepart where quantity < threshold");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }

        ArrayList<String> parts = new ArrayList<>();

        try {
            while (rs.next()) {
                // read the result set
                String part = rs.getString("partName") + ", " + rs.getString("vehicleType") + ", Quantity: " + rs.getString("quantity") + ", Threshold: " + rs.getString("threshold");
                parts.add(part);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        lowPartArray = CreateArray(parts);

        listLowStock.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return lowPartArray.length;
            }

            public String getElementAt(int i) {
                return lowPartArray[i];
            }
        });
    }

    private String[] CreateArray(ArrayList<String> tasks) {
        String[] newArray = new String[tasks.size()];
        newArray = tasks.toArray(newArray);
        return newArray;
    }

    private void AddPartAll() {
        String selected = listStock.getSelectedValue();

        String[] parts = selected.split(", Quantity: ");
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

        String partToOrder = partName + ", " + vType + ", Quantity: " + 1;

        order.add(partToOrder);
        UpdateOrder();
    }

    private void AddPartLow() {
        String selected = listLowStock.getSelectedValue();

        String[] parts = selected.split(", Quantity: ");
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

        String partToOrder = partName + ", " + vType + ", Quantity: " + 1;

        order.add(partToOrder);
        UpdateOrder();
    }

    private void RemovePart() {
        String selected = listPartsOrder.getSelectedValue();

        order.remove(selected);
        UpdateOrder();
    }

    private void UpdateOrder() {
        partOrder = CreateArray(order);
        listPartsOrder.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return partOrder.length;
            }

            public String getElementAt(int i) {
                return partOrder[i];
            }
        });
    }

    private boolean IsPartInOrder(String s) {
        String[] parts = s.split(", ");
        String nameAndType = parts[0] + ", " + parts[1];
        for (int i = 0; i < listPartsOrder.getModel().getSize(); i++) {
            String check = listPartsOrder.getModel().getElementAt(i);
            String[] checkParts = check.split(", ");
            check = checkParts[0] + ", " + checkParts[1];
            if (check.equals(nameAndType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStockControl = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listStock = new javax.swing.JList<>();
        labelAllStock = new javax.swing.JLabel();
        textFieldSearchAllStock = new javax.swing.JTextField();
        buttonOrder = new javax.swing.JButton();
        lblPartsOrder = new javax.swing.JLabel();
        buttonRemove = new javax.swing.JButton();
        buttonAllStockOrder = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        listLowStock = new javax.swing.JList<>();
        textFieldQuantity = new javax.swing.JTextField();
        buttonChangeQuantity = new javax.swing.JButton();
        labelLowStock = new javax.swing.JLabel();
        textFieldUserDetails = new javax.swing.JTextField();
        lblLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        buttonLowStockOrder = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        listPartsOrder = new javax.swing.JList<>();
        buttonSearchAllStock = new javax.swing.JButton();
        textFieldConfigureThreshold = new javax.swing.JTextField();
        buttonConfigureThreshold = new javax.swing.JButton();
        buttonStockLevelReport = new javax.swing.JButton();
        buttonPartSale = new javax.swing.JButton();
        buttonStockLevelReport1 = new javax.swing.JButton();
        buttonEditSelectPart = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblStockControl.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        lblStockControl.setText("Stock Control");
        add(lblStockControl, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, -1, -1));

        listStock.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane2.setViewportView(listStock);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 1160, 140));

        labelAllStock.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelAllStock.setText("All Stock:");
        add(labelAllStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        textFieldSearchAllStock.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        textFieldSearchAllStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSearchAllStockActionPerformed(evt);
            }
        });
        add(textFieldSearchAllStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 227, 30));

        buttonOrder.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonOrder.setText("Order Selected Part(s)");
        buttonOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOrderActionPerformed(evt);
            }
        });
        add(buttonOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 650, -1, -1));

        lblPartsOrder.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        lblPartsOrder.setText("Parts Order:");
        add(lblPartsOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 520, -1, -1));

        buttonRemove.setText("Remove");
        buttonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveActionPerformed(evt);
            }
        });
        add(buttonRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 620, 150, -1));

        buttonAllStockOrder.setText("Select to Order");
        buttonAllStockOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAllStockOrderActionPerformed(evt);
            }
        });
        add(buttonAllStockOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 320, -1, -1));

        listLowStock.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane4.setViewportView(listLowStock);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 1160, 140));

        textFieldQuantity.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        add(textFieldQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 590, 30, 30));

        buttonChangeQuantity.setText("Change Quantity");
        buttonChangeQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangeQuantityActionPerformed(evt);
            }
        });
        add(buttonChangeQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 590, -1, -1));

        labelLowStock.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelLowStock.setText("Low/Missing Stock:");
        add(labelLowStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, -1));

        textFieldUserDetails.setEditable(false);
        textFieldUserDetails.setFocusable(false);
        add(textFieldUserDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 220, 30));

        lblLoggedIn.setText("Logged In as:");
        add(lblLoggedIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        buttonExit.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonExit.setText("Logout");
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });
        add(buttonExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, -1, -1));

        buttonBack.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

        buttonLowStockOrder.setText(" Select to Order");
        buttonLowStockOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLowStockOrderActionPerformed(evt);
            }
        });
        add(buttonLowStockOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 510, -1, -1));

        listPartsOrder.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane6.setViewportView(listPartsOrder);

        add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 550, 930, 130));

        buttonSearchAllStock.setText("Search");
        buttonSearchAllStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchAllStockActionPerformed(evt);
            }
        });
        add(buttonSearchAllStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, -1, -1));

        textFieldConfigureThreshold.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        add(textFieldConfigureThreshold, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 320, 30, 30));

        buttonConfigureThreshold.setText("Configure Threshold");
        buttonConfigureThreshold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfigureThresholdActionPerformed(evt);
            }
        });
        add(buttonConfigureThreshold, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 320, -1, -1));

        buttonStockLevelReport.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonStockLevelReport.setText("Stock Level Report");
        buttonStockLevelReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStockLevelReportActionPerformed(evt);
            }
        });
        add(buttonStockLevelReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 140, -1, -1));

        buttonPartSale.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonPartSale.setText("Part Sale");
        buttonPartSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPartSaleActionPerformed(evt);
            }
        });
        add(buttonPartSale, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 140, -1, -1));

        buttonStockLevelReport1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        buttonStockLevelReport1.setText("Edit Spare Parts");
        buttonStockLevelReport1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStockLevelReport1ActionPerformed(evt);
            }
        });
        add(buttonStockLevelReport1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, -1, -1));

        buttonEditSelectPart.setText("Edit Selected Part");
        buttonEditSelectPart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditSelectPartActionPerformed(evt);
            }
        });
        add(buttonEditSelectPart, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 320, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldSearchAllStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchAllStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSearchAllStockActionPerformed

    private void buttonOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOrderActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new PartOrder(username, order);
    }//GEN-LAST:event_buttonOrderActionPerformed

    private void buttonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveActionPerformed
        String selected = listPartsOrder.getSelectedValue();
        if (selected != null) {
            RemovePart();
        } else {
            String mess = "Select a part";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }//GEN-LAST:event_buttonRemoveActionPerformed

    private void buttonAllStockOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAllStockOrderActionPerformed
        String selected = listStock.getSelectedValue();
        if (selected != null) {
            boolean x = IsPartInOrder(selected);
            if (x == false) {
                AddPartAll();
            }
        } else {
            String mess = "Select a part";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }//GEN-LAST:event_buttonAllStockOrderActionPerformed

    private void buttonChangeQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeQuantityActionPerformed
        String quantity = textFieldQuantity.getText();
        String selected = listPartsOrder.getSelectedValue();

        if (selected != null) {
            if (!quantity.isEmpty()) {
                int i = order.indexOf(selected);
                String[] parts = selected.split(", Quantity: ");
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

                selected = partName + ", " + vType + ", Quantity: " + quantity;

                order.set(i, selected);
            }
            UpdateOrder();
        } else {
            String mess = "Select a part";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }//GEN-LAST:event_buttonChangeQuantityActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new MainMenu(username);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonLowStockOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLowStockOrderActionPerformed
        String selected = listLowStock.getSelectedValue();
        if (selected != null) {
            boolean x = IsPartInOrder(selected);
            if (x == false) {
                AddPartLow();
            }
        } else {
            String mess = "Select a part";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }//GEN-LAST:event_buttonLowStockOrderActionPerformed

    private void buttonSearchAllStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchAllStockActionPerformed
        try {
            String sql = ("select * from sparepart where partName LIKE '%"
                    + textFieldSearchAllStock.getText() + "%' or vehicleType LIKE '%"
                    + textFieldSearchAllStock.getText() + "%'");
            PreparedStatement ps = null;

            try {
                ps = connection.prepareStatement(sql);

            } catch (Exception e) {
                e.printStackTrace();

            }
            this.rs = ps.executeQuery();

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        ShowAllParts();
    }//GEN-LAST:event_buttonSearchAllStockActionPerformed

    private void buttonConfigureThresholdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfigureThresholdActionPerformed
        String sql = "";
        String selected = listStock.getSelectedValue();
        if (selected != null) {

            String[] parts = selected.split(", ");
            String partName = parts[0];
            String vType = parts[1];

            String threshold = textFieldConfigureThreshold.getText();
            try {
                if (!threshold.equals("")) {
                    sql = ("update sparepart set threshold = " + Double.parseDouble(threshold) + " "
                            + "where partName = '" + partName + "' and vehicleType = '" + vType + "'");
                    PreparedStatement ps = null;
                    try {
                        ps = connection.prepareStatement(sql);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ps.executeUpdate();
                }
                textFieldConfigureThreshold.setText("");
                ShowLowParts();
                ShowAllParts();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            String mess = "Select a part";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }//GEN-LAST:event_buttonConfigureThresholdActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new LogIn();
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonStockLevelReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStockLevelReportActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new Report(username);
    }//GEN-LAST:event_buttonStockLevelReportActionPerformed

    private void buttonPartSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPartSaleActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new PartSale(username);
    }//GEN-LAST:event_buttonPartSaleActionPerformed

    private void buttonStockLevelReport1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStockLevelReport1ActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new UpdateSparePart(username);
    }//GEN-LAST:event_buttonStockLevelReport1ActionPerformed

    private void buttonEditSelectPartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditSelectPartActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new UpdateSparePart(username);
    }//GEN-LAST:event_buttonEditSelectPartActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAllStockOrder;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonChangeQuantity;
    private javax.swing.JButton buttonConfigureThreshold;
    private javax.swing.JButton buttonEditSelectPart;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonLowStockOrder;
    private javax.swing.JButton buttonOrder;
    private javax.swing.JButton buttonPartSale;
    private javax.swing.JButton buttonRemove;
    private javax.swing.JButton buttonSearchAllStock;
    private javax.swing.JButton buttonStockLevelReport;
    private javax.swing.JButton buttonStockLevelReport1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel labelAllStock;
    private javax.swing.JLabel labelLowStock;
    private javax.swing.JLabel lblLoggedIn;
    private javax.swing.JLabel lblPartsOrder;
    private javax.swing.JLabel lblStockControl;
    private javax.swing.JList<String> listLowStock;
    private javax.swing.JList<String> listPartsOrder;
    private javax.swing.JList<String> listStock;
    private javax.swing.JTextField textFieldConfigureThreshold;
    private javax.swing.JTextField textFieldQuantity;
    private javax.swing.JTextField textFieldSearchAllStock;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
