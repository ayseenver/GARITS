/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.GUI;

import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ahmetsesli
 */
public class TeamProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection connection = null;
        try
        {
          // create a database connection
          connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Ayse/Documents/NetBeansProjects/GARITS/GARITSDB.db");
          Statement statement = connection.createStatement();
          statement.setQueryTimeout(30);  // set timeout to 30 sec.

        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          System.err.println(e.getMessage());
        }
        finally
        {
          try
          {
            if(connection != null)
              connection.close();
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
          }
        }
        new LogIn();
    }
}
