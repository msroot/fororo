package server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import shared.User;

public class DBUser {

	public DBUser() {}

	

	public User findByName(String name) {

		DBManager connectionObj = new DBManager();
		Connection connection = connectionObj.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet set = connectionObj.getSet("SELECT * FROM FUSER WHERE NAME="
				+ name);

		try {
			User.Type type = (set.getInt("TYPE") == 1) ? User.Type.NORMAL
					: User.Type.ADMIN;
			
			return new User(set.getString("NAME"), set.getString("PASSWORD"),
					type);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
