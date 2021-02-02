package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DatabaseHandler {

	private String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://mysql5025.site4now.net/db_a6ecdc_users?autoReconnect=true";
	String username = "a6ecdc_users";
	String password = "mate2120";

	private static DatabaseHandler handler;

	public static Connection conn;
	public static Statement stmt;

	public DatabaseHandler() {

		connectToDatabase();
	}

	private void connectToDatabase() {

		try {
			// Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
			System.out.println("Connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static DatabaseHandler getInstance() {

		if (handler == null) {

			handler = new DatabaseHandler();
		}
		return handler;
	}

	/**
	 * Metoda koja izvlaci podatke iz baze podataka.
	 * 
	 * @param query
	 * @return ResultSet result
	 */

	public ResultSet execQuery(String query) {
		
		ResultSet result;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(query);
		} catch (SQLException ex) {
			System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
			return null;
		} finally {
		}
		return result;
	}

	/**
	 * Metoda koja sprema podatke u bazu podataka.
	 * 
	 * @param qu
	 * @return boolean
	 */
	public boolean execAction(String qu) {
		try {
			stmt = conn.createStatement();
			stmt.execute(qu);
			return true;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
			System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
			return false;
		} finally {
		}
	}

}
