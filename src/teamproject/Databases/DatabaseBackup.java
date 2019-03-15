/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ayse
 */
public class DatabaseBackup implements Runnable {

    Connection connection;
    Statement statement;

    @Override
    public void run() {
        backupDatabase();
    }

    private void backupDatabase() {
        connection = null;
        
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

        //backup
        try {
            connection.createStatement().executeUpdate("backup to database.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //close connection
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
