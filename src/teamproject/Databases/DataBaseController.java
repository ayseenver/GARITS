package teamproject.Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseController {
    
    /*
        public void EstablishConnection(){
            Connection connection = null;
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
        }
    */

	public void backUpDatabase() {
		// TODO - implement DataBaseController.backUpDatabase
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param details
	 */
	public void restoreDatabase(String[] details) {
		// TODO - implement DataBaseController.restoreDatabase
		throw new UnsupportedOperationException();
	}

	public static DataBaseController DataBaseController() {
		// TODO - implement DataBaseController.DataBaseController
		throw new UnsupportedOperationException();
	}

}