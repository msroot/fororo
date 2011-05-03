package server.db;

import java.sql.*;

class SimpleJDBC {
	
	
/*http://www.rgagnon.com/javadetails/java-0112.html
http://infolab.stanford.edu/~ullman/fcdb/oracle/or-jdbc.html#0.1_sample
http://infolab.stanford.edu/~ullman/fcdb/oracle/SimpleJDBC.java
https://inside.cs.rmit.edu.au/support/inside:oraclehowto  

user: s3252905
pass: yA6xsuxc
*/

	
	public static void main(String args[]) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@emu.cs.rmit.edu.au:1521:GENERAL",
				"s3252905", "yA6xsuxc");

		Statement stmt = conn.createStatement();

		ResultSet rset = stmt.executeQuery("SELECT NAME FROM FUSER");

		while (rset.next())
			System.out.println(rset.getString(1));
	}
}
