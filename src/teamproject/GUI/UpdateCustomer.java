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
    private String previousPage;
    Statement statement;
    Connection connection;
    DB_ImplClass db = new DB_ImplClass();
    ResultSet rc;
    ResultSet rs;
    ResultSet rsA;
    Customer c = new Customer();
    String roleName;
    String discount;
    String[] discountArray;
    String[] taskArray;
    String[] bandArray;
    Map<String, String> discountDetail = new HashMap<>();

    public UpdateCustomer(String username, String previousPage) {
        this.username = username;
        this.previousPage = previousPage;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonUpdateCustomer.setVisible(false); //no customer has been passed in, new customer
        buttonDeleteCustomer.setVisible(false);
        buttonNewCustomer.setVisible(true);
        accountHolderPane.setVisible(false);

        this.textFieldUsername.setText(username);
        connection = db.connect();
        statement = db.getStatement();

        GetRole();

    }

    public UpdateCustomer(String username, Customer c, String previousPage) { //existing custmer
        this(username, previousPage);
        this.c = c;
        buttonUpdateCustomer.setVisible(true);
        buttonDeleteCustomer.setVisible(true);
        buttonNewCustomer.setVisible(false);

        ShowCustomerDetails();
        checkPretickedBoxes();
    }

    public UpdateCustomer(String username, Customer c, String previousPage, String vehicle) { //vehicle page
        this(username, previousPage);
        this.c = c;
        buttonUpdateCustomer.setVisible(false); 
        buttonDeleteCustomer.setVisible(false);
        buttonNewCustomer.setVisible(true);
        accountHolderPane.setVisible(false);

        ShowCustomerDetails();
    }

    private void ShowCustomerDetails() {
        textFieldFullName.setText(c.getName());
        textFieldEmail.setText(c.getEmailAddress());
        textFieldAddress.setText(c.getAddress());
        textFieldPostCode.setText(c.getPostCode());
        textFieldTelephone.setText(c.getTelephoneNumber());
        textFieldMobile.setText(c.getMobileNumber());
        textFieldFax.setText(c.getFax());
    }

    private void UpdateCustomer() {
        c.setAddress(textFieldAddress.getText());
        c.setEmailAddress(textFieldAddress.getText());
        c.setName(textFieldFullName.getText());
        c.setPostCode(textFieldPostCode.getText());
        c.setMobileNumber(textFieldMobile.getText());
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
                        + "((select accountID from customeraccount where CustomerID = '"
                        + c.getID() + "')) ";
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
            double partPercentage = Double.parseDouble(discountDetail.get("Parts"));

            //create a variable discount
            try {
                String sql = "INSERT into variablediscount (sparePartPercentage) "
                        + "values (" + partPercentage + ")";
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
                            + c.getID() + "))";
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
                String sql = "INSERT INTO flexiblediscount (orderValueThisMonth, credit, toBeDeducted) "
                        + "values (0,0,0)";
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
                        + c.getID() + ")))";
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
                            + "VALUES (1, " + c.getID() + ")";
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
                            + "VALUES (0, " + c.getID() + ")";
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
                double partPercentage = Double.parseDouble(discountDetail.get("Parts"));

                //create a variable discount
                try {
                    String sql = "INSERT into variablediscount (sparePartPercentage) "
                            + "values (" + partPercentage + ")";
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
                    String sql = "INSERT INTO flexiblediscount (orderValueThisMonth, credit, toBeDeducted) "
                            + "values (0,0,0)";
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

    private void GetRole() {
        try {
            this.rs = statement.executeQuery("select * from User where deleted = 0");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            while (rs.next()) {
                // read the result set
                String user = rs.getString("username");

                //Code to get Role name from Databse
                if (username.equals(user)) {
                    this.rs = statement.executeQuery("select roleName from User where username = '" + username + "'");

                    roleName = rs.getString("roleName");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //Code To check Which roleName is selected 
        if (!roleName.equalsIgnoreCase("franchisee")) {
            receptionist_menu();
        }
    }

    public void receptionist_menu() {
        checkBoxAccountHolder.setEnabled(false);
        checkBoxConfigurePayLater.setEnabled(false);
        checkBoxDiscountPlan.setEnabled(false);
        buttonSetDiscountPlan.setEnabled(false);
        textFieldPercentage.setEnabled(false);
        comboBoxDiscountPlan.setEnabled(false);
    }

    private void checkPretickedBoxes() {
        checkAccountHolder();
        checkPayLater();
        checkDiscount();
    }

    private void checkAccountHolder() {
        checkBoxAccountHolder.setSelected(false);
        String accountHolder = "";

        try {
            this.rsA = connection.createStatement().executeQuery("select * from CustomerAccount where customerID = "
                    + c.getID());

            try {
                while (rsA.next()) {

                    checkBoxAccountHolder.setSelected(true);
                    accountHolderPane.setVisible(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
        }
    }

    private void checkDiscount() {
        //need to get from discountPlan table, they might be an easier way
        String flexibleID;
        String fixedID;
        String variableID;
        try {
            while (rsA.next()) {
                flexibleID = rsA.getString("FlexibleDiscountDiscountID");
                System.out.println(flexibleID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkPayLater() {
        checkBoxConfigurePayLater.setSelected(false);
        try {
            this.rsA = connection.createStatement().executeQuery("select configuredPayLater from CustomerAccount where customerID = "
                    + c.getID());

            String configuredPayLater = "";
            try {
                while (rsA.next()) {
                    configuredPayLater = rsA.getString("ConfiguredPayLater");

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (configuredPayLater.equals("1")) {
                checkBoxConfigurePayLater.setSelected(true);
            }
        } catch (SQLException e) {
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
        labelDiscountDetail = new javax.swing.JLabel();
        comboBoxDiscountPlan = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        listBusinessType = new javax.swing.JList<>();
        textFieldPercentage = new javax.swing.JTextField();
        buttonSetDiscountPlan = new javax.swing.JButton();
        labelPercentage = new javax.swing.JLabel();
        labelMobile = new javax.swing.JLabel();
        textFieldMobile = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, -1, -1));
        jPanel1.add(textFieldPostCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 360, 260, -1));

        labelPostCode.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelPostCode.setText("*Post Code:");
        jPanel1.add(labelPostCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, -1, -1));

        labelAddress.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelAddress.setText("*Address:");
        jPanel1.add(labelAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, -1, -1));

        labelEmail.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelEmail.setText("*Email:");
        jPanel1.add(labelEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, -1, -1));

        labelFullName.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelFullName.setText("*Full Name:");
        jPanel1.add(labelFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, -1, -1));

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

        labelTelephone.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelTelephone.setText("*Telephone:");
        jPanel1.add(labelTelephone, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, -1, -1));
        jPanel1.add(textFieldFax, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 260, -1));
        jPanel1.add(textFieldFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 260, -1));
        jPanel1.add(textFieldAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 260, 70));
        jPanel1.add(textFieldTelephone, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 390, 260, -1));

        labelCustomerDetails.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelCustomerDetails.setText("Customer Details");
        jPanel1.add(labelCustomerDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

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
        jPanel1.add(checkBoxAccountHolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, -1, -1));

        labelVariableDiscount.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        jPanel1.add(labelVariableDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, -1, -1));

        labelFax1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelFax1.setText("Fax:");
        jPanel1.add(labelFax1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 450, -1, -1));

        buttonNewCustomer.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonNewCustomer.setText("Add Vehicles");
        buttonNewCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewCustomerActionPerformed(evt);
            }
        });
        jPanel1.add(buttonNewCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 480, -1, -1));
        jPanel1.add(textFieldEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, 260, -1));

        buttonUpdateCustomer.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonUpdateCustomer.setText("Update customer details");
        buttonUpdateCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateCustomerActionPerformed(evt);
            }
        });
        jPanel1.add(buttonUpdateCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 650, -1, -1));

        buttonDeleteCustomer.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonDeleteCustomer.setText("Delete Customer");
        buttonDeleteCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteCustomerActionPerformed(evt);
            }
        });
        jPanel1.add(buttonDeleteCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 650, -1, -1));

        checkBoxConfigurePayLater.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        checkBoxConfigurePayLater.setText("Pay Later Option");
        accountHolderPane.add(checkBoxConfigurePayLater);
        checkBoxConfigurePayLater.setBounds(10, 0, 133, 27);

        checkBoxDiscountPlan.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        checkBoxDiscountPlan.setText("Discount plan");
        accountHolderPane.add(checkBoxDiscountPlan);
        checkBoxDiscountPlan.setBounds(10, 30, 115, 29);

        labelDiscountDetail.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelDiscountDetail.setText("Details:");
        accountHolderPane.add(labelDiscountDetail);
        labelDiscountDetail.setBounds(30, 70, 47, 20);

        comboBoxDiscountPlan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Fixed", "Variable", "Flexible" }));
        comboBoxDiscountPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxDiscountPlanActionPerformed(evt);
            }
        });
        accountHolderPane.add(comboBoxDiscountPlan);
        comboBoxDiscountPlan.setBounds(140, 30, 140, 20);

        jScrollPane7.setViewportView(listBusinessType);

        accountHolderPane.add(jScrollPane7);
        jScrollPane7.setBounds(80, 70, 200, 120);
        accountHolderPane.add(textFieldPercentage);
        textFieldPercentage.setBounds(280, 160, 40, 20);

        buttonSetDiscountPlan.setText("Set");
        buttonSetDiscountPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSetDiscountPlanActionPerformed(evt);
            }
        });
        accountHolderPane.add(buttonSetDiscountPlan);
        buttonSetDiscountPlan.setBounds(340, 160, 70, 30);

        labelPercentage.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelPercentage.setText("%");
        accountHolderPane.add(labelPercentage);
        labelPercentage.setBounds(320, 160, 12, 20);

        jPanel1.add(accountHolderPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 240, 420, 230));

        labelMobile.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelMobile.setText("Mobile:");
        jPanel1.add(labelMobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, -1, -1));
        jPanel1.add(textFieldMobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 420, 260, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));
    }// </editor-fold>//GEN-END:initComponents

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
        if (previousPage.equalsIgnoreCase("createJobCustomer")) {
            new CreateJobCustomer(username);
        } else {
            new CustomerList(username);
        }
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonNewCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewCustomerActionPerformed
        if (textFieldFullName.getText().equals("") || textFieldAddress.getText().equals("")
                || textFieldEmail.getText().equals("") || textFieldPostCode.getText().equals("")
                || textFieldTelephone.getText().equals("")) {
            String message = "Please fill in all the boxes";
            JOptionPane.showMessageDialog(new JFrame(), message);
        } else {
            try {
                String sql = ("INSERT INTO Customer (name, address, emailAddress, "
                        + "postCode, telephoneNumber, mobileNumber, fax, dateCreated, deleted) "
                        + "VALUES ('" + textFieldFullName.getText() + "', "
                        + "'" + textFieldAddress.getText() + "', "
                        + "'" + textFieldEmail.getText() + "', "
                        + "'" + textFieldPostCode.getText() + "', "
                        + "'" + textFieldTelephone.getText() + "', "
                        + "'" + textFieldMobile.getText() + "', "
                        + "'" + textFieldFax.getText() + "', "
                        + "date('now'), "
                        + "0)");
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
            
            //get the customer id
            try {
                String sql = ("select ID from customer where id in (select max(id) from customer)");
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
            
            String id = "";
            try{
                while(rs.next()){
                    id = rs.getString("ID");
                }
            }catch(SQLException e){
                System.err.println(e.getMessage());
            }
            
            c.setID(id);

            JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
            f.dispose();
            db.closeConnection(connection);
            new UpdateCustomerVehicle(username, c, "UpdateCustomer");
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
        String message = "Customer Details Updated";
        if (textFieldFullName.getText().equals("") || textFieldAddress.getText().equals("")
                || textFieldEmail.getText().equals("") || textFieldPostCode.getText().equals("")
                || textFieldTelephone.getText().equals("")) {
            message = "Please fill in all the boxes";

        } else {
            //customer exists, update customer details
            try {
                String sql = ("UPDATE Customer "
                        + "SET name = '" + textFieldFullName.getText() + "', "
                        + "address = '" + textFieldAddress.getText() + "', "
                        + "emailAddress = '" + textFieldEmail.getText() + "', "
                        + "postCode = '" + textFieldPostCode.getText() + "', "
                        + "telephoneNumber = '" + textFieldTelephone.getText() + "', "
                        + "mobileNumber = '" + textFieldMobile.getText() + "', "
                        + "fax = '" + textFieldFax.getText() + "' " //optional
                        + "WHERE ID = " + c.getID());
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
                    String sql = ("select * from customeraccount where customerID = " + c.getID());
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
                                    + "where customerID = " + c.getID() + ")";
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
                                    + "where customerID = " + c.getID() + ")";
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
                                    + "(select accountID from customeraccount where CustomerID = " + c.getID() + ")";
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
                                        + c.getID() + ")";
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
            JOptionPane.showMessageDialog(new JFrame(), message);
            //go back to Previous page
            JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
            f.dispose();
            db.closeConnection(connection);
            if (previousPage.equalsIgnoreCase("createJobCustomer")) {
                new CreateJobCustomer(username);
            } else {
                new CustomerList(username);
            }
        }

    }//GEN-LAST:event_buttonUpdateCustomerActionPerformed

    private void buttonDeleteCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteCustomerActionPerformed

        String message = "Are you sure you want to delete Customer?";
        int reply = JOptionPane.showConfirmDialog(null, message, "Delete Customer", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {

            try {
                String sql = ("update customer set deleted = 1 where ID = " + c.getID());
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
                String sql = ("update vehicle set deleted = 1 where CustomerID = " + c.getID());
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

            //go back to customer list
            JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
            f.dispose();
            db.closeConnection(connection);
            if (previousPage.equalsIgnoreCase("createJobCustomer")) {
                new CreateJobCustomer(username);
            } else {
                new CustomerList(username);
            }
        }

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
    private javax.swing.JLabel labelMobile;
    private javax.swing.JLabel labelPercentage;
    private javax.swing.JLabel labelPostCode;
    private javax.swing.JLabel labelTelephone;
    private javax.swing.JLabel labelVariableDiscount;
    private javax.swing.JList<String> listBusinessType;
    private javax.swing.JTextField textFieldAddress;
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JTextField textFieldFax;
    private javax.swing.JTextField textFieldFullName;
    private javax.swing.JTextField textFieldMobile;
    private javax.swing.JTextField textFieldPercentage;
    private javax.swing.JTextField textFieldPostCode;
    private javax.swing.JTextField textFieldTelephone;
    private javax.swing.JTextField textFieldUsername;
    // End of variables declaration//GEN-END:variables
}
