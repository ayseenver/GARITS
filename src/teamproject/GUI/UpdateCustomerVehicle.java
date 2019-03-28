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

public class UpdateCustomerVehicle extends javax.swing.JPanel {

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

    public UpdateCustomerVehicle(String username, Customer c) {
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
        listVehicles.removeAll();
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

        listVehicles.setModel(new javax.swing.AbstractListModel<String>() {
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
        labelVehicles = new javax.swing.JLabel();
        labelRegistrationNo = new javax.swing.JLabel();
        labelMake = new javax.swing.JLabel();
        labelModel = new javax.swing.JLabel();
        labelChassisNo = new javax.swing.JLabel();
        labelLastMoTDate = new javax.swing.JLabel();
        labelEngineSerialNo = new javax.swing.JLabel();
        labelMoTDateFormat = new javax.swing.JLabel();
        textFieldEngineSerial = new javax.swing.JTextField();
        textFieldModel = new javax.swing.JTextField();
        textFieldMake = new javax.swing.JTextField();
        textFieldRegistrationNo = new javax.swing.JTextField();
        textFieldChassisNo = new javax.swing.JTextField();
        textFieldColour = new javax.swing.JTextField();
        buttonNewVehicle = new javax.swing.JButton();
        labelCustomerDetails = new javax.swing.JLabel();
        buttonBack = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        listVehicles = new javax.swing.JList<>();
        labelVariableDiscount = new javax.swing.JLabel();
        textFieldNextServiceDate = new javax.swing.JTextField();
        textFieldNextMoTDate = new javax.swing.JTextField();
        labelLastServiceDate = new javax.swing.JLabel();
        labelServiceDateFormat = new javax.swing.JLabel();
        buttonEditVehicle = new javax.swing.JButton();
        buttonSaveVehicleChanges = new javax.swing.JButton();
        buttonDeleteVehicle = new javax.swing.JButton();

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
        jPanel1.add(buttonDone, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 660, -1, -1));

        labelColour.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelColour.setText("*Colour:");
        jPanel1.add(labelColour, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 510, -1, -1));

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

        labelVehicles.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelVehicles.setText("Vehicles:");
        jPanel1.add(labelVehicles, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, -1, -1));

