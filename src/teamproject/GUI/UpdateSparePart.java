package teamproject.GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import teamproject.Customer_Account.Customer;
import teamproject.Customer_Account.Vehicle;
import teamproject.Databases.DB_ImplClass;
import teamproject.Spare_Parts.SparePart;

public class UpdateSparePart extends javax.swing.JPanel {

    private String username;
    Statement statement;
    Connection connection = null;
    Customer c;
    DB_ImplClass db = new DB_ImplClass();
    ResultSet rs;
    String[] partArray;
    SparePart p = new SparePart();

    public UpdateSparePart(String username) {
        this.username = username;
        this.c = c;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.textFieldUsername.setText(username);
        connection = db.connect();
        statement = db.getStatement();

        GetRole();

        ShowParts();

    }

    private String[] CreateArray(ArrayList<String> vehicles) {
        String[] newArray = new String[vehicles.size()];
        newArray = vehicles.toArray(newArray);
        return newArray;
    }

    private void GetRole() {
        String roleName = "";
        try {
            this.rs = statement.executeQuery("select * from User where deleted = 0");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (rs.next()) {
                // read the result set
                String user = rs.getString("username");

                //Code to get Role name from Databse
                if (username.equals(user)) {
                    // this.rs = statement.executeQuery("select roleName from User where username = '" + username + "'"); // I dont see the point of this line
                    //it get the role name if the username equals anyway plus gets ride of the error message

                    roleName = rs.getString("roleName");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Only frachisee can edit threshold
        if (!(roleName.equals("franchisee"))) {
            textFieldThreshold.setVisible(false);
            labelThreshold.setVisible(false);
        }
    }

    private void ShowParts() {
        listSpareParts.removeAll();
        ArrayList<String> parts = new ArrayList<>();

        //get all parts
        try {
            String sql = ("select * from sparepart where deleted = 0");
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            while (rs.next()) {
                // read the result set
                String p = rs.getString("partName") + ", " + rs.getString("vehicleType") + ", " + rs.getString("quantity");
                parts.add(p);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        partArray = CreateArray(parts);

        listSpareParts.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return partArray.length;
            }

            public String getElementAt(int i) {
                return partArray[i];
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        buttonDone = new javax.swing.JButton();
        labelThreshold = new javax.swing.JLabel();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        textFieldUsername = new javax.swing.JTextField();
        labelSpareParts = new javax.swing.JLabel();
        labelRegistrationNo = new javax.swing.JLabel();
        labelMake = new javax.swing.JLabel();
        labelChassisNo = new javax.swing.JLabel();
        labelEngineSerialNo = new javax.swing.JLabel();
        textFieldQuantity = new javax.swing.JTextField();
        textFieldVehicleType = new javax.swing.JTextField();
        textFieldPartName = new javax.swing.JTextField();
        textFieldCost = new javax.swing.JTextField();
        textFieldThreshold = new javax.swing.JTextField();
        buttonNewSparePart = new javax.swing.JButton();
        labelCustomerDetails = new javax.swing.JLabel();
        buttonBack = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        listSpareParts = new javax.swing.JList<>();
        labelVariableDiscount = new javax.swing.JLabel();
        textFieldManufactureName = new javax.swing.JTextField();
        labelLastServiceDate = new javax.swing.JLabel();
        buttonSavePartChanges = new javax.swing.JButton();
        buttonDeleteSparePart = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, -1, -1));

        buttonDone.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonDone.setText("Done");
        buttonDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDoneActionPerformed(evt);
            }
        });
        jPanel1.add(buttonDone, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 640, -1, -1));

