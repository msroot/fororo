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

			
			
//			PreparedStatement pstmt = 
//				 con.prepareStatement("INSERT INTO acc (acc_name) " +
//				                        "VALUES (:acc_name)", 
//				                             new String[] { "acc_id", "acc_balance" });
//				 
//				pstmt.setString(1, "Red Triangle");
//				pstmt.executeUpdate();
//				 
//				ResultSet rs = pstmt.getGeneratedKeys();
//				if (rs.next()) {
//				    System.out.println("Account Id: " + rs.getInt(1));
//				    System.out.println("Balance: " + rs.getInt(2));
//				}
				
				
				
			preparedStatement = connection.prepareStatement(SQL_INSERT,
					//Statement.RETURN_GENERATED_KEYS);
					new String[] {"ID"});

			int affectedRows = preparedStatement.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException(
						"Creating user failed, no rows affected.");
			}

			//generatedKeys = preparedStatement.getGeneratedKeys();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				String getRowID = rs.getString(1);
				System.out.print(getRowID+"\n -------------------\n");
				
				return new Topic(getRowID, name, description, isActive);

			} else {
				throw new SQLException(
						"failed, no generated key obtained.");
			}
		} finally {
			preparedStatement.close();
			 
		}
	}

 
	public static void main(String[] args) throws SQLException {

		/* create Topic */
		Topic top = new Topic(null, "getGenerated keys", "topic description", true);
		Topic newTopic = create(top);
		System.out.print("id: " + newTopic.id() + "\n" + " desc:"
				+ newTopic.description() + "\n" + " active:"
				+ newTopic.isActive() + "\n" + " name:" + newTopic.name());
		System.out.print("\n create -------------------\n");

	}
}
