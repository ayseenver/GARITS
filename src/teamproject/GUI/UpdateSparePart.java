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

public class UpdateSparePart extends javax.swing.JPanel {

    private String username;
    Statement statement;
    Connection connection = null;
    Customer c;
    DB_ImplClass db = new DB_ImplClass();
    ResultSet rs;
    String[] vehicleArray;
    Vehicle ve = new Vehicle();
    ArrayList<String> vehicles = new ArrayList<>();
    ArrayList<String> customers = new ArrayList<>();
    String reg;

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

        ShowVehicles();

    }

    private String[] CreateArray(ArrayList<String> vehicles) {
        String[] newArray = new String[vehicles.size()];
        newArray = vehicles.toArray(newArray);
        return newArray;
    }

    private void ShowVehicles() {
        listSpareParts.removeAll();
        ArrayList<String> vehicles = new ArrayList<>();

        //get all vehicles belonging to this owner
        try {
            String sql = ("select * from Vehicle where CustomerID = "
                    + "(select ID from customer where name = '" + c.getName() + "' and address = '" + c.getAddress() + "')");
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
                String v = rs.getString("registrationNumber") + ", " + rs.getString("make") + ", " + rs.getString("model") + ", " + rs.getString("engineSerial") + ", " + rs.getString("chassisNumber") + ", " + rs.getString("colour");
                vehicles.add(v);
            }
        } catch (SQLException e) {
        }

        vehicleArray = CreateArray(vehicles);

        listSpareParts.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return vehicleArray.length;
            }

            public String getElementAt(int i) {
                return vehicleArray[i];
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
        labelColour = new javax.swing.JLabel();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        textFieldUsername = new javax.swing.JTextField();
        labelSpareParts = new javax.swing.JLabel();
        labelRegistrationNo = new javax.swing.JLabel();
        labelMake = new javax.swing.JLabel();
        labelModel = new javax.swing.JLabel();
        labelChassisNo = new javax.swing.JLabel();
        labelEngineSerialNo = new javax.swing.JLabel();
        textFieldQuantity = new javax.swing.JTextField();
        textFieldYear = new javax.swing.JTextField();
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
        buttonEditSparePart = new javax.swing.JButton();
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

        labelColour.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelColour.setText("*Threshold:");
        jPanel1.add(labelColour, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 500, -1, -1));

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

        labelModel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelModel.setText("*Vehicle Year:");
        jPanel1.add(labelModel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 410, -1, -1));

        labelChassisNo.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelChassisNo.setText("*Cost(£) Per Part:");
        jPanel1.add(labelChassisNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 470, -1, -1));

        labelEngineSerialNo.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelEngineSerialNo.setText("*Quantity:");
        jPanel1.add(labelEngineSerialNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 440, -1, -1));
        jPanel1.add(textFieldQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 440, 250, -1));
        jPanel1.add(textFieldYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 410, 250, -1));
        jPanel1.add(textFieldVehicleType, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 380, 250, -1));
        jPanel1.add(textFieldPartName, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 350, 250, -1));
        jPanel1.add(textFieldCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 470, 250, -1));
        jPanel1.add(textFieldThreshold, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 500, 250, -1));

        buttonNewSparePart.setText("New Spart Part");
        buttonNewSparePart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewSparePartActionPerformed(evt);
            }
        });
        jPanel1.add(buttonNewSparePart, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 560, 130, -1));

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

        jScrollPane5.setViewportView(listSpareParts);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 700, 120));

        labelVariableDiscount.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        jPanel1.add(labelVariableDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, -1, -1));
        jPanel1.add(textFieldManufactureName, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 530, 250, 30));

        labelLastServiceDate.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelLastServiceDate.setText("*Manufacture: ");
        jPanel1.add(labelLastServiceDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 530, -1, -1));

        buttonEditSparePart.setText("Edit");
        buttonEditSparePart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditSparePartActionPerformed(evt);
            }
        });
        jPanel1.add(buttonEditSparePart, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 290, 100, -1));

        buttonSavePartChanges.setText("Save Changes");
        buttonSavePartChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSavePartChangesActionPerformed(evt);
            }
        });
        jPanel1.add(buttonSavePartChanges, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 560, 130, -1));

        buttonDeleteSparePart.setText("Delete");
        buttonDeleteSparePart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteSparePartActionPerformed(evt);
            }
        });
        jPanel1.add(buttonDeleteSparePart, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 100, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDoneActionPerformed
        if (vehicleArray.length > 0) {
            try {
                String sql = ("update customer set deleted = 0 where ID in (select max(id) from customer)");
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

            JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
            f.dispose();
            db.closeConnection(connection);
            new MainMenu(username);
        } else {
            String mess = "Customer needs at least one vehicle";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }//GEN-LAST:event_buttonDoneActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new CustomerList(username);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new LogIn();
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonEditSparePartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditSparePartActionPerformed
        if (listSpareParts.getSelectedValue() == null) {
            String mess = "Please choose vehicle record first!";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
            String selected = listSpareParts.getSelectedValue();
            String[] parts = selected.split(", ");
            reg = parts[0];

            try {
                String sql = ("Select * from vehicle where registrationNumber = '" + reg + "'");
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
                ve.setRegistrationNumber(rs.getString("registrationNumber"));
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                ve.setMake(rs.getString("make"));
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            try {
                ve.setModel(rs.getString("model"));
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            try {
                ve.setEngineSerial(rs.getString("engineSerial"));
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            try {
                ve.setChassisNumber(rs.getString("chassisNumber"));
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            try {
                ve.setColour(rs.getString("colour"));
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                ve.setNextMoTDate(rs.getString("nextMoTDate"));
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                ve.setNextServiceDate(rs.getString("nextServiceDate"));
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            textFieldPartName.setText(ve.getRegistrationNumber());
            textFieldVehicleType.setText(ve.getMake());
            textFieldYear.setText(ve.getModel());
            textFieldQuantity.setText(ve.getEngineSerial());
            textFieldCost.setText(ve.getChassisNumber());
            textFieldThreshold.setText(ve.getColour());
            textFieldManufactureName.setText(ve.getNextServiceDate());
            textFieldNextMoTDate.setText(ve.getNextMoTDate());
        }
    }//GEN-LAST:event_buttonEditSparePartActionPerformed

    private void buttonNewSparePartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewSparePartActionPerformed
        if (textFieldPartName.getText().equals("") || textFieldVehicleType.getText().equals("")
                || textFieldYear.getText().equals("") || textFieldQuantity.getText().equals("")
                || textFieldCost.getText().equals("") || textFieldThreshold.getText().equals("")) {
            String mess = "Please fill in all the boxes";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
            try {
                String sql = ("INSERT INTO Vehicle (registrationNumber, "
                        + "CustomerID, make, "
                        + "model, engineSerial, chassisNumber, colour, "
                        + "nextServiceDate, nextMOTDate, deleted) "
                        + "VALUES ('" + textFieldPartName.getText() + "', "
                        + "(select ID from customer where name = '" + c.getName() + "' "
                        + "and address = '" + c.getAddress() + "'), "
                        + "'" + textFieldVehicleType.getText() + "', "
                        + "'" + textFieldYear.getText() + "', "
                        + "'" + textFieldQuantity.getText() + "', "
                        + "'" + textFieldCost.getText() + "', "
                        + "'" + textFieldThreshold.getText() + "', "
                        + "'" + textFieldManufactureName.getText() + "', "
                        + "'" + textFieldNextMoTDate.getText() + "', 0)");
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
            ShowVehicles();
        }
    }//GEN-LAST:event_buttonNewSparePartActionPerformed

    private void buttonSavePartChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSavePartChangesActionPerformed
        if (textFieldPartName.getText().equals("") || textFieldVehicleType.getText().equals("")
                || textFieldYear.getText().equals("") || textFieldQuantity.getText().equals("")
                || textFieldCost.getText().equals("") || textFieldThreshold.getText().equals("")) {
            String mess = "Please fill in all the boxes";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
            try {
                String sql = ("UPDATE Vehicle SET registrationNumber = '" + textFieldPartName.getText() + "', "
                        + "make = '" + textFieldVehicleType.getText() + "', "
                        + "model = '" + textFieldYear.getText() + "', "
                        + "engineSerial = '" + textFieldQuantity.getText() + "', "
                        + "chassisNumber = '" + textFieldCost.getText() + "', "
                        + "colour = '" + textFieldThreshold.getText() + "', "
                        + "nextServiceDate = '" + textFieldManufactureName.getText() + "', "
                        + "nextMOTDate = '" + textFieldNextMoTDate.getText() + "' "
                        + "WHERE registrationNumber = '" + reg + "' "
                        + "AND CustomerID = (select ID from customer where name = '" + c.getName() + "' "
                        + "and address = '" + c.getAddress() + "')");

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
            ShowVehicles();
        }
    }//GEN-LAST:event_buttonSavePartChangesActionPerformed

    private void buttonDeleteSparePartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteSparePartActionPerformed
        if (listSpareParts.getSelectedValue() == null) {
            String mess = "Please choose vehicle record first!";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
            String selected = listSpareParts.getSelectedValue();
            String[] parts = selected.split(", ");
            String regNo = parts[0];

            //delete this vehicle
            try {
                String sql = ("update vehicle set deleted = 1 where registrationNumber = " + regNo);
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
        ShowVehicles();
    }//GEN-LAST:event_buttonDeleteSparePartActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonDeleteSparePart;
    private javax.swing.JButton buttonDone;
    private javax.swing.JButton buttonEditSparePart;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonNewSparePart;
    private javax.swing.JButton buttonSavePartChanges;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelChassisNo;
    private javax.swing.JLabel labelColour;
    private javax.swing.JLabel labelCustomerDetails;
    private javax.swing.JLabel labelEngineSerialNo;
    private javax.swing.JLabel labelLastServiceDate;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelMake;
    private javax.swing.JLabel labelModel;
    private javax.swing.JLabel labelRegistrationNo;
    private javax.swing.JLabel labelSpareParts;
    private javax.swing.JLabel labelVariableDiscount;
    private javax.swing.JList<String> listSpareParts;
    private javax.swing.JTextField textFieldCost;
    private javax.swing.JTextField textFieldManufactureName;
    private javax.swing.JTextField textFieldPartName;
    private javax.swing.JTextField textFieldQuantity;
    private javax.swing.JTextField textFieldThreshold;
    private javax.swing.JTextField textFieldUsername;
    private javax.swing.JTextField textFieldVehicleType;
    private javax.swing.JTextField textFieldYear;
    // End of variables declaration//GEN-END:variables
}
