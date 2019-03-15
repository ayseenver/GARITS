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

/**
 *
 * @author Ayse
 */
public class Automation implements Runnable {

    Connection connection;
    Statement statement;
    ResultSet rs;

    @Override
    public void run() {
        connect();
        backupDatabase();
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

    private void backupDatabase() {
        connection = null;
        //backup
        try {
            connection.createStatement().executeUpdate("backup to database.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkDueMoT() {
        //get all vehicles with MoTs due in 7 days (5 working days)
        try {
            this.rs = statement.executeQuery("select * from vehicle where ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
