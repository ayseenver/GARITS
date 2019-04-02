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
import teamproject.Databases.DB_ImplClass;

public class CustomerList extends javax.swing.JPanel {

    private String username;
    String payment;
    String details;
    private ResultSet rsC;
    private ResultSet rsD;
    private ResultSet rs;
    private ResultSet rsP;
    private ResultSet rsCr;
    private Statement statement;
    String[] nameArray;
    String[] detailArray;
    Customer c = new Customer();
    Connection connection = null;
    DB_ImplClass db = new DB_ImplClass();

    public CustomerList(String username) {
        this.username = username;
        this.payment = "invoice";
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        this.textFieldUserDetails.setText(username);
        connection = db.connect();
        statement = db.getStatement();
        comboBoxPayCustomer.setVisible(false);
        buttonConfirmPayment.setVisible(false);
        labelPayCustomer.setVisible(false);
        labelPayCustomerExplained.setVisible(false);
        try {
            this.rsC = statement.executeQuery("select * from Customer where deleted = 0");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        ShowCustomers();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void ShowCustomers() {
        listCustomers.removeAll();
        ArrayList<String> names = new ArrayList<>();

        try {
            while (rsC.next()) {
                // read the result set
                String name = rsC.getString("name");
                names.add(name);
            }
        } catch (SQLException e) {
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

    private void ShowSelectedCustomersOverview() {
        GetSelectedCustomer();
        textAreaCustomerOverview.setText("Date: " + c.getDateCreated() + '\n' + "Name: " + c.getName() + '\n'
                + "Address: " + c.getAddress() + '\n' + "Post Code: " + c.getPostCode() + '\n' + "Tel.: " + c.getTelephoneNumber()
                + '\n' + "Mobile: " + c.getMobileNumber() + '\n' + "Email: " + c.getEmailAddress());
    }

    private void showCustomerCredit() {
        String credit = "-1";
        try {
            this.rsCr = statement.executeQuery("Select credit from flexiblediscount where discountID = "
                    + "(select discountID from flexiblediscount where discountID = "
                    + "(select flexibleDiscountdiscountID from discountplan where customeraccountaccountID = "
                    + "(select accountID from customeraccount where customerID = "
                    + "(select ID from customer where name = '" + listCustomers.getSelectedValue() + "'))))");
            while (rsCr.next()) {
                credit = rsCr.getString("credit");
            }
            if (!credit.equals("-1")) {
                textAreaCustomerOverview.append("\nCredit: £" + credit);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }

    }

    private void ShowPayCustomer() {
        buttonConfirmPayment.setVisible(false);
        comboBoxPayCustomer.setVisible(false);
        labelPayCustomer.setVisible(false);
        labelPayCustomerExplained.setVisible(false);

        String flexibleDiscount = null;
        try {
            this.rsP = statement.executeQuery("select * from DiscountPlan where CustomerAccountaccountID = (select accountID from CustomerAccount where customerID ="
                    + "(select ID from customer where name = '" + details + "'))");

            while (rsP.next()) {
                flexibleDiscount = rsP.getString("FlexibleDiscountdiscountID");
            }
            if (flexibleDiscount != null) {
                buttonConfirmPayment.setVisible(true);
                comboBoxPayCustomer.setVisible(true);
                labelPayCustomer.setVisible(true);
                labelPayCustomerExplained.setVisible(true);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    private String[] CreateArray(ArrayList<String> customers) {
        String[] newArray = new String[customers.size()];
        newArray = customers.toArray(newArray);
        return newArray;
    }

    private void GetSelectedCustomer() {
        try {
            String sql = ("select * from Customer where name = '" + listCustomers.getSelectedValue()) + "' "
                    + "and deleted = 0";
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

        try {
            c.setName(rsC.getString("name"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            c.setEmailAddress(rsC.getString("emailAddress"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            c.setAddress(rsC.getString("address"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            c.setPostCode(rsC.getString("postCode"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            c.setTelephoneNumber(rsC.getString("telephoneNumber"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            c.setMobileNumber(rsC.getString("mobileNumber"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            c.setFax(rsC.getString("fax"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            c.setDateCreated(rsC.getString("dateCreated"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelCustomers = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelCustomerDetail = new javax.swing.JLabel();
        buttonSearchCustomer = new javax.swing.JButton();
        textFieldSearchCustomer = new javax.swing.JTextField();
        labelPayCustomerExplained = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        listCustomers = new javax.swing.JList<>();
        buttonNewCustomer = new javax.swing.JButton();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        buttonEditCustomer = new javax.swing.JButton();
        labelSelectCustomer = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        listVehicleDetails = new javax.swing.JList<>();
        buttonDone = new javax.swing.JButton();
        buttonEditVehicles = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaCustomerOverview = new javax.swing.JTextArea();
        labelCustomerDetail1 = new javax.swing.JLabel();
        labelPayCustomer = new javax.swing.JLabel();
        buttonConfirmPayment = new javax.swing.JButton();
        comboBoxPayCustomer = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelCustomers.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelCustomers.setText("Customer/Vehicle Record");
        add(labelCustomers, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, -1, -1));

        labelCustomerDetail.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelCustomerDetail.setText("Customer Overview:");
        add(labelCustomerDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, -1, -1));

        buttonSearchCustomer.setText("Search");
        buttonSearchCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchCustomerActionPerformed(evt);
            }
        });
        add(buttonSearchCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, -1, -1));
        add(textFieldSearchCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 130, -1));

        labelPayCustomerExplained.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        labelPayCustomerExplained.setText("*only customer that have a flexible discount ");
        add(labelPayCustomerExplained, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 640, -1, -1));

        listCustomers.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listCustomers.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listCustomersValueChanged(evt);
            }
        });
        jScrollPane10.setViewportView(listCustomers);

        add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 560, 400));

        buttonNewCustomer.setText("Add New Customer");
        buttonNewCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewCustomerActionPerformed(evt);
            }
        });
        add(buttonNewCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, -1, -1));

        textFieldUserDetails.setEditable(false);
        textFieldUserDetails.setFocusable(false);
        textFieldUserDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUserDetailsActionPerformed(evt);
            }
        });
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
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, -1));

        buttonEditCustomer.setText("Edit customer details");
        buttonEditCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditCustomerActionPerformed(evt);
            }
        });
        add(buttonEditCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 620, 170, -1));

        labelSelectCustomer.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelSelectCustomer.setText("Select Customer:");
        add(labelSelectCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        listVehicleDetails.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane11.setViewportView(listVehicleDetails);

        add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 430, 560, 190));

