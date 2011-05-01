package server.db;

import java.sql.*;

public class DBManagment {
	/*
	 * http://www.rgagnon.com/javadetails/java-0112.html
	 * http://infolab.stanford.edu/~ullman/fcdb/oracle/or-jdbc.html#0.1_sample
	 * http://infolab.stanford.edu/~ullman/fcdb/oracle/SimpleJDBC.java
	 */

	// RMIT oracle General
	static String user = "s3289732";
	static String pwd = "pass";
	static String port = "1521";
	static String db = "sadi2";
	static String server = "";
	
	static String url = "jdbc:oracle:"+user+":"+pwd+"@//server.local:"+port+"/"+db+"";

	// "jdbc:oracle:thin:@//server.local:1521/sadi2";

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection conn = DriverManager.getConnection(url, user, pwd);

		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("select BANNER from SYS.V_$VERSION");
		while (rset.next()) {
			System.out.println(rset.getString(1));
		}
		stmt.close();
		System.out.println("Ok.");
	}
}
