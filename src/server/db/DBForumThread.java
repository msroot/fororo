package server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import shared.ForumThread;

public class DBForumThread {
	static DBManager db = DBManager.getInstance();
	static Connection connection = db.getConnection();

	public static ForumThread getById(String id) {

		try {

			String dbId = null;
			String title = null;
			String description = null;
			String topicId = null;

			ResultSet set = db.getSet("SELECT * FROM FTHREAD WHERE ID='" + id
					+ "'");

			while (set.next()) {
				dbId = set.getString("ID");
				title = set.getString("TITLE");
				description = set.getString("DESCRIPTION");
				topicId = set.getString("TOPICID");
			}

			return new ForumThread(dbId, title, description, topicId);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<ForumThread> getAllByTopic(String topicId) {
		List<ForumThread> AllByTopic = new ArrayList<ForumThread>();

		String title = null;
		String description = null;
		String threadId = null;
		try {
			ResultSet set = db.getSet("SELECT * FROM FTHREAD WHERE TOPICID='"
					+ topicId + "'");

			while (set.next()) {
				title = set.getString("TITLE");
				description = set.getString("DESCRIPTION");
				threadId = set.getString("TOPICID");

				AllByTopic.add(new ForumThread(threadId, title, description,
						topicId));
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
		try {

			/* We need to connect again. this is the only way it works 
			 * all other statetement work with the current cunnection 
			 * but not this :( */
			
			Connection connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@emu.cs.rmit.edu.au:1521:GENERAL",
					"s3252905", "yA6xsuxc");

			String q = "insert into FTHREAD  values ('','" + title + "', '"
					+ description + "','" + topicId + "')";

			Statement stmt = connection.createStatement();

			int rowsAffected = stmt.executeUpdate(q, new String[] { "ID" });
			if (rowsAffected == 1) {
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				while (generatedKeys.next()) {
					String rowID = generatedKeys.getString(1);
					/*
					 * System.out.println(rowID); System.out.println(": " + q);
					 */
					return new ForumThread(rowID, title, description, topicId);

				}
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
			return new ForumThread(id, title, description, topicId);
		}
		return null;
	}

}
