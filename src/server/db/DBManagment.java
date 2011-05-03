package server.db;

import java.sql.*;

public class DBManagment {

	public Connection connection = null;

 
	public DBManagment() {
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
		DBManagment connectionObj = new DBManagment();
		Connection cn = connectionObj.getConnection();

		Statement stmt = cn.createStatement();
		
		
		ResultSet set = connectionObj.getSet("SELECT * FROM FUSER");
		while (set.next()) {
			System.out.println("User:" + set.getString("NAME") + " pass:"
					+ set.getString("PASSWORD"));
		}
		stmt.close();

	}

}
