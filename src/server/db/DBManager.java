package server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.utils.DBPreferences;

public class DBManager {

	public DBPreferences prefs = new DBPreferences();
	public String db_user = prefs.user;
	public String db_pass = prefs.pass;
	public String db_server = prefs.server;
	public String db_database = prefs.database;
	public String db_port = prefs.port;

	public Connection connection = null;
	private static DBManager instance = null;

	/**
	 * Singleton constructor for DBManager
	 */
	protected DBManager() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			try {
				this.connection = DriverManager.getConnection(
						"jdbc:oracle:thin:@" + db_server + ":" + db_port + ":"
								+ db_database + "", db_user, db_pass);

				connection.setAutoCommit(false);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return the instance of DBManager object 	
	 */
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
	 * General function for INSERT, UPDATE, or DELETE 
	 * statement or an SQL statement that
	 * @return int rowsAffected
	 * @param String query
	 */
	public int updateSet(String query) {

		try {

			// WE need new connection for update
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@"
					+ db_server + ":" + db_port + ":" + db_database + "", db_user, db_pass);

			Statement stmt = con.createStatement();
			int rowsAffected = stmt.executeUpdate(query);
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