        labelThreshold.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelThreshold.setText("*Threshold:");
        jPanel1.add(labelThreshold, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 470, -1, -1));

        labelLoggedIn.setText("Logged In as:");
        jPanel1.add(labelLoggedIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        buttonExit.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonExit.setText("Logout");
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });
        jPanel1.add(buttonExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, -1, -1));

        textFieldUsername.setEditable(false);
        textFieldUsername.setFocusable(false);
        jPanel1.add(textFieldUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 220, 30));

        labelSpareParts.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelSpareParts.setText("All Spare Parts:");
        jPanel1.add(labelSpareParts, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, -1));

        labelRegistrationNo.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelRegistrationNo.setText("*PartName:");
        jPanel1.add(labelRegistrationNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, -1, -1));

        labelMake.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelMake.setText("*Vehicle Type:");
        jPanel1.add(labelMake, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 380, -1, -1));

        labelChassisNo.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelChassisNo.setText("*Cost(£) Per Part:");
        jPanel1.add(labelChassisNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 440, -1, -1));

        labelEngineSerialNo.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelEngineSerialNo.setText("*Quantity:");
        jPanel1.add(labelEngineSerialNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 410, -1, -1));
        jPanel1.add(textFieldQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 410, 250, -1));
        jPanel1.add(textFieldVehicleType, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 380, 250, -1));
        jPanel1.add(textFieldPartName, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 350, 250, -1));
        jPanel1.add(textFieldCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 440, 250, -1));

        textFieldThreshold.setText("10");
        jPanel1.add(textFieldThreshold, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 470, 250, -1));

        buttonNewSparePart.setText("New Spart Part");
        buttonNewSparePart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewSparePartActionPerformed(evt);
            }
        });
        jPanel1.add(buttonNewSparePart, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 530, 130, -1));

        labelCustomerDetails.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelCustomerDetails.setText("Edit Spare Part");
        jPanel1.add(labelCustomerDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        buttonBack.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });
        jPanel1.add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, -1));

        listSpareParts.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listSparePartsValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(listSpareParts);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 700, 120));

        labelVariableDiscount.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        jPanel1.add(labelVariableDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, -1, -1));
        jPanel1.add(textFieldManufactureName, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 500, 250, 30));

        labelLastServiceDate.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelLastServiceDate.setText("*Manufacture: ");
        jPanel1.add(labelLastServiceDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 500, -1, -1));

        buttonSavePartChanges.setText("Save Changes");
        buttonSavePartChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSavePartChangesActionPerformed(evt);
            }
        });
        jPanel1.add(buttonSavePartChanges, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 530, 130, -1));

        buttonDeleteSparePart.setText("Delete Part");
        buttonDeleteSparePart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteSparePartActionPerformed(evt);
            }
        });
        jPanel1.add(buttonDeleteSparePart, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 100, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDoneActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new MainMenu(username);
    }//GEN-LAST:event_buttonDoneActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new StockControl(username);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new LogIn();
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonNewSparePartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewSparePartActionPerformed
        if (textFieldPartName.getText().isEmpty() || textFieldVehicleType.getText().isEmpty()
                || textFieldQuantity.getText().isEmpty() || textFieldCost.getText().isEmpty()
                || textFieldThreshold.getText().isEmpty() || textFieldManufactureName.getText().isEmpty()) {
            String mess = "Please fill in all the boxes";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
            Boolean error = false;
            Boolean qError = false;
            //check if quantity is an integer
            char[] chars = textFieldQuantity.getText().toCharArray();
            for (char c : chars) {
                if (!(Character.isDigit(c))) {
                    error = true;
                    qError = true;
                }
            }

            if (qError == true) {
                String mess = "Quantity must be a number";
                JOptionPane.showMessageDialog(new JFrame(), mess);
            }

            Boolean pError = false;
            //check if price is an integer
            chars = textFieldCost.getText().toCharArray();
            for (char c : chars) {
                if (!(Character.isDigit(c))) {
                    error = true;
                    pError = true;
                }
            }

            if (pError == true) {
                String mess = "Cost must be a number";
                JOptionPane.showMessageDialog(new JFrame(), mess);
            }

            Boolean tError = false;
            //check if threshold is an integer
            chars = textFieldThreshold.getText().toCharArray();
            for (char c : chars) {
                if (!(Character.isDigit(c))) {
                    error = true;
                    tError = true;
                }
            }

            if (tError == true) {
                String mess = "Threshold must be a number";
                JOptionPane.showMessageDialog(new JFrame(), mess);
            }

            if (error == false) {
                ArrayList<String> mans = new ArrayList<>();
                try {
                    //get all the manufacturs to see if this one exists
                    String sql = "select * from manufacturer";
                    PreparedStatement ps = null;
                    try {
                        ps = connection.prepareStatement(sql);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    rs = ps.executeQuery();

                    try {
                        while (rs.next()) {
                            mans.add(rs.getString("name"));
                        }
                    } catch (SQLException e) {
                        System.err.println(e.getMessage());
                    }

                    //create a new manufacturer if one does not exist
                    if (!mans.contains(textFieldManufactureName.getText())) {
                        sql = ("INSERT INTO manufacturer (name) values ('" + textFieldManufactureName.getText() + "')");
                        ps = null;
                        try {
                            ps = connection.prepareStatement(sql);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        ps.executeUpdate();
                    }

                    sql = ("INSERT INTO SparePart (partName, vehicleType, quantity, "
                            + "costPrice, sellingPrice, threshold, Manufacturername, deleted) "
                            + "VALUES ('" + textFieldPartName.getText() + "', "
                            + "'" + textFieldVehicleType.getText() + "', "
                            + "'" + textFieldQuantity.getText() + "', "
                            + "'" + textFieldCost.getText() + "', "
                            + "'" + (Double.parseDouble(textFieldCost.getText()) * 1.3) + "', "
                            + "'" + textFieldThreshold.getText() + "', "
                            + "(select name from manufacturer where name = '" + textFieldManufactureName.getText() + "'), 0)");
                    ps = null;
                    try {
                        ps = connection.prepareStatement(sql);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
                ShowParts();
            }
        }
    }//GEN-LAST:event_buttonNewSparePartActionPerformed

    private void buttonSavePartChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSavePartChangesActionPerformed
        if (textFieldPartName.getText().isEmpty() || textFieldVehicleType.getText().isEmpty()
                || textFieldQuantity.getText().isEmpty() || textFieldCost.getText().isEmpty()
                || textFieldThreshold.getText().isEmpty() || textFieldManufactureName.getText().isEmpty()) {
            String mess = "Please fill in all the boxes";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
            try {
                String sql = ("UPDATE SparePart SET "
                        + "partID = 'partID', "
                        + "partName = 'partName', "
                        + "vehicleType = 'vehicleType', "
                        + "quantity = 'quantity', "
                        + "costPrice = 'costPrice', "
                        + "sellingPrice = 'sellingPrice', "
                        + "threshold = 'threshold', "
                        + "Manufacturername = 'Manufacturername', "
                        + "deleted = 'deleted' "
                        + "WHERE partName = 'partName' AND "
                        + "vehicleType = 'vehicleType' AND "
                        + "quantity = 'quantity'");

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
            ShowParts();
        }
    }//GEN-LAST:event_buttonSavePartChangesActionPerformed

    private void buttonDeleteSparePartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteSparePartActionPerformed
        if (listSpareParts.getSelectedValue() == null) {
            String mess = "Please choose a part first!";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
            String selected = listSpareParts.getSelectedValue();
            String name = "";
            String model = "";
            String[] parts = selected.split(", ");
            if (parts.length == 3) {
                name = parts[0];
                model = parts[1];
            } else if (parts.length == 4) {
                name = parts[0] + ", " + parts[1];
                model = parts[2];
            }

            //delete this part
            try {
                String sql = ("update sparepart set deleted = 1 where partName = '" + name + "' and vehicleType = '" + model + "'");
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
        ShowParts();
    }//GEN-LAST:event_buttonDeleteSparePartActionPerformed

    private void listSparePartsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listSparePartsValueChanged
        String selected = listSpareParts.getSelectedValue();
        String name = "";
        String model = "";
        String quantity = "";
        String[] parts = selected.split(", ");
        if (parts.length == 3) {
            name = parts[0];
            model = parts[1];
            quantity = parts[2];
        } else if (parts.length == 4) {
            name = parts[0] + ", " + parts[1];
            model = parts[2];
            quantity = parts[3];
        }

        try {
            String sql = ("Select * from sparepart where partName = '" + name + "' and vehicleType = '" + model + "' "
                    + "and quantity = " + quantity + " and deleted = 0");
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            p.setPartName(rs.getString("partName"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            p.setVehicleType(rs.getString("vehicleType"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            p.setQuantity(Integer.parseInt(rs.getString("quantity")));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            p.setCostPrice(Double.parseDouble(rs.getString("costPrice")));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            p.setSellingPrice(Double.parseDouble(rs.getString("costPrice")) * 1.3);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            p.setThreshold(Integer.parseInt(rs.getString("threshold")));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            p.setManufacturerName(rs.getString("Manufacturername"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        textFieldPartName.setText(p.getPartName());
        textFieldVehicleType.setText(p.getVehicleType());
        textFieldQuantity.setText(p.getQuantity() + "");
        textFieldCost.setText(p.getCostPrice() + "");
        textFieldThreshold.setText(p.getThreshold() + "");
        textFieldManufactureName.setText(p.getManufacturerName());
    }//GEN-LAST:event_listSparePartsValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonDeleteSparePart;
    private javax.swing.JButton buttonDone;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonNewSparePart;
    private javax.swing.JButton buttonSavePartChanges;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelChassisNo;
    private javax.swing.JLabel labelCustomerDetails;
    private javax.swing.JLabel labelEngineSerialNo;
    private javax.swing.JLabel labelLastServiceDate;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelMake;
    private javax.swing.JLabel labelRegistrationNo;
    private javax.swing.JLabel labelSpareParts;
    private javax.swing.JLabel labelThreshold;
    private javax.swing.JLabel labelVariableDiscount;
    private javax.swing.JList<String> listSpareParts;
    private javax.swing.JTextField textFieldCost;
    private javax.swing.JTextField textFieldManufactureName;
    private javax.swing.JTextField textFieldPartName;
    private javax.swing.JTextField textFieldQuantity;
    private javax.swing.JTextField textFieldThreshold;
    private javax.swing.JTextField textFieldUsername;
    private javax.swing.JTextField textFieldVehicleType;
    // End of variables declaration//GEN-END:variables
}
