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
import shared.Config;
import shared.ForumThread;
import shared.Topic;

public class DBConfig {
	static DBManager db = DBManager.getInstance();
	static Connection connection = db.getConnection();

	public static Config update(Config config) {
		String message = config.message();
		int status = db.updateSet("UPDATE FCONFIG SET WELCOME='" + message
				+ "'   WHERE ID='1'");
		if (status == 1) {
			return config;
		}
		return null;
	}

	public static Config get() {
		try {
			ResultSet set = db.getSet("SELECT * FCONFIG");

			set.next();
			return mapConfig(set);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Config mapConfig(ResultSet set) throws SQLException {
		return new Config(set.getString("WELCOME"));

	}

}
