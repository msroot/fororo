package server.db.temp;
//Added by Nathan Folkert:

/***********************************************************************
**
**	File:		/afs/ir/users/c/j/cjurney/public/cs145/SQLLoader.java
**
**	Author:	Craig Jurney, ITSS
**
**	Written:	April, 2000
**
**	Classes:	SQLLoader
**
**	Description:	Simple template class used for loading a single table.
**
**	Maintenance:
**		04/10/00	cjurney	Initial version
**
**----------------------------------------------------------------------
** Copyright (c) 2000 Board of Trustees, Leland Stanford Jr. University
************************************************************************/

import java.sql.*;

import oracle.jdbc.driver.SQLUtil;

public class SQLLoader
{
	public static String USERNAME = new String("yourname");
        public static String PASSWORD = new String("yourpasswd");

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
			for (int i=0; i<InsertRows.length; ++i)
			{
				System.out.print(InsertRows[i] + "...");
				int rowsAffected = stmt.executeUpdate(InsertRows[i]);
				if (rowsAffected == 1)
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

	static String[]	InsertRows = {
"insert into TABLE1 values ('AK','Alaska',null,'Juneau','West', 589757,null,null)",
"insert into TABLE1 values ('AL','Alabama',null,'Montgomery','South',  51609,null,null)",
"insert into TABLE1 values ('AR','Arkansas',null,'Little Rock','South',  53104,null,null)",
"insert into TABLE1 values ('AZ','Arizona',null,'Phoenix','West', 113909,null,null)",
"insert into TABLE1 values ('CA','California',null,'Sacramento','West', 158693,null,null)",
"insert into TABLE1 values ('CO','Colorado',null,'Denver','West', 104247,null,null)",
"insert into TABLE1 values ('CT','Connecticut',null,'Hartford','Northeast',   5009,null,null)",
"insert into TABLE1 values ('DE','Delaware',null,'Dover','South',   2057,null,null)",
"insert into TABLE1 values ('FL','Florida',null,'Tallahassee','South',  58560,null,null)",
"insert into TABLE1 values ('GA','Georgia',null,'Atlanta','South',  58876,null,null)",
"insert into TABLE1 values ('HI','Hawaii',null,'Honolulu','West',   6540,null,null)",
"insert into TABLE1 values ('IA','Iowa',null,'Des Moines','North Central',  56290,null,null)",
"insert into TABLE1 values ('ID','Idaho',null,'Boise','West',  83557,null,null)",
"insert into TABLE1 values ('IL','Illinois',null,'Springfield','North Central',  56400,null,null)",
"insert into TABLE1 values ('IN','Indiana',null,'Indianapolis','North Central',  36291,null,null)",
"insert into TABLE1 values ('KS','Kansas',null,'Topeka','North Central',  82264,null,null)",
"insert into TABLE1 values ('KY','Kentucky',null,'Frankfort','South',  40395,null,null)",
"insert into TABLE1 values ('LA','Louisiana',null,'Baton Rouge','South',  48523,null,null)",
"insert into TABLE1 values ('MA','Massachusetts',null,'Boston','Northeast',   8257,null,null)",
"insert into TABLE1 values ('MD','Maryland',null,'Annapolis','South',  10577,null,null)",
"insert into TABLE1 values ('ME','Maine',null,'Augusta','Northeast',  33215,null,null)",
"insert into TABLE1 values ('MI','Michigan',null,'Lansing','North Central',  58216,null,null)",
"insert into TABLE1 values ('MN','Minnesota',null,'St. Paul','North Central',  84068,null,null)",
"insert into TABLE1 values ('MO','Missouri',null,'Jefferson City','North Central',  69686,null,null)",
"insert into TABLE1 values ('MS','Mississippi',null,'Jackson','South',  47716,null,null)",
"insert into TABLE1 values ('MT','Montana',null,'Helena','West', 147138,null,null)",
"insert into TABLE1 values ('NC','North Carolina',null,'Raleigh','South',  52586,null,null)",
"insert into TABLE1 values ('ND','North Dakota',null,'Bismarck','North Central',  70665,null,null)",
"insert into TABLE1 values ('NE','Nebraska',null,'Lincoln','North Central',  77227,null,null)",
"insert into TABLE1 values ('NH','New Hampshire',null,'Concord','Northeast',   9304,null,null)",
"insert into TABLE1 values ('NJ','New Jersey',null,'Trenton','Northeast',   7836,null,null)",
"insert into TABLE1 values ('NM','New Mexico',null,'Santa Fe','West', 121666,null,null)",
"insert into TABLE1 values ('NV','Nevada',null,'Carson City','West', 110540,null,null)",
"insert into TABLE1 values ('NY','New York',null,'Albany','Northeast',  49576,null,null)",
"insert into TABLE1 values ('OH','Ohio',null,'Columbus','North Central',  41222,null,null)",
"insert into TABLE1 values ('OK','Oklahoma',null,'Oklahoma City','South',  69919,null,null)",
"insert into TABLE1 values ('OR','Oregon',null,'Salem','West',  96981,null,null)",
"insert into TABLE1 values ('PA','Pennsylvania',null,'Harrisburg','Northeast',  45333,null,null)",
"insert into TABLE1 values ('RI','Rhode Island',null,'Providence','Northeast',   1214,null,null)",
"insert into TABLE1 values ('SC','South Carolina',null,'Columbia','South',  31055,null,null)",
"insert into TABLE1 values ('SD','South Dakota',null,'Pierre','North Central',  77047,null,null)",
"insert into TABLE1 values ('TN','Tennessee',null,'Nashville','South',  42244,null,null)",
"insert into TABLE1 values ('TX','Texas',null,'Austin','South', 267338,null,null)",
"insert into TABLE1 values ('UT','Utah',null,'Salt Lake City','West',  84916,null,null)",
"insert into TABLE1 values ('VA','Virginia',null,'Richmond','South',  40817,null,null)",
"insert into TABLE1 values ('VT','Vermont',null,'Montpelier','Northeast',   9609,null,null)",
"insert into TABLE1 values ('WA','Washington',null,'Olympia','West',  68192,null,null)",
"insert into TABLE1 values ('WI','Wisconsin',null,'Madison','North Central',  56154,null,null)",
"insert into TABLE1 values ('WV','West Virginia',null,'Charleston','South',  24181,null,null)",
"insert into TABLE1 values ('WY','Wyoming',null,'Cheyenne','West',  97914,null,null)",
	};
}
