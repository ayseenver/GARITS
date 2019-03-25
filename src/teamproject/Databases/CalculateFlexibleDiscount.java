/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.Databases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ayse
 */
public class CalculateFlexibleDiscount implements Runnable {

    Statement statement;
    ResultSet rs;
    DB_ImplClass db = new DB_ImplClass();
    Connection connection;

    @Override
    public void run() {
        connect();
        closeConnection();
        System.out.println("done");
    }

    private void connect() {
        connection = db.connect();
    }
    
    private void CalculateFlexibleDiscount(){
        
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
