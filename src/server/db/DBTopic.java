package server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import shared.Topic;
import shared.User;

public class DBTopic {
	static DBManager db = DBManager.getInstance();
	static Connection connection = db.getConnection();
	
	public static Topic getById(String topicId) {
		String id = null;
		String name = null;
		String description = null;
		Boolean isActive = null;
		try {
			ResultSet set = db.getSet("SELECT * FROM FTOPIC WHERE ID='"
					+ topicId + "'");

			while (set.next()) {
				id = set.getString("ID");
				name = set.getString("NAME");
				description = set.getString("DESCRIPTION");
				isActive = Boolean.parseBoolean(set.getString("ISACTIVE"));

				return new Topic(id, name, description, isActive);
			}

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
	public static String findTopicIDByRowID(String rowID) {
		String id = null;
		String table = "FTOPIC";

		try {
			//select ID from ftopic where rowid='AABg0yAAEAAAhZVAAF'
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

			while (set.next()) {
				id = set.getString("ID");
				name = set.getString("NAME");
				description = set.getString("DESCRIPTION");
				isActive = Boolean.parseBoolean(set.getString("ISACTIVE"));

				topics.add(new Topic(id, name, description, isActive));
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
	 * @return <Topic> or  null
	 */
	public static Topic create(Topic topic) {

		// int status = db
		// .updateSet("insert into FTOPIC (NAME, DESCRIPTION, ISACTIVE) values ('"
		// + name + "', '" + description + "','" + isActive + "')");
		// if (status == 1) {
		// // FIXME:how do i find this topic id?
		// String id = null;
		// return new Topic(id, name, description, isActive);
		// }
		//
		// return null;
		
		
		String name = topic.name();
		String description = topic.description();
		Boolean isActive = topic.isActive();
		PreparedStatement preparedStatement = null;
		ResultSet generatedKeys = null;
		
		
		
		try {

			String SQL_INSERT = "insert into FTOPIC (NAME, DESCRIPTION, ISACTIVE) values ('"
					+ name + "', '" + description + "','" + isActive.toString() + "')";

			preparedStatement = connection.prepareStatement(SQL_INSERT,
					Statement.RETURN_GENERATED_KEYS);

			int affectedRows = preparedStatement.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Creating  failed, no rows affected.");
			}

			generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				String rowID = generatedKeys.getString(1);
				String id = findTopicIDByRowID(rowID);
				return new Topic(id, name, description, isActive);

			}
			return null;

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

		int status = db.updateSet("UPDATE FTOPIC SET NAME='" + name
				+ "' ,DESCRIPTION='" + description + "', ISACTIVE='"
				+ isActive.toString() + "'  WHERE ID='" + id + "'");
		if (status == 1) {
			return new Topic(id, name, description, isActive);
		}
		return null;
	}

}
