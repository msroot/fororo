package server.db;

import java.sql.*;

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

	public Connection getConnection() {
		return connection;
	}

	public static void main(String[] args) throws SQLException {
		DBManager db = DBManager.getInstance();
		Connection connect = db.getConnection();
		Statement statement = connect.createStatement();

		ResultSet set = db.getSet("SELECT * FROM FUSER");
		while (set.next()) {
			System.out.println("User:" + set.getString("NAME") + " pass:"
					+ set.getString("PASSWORD"));
		}
		statement.close();

	}

}
