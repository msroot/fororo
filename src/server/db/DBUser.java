package server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import shared.User;

public class DBUser extends DBManagment {

	public DBUser() {
		super();
	}

	public User findByName(String name) {

		DBManagment connectionObj = new DBManagment();
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
			return new User(set.getString("NAME"), set.getString("PASSWORD"),
					User.Type.NORMAL);
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
