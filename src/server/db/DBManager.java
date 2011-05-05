package server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import shared.Topic;

public class DBManager {

	public Connection connection = null;
	private static DBManager instance = null;

	/* singleton contructor */
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

	/* @return the instance of DBManager object */
	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	/*
	 * Function for collect and view an SQL statement
	 * 
	 * @param String query
	 * 
	 * @return <ResultSet>
	 */
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

	/*
	 * function for INSERT, UPDATE, or DELETE statement or an SQL statement that
	 * returns nothing
	 * 
	 * @param String query
	 */
	public int updateSet(String query) {

		try {

			// WE need new connection for update
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@emu.cs.rmit.edu.au:1521:GENERAL",
					"s3252905", "yA6xsuxc");
			Statement stmt = con.createStatement();
			int rowsAffected = stmt.executeUpdate(query);

			if (rowsAffected == 1) {
				System.out.println("OK" + rowsAffected);
			}

			stmt.close();
			con.close();
			return rowsAffected;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;

	}



	public Connection getConnection() {
		return connection;
	}

}
