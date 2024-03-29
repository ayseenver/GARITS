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
import teamproject.User_Accounts.User;

/**
 *
 * @author ahmetsesli
 */
public class UserAccount extends javax.swing.JPanel {

    private String username;
    Statement statement;
    Connection connection = null;
    DB_ImplClass db = new DB_ImplClass();
    ResultSet rs;
    String[] userArray;
    User u = new User();

    /**
     * Creates new form NewJPanel
     */
    public UserAccount(String username) {
        this.username = username;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.textFieldUserDetails.setText(username);
        connection = db.connect();
        statement = db.getStatement();

        labelHourlyRate.setVisible(false);
        textFieldHourlyRate.setVisible(false);

        UpdateUserList();
    }

    private String[] CreateArray(ArrayList<String> tasks) {
        String[] newArray = new String[tasks.size()];
        newArray = tasks.toArray(newArray);
        return newArray;
    }

    private void UpdateUserList() {
        try {
            this.rs = statement.executeQuery("select * from User where deleted = 0");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }

        listUsers.removeAll();
        ArrayList<String> users = new ArrayList<>();

        try {
            while (rs.next()) {
                // read the result set
                String user = rs.getString("username");
                users.add(user);
            }
        } catch (SQLException e) {
        }

        userArray = CreateArray(users);

        listUsers.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return userArray.length;
            }

