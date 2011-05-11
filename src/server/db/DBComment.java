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
 * Responsible all c.r.u.i.d. functionality of the {@link ForumThreadComment}
 * 
 * @author John Kolovos
 * 
 */
public class DBComment {
	static DBManager db = DBManager.getInstance();
	static Connection connection = db.getConnection();

	static String db_user = db.db_user;
	static String db_pass = db.db_pass;
	static String db_server = db.db_server;
	static String db_database = db.db_database;
	static String db_port = db.db_port;

	/**
	 * Finds all {@link Comment} with the give theadId
	 * 
	 * @param threadId
	 * @return List<Comment>
	 */
	public static List<Comment> getAll(String threadId) {
		List<Comment> comments = new ArrayList<Comment>();

		try {
			ResultSet set = db.getSet("SELECT * FROM FCOMMENT WHERE THREADID='"
					+ threadId + "'");

			while (set.next()) {
				comments.add(mapComment(set));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}

	/*
	 * Create and return a new {@link Comment}
	 * 
	 * @param <Comment>
	 * 
	 * @return <Comment>
	 */
	public static Comment create(Comment comment) {
		String content = comment.content();
		String userName = comment.userName();
		String threadId = comment.threadId();

		try {

			String q = "insert into FCOMMENT (CONTENT,THREADID, USERID, CREATED) values ('"
					+ content
					+ "', '"
					+ threadId
					+ "','"
					+ userName
					+ "','"
					+ now() + "')";

			Connection connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + db_server + ":" + db_port + ":"
							+ db_database + "", db_user, db_pass);

			Statement stmt = connection.createStatement();

			int rowsAffected = stmt.executeUpdate(q, new String[] { "ID" });
			if (rowsAffected == 1) {
				ResultSet generatedKeys = stmt.getGeneratedKeys();

				while (generatedKeys.next()) {
					String rowID = generatedKeys.getString(1);
					return new Comment(rowID, content, userName, threadId,
							now());
				}
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Updates the given {@link Comment}
	 * 
	 * @param Comment
	 * @return Comment
	 */
	public static Comment update(Comment comment) {

		String id = comment.id();
		String content = comment.content();
		String userId = comment.userName();
		String threadId = comment.threadId();

		int status = db.updateSet("UPDATE FCOMMENT SET CONTENT='" + content
				+ "' ,THREADID='" + threadId + "', USERID='" + userId
				+ "'  WHERE ID='" + id + "'");
		if (status == 1) {
			return comment;
		}
		return null;
	}

	private static String now() {
		return Calendar.getInstance().getTime().toString();
	}

	/**
	 * Deletes the given {@link Comment}
	 * 
	 * @param Comment
	 * @return Comment
	 */
	public static Comment delete(Comment comment) {
		try {

			String id = comment.id();
			Connection connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + db_server + ":" + db_port + ":"
							+ db_database + "", db_user, db_pass);
			String q = "DELETE FROM FCOMMENT  WHERE ID='" + id + "'";
			Statement stmt = connection.createStatement();
			int rowsAffected = stmt.executeUpdate(q);

			if (rowsAffected == 1) {
				return comment;
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Maps a {@link ResultSet} to a new {@link Comment}
	 * 
	 * @param Comment
	 * @return Comment
	 */
	private static Comment mapComment(ResultSet set) throws SQLException {
		return new Comment(set.getString("ID"), set.getString("CONTENT"), set
				.getString("USERID"), set.getString("THREADID"), set
				.getString("CREATED"));

	}

}
