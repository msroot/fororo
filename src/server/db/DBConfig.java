package server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import shared.Config;

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
			ResultSet set = db.getSet("SELECT * FROM FCONFIG");

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
