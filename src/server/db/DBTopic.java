package server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import shared.ForumThread;
import shared.Topic;

public class DBTopic {
	static DBManager db = DBManager.getInstance();
	static Connection connection = db.getConnection();
	

	public static Topic getById(String topicId) {
/*		String id = null;
		String name = null;
		String description = null;
		Boolean isActive = null;*/
		try {
			ResultSet set = db.getSet("SELECT * FROM FTOPIC WHERE ID='"
					+ topicId + "'");


//			while (set.next()) {
//				id = set.getString("ID");
//				name = set.getString("NAME");
//				description = set.getString("DESCRIPTION");
//				isActive = Boolean.parseBoolean(set.getString("ISACTIVE"));*/
//
//				public Topic(String id, String name, String description, boolean isActive, String userName, String created){
//				return new Topic(id, name, description, isActive);
//			}
			set.next();			
			return mapTopic(set);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * Returns the current row id
	 * 
	 * @param String rowID
	 * 
	 * @return String id
	 */
	public static String findIDByRowID(String rowID) {
		String id = null;
		String table = "FTOPIC";

		try {
			// select ID from ftopic where rowid='AABg0yAAEAAAhZVAAF'
			ResultSet set = db.getSet("SELECT ID FROM  " + table
					+ "  WHERE rowid='" + rowID + "'");

			while (set.next()) {
				id = set.getString("ID");

				System.out.print(rowID + "-" + id);
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Topic> getAll() {
		List<Topic> topics = new ArrayList<Topic>();

		String id = null;
		String name = null;
		String description = null;
		Boolean isActive = null;
		try {
			ResultSet set = db.getSet("SELECT * FROM FTOPIC");

//			while (set.next()) {
//				id = set.getString("ID");
//				name = set.getString("NAME");
//				description = set.getString("DESCRIPTION");
//				isActive = Boolean.parseBoolean(set.getString("ISACTIVE"));

				//topics.add(new Topic(id, name, description, isActive));
				
			//}

			while (set.next()) {
				topics.add(mapTopic(set));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return topics;
	}

	/*
	 * Creates and new topic, with update statment after finds the post ID and
	 * creates and return new post. we use 2 sql statments that we dont use the
	 * DBManager update function
	 * 
	 * @param <Topic>
	 * 
	 * @return <Topic> or null
	 */
	public static Topic create(Topic topic) {

		String name = topic.name();
		String description = topic.description();
		Boolean isActive = topic.isActive();
		String user = topic.userName();
		
		try {

			String q = "insert into FTOPIC (NAME, DESCRIPTION, ISACTIVE,USERID,CREATED) values ('"
					+ name
					+ "', '"
					+ description
					+ "','"
					+ isActive.toString()
					+ "','"+user+"', '"+now()+"')";

			Connection connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@emu.cs.rmit.edu.au:1521:GENERAL",
					"s3252905", "yA6xsuxc");

			Statement stmt = connection.createStatement();

			int rowsAffected = stmt.executeUpdate(q, new String[] { "ID" });
			if (rowsAffected == 1) {
				ResultSet generatedKeys = stmt.getGeneratedKeys();

				while (generatedKeys.next()) {
					String rowID = generatedKeys.getString(1);
					//return new Topic(rowID, name, description, isActive);
					return getById(rowID);
					
				}
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Topic update(Topic topic) {
		String id = topic.id();
		String name = topic.name();
		String description = topic.description();
		Boolean isActive = topic.isActive();
		String user = topic.userName();
 		
		int status = db.updateSet("UPDATE FTOPIC SET NAME='" + name
				+ "' ,DESCRIPTION='" + description + "', ISACTIVE='"
				+ isActive.toString() + "', USERID='"+user+"'  WHERE ID='" + id + "'");
		if (status == 1) {
			return topic;
		}
		return null;
	}

	
	private static String now(){
		return Calendar.getInstance().getTime().toString();
	}
	 private static Topic mapTopic(ResultSet set) throws SQLException{
		return new Topic(
				set.getString("ID"),
				set.getString("NAME"),
				set.getString("DESCRIPTION"),
				Boolean.parseBoolean(set.getString("ISACTIVE")),
				set.getString("USERID"),
				set.getString("CREATED")
				);
	
	 }
}
