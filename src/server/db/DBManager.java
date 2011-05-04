package server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

	public Connection connection = null;
	private static DBManager instance = null;

	protected DBManager() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			try {
				this.connection = DriverManager.getConnection(
						"jdbc:oracle:thin:@emu.cs.rmit.edu.au:1521:GENERAL",
						"s3252905", "yA6xsuxc");
				connection.setAutoCommit(false);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	public ResultSet getSet(String query) {

		DBManager db = DBManager.getInstance();

		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ResultSet rSet = null;
		try {
			rSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rSet;

	}
	
	/*INSERT, UPDATE, or DELETE statement or an SQL statement that returns nothing,*/
	public int updateSet(String query) {

		DBManager db = DBManager.getInstance();
		int updateStatus = 0 ;
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			updateStatus = statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		//System.out.print(updateStatus);
		
		return updateStatus;

	}

	
	
	

	public Connection getConnection() {
		return connection;
	}

 

}