        buttonDone.setText("Done");
        buttonDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDoneActionPerformed(evt);
            }
        });
        add(buttonDone, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 670, 80, 30));

        buttonEditVehicles.setText("Edit vehicles");
        buttonEditVehicles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditVehiclesActionPerformed(evt);
            }
        });
        add(buttonEditVehicles, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 620, -1, -1));

        textAreaCustomerOverview.setColumns(20);
        textAreaCustomerOverview.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        textAreaCustomerOverview.setRows(5);
        jScrollPane1.setViewportView(textAreaCustomerOverview);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 220, 560, 180));

        labelCustomerDetail1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelCustomerDetail1.setText("Vehicle Details: ");
        add(labelCustomerDetail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 400, -1, -1));

        labelPayCustomer.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelPayCustomer.setText("Pay Customer:");
        add(labelPayCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 620, -1, -1));

        buttonConfirmPayment.setText("Confirm");
        buttonConfirmPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmPaymentActionPerformed(evt);
            }
        });
        add(buttonConfirmPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 620, 100, -1));

        comboBoxPayCustomer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cheque", "Next Invoice" }));
        add(comboBoxPayCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 620, 100, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchCustomerActionPerformed
        try {
            String sql = ("select * from Customer where name LIKE '%"
                    + textFieldSearchCustomer.getText() + "%' and deleted = 0");
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
    }//GEN-LAST:event_buttonSearchCustomerActionPerformed

    private void buttonNewCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewCustomerActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new UpdateCustomer(username, "CustomerList");
    }//GEN-LAST:event_buttonNewCustomerActionPerformed

    private void textFieldUserDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserDetailsActionPerformed

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
        new MainMenu(username);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonEditCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditCustomerActionPerformed
        if (listCustomers.getSelectedValue() == null) {
            String mess = "Please choose customer record first!";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
            JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
            f.dispose();
            GetSelectedCustomer();
            db.closeConnection(connection);
            new UpdateCustomer(username, c, "CustomerList");
        }
    }//GEN-LAST:event_buttonEditCustomerActionPerformed

    private void buttonDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDoneActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new MainMenu(username);
    }//GEN-LAST:event_buttonDoneActionPerformed

    private void buttonEditVehiclesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditVehiclesActionPerformed
        if (listCustomers.getSelectedValue() == null) {
            String mess = "Please choose customer record first!";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
            JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
            f.dispose();
            GetSelectedCustomer();
            db.closeConnection(connection);
            new UpdateCustomerVehicle(username, c, "CustomerList");
        }
    }//GEN-LAST:event_buttonEditVehiclesActionPerformed

    private void buttonConfirmPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmPaymentActionPerformed
        String message = "Customer Paid";
        String paymentMethod=comboBoxPayCustomer.getSelectedItem().toString();
        if (listCustomers.getSelectedValue() != null) {
            if (paymentMethod.equals("Cheque")){
            }
            else{
            }
            //set this customer's credit to 0
            try {
                String sql = ("update flexiblediscount set credit = 0 where discountID = "
                        + "(select discountID from flexiblediscount where discountID = "
                        + "(select flexibleDiscountdiscountID from discountplan where customeraccountaccountID = "
                        + "(select accountID from customeraccount where customerID = "
                        + "(select ID from customer where name = '" + listCustomers.getSelectedValue() + "'))))");
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
        } else {
            message = "Please choose customer record first!";
        }
        JOptionPane.showMessageDialog(new JFrame(), message);
    }//GEN-LAST:event_buttonConfirmPaymentActionPerformed

    private void listCustomersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listCustomersValueChanged
        details = (listCustomers.getSelectedValue());
        try {
            String sql = ("select * from vehicle where CustomerID = "
                    + "(select ID from customer where name = '" + details + "') and deleted = 0");
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.rsD = ps.executeQuery();
            ShowPayCustomer();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        ShowSelectedCustomersOverview();
        showCustomerCredit();
        listVehicleDetails.removeAll();
        ArrayList<String> vehicle = new ArrayList<>();

        try {
            while (rsD.next()) {
                String detail = rsD.getString("registrationNumber") + ", "
                        + rsD.getString("make") + ", "
                        + rsD.getString("model") + ", "
                        + rsD.getString("engineSerial")
                        + ", " + rsD.getString("chassisNumber")
                        + ", " + rsD.getString("colour");
                vehicle.add(detail);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        detailArray = CreateArray(vehicle);

        listVehicleDetails.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return detailArray.length;
            }

            public String getElementAt(int i) {
                return detailArray[i];
            }
        });
    }//GEN-LAST:event_listCustomersValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonConfirmPayment;
    private javax.swing.JButton buttonDone;
    private javax.swing.JButton buttonEditCustomer;
    private javax.swing.JButton buttonEditVehicles;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonNewCustomer;
    private javax.swing.JButton buttonSearchCustomer;
    private javax.swing.JComboBox<String> comboBoxPayCustomer;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JLabel labelCustomerDetail;
    private javax.swing.JLabel labelCustomerDetail1;
    private javax.swing.JLabel labelCustomers;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelPayCustomer;
    private javax.swing.JLabel labelPayCustomerExplained;
    private javax.swing.JLabel labelSelectCustomer;
    private javax.swing.JList<String> listCustomers;
    private javax.swing.JList<String> listVehicleDetails;
    private javax.swing.JTextArea textAreaCustomerOverview;
    private javax.swing.JTextField textFieldSearchCustomer;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
