package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

/**
 * 
 * DatabaseHandler je klasa zaduzena za povezivanje na bazu putem @url, @username, @password.
 * Prilikom izrade DatabaseHandlera koristim pattern Singleton, tako da uvijek isti objekt DatabaseHandlera se proteze kroz cijeli
 * projekt umjesto da se na svakom Frameu stvara novi objekt. Spajamo se na udaljenu mySQL bazu	koju sam na mjesec dana kreirao na SmarterASP.net
 * 
 * @author Mate
 *
 */

public class DatabaseHandler {

	private String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://mysql5025.site4now.net/db_a6ecdc_users?autoRecconect=true";
	String username = "a6ecdc_users";
	String password = "mate2120";

	private static DatabaseHandler handler;

	public static Connection conn;
	public static Statement stmt;

	public DatabaseHandler() {

		connectToDatabase();
	}
	
	/**
	 * Metoda kojom se spajamo na bazu
	 */

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
	
	/**
	 * Glavna metoda da bi Singleton funkcionirao, kada se pozove
	 * provjerava se da li vec postoji handler, ondnosno da li je trenutni null, 
	 * ako je, stvara se novi objekt koji ce se dodjeliti svim Frameovima, ukoliko nije null
	 * vraca se postojeci handler
	 * 
	 * @return handler
	 */

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
