package teamproject.Databases;

import java.sql.Connection;
import java.sql.ResultSet;

public class DB_ImplClass implements DBConnectivity {

	/**
	 * 
	 * @param SQL
	 * @param connect
	 */
	public ResultSet read(String SQL, Connection connect) {
		// TODO - implement DB_ImplClass.read
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param SQL
	 * @param connect
	 */
	public int write(String SQL, Connection connect) {
		// TODO - implement DB_ImplClass.write
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param SQL
	 */
	public Connection connect(String SQL) {
		// TODO - implement DB_ImplClass.connect
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param connect
	 */
	public ResultSet closeConnection(Connection connect) {
		// TODO - implement DB_ImplClass.closeConnection
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param DB
	 */
	public static DB_ImplClass DB_ImplClass(String DB) {
		// TODO - implement DB_ImplClass.DB_ImplClass
		throw new UnsupportedOperationException();
	}

}