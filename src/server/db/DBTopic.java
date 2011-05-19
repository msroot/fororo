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

import shared.Comment;
import shared.ForumThread;
import shared.Topic;

/**
 * Responsible all c.r.u.i.d. functionality of the {@link Topic}
 * 
 * @author John Kolovos
 * 
 */
public class DBTopic {
	static DBManager db = DBManager.getInstance();
	static Connection connection = db.getConnection();
	static String db_user = db.db_user;
	static String db_pass = db.db_pass;
	static String db_server = db.db_server;
	static String db_database = db.db_database;
	static String db_port = db.db_port;

	/**
	 * Finds all topics with the given id
	 * 
	 * @param topicId
	 * @return <Topic>
	 */
	public static Topic getById(String topicId) {
		try {
			ResultSet set = db.getSet("SELECT * FROM FTOPIC WHERE ID='"
					+ topicId + "'");
			set.next();
			return mapTopic(set);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Collects all topics exist in db
	 * 
	 * @return List
	 */
	public static List<Topic> getAll() {
		List<Topic> topics = new ArrayList<Topic>();
		try {
			ResultSet set = db.getSet("SELECT * FROM FTOPIC");
			while (set.next()) {
				topics.add(mapTopic(set));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return topics;
	}

	/*
	 * Creates and new topic, with update statement after finds the post ID and
	 * creates and return new post. we use 2 sql statements that we dont use the
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
					+ "','" + user + "', '" + now() + "')";

			Connection connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + db_server + ":" + db_port + ":"
							+ db_database + "", db_user, db_pass);

			Statement stmt = connection.createStatement();

			int rowsAffected = stmt.executeUpdate(q, new String[] { "ID" });
			if (rowsAffected == 1) {
				ResultSet generatedKeys = stmt.getGeneratedKeys();

				while (generatedKeys.next()) {
					String rowID = generatedKeys.getString(1);
					return getById(rowID);

				}
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Updates a give topic
	 * 
	 * @param <Topic>
	 * @return <Topic>
	 */
	public static Topic update(Topic topic) {
		String id = topic.id();
		String name = topic.name();
		String description = topic.description();
		Boolean isActive = topic.isActive();
		String user = topic.userName();

		int status = db.updateSet("UPDATE FTOPIC SET NAME='" + name
				+ "' ,DESCRIPTION='" + description + "', ISACTIVE='"
				+ isActive.toString() + "', USERID='" + user + "'  WHERE ID='"
				+ id + "'");
		if (status == 1) {
			return topic;
		}
		return null;
	}

	/**
	 * Deletes a given <Topic>
	 * 
	 * @param topic
	 * @return
	 */
	public static Topic delete(Topic topic) {
		try {

			String id = topic.id();
			Connection connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + db_server + ":" + db_port + ":"
							+ db_database + "", db_user, db_pass);

			String q = "DELETE FROM FTOPIC  WHERE ID='" + id + "'";
			Statement stmt = connection.createStatement();
			int rowsAffected = stmt.executeUpdate(q);

			if (rowsAffected == 1) {
				return topic;
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String now() {
		return Calendar.getInstance().getTime().toString();
	}

	/**
	 * Map a given ResultSet set to <Topic>
	 * 
	 * @param set
	 * @return <Topic>
	 * @throws SQLException
	 */
	private static Topic mapTopic(ResultSet set) throws SQLException {
		return new Topic(set.getString("ID"), set.getString("NAME"), set
				.getString("DESCRIPTION"), Boolean.parseBoolean(set
				.getString("ISACTIVE")), set.getString("USERID"), set
				.getString("CREATED"));

	}
}
