package teamproject.Databases;

import java.sql.Connection;
import java.sql.ResultSet;

public interface DBConnectivity {

	/**
	 * 
	 * @param SQL
	 * @param connect
	 */
	abstract ResultSet read(String SQL, Connection connect);

	/**
	 * 
	 * @param SQL
	 * @param connect
	 */
	abstract int write(String SQL, Connection connect);

	/**
	 * 
	 * @param SQL
	 */
	abstract Connection connect(String SQL);

	/**
	 * 
	 * @param connect
	 */
	ResultSet closeConnection(Connection connect);

}