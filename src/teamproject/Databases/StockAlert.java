/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ayse
 */
public class StockAlert implements Runnable {

    ArrayList<String> paymentReminders = new ArrayList<>();
    ArrayList<String> stockReminders = new ArrayList<>();
    Connection connection;
    Statement statement;
    ResultSet rs;

    public StockAlert() {
        connect();
        
        //get all of the low stock
        try {
            this.rs = connection.createStatement().executeQuery("select * from sparepart where quantity < threshold");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //add them to a string array
        try {
            while (rs.next()) {
                String reminder = "Part name: " + rs.getString("partName") + ", Vehicle: " + rs.getString("vehicleType");
                stockReminders.add(reminder);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        stockAlert();
        closeConnection();
    }

    private void connect() {
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:GARITSDB.db");
            this.statement = connection.createStatement();
            this.statement.setQueryTimeout(30);  // set timeout to 30 sec.
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
    }

    private void stockAlert() {
        String[] options = new String[2];
        options[0] = "Achknowledge";
        options[1] = "Remind later";
        ArrayList<String> remove = new ArrayList<>();

        for (String s : stockReminders) {
            int i = JOptionPane.showOptionDialog(new JFrame(), s, "Stock alert",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);

            //achknowledge button selected
            if (i == 0) {
                remove.add(s);
            }
        }

        //don't show these ones again
        for (String s : remove) {
            stockReminders.remove(s);
        }
    }

    private void closeConnection() {
        //close connection
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
