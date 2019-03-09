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
import teamproject.Databases.DB_ImplClass;

/**
 *
 * @author ahmetsesli
 */
public class PartSale extends javax.swing.JPanel {
    private String username;
    Statement statement;
    Connection connection = null;
    DB_ImplClass db = new DB_ImplClass();
    ResultSet rs;
    String[] partArray;
    String[] partOrder;
    ArrayList<String> order = new ArrayList<>();
    int quantity;

    /**
     * Creates new form NewJPanel
     */
    public PartSale(String username) {
        this.username = username;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        
        this.textFieldUserDetails.setText(username);
        connection = db.connect();
        statement = db.getStatement();
        
        ShowAllParts();
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void ShowAllParts(){
        try{
            this.rs = statement.executeQuery("select * from sparepart");
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          System.err.println(e.getMessage());
        }
        
        ArrayList<String> parts = new ArrayList<>();
        
        try{
        while(rs.next())
          {
            // read the result set
            String part = "Part Name: " + rs.getString("partName") + ", Vehicle Type: " + rs.getString("vehicleType");
            parts.add(part);
          } 
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        
        
        partArray = CreateArray(parts);
                
        listStock.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return partArray.length; }
            public String getElementAt(int i) { return partArray[i]; }
        });  
    }
    
    private void AddPart(){
        String selected = listStock.getSelectedValue();
        
        order.add(selected);
        
        partOrder = CreateArray(order);
                
        listCart.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return partOrder.length; }
            public String getElementAt(int i) { return partOrder[i]; }
        });
    }
    
    private void RemovePart(){
        String selected = listCart.getSelectedValue();
        
        order.remove(selected);
        
        partOrder = CreateArray(order);
                
        listCart.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return partOrder.length; }
            public String getElementAt(int i) { return partOrder[i]; }
        });
    }
    
    private String[] CreateArray(ArrayList<String> tasks){
        String[] newArray = new String[tasks.size()];
        newArray = tasks.toArray(newArray);
        return newArray;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelPartSale = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listStock = new javax.swing.JList<>();
        labelAllStock = new javax.swing.JLabel();
        textFieldSearchAllStock = new javax.swing.JTextField();
        buttonProduceInvoice = new javax.swing.JButton();
        labelCart = new javax.swing.JLabel();
        buttonRemove = new javax.swing.JButton();
        buttonAddToCart = new javax.swing.JButton();
        textFieldQuantity = new javax.swing.JTextField();
        buttonChangeQuantity = new javax.swing.JButton();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        listCart = new javax.swing.JList<>();
        buttonSearchAllStock = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelPartSale.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelPartSale.setText("Part Sale");
        add(labelPartSale, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, -1, -1));

        listStock.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane2.setViewportView(listStock);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 1160, 210));

        labelAllStock.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelAllStock.setText("All Stock:");
        add(labelAllStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        textFieldSearchAllStock.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        textFieldSearchAllStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSearchAllStockActionPerformed(evt);
            }
        });
        add(textFieldSearchAllStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 227, 30));

        buttonProduceInvoice.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonProduceInvoice.setText("Produce Invoice");
        buttonProduceInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonProduceInvoiceActionPerformed(evt);
            }
        });
        add(buttonProduceInvoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 620, -1, -1));

        labelCart.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelCart.setText("Cart:");
        add(labelCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, -1, -1));

        buttonRemove.setText("Remove");
        buttonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveActionPerformed(evt);
            }
        });
        add(buttonRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 590, 150, -1));

        buttonAddToCart.setText("Add to Cart");
        buttonAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddToCartActionPerformed(evt);
            }
        });
        add(buttonAddToCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 410, -1, -1));

        textFieldQuantity.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        add(textFieldQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 560, 30, 30));

        buttonChangeQuantity.setText("Change Quantity");
        buttonChangeQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangeQuantityActionPerformed(evt);
            }
        });
        add(buttonChangeQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 560, -1, -1));
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
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

        listCart.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane6.setViewportView(listCart);

        add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 960, 190));

        buttonSearchAllStock.setText("Search");
        buttonSearchAllStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchAllStockActionPerformed(evt);
            }
        });
        add(buttonSearchAllStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldSearchAllStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchAllStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSearchAllStockActionPerformed

    private void buttonProduceInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonProduceInvoiceActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();

        //create a new invoice
        try{
            String sql = ("INSERT INTO Invoice (dateProduced) VALUES (date('now'))");
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        } 
        
        for (String s : order){
            String[] parts = s.split(", ");
            String[] nameParts = parts[0].split(": ");
            String partName = nameParts[1];
            String[] vParts = parts[1].split(": ");
            String vType = vParts[1];
        
            //create a new part invoice for this part
            try{
                String sql = ("INSERT INTO Invoice_SparePart (InvoiceinvoiceNumber, SparePartpartID) "
                        + "VALUES ((select max(invoiceNumber) from invoice), "
                        + "(select partID from sparepart where partName = '" + partName + "' and vehicleType = '" + vType + "'))");
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } 
                catch (Exception e) {
                    e.printStackTrace();
                }
                ps.executeUpdate();
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            } 
        }
        
        db.closeConnection(connection);
        new Invoice(username);
    }//GEN-LAST:event_buttonProduceInvoiceActionPerformed

    private void buttonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveActionPerformed
        RemovePart();
    }//GEN-LAST:event_buttonRemoveActionPerformed

    private void buttonAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddToCartActionPerformed
        AddPart();
    }//GEN-LAST:event_buttonAddToCartActionPerformed

    private void buttonChangeQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeQuantityActionPerformed
        if (!textFieldQuantity.getText().equals("")){
            quantity = Integer.parseInt(textFieldQuantity.getText());
        }
    }//GEN-LAST:event_buttonChangeQuantityActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new StockControl(username);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonSearchAllStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchAllStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchAllStockActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        db.closeConnection(connection);
        System.exit(0);
    }//GEN-LAST:event_buttonExitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddToCart;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonChangeQuantity;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonProduceInvoice;
    private javax.swing.JButton buttonRemove;
    private javax.swing.JButton buttonSearchAllStock;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel labelAllStock;
    private javax.swing.JLabel labelCart;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelPartSale;
    private javax.swing.JList<String> listCart;
    private javax.swing.JList<String> listStock;
    private javax.swing.JTextField textFieldQuantity;
    private javax.swing.JTextField textFieldSearchAllStock;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
