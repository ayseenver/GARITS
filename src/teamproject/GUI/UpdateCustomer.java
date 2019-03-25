package teamproject.GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import teamproject.Customer_Account.Customer;
import teamproject.Databases.DB_ImplClass;

public class UpdateCustomer extends javax.swing.JPanel {

    private String username;
    Statement statement;
    Connection connection;
    DB_ImplClass db = new DB_ImplClass();
    ResultSet rc;
    ResultSet rs;
    Customer c = new Customer();
    String discount;
    String[] discountArray;
    String[] taskArray;
    String[] bandArray;
    Map<String, String> discountDetail = new HashMap<>();

    public UpdateCustomer(String username) {
        this.username = username;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonUpdateCustomer.setVisible(false); //no customer has been passed in, new customer
        buttonDeleteCustomer.setVisible(false);
        buttonNewCustomer.setVisible(true);
        accountHolderPane.setVisible(false);

        this.textFieldUsername.setText(username);
        connection = db.connect();
        statement = db.getStatement();
    }

    public UpdateCustomer(String username, Customer c) { //existing custmer
        this(username);
        this.c = c;
        buttonUpdateCustomer.setVisible(true);
        buttonDeleteCustomer.setVisible(true);
        buttonNewCustomer.setVisible(false);

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

    private void ShowVariableTasks() {
        try {
            this.rs = statement.executeQuery("select * from Task");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("MoT");
        tasks.add("Service");
        tasks.add("Repair");
        tasks.add("Parts");

        //add all tasks to task list
        try {
            while (rs.next()) {
                // read the result set
                String task = rs.getString("description");
                tasks.add(task);
            }
        } catch (SQLException e) {
        }

        taskArray = CreateArray(tasks);

        listBusinessType.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return taskArray.length;
            }

            public String getElementAt(int i) {
                return taskArray[i];
            }
        });
    }

    private void ShowFlexiBands() {
        try {
            this.rs = statement.executeQuery("select * from flexibands");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        ArrayList<String> bands = new ArrayList<>();

        //add all tasks to task list
        try {
            while (rs.next()) {
                // read the result set
                String band = rs.getString("bandRange");
                bands.add(band);
            }
        } catch (SQLException e) {
        }

        bandArray = CreateArray(bands);

        listBusinessType.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return bandArray.length;
            }

            public String getElementAt(int i) {
                return bandArray[i];
            }
        });
    }

    private void ExistingCustomerDiscount() {
        //check for which discount it is
        String type = comboBoxDiscountPlan.getSelectedItem().toString();
        if (type.equals("Fixed")) {
            //get the overall percentage
            String percentage = discountDetail.get("Overall");
            try {
                String sql = "INSERT INTO FixedDiscount (percentage) VALUES ("
                        + Double.parseDouble(percentage) + ")";
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

            try {
                String sql = "INSERT INTO DiscountPlan "
                        + "(FixedDiscountdiscountID, CustomerAccountaccountID) "
                        + "VALUES ((select discountID from fixedDiscount where discountID in "
                        + "(select max(discountID) from fixedDiscount)), "
                        + "((select accountID from customeraccount where CustomerID = "
                        + "(select ID from customer where name = '" + textFieldFullName.getText() + "' "
                        + "and address = '" + textFieldAddress.getText() + "'))))";
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
        } else if (type.equals("Variable")) {
            double motPercentage = Double.parseDouble(discountDetail.get("MoT"));
            double servicePercentage = Double.parseDouble(discountDetail.get("Service"));
            double partPercentage = Double.parseDouble(discountDetail.get("Parts"));
            double repairPercentage = Double.parseDouble(discountDetail.get("Repair"));

            //create a variable discount
            try {
                String sql = "INSERT into variablediscount (MoTPercentage, servicePercentage, "
                        + "sparePartPercentage, repairPercentage) "
                        + "values (" + motPercentage + ", " + servicePercentage + ", "
                        + partPercentage + ", " + repairPercentage + ")";
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

            for (Map.Entry<String, String> entry : discountDetail.entrySet()) {
                String task = entry.getKey();
                String percentage = entry.getValue();

                //get the ID for the selected task
                try {
                    this.rs = statement.executeQuery("select taskID from Task where description = '" + task + "'");
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }

                String taskID = "";

                try {
                    while (rs.next()) {
                        taskID = rs.getString("taskID");

                        //create a task - variable discount record
                        try {
                            String sql = "INSERT into task_variablediscount (TasktaskID, VariableDiscountdiscountID, percentage) "
                                    + "values ((select taskID from task where taskID = '" + taskID + "'), "
                                    + "(select discountID from variablediscount where discountID in (select max(discountID) "
                                    + "from variablediscount)), " + percentage + ")";
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
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }

                //insert this discount plan into the customer record
                try {
                    String sql = "INSERT INTO DiscountPlan "
                            + "(VariableDiscountdiscountID, CustomerAccountaccountID) "
                            + "VALUES ((select discountID from VariableDiscount where discountID in "
                            + "(select max(discountID) from VariableDiscount)), "
                            + "(select accountID from customeraccount where CustomerID = "
                            + "(select ID from customer where name = '" + textFieldFullName.getText() + "' "
                            + "and address = '" + textFieldAddress.getText() + "')))";
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
        } else if (type.equals("Flexible")) {
            try {
                String sql = "INSERT INTO flexiblediscount (orderValueThisMonth, credit) "
                        + "values (0,0)";
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

            //insert into the discount plan table
            try {
                String sql = "INSERT INTO DiscountPlan "
                        + "(FlexibleDiscountdiscountID, CustomerAccountaccountID) "
                        + "VALUES ((select discountID from flexibleDiscount where discountID in "
                        + "(select max(discountID) from flexibleDiscount)), "
                        + "(select accountID from customeraccount where CustomerID = "
                        + "(select ID from customer where name = '" + textFieldFullName.getText() + "' "
                        + "and address = '" + textFieldAddress.getText() + "')))";
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

            //get all of the flexi bands, create a record for them
            for (Map.Entry<String, String> entry : discountDetail.entrySet()) {
                String band = entry.getKey();
                double percentage = Double.parseDouble(entry.getValue());

                //get the ID for the selected band
                try {
                    this.rs = statement.executeQuery("select bandID from flexibands where bandRange = '" + band + "'");
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }

                String bandID = "";

                try {
                    while (rs.next()) {
                        bandID = rs.getString("bandID");
                    }
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }

                //create a flexiband - flexibleDiscount record
                try {
                    String sql = "INSERT into flexibands_flexiblediscount (flexibleDiscountdiscountID, FlexiBandsbandID, percentage) "
                            + "values ((select discountID from flexibleDiscount where discountID in(select max(discountID) from flexibleDiscount)), "
                            + "(select bandID from flexibands where bandID = '" + bandID + "'), " + percentage + ")";
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
        }
    }

    private String[] CreateArray(ArrayList<String> discounts) {
        String[] newArray = new String[discounts.size()];
        newArray = discounts.toArray(newArray);
        return newArray;
    }

    private void CreateAccountHolder() {
//if "account holder" selected, insert this customer into account holder table.
        if (checkBoxAccountHolder.isSelected()) {
            if (checkBoxConfigurePayLater.isSelected()) {
                try {
                    String sql = "INSERT INTO CustomerAccount (configuredPayLater, CustomerID) "
                            + "VALUES (1, (select ID from customer where name = '" + textFieldFullName.getText() + "' "
                            + "and address = '" + textFieldAddress.getText() + "'))";
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
                try {
                    String sql = "INSERT INTO CustomerAccount (configuredPayLater, CustomerID) "
                            + "VALUES (0, (select ID from customer where name = '" + textFieldFullName.getText() + "' "
                            + "and address = '" + textFieldAddress.getText() + "'))";
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
        }

        //if a discount plan is selected, get the details and insert into database
        if (checkBoxDiscountPlan.isSelected()) {
            String type = comboBoxDiscountPlan.getSelectedItem().toString();
            if (type.equals("Fixed")) {
                //get the overall percentage
                String percentage = discountDetail.get("Overall");
                try {
                    String sql = "INSERT INTO FixedDiscount (percentage) VALUES ("
                            + Double.parseDouble(percentage) + ")";
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

                try {
                    String sql = "INSERT INTO DiscountPlan "
                            + "(FixedDiscountdiscountID, CustomerAccountaccountID) "
                            + "VALUES ((select discountID from fixedDiscount where discountID in "
                            + "(select max(discountID) from fixedDiscount)), "
                            + "(select accountID from customerAccount where accountID in "
                            + "(select max(accountID) from customerAccount)))";
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
            } else if (type.equals("Variable")) {
                double motPercentage = Double.parseDouble(discountDetail.get("MoT"));
                double servicePercentage = Double.parseDouble(discountDetail.get("Service"));
                double partPercentage = Double.parseDouble(discountDetail.get("Parts"));
                double repairPercentage = Double.parseDouble(discountDetail.get("Repair"));

                //create a variable discount
                try {
                    String sql = "INSERT into variablediscount (MoTPercentage, servicePercentage, "
                            + "sparePartPercentage, repairPercentage) "
                            + "values (" + motPercentage + ", " + servicePercentage + ", "
                            + partPercentage + ", " + repairPercentage + ")";
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

                for (Map.Entry<String, String> entry : discountDetail.entrySet()) {
                    String task = entry.getKey();
                    String percentage = entry.getValue();

                    //get the ID for the selected task
                    try {
                        this.rs = statement.executeQuery("select taskID from Task where description = '" + task + "'");
                    } catch (SQLException e) {
                        System.err.println(e.getMessage());
                    }

                    String taskID = "";

                    try {
                        while (rs.next()) {
                            taskID = rs.getString("taskID");
                            //create a task - variable discount record
                            try {
                                String sql = "INSERT into task_variablediscount (TasktaskID, VariableDiscountdiscountID, percentage) "
                                        + "values ((select taskID from task where taskID = '" + taskID + "'), "
                                        + "(select discountID from variablediscount where discountID in (select max(discountID) "
                                        + "from variablediscount)), " + percentage + ")";
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
                    } catch (SQLException e) {
                        System.err.println(e.getMessage());
                    }

                    //insert this discount plan into the customer record
                    try {
                        String sql = "INSERT INTO DiscountPlan "
                                + "(VariableDiscountdiscountID, CustomerAccountaccountID) "
                                + "VALUES ((select discountID from VariableDiscount where discountID in "
                                + "(select max(discountID) from VariableDiscount)), "
                                + "(select accountID from customerAccount where accountID in "
                                + "(select max(accountID) from customerAccount)))";
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
            } else if (type.equals("Flexible")) {
                try {
                    String sql = "INSERT INTO flexiblediscount (orderValueThisMonth, credit) "
                            + "values (0,0)";
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

                //insert into the discount plan table
                try {
                    String sql = "INSERT INTO DiscountPlan "
                            + "(FlexibleDiscountdiscountID, CustomerAccountaccountID) "
                            + "VALUES ((select discountID from flexibleDiscount where discountID in "
                            + "(select max(discountID) from flexibleDiscount)), "
                            + "(select accountID from customerAccount where accountID in "
                            + "(select max(accountID) from customerAccount)))";
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

                //get all of the flexi bands, create a record for them
                for (Map.Entry<String, String> entry : discountDetail.entrySet()) {
                    String band = entry.getKey();
                    double percentage = Double.parseDouble(entry.getValue());

                    //get the ID for the selected band
                    try {
                        this.rs = statement.executeQuery("select bandID from flexibands where bandRange = '" + band + "'");
                    } catch (SQLException e) {
                        System.err.println(e.getMessage());
                    }

                    String bandID = "";

                    try {
                        while (rs.next()) {
                            bandID = rs.getString("bandID");
                        }
                    } catch (SQLException e) {
                        System.err.println(e.getMessage());
                    }

                    //create a flexiband - flexibleDiscount record
                    try {
                        String sql = "INSERT into flexibands_flexiblediscount (flexibleDiscountdiscountID, FlexiBandsbandID, percentage) "
                                + "values ((select discountID from flexibleDiscount where discountID in(select max(discountID) from flexibleDiscount)), "
                                + "(select bandID from flexibands where bandID = '" + bandID + "'), " + percentage + ")";
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
            }
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
        labelCustomerDetails = new javax.swing.JLabel();
        buttonBack = new javax.swing.JButton();
        checkBoxAccountHolder = new javax.swing.JCheckBox();
        labelVariableDiscount = new javax.swing.JLabel();
        labelFax1 = new javax.swing.JLabel();
        buttonNewCustomer = new javax.swing.JButton();
        textFieldEmail = new javax.swing.JTextField();
        buttonUpdateCustomer = new javax.swing.JButton();
        buttonDeleteCustomer = new javax.swing.JButton();
        accountHolderPane = new javax.swing.JLayeredPane();
        checkBoxConfigurePayLater = new javax.swing.JCheckBox();
        checkBoxDiscountPlan = new javax.swing.JCheckBox();
        textFieldSearchDiscountDetails = new javax.swing.JTextField();
        labelDiscountDetail = new javax.swing.JLabel();
        comboBoxDiscountPlan = new javax.swing.JComboBox<>();
        buttonSearchDiscountDetails = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        listBusinessType = new javax.swing.JList<>();
        textFieldPercentage = new javax.swing.JTextField();
        buttonSetDiscountPlan = new javax.swing.JButton();
        labelPercentage = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, -1, -1));
        jPanel1.add(textFieldPostCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 260, -1));

        labelPostCode.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelPostCode.setText("*Post Code:");
        jPanel1.add(labelPostCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 410, -1, -1));

        labelAddress.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelAddress.setText("*Address:");
        jPanel1.add(labelAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, -1, -1));

        labelEmail.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelEmail.setText("*Email:");
        jPanel1.add(labelEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, -1, -1));

        labelFullName.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelFullName.setText("*Full Name:");
        jPanel1.add(labelFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, -1, -1));

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
        jPanel1.add(textFieldUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 220, 30));

        labelTelephone.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelTelephone.setText("*Telephone:");
        jPanel1.add(labelTelephone, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 440, -1, -1));
        jPanel1.add(textFieldFax, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 470, 260, -1));
        jPanel1.add(textFieldFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, 260, -1));
        jPanel1.add(textFieldAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, 260, 70));
        jPanel1.add(textFieldTelephone, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 440, 260, -1));

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

        checkBoxAccountHolder.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        checkBoxAccountHolder.setText("Account Holder");
        checkBoxAccountHolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxAccountHolderActionPerformed(evt);
            }
        });
        jPanel1.add(checkBoxAccountHolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 260, -1, -1));

        labelVariableDiscount.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        jPanel1.add(labelVariableDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, -1, -1));

        labelFax1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelFax1.setText("Fax:");
        jPanel1.add(labelFax1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 470, -1, -1));

        buttonNewCustomer.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonNewCustomer.setText("New customer");
        buttonNewCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewCustomerActionPerformed(evt);
            }
        });
        jPanel1.add(buttonNewCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 500, -1, -1));
        jPanel1.add(textFieldEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 290, 260, -1));

        buttonUpdateCustomer.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonUpdateCustomer.setText("Update customer details");
        buttonUpdateCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateCustomerActionPerformed(evt);
            }
        });
        jPanel1.add(buttonUpdateCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 670, -1, -1));

        buttonDeleteCustomer.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonDeleteCustomer.setText("Delete Customer");
        buttonDeleteCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteCustomerActionPerformed(evt);
            }
        });
        jPanel1.add(buttonDeleteCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 670, -1, -1));

        checkBoxConfigurePayLater.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        checkBoxConfigurePayLater.setText("Pay Later Option");
        accountHolderPane.add(checkBoxConfigurePayLater);
        checkBoxConfigurePayLater.setBounds(10, 0, 133, 27);

        checkBoxDiscountPlan.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        checkBoxDiscountPlan.setText("Discount plan");
        accountHolderPane.add(checkBoxDiscountPlan);
        checkBoxDiscountPlan.setBounds(10, 30, 115, 29);

        textFieldSearchDiscountDetails.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        textFieldSearchDiscountDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSearchDiscountDetailsActionPerformed(evt);
            }
        });
        accountHolderPane.add(textFieldSearchDiscountDetails);
        textFieldSearchDiscountDetails.setBounds(80, 60, 120, 20);

        labelDiscountDetail.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelDiscountDetail.setText("Details:");
        accountHolderPane.add(labelDiscountDetail);
        labelDiscountDetail.setBounds(30, 60, 47, 20);

        comboBoxDiscountPlan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Fixed", "Variable", "Flexible" }));
        comboBoxDiscountPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxDiscountPlanActionPerformed(evt);
            }
        });
        accountHolderPane.add(comboBoxDiscountPlan);
        comboBoxDiscountPlan.setBounds(140, 30, 63, 20);

        buttonSearchDiscountDetails.setText("Search");
        buttonSearchDiscountDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchDiscountDetailsActionPerformed(evt);
            }
        });
        accountHolderPane.add(buttonSearchDiscountDetails);
        buttonSearchDiscountDetails.setBounds(200, 60, 80, 20);

        jScrollPane7.setViewportView(listBusinessType);

        accountHolderPane.add(jScrollPane7);
        jScrollPane7.setBounds(80, 80, 200, 120);
        accountHolderPane.add(textFieldPercentage);
        textFieldPercentage.setBounds(280, 170, 40, 20);

        buttonSetDiscountPlan.setText("Set");
        buttonSetDiscountPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSetDiscountPlanActionPerformed(evt);
            }
        });
        accountHolderPane.add(buttonSetDiscountPlan);
        buttonSetDiscountPlan.setBounds(340, 170, 70, 30);

        labelPercentage.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelPercentage.setText("%");
        accountHolderPane.add(labelPercentage);
        labelPercentage.setBounds(320, 170, 12, 20);

        jPanel1.add(accountHolderPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 290, 420, 230));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldSearchDiscountDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchDiscountDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSearchDiscountDetailsActionPerformed

    private void buttonSearchDiscountDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchDiscountDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchDiscountDetailsActionPerformed

    private void buttonSetDiscountPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSetDiscountPlanActionPerformed
        String selected = listBusinessType.getSelectedValue();
        String percentage = textFieldPercentage.getText();
        if (selected == null || percentage.isEmpty()) {
            String mess = "Select an item and input a percentage";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
            discountDetail.put(selected, percentage);
            String mess = "Configured successfully!";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }//GEN-LAST:event_buttonSetDiscountPlanActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new CustomerList(username);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonNewCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewCustomerActionPerformed
        if (textFieldFullName.getText().equals("") || textFieldAddress.getText().equals("")
                || textFieldEmail.getText().equals("") || textFieldPostCode.getText().equals("")
                || textFieldTelephone.getText().equals("")) {
            String mess = "Please fill in all the boxes";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
            try {
                String sql = ("INSERT INTO Customer (name, address, emailAddress, "
                        + "postCode, telephoneNumber, fax, dateCreated, deleted) "
                        + "VALUES ('" + textFieldFullName.getText() + "', "
                        + "'" + textFieldAddress.getText() + "', "
                        + "'" + textFieldEmail.getText() + "', "
                        + "'" + textFieldPostCode.getText() + "', "
                        + "'" + textFieldTelephone.getText() + "', "
                        + "'" + textFieldFax.getText() + "', "
                        + "date('now'), "
                        + "1)");
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

            CreateAccountHolder();

            JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
            f.dispose();
            db.closeConnection(connection);
            new UpdateCustomerVehicle(username, c);
        }
    }//GEN-LAST:event_buttonNewCustomerActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new LogIn();
    }//GEN-LAST:event_buttonExitActionPerformed

    private void comboBoxDiscountPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxDiscountPlanActionPerformed
        discount = comboBoxDiscountPlan.getSelectedItem().toString();
        ArrayList<String> discounts = new ArrayList<>();
        if (discount.equals("Fixed")) {
            discounts.add("Overall");
            discountArray = CreateArray(discounts);

            listBusinessType.setModel(new javax.swing.AbstractListModel<String>() {
                public int getSize() {
                    return discountArray.length;
                }

                public String getElementAt(int i) {
                    return discountArray[i];
                }
            });
        } else if (discount.equals("Variable")) {
            ShowVariableTasks();
        } else if (discount.equals("Flexible")) {
            ShowFlexiBands();
        }
    }//GEN-LAST:event_comboBoxDiscountPlanActionPerformed

    private void buttonUpdateCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateCustomerActionPerformed
        String originalName = c.getName();
        String originalAddress = c.getAddress();

        if (textFieldFullName.getText().equals("") || textFieldAddress.getText().equals("")
                || textFieldEmail.getText().equals("") || textFieldPostCode.getText().equals("")
                || textFieldTelephone.getText().equals("")) {
            String mess = "Please fill in all the boxes";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
            //customer exists, update customer details
            try {
                String sql = ("UPDATE Customer "
                        + "SET name = '" + textFieldFullName.getText() + "', "
                        + "address = '" + textFieldAddress.getText() + "', "
                        + "emailAddress = '" + textFieldEmail.getText() + "', "
                        + "postCode = '" + textFieldPostCode.getText() + "', "
                        + "telephoneNumber = '" + textFieldTelephone.getText() + "', "
                        + "fax = '" + textFieldFax.getText() + "' " //optional
                        + "WHERE ID = (select ID from customer where name = '" + originalName + "' "
                        + "and address = '" + originalAddress + "')");
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

            if (checkBoxAccountHolder.isSelected()) {
                try {
                    String sql = ("select * from customeraccount where customerID = (select ID from customer where name = '" + originalName + "' "
                            + "and address = '" + originalAddress + "')");
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

                String accountID = "";
                try {
                    while (rs.next()) {
                        accountID = rs.getString("accountID");
                    }
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }

                //if customer is not already an account holder
                if (accountID.equals("")) {
                    CreateAccountHolder();
                } else {
                    //get the account holder details and update them.
                    //update the pay later
                    if (checkBoxConfigurePayLater.isSelected()) {
                        try {
                            String sql = "update CustomerAccount "
                                    + "set configuredPayLater = 1 "
                                    + "where customerID = (select ID from customer where name = '" + textFieldFullName.getText() + "' "
                                    + "and address = '" + textFieldAddress.getText() + "')";
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
                        try {
                            String sql = "update CustomerAccount "
                                    + "set configuredPayLater = 0 "
                                    + "where customerID = (select ID from customer where name = '" + textFieldFullName.getText() + "' "
                                    + "and address = '" + textFieldAddress.getText() + "')";
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

                    if (checkBoxDiscountPlan.isSelected()) {
                        //see if they have any discount plans
                        try {
                            String sql = "select * from discountplan "
                                    + "where CustomerAccountaccountID = "
                                    + "(select accountID from customeraccount where CustomerID = (select ID from customer where name = '" + textFieldFullName.getText() + "' "
                                    + "and address = '" + textFieldAddress.getText() + "'))";
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

                        String discountAccountID = "";
                        try {
                            while (rs.next()) {
                                discountAccountID = rs.getString("CustomerAccountaccountID");
                            }
                        } catch (SQLException e) {
                            System.err.println(e.getMessage());
                        }

                        if (discountAccountID.equals("")) {
                            //there are no discount plans.
                            ExistingCustomerDiscount();
                        } else {
                            //set the existing plans to null and create the new ones.
                            //first, set all existing plans to null
                            try {
                                String sql = "update discountplan set FixedDiscountdiscountID = null, "
                                        + "variablediscountdiscountID = null, "
                                        + "flexibleDiscountdiscountID = null "
                                        + "where CustomerAccountaccountID = "
                                        + "(select accountID from customeraccount where CustomerID = "
                                        + "(select ID from customer where name = '" + textFieldFullName.getText() + "' "
                                        + "and address = '" + textFieldAddress.getText() + "'))";
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

                            //delete the null plans
                            try {
                                String sql = "delete from discountplan where "
                                        + "fixeddiscountdiscountID is null and "
                                        + "variablediscountdiscountID is null and "
                                        + "flexiblediscountdiscountID is null";
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
                            //create a new discount plan
                            ExistingCustomerDiscount();
                        }
                    }
                }
            }

            //go back to customer list
            JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
            f.dispose();
            db.closeConnection(connection);
            new CustomerList(username);
        }
    }//GEN-LAST:event_buttonUpdateCustomerActionPerformed

    private void buttonDeleteCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteCustomerActionPerformed
        try {
            String sql = ("select ID from customer where name = '" + c.getName() + "' "
                    + "and address = '" + c.getAddress() + "'");
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

        String customerID = "";

        try {
            while (rs.next()) {
                customerID = rs.getString("ID");
                try {
                    String sql = ("update customer set deleted = 1 where ID = " + customerID);
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

                //delete all vehciles with this CustomerID
                try {
                    String sql = ("update vehicle set deleted = 1 where CustomerID = " + customerID);
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

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //go back to customer list
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new CustomerList(username);
    }//GEN-LAST:event_buttonDeleteCustomerActionPerformed

    private void checkBoxAccountHolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxAccountHolderActionPerformed
        if (checkBoxAccountHolder.isSelected()) {
            accountHolderPane.setVisible(true);
        } else {
            accountHolderPane.setVisible(false);
        }
    }//GEN-LAST:event_checkBoxAccountHolderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane accountHolderPane;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonDeleteCustomer;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonNewCustomer;
    private javax.swing.JButton buttonSearchDiscountDetails;
    private javax.swing.JButton buttonSetDiscountPlan;
    private javax.swing.JButton buttonUpdateCustomer;
    private javax.swing.JCheckBox checkBoxAccountHolder;
    private javax.swing.JCheckBox checkBoxConfigurePayLater;
    private javax.swing.JCheckBox checkBoxDiscountPlan;
    private javax.swing.JComboBox<String> comboBoxDiscountPlan;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel labelAddress;
    private javax.swing.JLabel labelCustomerDetails;
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
