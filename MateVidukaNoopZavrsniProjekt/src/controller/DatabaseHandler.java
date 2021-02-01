package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {

	private String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://mysql5025.site4now.net/db_a6ecdc_users";
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

}
