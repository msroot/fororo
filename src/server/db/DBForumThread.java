package server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		PreparedStatement preparedStatement = null;
		ResultSet generatedKeys = null;
		try {
			String SQL_INSERT = "insert into FTHREAD (TITLE, DESCRIPTION, TOPICID) values ('"
					+ title + "', '" + description + "','" + topicId + "')";

			preparedStatement = connection.prepareStatement(SQL_INSERT,
					new String[] { "ID" });

			int affectedRows = preparedStatement.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Creating  failed, no rows affected.");
			}
			String rowID = null;
			generatedKeys = preparedStatement.getGeneratedKeys();

			while (generatedKeys.next()) {
				rowID = generatedKeys.getString(1);
				// String id = findTopicIDByRowID(rowID);
				return new ForumThread(rowID, title, description, topicId);
			}

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