        labelRegistrationNo.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelRegistrationNo.setText("*Registration No:");
        jPanel1.add(labelRegistrationNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 360, -1, -1));

        labelMake.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelMake.setText("*Make:");
        jPanel1.add(labelMake, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, -1, -1));

        labelModel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelModel.setText("*Model:");
        jPanel1.add(labelModel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, -1, -1));

        labelChassisNo.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelChassisNo.setText("*Chassis No:");
        jPanel1.add(labelChassisNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, -1, -1));

        labelLastMoTDate.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelLastMoTDate.setText("Next MoT Date: ");
        jPanel1.add(labelLastMoTDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 570, 140, -1));

        labelEngineSerialNo.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelEngineSerialNo.setText("*Engine Serial No:");
        jPanel1.add(labelEngineSerialNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 450, -1, -1));

        labelMoTDateFormat.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelMoTDateFormat.setText("(yyyy-mm-dd)");
        jPanel1.add(labelMoTDateFormat, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 580, -1, -1));
        jPanel1.add(textFieldEngineSerial, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 250, -1));
        jPanel1.add(textFieldModel, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 420, 250, -1));
        jPanel1.add(textFieldMake, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 390, 250, -1));
        jPanel1.add(textFieldRegistrationNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, 250, -1));
        jPanel1.add(textFieldChassisNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 480, 250, -1));
        jPanel1.add(textFieldColour, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 510, 250, -1));

        buttonNewVehicle.setText("New Vehicle");
        buttonNewVehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewVehicleActionPerformed(evt);
            }
        });
        jPanel1.add(buttonNewVehicle, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 610, 130, -1));

        labelCustomerDetails.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelCustomerDetails.setText("Customer Vehicles");
        jPanel1.add(labelCustomerDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        buttonBack.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });
        jPanel1.add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, -1));

        jScrollPane5.setViewportView(listVehicles);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 700, 120));

        labelVariableDiscount.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        jPanel1.add(labelVariableDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, -1, -1));
        jPanel1.add(textFieldNextServiceDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 540, 110, 30));
        jPanel1.add(textFieldNextMoTDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 570, 110, 30));

        labelLastServiceDate.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelLastServiceDate.setText("Next Service Date: ");
        jPanel1.add(labelLastServiceDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 540, -1, -1));

        labelServiceDateFormat.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelServiceDateFormat.setText("(yyyy-mm-dd)");
        jPanel1.add(labelServiceDateFormat, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 550, -1, -1));

        buttonEditVehicle.setText("Edit");
        buttonEditVehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditVehicleActionPerformed(evt);
            }
        });
        jPanel1.add(buttonEditVehicle, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, 100, -1));

        buttonSaveVehicleChanges.setText("Save Changes");
        buttonSaveVehicleChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveVehicleChangesActionPerformed(evt);
            }
        });
        jPanel1.add(buttonSaveVehicleChanges, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 610, 130, -1));

        buttonDeleteVehicle.setText("Delete");
        buttonDeleteVehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteVehicleActionPerformed(evt);
            }
        });
        jPanel1.add(buttonDeleteVehicle, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, 100, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1280, 720));
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

    private void buttonEditVehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditVehicleActionPerformed
        if (listVehicles.getSelectedValue() == null) {
            String mess = "Please choose vehicle record first!";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
            String selected = listVehicles.getSelectedValue();
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

            textFieldRegistrationNo.setText(ve.getRegistrationNumber());
            textFieldMake.setText(ve.getMake());
            textFieldModel.setText(ve.getModel());
            textFieldEngineSerial.setText(ve.getEngineSerial());
            textFieldChassisNo.setText(ve.getChassisNumber());
            textFieldColour.setText(ve.getColour());
            textFieldNextServiceDate.setText(ve.getNextServiceDate());
            textFieldNextMoTDate.setText(ve.getNextMoTDate());
        }
    }//GEN-LAST:event_buttonEditVehicleActionPerformed

    private void buttonNewVehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewVehicleActionPerformed
        if (textFieldRegistrationNo.getText().equals("") || textFieldMake.getText().equals("")
                || textFieldModel.getText().equals("") || textFieldEngineSerial.getText().equals("")
                || textFieldChassisNo.getText().equals("") || textFieldColour.getText().equals("")) {
            String mess = "Please fill in all the boxes";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
            try {
                String sql = ("INSERT INTO Vehicle (registrationNumber, "
                        + "CustomerID, make, "
                        + "model, engineSerial, chassisNumber, colour, "
                        + "nextServiceDate, nextMOTDate, deleted) "
                        + "VALUES ('" + textFieldRegistrationNo.getText() + "', "
                        + "(select ID from customer where name = '" + c.getName() + "' "
                        + "and address = '" + c.getAddress() + "'), "
                        + "'" + textFieldMake.getText() + "', "
                        + "'" + textFieldModel.getText() + "', "
                        + "'" + textFieldEngineSerial.getText() + "', "
                        + "'" + textFieldChassisNo.getText() + "', "
                        + "'" + textFieldColour.getText() + "', "
                        + "'" + textFieldNextServiceDate.getText() + "', "
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
    }//GEN-LAST:event_buttonNewVehicleActionPerformed

    private void buttonSaveVehicleChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveVehicleChangesActionPerformed
        if (textFieldRegistrationNo.getText().equals("") || textFieldMake.getText().equals("")
                || textFieldModel.getText().equals("") || textFieldEngineSerial.getText().equals("")
                || textFieldChassisNo.getText().equals("") || textFieldColour.getText().equals("")) {
            String mess = "Please fill in all the boxes";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
            try {
                String sql = ("UPDATE Vehicle SET registrationNumber = '" + textFieldRegistrationNo.getText() + "', "
                        + "make = '" + textFieldMake.getText() + "', "
                        + "model = '" + textFieldModel.getText() + "', "
                        + "engineSerial = '" + textFieldEngineSerial.getText() + "', "
                        + "chassisNumber = '" + textFieldChassisNo.getText() + "', "
                        + "colour = '" + textFieldColour.getText() + "', "
                        + "nextServiceDate = '" + textFieldNextServiceDate.getText() + "', "
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
    }//GEN-LAST:event_buttonSaveVehicleChangesActionPerformed

    private void buttonDeleteVehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteVehicleActionPerformed
        if (listVehicles.getSelectedValue() == null) {
            String mess = "Please choose vehicle record first!";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
            String selected = listVehicles.getSelectedValue();
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
    }//GEN-LAST:event_buttonDeleteVehicleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonDeleteVehicle;
    private javax.swing.JButton buttonDone;
    private javax.swing.JButton buttonEditVehicle;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonNewVehicle;
    private javax.swing.JButton buttonSaveVehicleChanges;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelChassisNo;
    private javax.swing.JLabel labelColour;
    private javax.swing.JLabel labelCustomerDetails;
    private javax.swing.JLabel labelEngineSerialNo;
    private javax.swing.JLabel labelLastMoTDate;
    private javax.swing.JLabel labelLastServiceDate;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelMake;
    private javax.swing.JLabel labelMoTDateFormat;
    private javax.swing.JLabel labelModel;
    private javax.swing.JLabel labelRegistrationNo;
    private javax.swing.JLabel labelServiceDateFormat;
    private javax.swing.JLabel labelVariableDiscount;
    private javax.swing.JLabel labelVehicles;
    private javax.swing.JList<String> listVehicles;
    private javax.swing.JTextField textFieldChassisNo;
    private javax.swing.JTextField textFieldColour;
    private javax.swing.JTextField textFieldEngineSerial;
    private javax.swing.JTextField textFieldMake;
    private javax.swing.JTextField textFieldModel;
    private javax.swing.JTextField textFieldNextMoTDate;
    private javax.swing.JTextField textFieldNextServiceDate;
    private javax.swing.JTextField textFieldRegistrationNo;
    private javax.swing.JTextField textFieldUsername;
    // End of variables declaration//GEN-END:variables
}
