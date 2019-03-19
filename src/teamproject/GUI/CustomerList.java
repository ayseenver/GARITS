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

        this.textFieldUserDetails.setText(username);
        connection = db.connect();
        statement = db.getStatement();

        try {
            this.rsC = statement.executeQuery("select * from Customer");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

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

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private String[] CreateArray(ArrayList<String> customers) {
        String[] newArray = new String[customers.size()];
        newArray = customers.toArray(newArray);
        return newArray;
    }

    private void GetSelectedCustomer() {
        try {
            String sql = ("select * from Customer where name = '" + listCustomers.getSelectedValue()) + "'";
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
            c.setFax(rsC.getString("fax"));
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
        buttonView = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        listCustomers = new javax.swing.JList<>();
        buttonNewCustomer = new javax.swing.JButton();
        labelVariableCustomer = new javax.swing.JLabel();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        buttonEditCustomer = new javax.swing.JButton();
        buttonConfirmPayment = new javax.swing.JButton();
        labelPayCustomer = new javax.swing.JLabel();
        comboBoxPaymentMethod = new javax.swing.JComboBox<>();
        labelSelectCustomer = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        listDetails = new javax.swing.JList<>();
        buttonDone = new javax.swing.JButton();
        buttonEditVehicles = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelCustomers.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelCustomers.setText("Customer/Vehicle Record");
        add(labelCustomers, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, -1, -1));

        labelCustomerDetail.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelCustomerDetail.setText("Vehicle Details: ");
        add(labelCustomerDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, -1, -1));

        buttonSearchCustomer.setText("Search");
        buttonSearchCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchCustomerActionPerformed(evt);
            }
        });
        add(buttonSearchCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, -1, -1));
        add(textFieldSearchCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 130, -1));

        buttonView.setText("View");
        buttonView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonViewActionPerformed(evt);
            }
        });
        add(buttonView, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 620, 100, -1));

        listCustomers.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane10.setViewportView(listCustomers);

        add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 560, 400));

        buttonNewCustomer.setText("Add New Customer");
        buttonNewCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewCustomerActionPerformed(evt);
            }
        });
        add(buttonNewCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, -1, -1));

        labelVariableCustomer.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        labelVariableCustomer.setText("*only customer that have flexible discount ");
        add(labelVariableCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 650, -1, -1));

        textFieldUserDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUserDetailsActionPerformed(evt);
            }
        });
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
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, -1));

        buttonEditCustomer.setText("Edit customer details");
        buttonEditCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditCustomerActionPerformed(evt);
            }
        });
        add(buttonEditCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 620, 170, -1));

        buttonConfirmPayment.setText("Confirm");
        add(buttonConfirmPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 620, 100, -1));

        labelPayCustomer.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelPayCustomer.setText("Pay Customer:");
        add(labelPayCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 620, -1, -1));

        comboBoxPaymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "discount", "send cheque" }));
        add(comboBoxPaymentMethod, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 620, -1, -1));

        labelSelectCustomer.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelSelectCustomer.setText("Select Customer:");
        add(labelSelectCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        listDetails.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane11.setViewportView(listDetails);

        add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 220, 560, 390));

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
        add(buttonEditVehicles, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 620, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchCustomerActionPerformed
      
        try {
          String sql = ("select name from Customer where name LIKE '%" + textFieldSearchCustomer.getText() + "%'"); 
        PreparedStatement ps = null;
       
                try {
                    ps = connection.prepareStatement(sql);
                     
                } catch (Exception e) {
                    e.printStackTrace();
                     
                }
                this.rsD = ps.executeQuery();
           
       } catch (SQLException e) {
               System.err.println(e.getMessage());
              
            }
        
       listCustomers.removeAll();
       ArrayList<String> names = new ArrayList<>();

        try {
            while (rsD.next()) {
                // read the result set
                String name = rsD.getString("name");
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
       
    }//GEN-LAST:event_buttonSearchCustomerActionPerformed

    private void buttonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonViewActionPerformed
        if (listCustomers.getSelectedValue() == null) {
            String mess = "Please choose customer first!";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
            details = (listCustomers.getSelectedValue());

            try {
                String sql = ("select * from vehicle where CustomerID = "
                        + "(select ID from customer where name = '" + details + "')");
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.rsD = ps.executeQuery();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            listDetails.removeAll();
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

            listDetails.setModel(new javax.swing.AbstractListModel<String>() {
                public int getSize() {
                    return detailArray.length;
                }

                public String getElementAt(int i) {
                    return detailArray[i];
                }
            });
        }
    }//GEN-LAST:event_buttonViewActionPerformed

    private void buttonNewCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewCustomerActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new UpdateCustomer(username);
    }//GEN-LAST:event_buttonNewCustomerActionPerformed

    private void textFieldUserDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserDetailsActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        db.closeConnection(connection);
        System.exit(0);
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
            new UpdateCustomer(username, c);
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
            new UpdateCustomerVehicle(username, c);
        }
    }//GEN-LAST:event_buttonEditVehiclesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonConfirmPayment;
    private javax.swing.JButton buttonDone;
    private javax.swing.JButton buttonEditCustomer;
    private javax.swing.JButton buttonEditVehicles;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonNewCustomer;
    private javax.swing.JButton buttonSearchCustomer;
    private javax.swing.JButton buttonView;
    private javax.swing.JComboBox<String> comboBoxPaymentMethod;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JLabel labelCustomerDetail;
    private javax.swing.JLabel labelCustomers;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelPayCustomer;
    private javax.swing.JLabel labelSelectCustomer;
    private javax.swing.JLabel labelVariableCustomer;
    private javax.swing.JList<String> listCustomers;
    private javax.swing.JList<String> listDetails;
    private javax.swing.JTextField textFieldSearchCustomer;
    private javax.swing.JTextField textFieldUserDetails;
    // End of variables declaration//GEN-END:variables
}
