package teamproject.GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;
import teamproject.Customer_Account.Customer;
import teamproject.Customer_Account.Vehicle;
import teamproject.Databases.DB_ImplClass;

public class UpdateCustomer extends javax.swing.JPanel {

    private String username;
    Statement statement;
    Connection connection;
    DB_ImplClass db = new DB_ImplClass();
    ResultSet rc;
    Customer c = new Customer();
    Vehicle v = new Vehicle();
    String Discount;

    public UpdateCustomer(String username) {
        this.username = username;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.textFieldUsername.setText(username);
        connection = db.connect();
        statement = db.getStatement();
    }

    public UpdateCustomer(String username, Customer c, Vehicle v) {
        this(username);
        this.c = c;
        this.v = v;

        ShowCustomerDetails();
    }

    private void ShowCustomerDetails() {
        textFieldFullName.setText(c.getName());
        textFieldEmail.setText(c.getEmailAddress());
        textFieldAddress.setText(c.getAddress());
        textFieldPostCode.setText(c.getPostCode());
        textFieldTelephone.setText(c.getTelephoneNumber());
        textFieldFax.setText(c.getFax());
    }

    private void UpdateCustomer() {
        c.setAddress(textFieldAddress.getText());
        c.setEmailAddress(textFieldAddress.getText());
        c.setName(textFieldFullName.getText());
        c.setPostCode(textFieldPostCode.getText());
        c.setTelephoneNumber(textFieldTelephone.getText());
        try {
            c.setFax(textFieldFax.getText());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        textFieldPostCode = new javax.swing.JTextField();
        labelPostCode = new javax.swing.JLabel();
        labelAddress = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        labelFullName = new javax.swing.JLabel();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        textFieldUsername = new javax.swing.JTextField();
        labelTelephone = new javax.swing.JLabel();
        textFieldFax = new javax.swing.JTextField();
        textFieldFullName = new javax.swing.JTextField();
        textFieldAddress = new javax.swing.JTextField();
        textFieldTelephone = new javax.swing.JTextField();
        buttonConfirmDiscount = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        labelCustomerDetails = new javax.swing.JLabel();
        buttonBack = new javax.swing.JButton();
        textFieldPercentage = new javax.swing.JTextField();
        checkBoxAccountHolder = new javax.swing.JCheckBox();
        labelCustomerInformation = new javax.swing.JLabel();
        labelVariableDiscount = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelPercentage = new javax.swing.JLabel();
        labelDiscountDetail = new javax.swing.JLabel();
        checkBoxDiscountPlan = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        textFieldSearchDiscountDetails = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        listBusinessType = new javax.swing.JList<>();
        checkBoxConfigurePayLater = new javax.swing.JCheckBox();
        buttonSearchDiscountDetails = new javax.swing.JButton();
        comboBoxDiscountPlan = new javax.swing.JComboBox<>();
        labelFax1 = new javax.swing.JLabel();
        buttonSetDiscountPlan1 = new javax.swing.JButton();
        buttonUpdateCustomer = new javax.swing.JButton();
        buttonVehicle = new javax.swing.JButton();
        textFieldEmail = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, -1, -1));
        jPanel1.add(textFieldPostCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 260, -1));

        labelPostCode.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelPostCode.setText("Post Code:");
        jPanel1.add(labelPostCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, -1, -1));

        labelAddress.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelAddress.setText("Address:");
        jPanel1.add(labelAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, -1, -1));

        labelEmail.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelEmail.setText("Email:");
        jPanel1.add(labelEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, -1, -1));

        labelFullName.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelFullName.setText("Full Name:");
        jPanel1.add(labelFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, -1, -1));

        labelLoggedIn.setText("Logged In as:");
        jPanel1.add(labelLoggedIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        buttonExit.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonExit.setText("Exit");
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });
        jPanel1.add(buttonExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 0, -1, -1));
        jPanel1.add(textFieldUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 220, 30));

        labelTelephone.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelTelephone.setText("Telephone:");
        jPanel1.add(labelTelephone, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, -1, -1));
        jPanel1.add(textFieldFax, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 470, 260, -1));
        jPanel1.add(textFieldFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, 260, -1));
        jPanel1.add(textFieldAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, 260, 70));
        jPanel1.add(textFieldTelephone, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 440, 260, -1));

        buttonConfirmDiscount.setText("Confirm");
        jPanel1.add(buttonConfirmDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 320, 100, -1));

        jLabel59.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel59.setText("Full Name:");
        jPanel1.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, -1, -1));

        labelCustomerDetails.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelCustomerDetails.setText("Customer Details");
        jPanel1.add(labelCustomerDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        buttonBack.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });
        jPanel1.add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, -1));
        jPanel1.add(textFieldPercentage, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 460, 40, -1));

        checkBoxAccountHolder.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        checkBoxAccountHolder.setText("Account Holder");
        jPanel1.add(checkBoxAccountHolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 260, -1, -1));

        labelCustomerInformation.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        labelCustomerInformation.setText("*Customer needs to be an Account");
        jPanel1.add(labelCustomerInformation, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 270, -1, -1));

        labelVariableDiscount.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        jPanel1.add(labelVariableDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, -1, -1));

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel5.setText("holder to have a discount plan");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 280, -1, -1));

        labelPercentage.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelPercentage.setText("%");
        jPanel1.add(labelPercentage, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 460, -1, 20));

        labelDiscountDetail.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelDiscountDetail.setText("Details:");
        jPanel1.add(labelDiscountDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 350, -1, 20));

        checkBoxDiscountPlan.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        checkBoxDiscountPlan.setText("Discount plan");
        jPanel1.add(checkBoxDiscountPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 320, -1, -1));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel2.setText("or/and pay later");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 290, -1, -1));

        textFieldSearchDiscountDetails.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        textFieldSearchDiscountDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSearchDiscountDetailsActionPerformed(evt);
            }
        });
        jPanel1.add(textFieldSearchDiscountDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, 120, 20));

        listBusinessType.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane7.setViewportView(listBusinessType);

        jPanel1.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, 200, 120));

        checkBoxConfigurePayLater.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        checkBoxConfigurePayLater.setText("Pay Later Option");
        checkBoxConfigurePayLater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxConfigurePayLaterActionPerformed(evt);
            }
        });
        jPanel1.add(checkBoxConfigurePayLater, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 290, -1, -1));

        buttonSearchDiscountDetails.setText("Search");
        buttonSearchDiscountDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchDiscountDetailsActionPerformed(evt);
            }
        });
        jPanel1.add(buttonSearchDiscountDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 350, 80, 20));

        comboBoxDiscountPlan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Fixed", "Variable", "Flexible" }));
        comboBoxDiscountPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxDiscountPlanActionPerformed(evt);
            }
        });
        jPanel1.add(comboBoxDiscountPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 320, -1, -1));

        labelFax1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelFax1.setText("Fax:");
        jPanel1.add(labelFax1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 470, -1, -1));

        buttonSetDiscountPlan1.setText("Set");
        buttonSetDiscountPlan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSetDiscountPlan1ActionPerformed(evt);
            }
        });
        jPanel1.add(buttonSetDiscountPlan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 460, 70, 30));

        buttonUpdateCustomer.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonUpdateCustomer.setText("Update customer details");
        buttonUpdateCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateCustomerActionPerformed(evt);
            }
        });
        jPanel1.add(buttonUpdateCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 660, -1, -1));

        buttonVehicle.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonVehicle.setText("View Vehicles");
        buttonVehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVehicleActionPerformed(evt);
            }
        });
        jPanel1.add(buttonVehicle, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 660, -1, -1));
        jPanel1.add(textFieldEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 290, 260, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldSearchDiscountDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchDiscountDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSearchDiscountDetailsActionPerformed

    private void buttonSearchDiscountDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchDiscountDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchDiscountDetailsActionPerformed

    private void buttonSetDiscountPlan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSetDiscountPlan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSetDiscountPlan1ActionPerformed

    private void checkBoxConfigurePayLaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxConfigurePayLaterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxConfigurePayLaterActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new CustomerList(username);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonUpdateCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateCustomerActionPerformed
        String originalName;
        String originalAddress;
        //This does not work
        try {
            originalName = c.getName();
        } catch (Exception e) {
            originalName = "";
        }
        try {
            originalAddress = c.getAddress();
        } catch (Exception e) {
            originalAddress = "";
        }

        if (originalName.equals("") && originalAddress.equals("")) {
            //customer does not exist, create new customer
            try {
                String sql = ("INSERT INTO Customer (name, address, emailAddress, "
                        + "postCode, telephoneNumber, fax, dateCreated) "
                        + "VALUES ('" + textFieldFullName.getText() + "', "
                        + "'" + textFieldAddress.getText() + "', "
                        + "'" + textFieldAddress.getText() + "', "
                        + "'" + textFieldPostCode.getText() + "', "
                        + "'" + textFieldTelephone.getText() + "', "
                        + "'" + textFieldFax.getText() + "', " //optional
                        + " date('now')");
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
            UpdateCustomer();
        } else {
            //customer exists, update customer and vehicles
            try {
                String sql = ("UPDATE Customer "
                        + "SET name = '" + textFieldFullName.getText() + "', "
                        + "address = '" + textFieldAddress.getText() + "', "
                        + "emailAddress = '" + textFieldEmail.getText() + "', "
                        + "postCode = '" + textFieldPostCode.getText() + "', "
                        + "telephoneNumber = '" + textFieldTelephone.getText() + "', "
                        + "fax = '" + textFieldFax.getText() + "' " //optional
                        + "WHERE name = '" + originalName + "' "
                        + "AND address = '" + originalAddress + "'");
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
            UpdateCustomer();
        }
    }//GEN-LAST:event_buttonUpdateCustomerActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        db.closeConnection(connection);
        System.exit(0);
    }//GEN-LAST:event_buttonExitActionPerformed

    private void comboBoxDiscountPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxDiscountPlanActionPerformed
        Discount = comboBoxDiscountPlan.getSelectedItem().toString();
    }//GEN-LAST:event_comboBoxDiscountPlanActionPerformed

    private void buttonVehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVehicleActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new UpdateCustomerVehicle(username, c);
    }//GEN-LAST:event_buttonVehicleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonConfirmDiscount;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonSearchDiscountDetails;
    private javax.swing.JButton buttonSetDiscountPlan1;
    private javax.swing.JButton buttonUpdateCustomer;
    private javax.swing.JButton buttonVehicle;
    private javax.swing.JCheckBox checkBoxAccountHolder;
    private javax.swing.JCheckBox checkBoxConfigurePayLater;
    private javax.swing.JCheckBox checkBoxDiscountPlan;
    private javax.swing.JComboBox<String> comboBoxDiscountPlan;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel labelAddress;
    private javax.swing.JLabel labelCustomerDetails;
    private javax.swing.JLabel labelCustomerInformation;
    private javax.swing.JLabel labelDiscountDetail;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelFax1;
    private javax.swing.JLabel labelFullName;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelPercentage;
    private javax.swing.JLabel labelPostCode;
    private javax.swing.JLabel labelTelephone;
    private javax.swing.JLabel labelVariableDiscount;
    private javax.swing.JList<String> listBusinessType;
    private javax.swing.JTextField textFieldAddress;
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JTextField textFieldFax;
    private javax.swing.JTextField textFieldFullName;
    private javax.swing.JTextField textFieldPercentage;
    private javax.swing.JTextField textFieldPostCode;
    private javax.swing.JTextField textFieldSearchDiscountDetails;
    private javax.swing.JTextField textFieldTelephone;
    private javax.swing.JTextField textFieldUsername;
    // End of variables declaration//GEN-END:variables
}
