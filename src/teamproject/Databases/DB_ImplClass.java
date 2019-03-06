package teamproject.Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_ImplClass implements DBConnectivity {
        Connection connection;
        Statement statement;

        public DB_ImplClass() {
        }

	public Connection connect() {
        connection = null;
        try
        {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:GARITSDB.db");
            this.statement = connection.createStatement();
            this.statement.setQueryTimeout(30);  // set timeout to 30 sec.
        }
        catch(SQLException e)
            {
                // if the error message is "out of memory",
                // it probably means no database file is found
                System.err.println(e.getMessage());
            }
        return connection;
	}

	public void closeConnection(Connection connect) {
                try{
                    connect.close(); 
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
	}

    public Statement getStatement() {
        return statement;
    }
        
}