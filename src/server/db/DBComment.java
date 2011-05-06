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

public class DBComment {
	static DBManager db = DBManager.getInstance();
	static Connection connection = db.getConnection();
	static Calendar calendar = Calendar.getInstance();
	static String now = calendar.getTime().toString();

	
	public static List<Comment> getAll(String threadId) {

		// if (threadId==null){return null;}

		List<Comment> comments = new ArrayList<Comment>();
		// Comment(String id, String content, String userName, String threadId, String created){

		String id = null;
		String content = null;
		String userId = null;
		String dbthreadId = null;
		try {
			ResultSet set = db.getSet("SELECT * FROM FCOMMENT WHERE THREADID='"
					+ threadId + "'");

			while (set.next()) {
//				id = set.getString("ID");
//				content = set.getString("CONTENT");
//				userId = set.getString("USERID");
//				dbthreadId = set.getString("THREADID");
				// String id, String comment, String userId, String threadId
				//comments.add(new Comment(id, content, userId, dbthreadId));
				comments.add(mapComment(set));
				
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}

	/*
	 * Creat and return a new comment
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
					+ content + "', '" + threadId + "','" + userName + "','"+now+"')";

			Connection connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@emu.cs.rmit.edu.au:1521:GENERAL",
					"s3252905", "yA6xsuxc");

			Statement stmt = connection.createStatement();

			int rowsAffected = stmt.executeUpdate(q, new String[] { "ID" });
			if (rowsAffected == 1) {
				ResultSet generatedKeys = stmt.getGeneratedKeys();

				while (generatedKeys.next()) {
					String rowID = generatedKeys.getString(1);
					//return new Comment(rowID, content, userId, threadId);
					return  new Comment(rowID, content, userName, threadId, now());
				}
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Comment update(Comment comment) {

		String id = comment.id();
		String content = comment.content();
		String userId = comment.userName();
		String threadId = comment.threadId();

		int status = db.updateSet("UPDATE FCOMMENT SET CONTENT='" + content
				+ "' ,THREADID='" + threadId + "', USERID='" + userId
				+ "'  WHERE ID='" + id + "'");
		if (status == 1) {
			//return new Comment(id, content, userId, threadId);
			return comment;
		}
		return null;
	}
	
	private static String now(){
		return Calendar.getInstance().getTime().toString();
	}
	 
	private static Comment mapComment(ResultSet set) throws SQLException{
		return new Comment(
				set.getString("ID"),
				set.getString("CONTENT"),
				set.getString("THREADID"),
				set.getString("USERID"),
				set.getString("CREATED")
				);
	
	 }
	 

}
