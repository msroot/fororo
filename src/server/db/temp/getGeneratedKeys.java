package server.db.temp;

import java.sql.*;

import server.db.DBManager;
import server.db.DBTopic;
import shared.Topic;
import shared.User;

public class getGeneratedKeys {
	static DBManager db = DBManager.getInstance();

	public static Topic create(Topic topic) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet generatedKeys = null;
		String name = topic.name();
		String description = topic.description();
		Boolean isActive = topic.isActive();
		connection = db.getConnection();
		try {

			String SQL_INSERT = "insert into FTOPIC (NAME, DESCRIPTION, ISACTIVE) values ('"
					+ name + "', '" + description + "','" + isActive + "')";

			preparedStatement = connection.prepareStatement(SQL_INSERT,
					Statement.RETURN_GENERATED_KEYS);

			int affectedRows = preparedStatement.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException(
						"Creating user failed, no rows affected.");
			}

			generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				String getRowID = generatedKeys.getString(1);
				return new Topic(getRowID, name, description, isActive);

			} else {
				throw new SQLException(
						"failed, no generated key obtained.");
			}
		} finally {
			preparedStatement.close();
			generatedKeys.close();
		}
	}

	// select * from ftopic where rowid='AABg0yAAEAAAhZXAAB'

	public static void main(String[] args) throws SQLException {

		/* create Topic */
		Topic top = new Topic(null, "generated keys", "topic description", true);
		Topic newTopic = create(top);
		System.out.print("id: " + newTopic.id() + "\n" + " desc:"
				+ newTopic.description() + "\n" + " active:"
				+ newTopic.isActive() + "\n" + " name:" + newTopic.name());
		System.out.print("\n create -------------------\n");

	}
}
