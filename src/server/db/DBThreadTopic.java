package server.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shared.ThreadTopic;

public class DBThreadTopic {
	static DBManager db = DBManager.getInstance();

	public static ThreadTopic getById(String topicId) {
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
				
		 
				return new ThreadTopic(id, name, description, isActive);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return null;
	}

	public static List<ThreadTopic> getAll() {
		List<ThreadTopic> topics = new ArrayList<ThreadTopic>();

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
				
		 
				topics.add(new ThreadTopic(id, name, description, isActive));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return topics;
	}



}
