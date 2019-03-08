package teamproject.Databases;

import java.sql.Connection;
import java.sql.ResultSet;

public interface DBConnectivity {

	/**
	 * 
	 * @param SQL
	 */
	abstract Connection connect();

	/**
	 * 
	 * @param connect
	 */
	void closeConnection(Connection connect);
        
        void Backup(Connection c);
        
        void Restore(Connection c);

}