            public String getElementAt(int i) {
                return userArray[i];
            }
        });
    }

    private void SelectUser() {
        try {
            String sql = ("select * from user where username = '" + listUsers.getSelectedValue() + "'");
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
    }

    private void SelectMechanic() {
        try {
            String sql = ("select * from mechanic where userusername in "
                    + "(select username from user where username = '" + listUsers.getSelectedValue() + "')");
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelUserAccounts = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelFirstName = new javax.swing.JLabel();
        buttonDone = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        listUsers = new javax.swing.JList<>();
        labelSelectUser = new javax.swing.JLabel();
        buttonNewUser = new javax.swing.JButton();
        buttonSearch = new javax.swing.JButton();
        textFieldFirstName = new javax.swing.JTextField();
        labelUserDetail = new javax.swing.JLabel();
        textFieldSearch = new javax.swing.JTextField();
        labelRole = new javax.swing.JLabel();
        textFieldUserID = new javax.swing.JTextField();
        textFieldLastName = new javax.swing.JTextField();
        labelLastName = new javax.swing.JLabel();
        buttonUpdateDetails = new javax.swing.JButton();
        buttonDeleteUser = new javax.swing.JButton();
        comboBoxRole = new javax.swing.JComboBox<>();
        labelUserID = new javax.swing.JLabel();
        textFieldUserDetails = new javax.swing.JTextField();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        labelPassword = new javax.swing.JLabel();
        textFieldPassword = new javax.swing.JTextField();
        labelHourlyRate = new javax.swing.JLabel();
        textFieldHourlyRate = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelUserAccounts.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelUserAccounts.setText("User Accounts");
        add(labelUserAccounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, -1, -1));

        labelFirstName.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        labelFirstName.setText("First Name:");
        add(labelFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, -1, -1));

        buttonDone.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonDone.setText("Done");
        buttonDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDoneActionPerformed(evt);
            }
        });
        add(buttonDone, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 650, -1, -1));

        listUsers.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        listUsers.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listUsersValueChanged(evt);
            }
        });
        jScrollPane9.setViewportView(listUsers);

        add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 1140, 240));

        labelSelectUser.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelSelectUser.setText("Select User:");
        add(labelSelectUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        buttonNewUser.setText("Create New User");
        buttonNewUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewUserActionPerformed(evt);
            }
        });
        add(buttonNewUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 580, 130, 30));

        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });
        add(buttonSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, -1, -1));
        add(textFieldFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 510, 130, -1));

        labelUserDetail.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelUserDetail.setText("User Details:");
        add(labelUserDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, -1, -1));
        add(textFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 150, -1));

        labelRole.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        labelRole.setText("Role:");
        add(labelRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 550, -1, -1));
        add(textFieldUserID, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 510, 130, -1));
        add(textFieldLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 510, 130, -1));

        labelLastName.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        labelLastName.setText("Last Name:");
        add(labelLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 510, -1, -1));

        buttonUpdateDetails.setText("Update Details");
        buttonUpdateDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateDetailsActionPerformed(evt);
            }
        });
        add(buttonUpdateDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 580, 130, 30));

        buttonDeleteUser.setText("Delete User");
        buttonDeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteUserActionPerformed(evt);
            }
        });
        add(buttonDeleteUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 580, 130, 30));

        comboBoxRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "receptionist", "mechanic", "foreperson", "franchisee", "admin" }));
        comboBoxRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxRoleActionPerformed(evt);
            }
        });
        add(comboBoxRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 550, 130, -1));

        labelUserID.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        labelUserID.setText("Username:");
        add(labelUserID, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 510, -1, -1));

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
        add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, -1));

        labelPassword.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        labelPassword.setText("Password:");
        add(labelPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 550, -1, -1));
        add(textFieldPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 550, 130, -1));

        labelHourlyRate.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        labelHourlyRate.setText("Hourly Rate:");
        add(labelHourlyRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 550, -1, -1));

        textFieldHourlyRate.setText("105");
        add(textFieldHourlyRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 550, 130, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDoneActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        db.closeConnection(connection);
        new MainMenu(username);
    }//GEN-LAST:event_buttonDoneActionPerformed

    private void buttonNewUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewUserActionPerformed
        String message = "New User Created";
        if (textFieldFirstName.getText().isEmpty() || textFieldLastName.getText().isEmpty()
                || textFieldPassword.getText().isEmpty() || comboBoxRole.getSelectedItem().toString().isEmpty()
                || textFieldUserID.getText().isEmpty()) {
            message = "Please fill in all the boxes";

        }
        if (textFieldHourlyRate.isVisible() && textFieldHourlyRate.getText().isEmpty()) {
            message = "Please fill in all the boxes";
        } else {

            User newU = new User();
            newU.setFirstName(textFieldFirstName.getText());
            newU.setLastName(textFieldLastName.getText());
            newU.setPassword(textFieldPassword.getText());
            newU.setRoleName(comboBoxRole.getSelectedItem().toString());
            newU.setUsername(textFieldUserID.getText());

            try {
                String sql = ("insert into user (username, password, roleName, firstName, surname, deleted)"
                        + "values("
                        + "'" + newU.getUsername() + "',"
                        + "'" + newU.getPassword() + "',"
                        + "'" + newU.getRoleName() + "',"
                        + "'" + newU.getFirstName() + "',"
                        + "'" + newU.getLastName() + "', 0)");
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

            if (comboBoxRole.getSelectedItem().toString().equals("mechanic") || comboBoxRole.getSelectedItem().toString().equals("foreperson")) {
                try {
                    String sql = ("insert into mechanic (hourlyRate, userusername)"
                            + "values(" + textFieldHourlyRate.getText() + ", (select username from user where "
                            + "username = '" + newU.getUsername() + "'))");
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
        JOptionPane.showMessageDialog(new JFrame(), message);
        try {
            this.rs = statement.executeQuery("select * from User");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }

        UpdateUserList();
    }//GEN-LAST:event_buttonNewUserActionPerformed

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        try {
            String sql = ("select * from user where username LIKE '%"
                    + textFieldSearch.getText() + "%' "
                    + "or firstName LIKE '%" + textFieldSearch.getText() + "%' "
                    + "or surname LIKE '%" + textFieldSearch.getText() + "%' and deleted = 0");
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
        UpdateUserList();
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void buttonUpdateDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateDetailsActionPerformed
        try {
            String sql = ("Update user "
                    + "set username = '" + textFieldUserID.getText() + "',"
                    + "password = '" + textFieldPassword.getText() + "',"
                    + "roleName = '" + comboBoxRole.getSelectedItem().toString() + "',"
                    + "firstName = '" + textFieldFirstName.getText() + "',"
                    + "surname = '" + textFieldLastName.getText() + "'"
                    + "where username = '" + u.getUsername() + "'");
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

        if (textFieldHourlyRate.isVisible() && (!(textFieldHourlyRate.getText().isEmpty()))) {
            try {
                String sql = ("Update mechanic "
                        + "set hourlyRate = '" + textFieldHourlyRate.getText() + "',"
                        + "userusername = '" + textFieldUserID.getText() + "' "
                        + "where userusername = '" + u.getUsername() + "'");
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
            String mess = "Please fill in all the boxes";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }

        String mess = "Update complete";
        JOptionPane.showMessageDialog(new JFrame(), mess);
    }//GEN-LAST:event_buttonUpdateDetailsActionPerformed

    private void buttonDeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteUserActionPerformed
        String selected = listUsers.getSelectedValue();
        if (selected != null) {
            SelectUser();
            int reply = JOptionPane.showConfirmDialog(null, "Are you Sure you want to delete the user?", "Delete User", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                //delete selected user from database.
                try {
                    String sql = ("update user set deleted = 1 "
                            + "where username = '" + rs.getString("username") + "'");
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
                UpdateUserList();
            }
        } else {
            String mess = "Select a user";
            JOptionPane.showMessageDialog(new JFrame(), mess);
        }
    }//GEN-LAST:event_buttonDeleteUserActionPerformed

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

    private void comboBoxRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxRoleActionPerformed
        if (comboBoxRole.getSelectedItem().toString().equals("mechanic")
                || comboBoxRole.getSelectedItem().toString().equals("foreperson")) {
            labelHourlyRate.setVisible(true);
            textFieldHourlyRate.setVisible(true);
        } else {
            labelHourlyRate.setVisible(false);
            textFieldHourlyRate.setVisible(false);
        }
    }//GEN-LAST:event_comboBoxRoleActionPerformed

    private void listUsersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listUsersValueChanged
        SelectUser();

        //update the user object with the details selected
        try {
            u.setFirstName(rs.getString("firstName"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            u.setLastName(rs.getString("surname"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            u.setPassword(rs.getString("password"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            u.setRoleName(rs.getString("roleName"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            u.setUsername(rs.getString("username"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //display the user details at the boxes at the botton
        textFieldUserID.setText(u.getUsername());
        textFieldFirstName.setText(u.getFirstName());
        textFieldLastName.setText(u.getLastName());
        textFieldPassword.setText(u.getPassword());
        comboBoxRole.setSelectedItem(u.getRoleName());

        //if it's a mechanic or foreperson, get their hourly rate.
        if (u.getRoleName().equals("mechanic") || u.getRoleName().equals("foreperson")) {
            labelHourlyRate.setVisible(true);
            textFieldHourlyRate.setVisible(true);

            SelectMechanic();
            try {
                textFieldHourlyRate.setText(rs.getString("hourlyRate"));
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            labelHourlyRate.setVisible(false);
            textFieldHourlyRate.setVisible(false);
        }
    }//GEN-LAST:event_listUsersValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonDeleteUser;
    private javax.swing.JButton buttonDone;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonNewUser;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JButton buttonUpdateDetails;
    private javax.swing.JComboBox<String> comboBoxRole;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel labelFirstName;
    private javax.swing.JLabel labelHourlyRate;
    private javax.swing.JLabel labelLastName;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelRole;
    private javax.swing.JLabel labelSelectUser;
    private javax.swing.JLabel labelUserAccounts;
    private javax.swing.JLabel labelUserDetail;
    private javax.swing.JLabel labelUserID;
    private javax.swing.JList<String> listUsers;
    private javax.swing.JTextField textFieldFirstName;
    private javax.swing.JTextField textFieldHourlyRate;
    private javax.swing.JTextField textFieldLastName;
    private javax.swing.JTextField textFieldPassword;
    private javax.swing.JTextField textFieldSearch;
    private javax.swing.JTextField textFieldUserDetails;
    private javax.swing.JTextField textFieldUserID;
    // End of variables declaration//GEN-END:variables
}
