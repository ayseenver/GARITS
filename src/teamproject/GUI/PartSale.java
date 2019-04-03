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
public class PartSale extends javax.swing.JPanel {

    private String username;
    String id;
    Statement statement;
    Connection connection = null;
    DB_ImplClass db = new DB_ImplClass();
    ResultSet rs;
    ResultSet rsC;
    private ResultSet rsD;
    String[] partArray;
    String[] partOrder;
    String[] nameArray;
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
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

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
        try {
            this.rsC = statement.executeQuery("select * from Customer where deleted = 0");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        ShowCustomers();
        ShowAllParts();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void ShowCustomers() {
        listCustomers.removeAll();
        ArrayList<String> names = new ArrayList<>();

        try {
            while (rsC.next()) {
                // read the result set
                String name = "ID: " + rsC.getString("ID") + ", " + rsC.getString("name");
                names.add(name);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        nameArray = CreateArray(names);

        listCustomers.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return nameArray.length;
            }

            public String getElementAt(int i) {
                return nameArray[i];
            }
        });
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

    private void AddPart() {
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

    private void UpdateOrder() {
        partOrder = CreateArray(order);
        listCart.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return partOrder.length;
            }

            public String getElementAt(int i) {
                return partOrder[i];
            }
        });
    }

    private void RemovePart(String selected) {
        order.remove(selected);

        partOrder = CreateArray(order);

        listCart.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return partOrder.length;
            }

            public String getElementAt(int i) {
                return partOrder[i];
            }
        });
    }

    private String[] CreateArray(ArrayList<String> tasks) {
        String[] newArray = new String[tasks.size()];
        newArray = tasks.toArray(newArray);
        return newArray;
    }

    private boolean IsPartInOrder(String s) {
        String[] parts = s.split(", ");
        String nameAndType = parts[0] + ", " + parts[1];
        for (int i = 0; i < listCart.getModel().getSize(); i++) {
            String check = listCart.getModel().getElementAt(i);
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

        labelPartSale = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listStock = new javax.swing.JList<>();
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
        labelAllStock1 = new javax.swing.JLabel();
        textFieldSelectedCustomer = new javax.swing.JTextField();
        labelVehicleSelected = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelPartSale1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listStock1 = new javax.swing.JList<>();
        textFieldSearchCustomers = new javax.swing.JTextField();
        buttonProduceInvoice1 = new javax.swing.JButton();
        labelCart1 = new javax.swing.JLabel();
        buttonRemove1 = new javax.swing.JButton();
        buttonAddToCart1 = new javax.swing.JButton();
        textFieldQuantity1 = new javax.swing.JTextField();
        buttonChangeQuantity1 = new javax.swing.JButton();
        textFieldUserDetails1 = new javax.swing.JTextField();
        labelLoggedIn1 = new javax.swing.JLabel();
        buttonExit1 = new javax.swing.JButton();
        buttonBack1 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        listCart1 = new javax.swing.JList<>();
        buttonSearchCustomers = new javax.swing.JButton();
        textFieldSelectedCustomer1 = new javax.swing.JTextField();
        labelVehicleSelected1 = new javax.swing.JLabel();
        textFieldSearchAllStock = new javax.swing.JTextField();
        buttonSearchAllStock = new javax.swing.JButton();
        labelAllStock = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        listCustomers = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelPartSale.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelPartSale.setText("Part Sale");
        add(labelPartSale, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, -1, -1));

        listStock.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane2.setViewportView(listStock);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 1150, 140));

        buttonProduceInvoice.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonProduceInvoice.setText("Produce Invoice");
        buttonProduceInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonProduceInvoiceActionPerformed(evt);
            }
        });
        add(buttonProduceInvoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 650, -1, -1));

        labelCart.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelCart.setText("Cart:");
        add(labelCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 510, -1, -1));

        buttonRemove.setText("Remove");
        buttonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveActionPerformed(evt);
            }
        });
        add(buttonRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 620, 150, -1));

        buttonAddToCart.setText("Add to Cart");
        buttonAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddToCartActionPerformed(evt);
            }
        });
        add(buttonAddToCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 510, -1, -1));

        textFieldQuantity.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        textFieldQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldQuantityActionPerformed(evt);
            }
        });
        add(textFieldQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 590, 30, 30));

        buttonChangeQuantity.setText("Change Quantity");
        buttonChangeQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangeQuantityActionPerformed(evt);
            }
        });
        add(buttonChangeQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 590, -1, -1));

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

        add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 540, 970, 140));

        labelAllStock1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelAllStock1.setText("Stock:");
        add(labelAllStock1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, -1, -1));

        textFieldSelectedCustomer.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        add(textFieldSelectedCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 310, 180, -1));

        labelVehicleSelected.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelVehicleSelected.setText("Selected Customer:");
        add(labelVehicleSelected, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 310, -1, -1));

        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelPartSale1.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelPartSale1.setText("Part Sale");
        jPanel1.add(labelPartSale1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, -1, -1));

        listStock1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane3.setViewportView(listStock1);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 1150, 140));

        textFieldSearchCustomers.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        textFieldSearchCustomers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSearchCustomersActionPerformed(evt);
            }
        });
        jPanel1.add(textFieldSearchCustomers, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 200, 30));

        buttonProduceInvoice1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonProduceInvoice1.setText("Produce Invoice");
        buttonProduceInvoice1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonProduceInvoice1ActionPerformed(evt);
            }
        });
        jPanel1.add(buttonProduceInvoice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 650, -1, -1));

        labelCart1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelCart1.setText("Cart:");
        jPanel1.add(labelCart1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 510, -1, -1));

        buttonRemove1.setText("Remove");
        buttonRemove1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemove1ActionPerformed(evt);
            }
        });
        jPanel1.add(buttonRemove1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 620, 150, -1));

        buttonAddToCart1.setText("Add to Cart");
        buttonAddToCart1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddToCart1ActionPerformed(evt);
            }
        });
        jPanel1.add(buttonAddToCart1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 510, -1, -1));

        textFieldQuantity1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        textFieldQuantity1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldQuantity1ActionPerformed(evt);
            }
        });
        jPanel1.add(textFieldQuantity1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 590, 30, 30));

        buttonChangeQuantity1.setText("Change Quantity");
        buttonChangeQuantity1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangeQuantity1ActionPerformed(evt);
            }
        });
        jPanel1.add(buttonChangeQuantity1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 590, -1, -1));

        textFieldUserDetails1.setEditable(false);
        textFieldUserDetails1.setFocusable(false);
        jPanel1.add(textFieldUserDetails1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 220, 30));

        labelLoggedIn1.setText("Logged In as:");
        jPanel1.add(labelLoggedIn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        buttonExit1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonExit1.setText("Logout");
        buttonExit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExit1ActionPerformed(evt);
            }
        });
        jPanel1.add(buttonExit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, -1, -1));

        buttonBack1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonBack1.setText("Back");
        buttonBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBack1ActionPerformed(evt);
            }
        });
        jPanel1.add(buttonBack1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

        listCart1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane8.setViewportView(listCart1);

        jPanel1.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 540, 970, 140));

        buttonSearchCustomers.setText("Search");
        buttonSearchCustomers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchCustomersActionPerformed(evt);
            }
        });
        jPanel1.add(buttonSearchCustomers, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 80, -1));

        textFieldSelectedCustomer1.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        jPanel1.add(textFieldSelectedCustomer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 310, 180, -1));

        labelVehicleSelected1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelVehicleSelected1.setText("Selected Customer:");
        jPanel1.add(labelVehicleSelected1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 310, -1, -1));

        textFieldSearchAllStock.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        textFieldSearchAllStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSearchAllStockActionPerformed(evt);
            }
        });
        jPanel1.add(textFieldSearchAllStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 200, 30));

        buttonSearchAllStock.setText("Search");
        buttonSearchAllStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchAllStockActionPerformed(evt);
            }
        });
        jPanel1.add(buttonSearchAllStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 80, -1));

        labelAllStock.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelAllStock.setText("Customers:");
        jPanel1.add(labelAllStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));

        listCustomers.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listCustomers.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listCustomersValueChanged(evt);
            }
        });
        jScrollPane7.setViewportView(listCustomers);

        jPanel1.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 1150, 140));

        jButton1.setText("Add New Customer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 140, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldSearchAllStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchAllStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSearchAllStockActionPerformed

    private void buttonProduceInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonProduceInvoiceActionPerformed
        String sql;
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();

        //create a new invoice
        try {
            sql = ("INSERT INTO Invoice (dateProduced, paylater) VALUES (date('now'), 0)");
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
            quantity = Integer.parseInt(parts[1]);

            //create a new part invoice for this part
            try {
                sql = ("INSERT INTO Invoice_SparePart (InvoiceinvoiceNumber, SparePartpartID, quantity) "
                        + "VALUES ((select max(invoiceNumber) from invoice), "
                        + "(select partID from sparepart where partName = '" + partName + "' and vehicleType = '" + vType + "'), "
                        + "" + quantity + ")");
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
                        + "(select sparepartpartid from invoice_sparepart where InvoiceinvoiceNumber = "
                        + "(select max(invoiceInvoiceNumber) from invoice_sparepart)) "
                        + "and partName = '" + partName + "' and vehicleType = '" + vType + "') - " + quantity
                        + " WHERE partID = (select partID from sparePart where partID in "
                        + "(select sparepartpartid from invoice_sparepart where InvoiceinvoiceNumber = "
                        + "(select max(invoiceInvoiceNumber) from invoice_sparepart)) "
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
        new Invoice(username, "PartSale");
    }//GEN-LAST:event_buttonProduceInvoiceActionPerformed

    private void buttonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveActionPerformed
        String selected = listCart.getSelectedValue();
        if (selected != null) {
            RemovePart(selected);
        } else {
            String mess = "Select a part";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }//GEN-LAST:event_buttonRemoveActionPerformed

    private void buttonAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddToCartActionPerformed
        String selected = listStock.getSelectedValue();
        if (selected != null) {
            boolean x = IsPartInOrder(selected);
            if (x == false) {
                AddPart();
            }
        } else {
            String mess = "Select a part";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }//GEN-LAST:event_buttonAddToCartActionPerformed

    private void buttonChangeQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeQuantityActionPerformed
        String quantity = textFieldQuantity.getText();
        String selected = listCart.getSelectedValue();

        if (selected != null) {
            if (!(quantity.isEmpty())) {

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

                int i = order.indexOf(selected);
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
        new StockControl(username);
    }//GEN-LAST:event_buttonBackActionPerformed

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

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new LogIn();
    }//GEN-LAST:event_buttonExitActionPerformed

    private void textFieldQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldQuantityActionPerformed

    private void listCustomersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listCustomersValueChanged
        String selected = listCustomers.getSelectedValue();
        textFieldSelectedCustomer.setText(selected);
    }//GEN-LAST:event_listCustomersValueChanged

    private void textFieldSearchCustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchCustomersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSearchCustomersActionPerformed

    private void buttonProduceInvoice1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonProduceInvoice1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonProduceInvoice1ActionPerformed

    private void buttonRemove1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemove1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRemove1ActionPerformed

    private void buttonAddToCart1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddToCart1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAddToCart1ActionPerformed

    private void textFieldQuantity1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldQuantity1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldQuantity1ActionPerformed

    private void buttonChangeQuantity1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeQuantity1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonChangeQuantity1ActionPerformed

    private void buttonExit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExit1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonExit1ActionPerformed

    private void buttonBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBack1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonBack1ActionPerformed

    private void buttonSearchCustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchCustomersActionPerformed
     try {
            String sql = ("select * from Customer where name LIKE '%"
                    + textFieldSearchCustomers.getText() + "%' and deleted = 0");
            PreparedStatement ps = null;

            try {
                ps = connection.prepareStatement(sql);

            } catch (Exception e) {
                e.printStackTrace();

            }
            this.rsC = ps.executeQuery();

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        ShowCustomers();
    }//GEN-LAST:event_buttonSearchCustomersActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
             JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new UpdateCustomer(username, "PartSale");
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddToCart;
    private javax.swing.JButton buttonAddToCart1;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonBack1;
    private javax.swing.JButton buttonChangeQuantity;
    private javax.swing.JButton buttonChangeQuantity1;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonExit1;
    private javax.swing.JButton buttonProduceInvoice;
    private javax.swing.JButton buttonProduceInvoice1;
    private javax.swing.JButton buttonRemove;
    private javax.swing.JButton buttonRemove1;
    private javax.swing.JButton buttonSearchAllStock;
    private javax.swing.JButton buttonSearchCustomers;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel labelAllStock;
    private javax.swing.JLabel labelAllStock1;
    private javax.swing.JLabel labelCart;
    private javax.swing.JLabel labelCart1;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelLoggedIn1;
    private javax.swing.JLabel labelPartSale;
    private javax.swing.JLabel labelPartSale1;
    private javax.swing.JLabel labelVehicleSelected;
    private javax.swing.JLabel labelVehicleSelected1;
    private javax.swing.JList<String> listCart;
    private javax.swing.JList<String> listCart1;
    private javax.swing.JList<String> listCustomers;
    private javax.swing.JList<String> listStock;
    private javax.swing.JList<String> listStock1;
    private javax.swing.JTextField textFieldQuantity;
    private javax.swing.JTextField textFieldQuantity1;
    private javax.swing.JTextField textFieldSearchAllStock;
    private javax.swing.JTextField textFieldSearchCustomers;
    private javax.swing.JTextField textFieldSelectedCustomer;
    private javax.swing.JTextField textFieldSelectedCustomer1;
    private javax.swing.JTextField textFieldUserDetails;
    private javax.swing.JTextField textFieldUserDetails1;
    // End of variables declaration//GEN-END:variables
}
