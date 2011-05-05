package server.db.temp;

//Added by Nathan Folkert:

/***********************************************************************
**
**	File:		/afs/ir/users/c/j/cjurney/public/cs145/SQLBuilder.java
**
**	Author:	Craig Jurney, ITSS
**
**	Written:	April, 2000
**
**	Classes:	SQLBuilder
**
**	Description:	Simple template class used for executing a finite set of DDL against a single datastore.
**
**	Maintenance:
**		04/10/00	cjurney	Initial version
**
**----------------------------------------------------------------------
** Copyright (c) 2000 Board of Trustees, Leland Stanford Jr. University
************************************************************************/

import java.sql.*;

public class SQLBuilder
{
	public static String USERNAME = new String("username");
        public static String PASSWORD = new String("passwd");
	static String[]	Tables = {
					"create table STATE (" +
						"ABBREVIATION char(2) not null, " +
						"NAME varchar(32) not null, " +
						"ENTERED_UNION date null, " + 
						"CAPITAL varchar(32) not null, " +
						"REGION varchar(16) not null, " +
						"AREA int not null, " +
						"FLOWER varchar(32) null, " +
						"BIRD varchar(32) null)"
						};

	public static void
	main(String[] args) throws ClassNotFoundException
	{
		
		// Load the Oracle Driver
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try
		{
		          // Get a connection from the connection factory
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@emu.cs.rmit.edu.au:1521:GENERAL",
					"s3252905", "yA6xsuxc");
			// Show some database/driver metadata
			 

			// Create a Statement object so we can submit SQL statements to the driver
			Statement stmt = con.createStatement();

			// Submit the statement
			for (int i=0; i<Tables.length; ++i)
			{
				System.out.print(Tables[i] + "...");
				int rowsAffected = stmt.executeUpdate(Tables[i]);
				if (rowsAffected == 0)	// DDL statements return rowcount of 0
					System.out.println("OK");
			}

			// Close the statement
			stmt.close();

			// Close the connection
			con.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
          	 }
	}	
}
