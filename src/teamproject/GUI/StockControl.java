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
public class StockControl extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    public StockControl() {
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
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

        lblStockControl = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listStock = new javax.swing.JList<>();
        labelAllStock = new javax.swing.JLabel();
        textFieldSearchAllStock = new javax.swing.JTextField();
        buttonOrder = new javax.swing.JButton();
        lblPartsOrder = new javax.swing.JLabel();
        buttonStockLevelReport = new javax.swing.JButton();
        buttonRemove = new javax.swing.JButton();
        buttonSelectToOrderAllStock = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        listLowStock = new javax.swing.JList<>();
        textFieldQuantity = new javax.swing.JTextField();
        buttonChangeQuantity = new javax.swing.JButton();
        labelLowStock = new javax.swing.JLabel();
        textFieldUserDetails = new javax.swing.JTextField();
        lblLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        selectFromLowStock = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        listPartsOrder = new javax.swing.JList<>();
        buttonSearchAllStock = new javax.swing.JButton();
        buttonPartSale = new javax.swing.JButton();
        textFieldConfigureThreshold = new javax.swing.JTextField();
        buttonConfigureThreshold = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblStockControl.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        lblStockControl.setText("Stock Control");
        add(lblStockControl, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, -1, -1));

        listStock.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listStock.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
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

        buttonStockLevelReport.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        buttonStockLevelReport.setText("Stock Level Report");
        buttonStockLevelReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStockLevelReportActionPerformed(evt);
            }
        });
        add(buttonStockLevelReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 140, -1, -1));

        buttonRemove.setText("Remove");
        buttonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveActionPerformed(evt);
            }
        });
        add(buttonRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 620, 150, -1));

        buttonSelectToOrderAllStock.setText("Select to Order");
        buttonSelectToOrderAllStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelectToOrderAllStockActionPerformed(evt);
            }
        });
        add(buttonSelectToOrderAllStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 320, -1, -1));

        listLowStock.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listLowStock.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(listLowStock);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 1160, 140));

        textFieldQuantity.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        textFieldQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldQuantityActionPerformed(evt);
            }
        });
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
        add(textFieldUserDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 220, 30));

        lblLoggedIn.setText("Logged In as:");
        add(lblLoggedIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        buttonExit.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonExit.setText("Exit");
        add(buttonExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 0, -1, -1));

        buttonBack.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

        selectFromLowStock.setText(" Select to Order");
        selectFromLowStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectFromLowStockActionPerformed(evt);
            }
        });
        add(selectFromLowStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 510, -1, -1));

        listPartsOrder.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listPartsOrder.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(listPartsOrder);

        add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 550, 930, 130));

        buttonSearchAllStock.setText("Search");
        buttonSearchAllStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchAllStockActionPerformed(evt);
            }
        });
        add(buttonSearchAllStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, -1, -1));

        buttonPartSale.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        buttonPartSale.setText("Part Sale");
        buttonPartSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPartSaleActionPerformed(evt);
            }
        });
        add(buttonPartSale, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 140, -1, -1));

        textFieldConfigureThreshold.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        textFieldConfigureThreshold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldConfigureThresholdActionPerformed(evt);
            }
        });
        add(textFieldConfigureThreshold, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 320, 30, 30));

        buttonConfigureThreshold.setText("Configure Threshold");
        buttonConfigureThreshold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfigureThresholdActionPerformed(evt);
            }
        });
        add(buttonConfigureThreshold, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 320, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldSearchAllStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchAllStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSearchAllStockActionPerformed

    private void buttonOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOrderActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        PartOrder partOrder = new PartOrder();
    }//GEN-LAST:event_buttonOrderActionPerformed

    private void buttonStockLevelReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStockLevelReportActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        Report stockReport = new Report();
    }//GEN-LAST:event_buttonStockLevelReportActionPerformed

    private void buttonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRemoveActionPerformed

    private void buttonSelectToOrderAllStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelectToOrderAllStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSelectToOrderAllStockActionPerformed

    private void textFieldQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldQuantityActionPerformed

    private void buttonChangeQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonChangeQuantityActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        MainMenu mainMenuFranchisee = new MainMenu();
    }//GEN-LAST:event_buttonBackActionPerformed

    private void selectFromLowStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectFromLowStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectFromLowStockActionPerformed

    private void buttonSearchAllStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchAllStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchAllStockActionPerformed

    private void buttonPartSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPartSaleActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        PartSale partSale = new PartSale();
    }//GEN-LAST:event_buttonPartSaleActionPerformed

    private void textFieldConfigureThresholdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldConfigureThresholdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldConfigureThresholdActionPerformed

    private void buttonConfigureThresholdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfigureThresholdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonConfigureThresholdActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonChangeQuantity;
    private javax.swing.JButton buttonConfigureThreshold;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonOrder;
    private javax.swing.JButton buttonPartSale;
    private javax.swing.JButton buttonRemove;
    private javax.swing.JButton buttonSearchAllStock;
    private javax.swing.JButton buttonSelectToOrderAllStock;
    private javax.swing.JButton buttonStockLevelReport;
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
    private javax.swing.JButton selectFromLowStock;
    private javax.swing.JTextField textFieldConfigureThreshold;
    private javax.swing.JTextField textFieldQuantity;
    private javax.swing.JTextField textFieldSearchAllStock;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
