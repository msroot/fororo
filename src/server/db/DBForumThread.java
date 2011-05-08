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

public class DBForumThread {

	static DBManager db = DBManager.getInstance();
	static Connection connection = db.getConnection();
 	static String db_user = db.db_user;
	static String db_pass = db.db_pass;
	static String db_server = db.db_server;
	static String db_database = db.db_database;
	static String db_port = db.db_port;
	
	public static ForumThread getById(String id) {

		try {

		/*	String dbId = null;
			String title = null;
			String description = null;
			String topicId = null;*/

			ResultSet set = db.getSet("SELECT * FROM FTHREAD WHERE ID='" + id
					+ "'");

			while (set.next()) {
				// dbId = set.getString("ID");
				// title = set.getString("TITLE");
				// description = set.getString("DESCRIPTION");
				// topicId = set.getString("TOPICID");
				return mapThreads(set);
			}

			// return new ForumThread(dbId, title, description, topicId);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<ForumThread> getAllByTopic(String topicId) {
		List<ForumThread> AllByTopic = new ArrayList<ForumThread>();

	/*	String title = null;
		String description = null;
		String threadId = null;*/
		try {
			ResultSet set = db.getSet("SELECT * FROM FTHREAD WHERE TOPICID='"
					+ topicId + "'");

			while (set.next()) {
				// title = set.getString("TITLE");
				// description = set.getString("DESCRIPTION");
				// threadId = set.getString("TOPICID");
				// AllByTopic.add(new ForumThread(threadId, title, description,
				// topicId));

				AllByTopic.add(mapThreads(set));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return AllByTopic;
	}

	public static ForumThread create(ForumThread thread) {
		String title = thread.title();
		String description = thread.content();
		String topicId = thread.topicId();
		String user = thread.userName();
		try {

			/*
			 * We need to connect again. this is the only way it works all other
			 * statetement work with the current cunnection but not this :(
			 */
			Connection connection = DriverManager.getConnection(
			"jdbc:oracle:thin:@" + db_server + ":" + db_port + ":"
			+ db_database + "", db_user, db_pass);
			

			String q = "insert into FTHREAD  values ('','" + title + "', '"
					+ description + "','" + topicId + "', '" + user + "', '"
					+ now() + "')";

			Statement stmt = connection.createStatement();

			int rowsAffected = stmt.executeUpdate(q, new String[] { "ID" });
			if (rowsAffected == 1) {
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				while (generatedKeys.next()) {
					String rowID = generatedKeys.getString(1);
					/*
					 * System.out.println(rowID); System.out.println(": " + q);
					 */

					return new ForumThread(rowID, title, description, topicId,
							user, now());

				}
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public static ForumThread delete(ForumThread thread) {
		try {

			String id= thread.id();
			Connection connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + db_server + ":" + db_port + ":"
					+ db_database + "", db_user, db_pass);
			
			
			String q = "DELETE FROM FTHREAD  WHERE ID='"+id+"'";
			Statement stmt = connection.createStatement();
			int rowsAffected = stmt.executeUpdate(q);
			
			if (rowsAffected == 1) {
				return thread;
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ForumThread update(ForumThread thread) {
		String id = thread.id();
		String title = thread.title();
		String description = thread.content();
		String topicId = thread.topicId();

		int status = db.updateSet("UPDATE FTHREAD  SET TITLE='" + title
				+ "' ,DESCRIPTION='" + description + "', TOPICID='" + topicId
				+ "'  WHERE ID='" + id + "'");
		if (status == 1) {
			// return new ForumThread(id, title, description, topicId);
			return thread;
		}
		return null;
	}

	private static String now() {
		return Calendar.getInstance().getTime().toString();
	}

	private static ForumThread mapThreads(ResultSet set) throws SQLException {
		return new ForumThread(set.getString("ID"), set.getString("TITLE"), set
				.getString("DESCRIPTION"), set.getString("TOPICID"), set
				.getString("USERID"), set.getString("CREATED"));

	}

}
