package server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import shared.ForumThread;
import shared.Topic;
import shared.User;

/**
 * Responsible all c.r.u.i.d. functionality of the {@link User}
 * 
 * @author John Kolovos
 * 
 */
public class DBUser {
	static DBManager db = DBManager.getInstance();
	static String db_user = db.db_user;
	static String db_pass = db.db_pass;
	static String db_server = db.db_server;
	static String db_database = db.db_database;
	static String db_port = db.db_port;

	public DBUser() {
	}

	/**
	 * Find and returns a user from db
	 * 
	 * @return <User>
	 */
	public static User getByName(String findName) {
		String name = null;
		String pass = null;
		User.Type type = null;
		Boolean active = null;

		try {

			ResultSet set = db.getSet("SELECT * FROM FUSER WHERE NAME='"
					+ findName + "'");

			while (set.next()) {
				return mapUsers(set);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Collects and returns an ArrayList of all the user from db
	 * 
	 * @return List<User>
	 */
	public static List<User> getAll() {
		List<User> userList = new ArrayList<User>();

		try {
			ResultSet set = db.getSet("SELECT * FROM FUSER");
			while (set.next()) {
				userList.add(mapUsers(set));
			}
			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Creates and returns a new user with default values of User.Type=NORMAL;
	 * and isActive=true
	 * 
	 * @param <User>
	 * @return <User>
	 */
	public static User create(User user) {

		String name = user.name();
		String pass = user.password();
		int status = db
				.updateSet("insert into FUSER (NAME, PASSWORD, TYPE, ISACTIVE, CREATED) values ('"
						+ name
						+ "', '"
						+ pass
						+ "','NORMAL','"
						+ user.isActive() + "','" + now() + "')");
		if (status == 1) {
			return new User(name, pass, User.Type.NORMAL, user.isActive(),
					now());
		}

		return null;

	}

	/**
	 * Updates user's information based on user name(ID) if user is duplicate in
	 * db will change both
	 * 
	 * @param <User>
	 * @return <User>
	 */
	public static User update(User user) {

		String name = user.name();
		String pass = user.password();
		User.Type type = user.type();
		Boolean isActive = user.isActive();

		int status = db.updateSet("UPDATE FUSER SET ISACTIVE='"
				+ isActive.toString() + "' ,TYPE='" + type.toString()
				+ "', PASSWORD='" + pass + "'  WHERE NAME='" + name + "'");
		if (status == 1) {
			return user;
		}

		return null;

	}

	public static User delete(User user) {
		try {

			String id = user.name();
			Connection connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + db_server + ":" + db_port + ":"
							+ db_database + "", db_user, db_pass);
			String q = "DELETE FROM FUSER  WHERE NAME='" + id + "'";
			Statement stmt = connection.createStatement();
			int rowsAffected = stmt.executeUpdate(q);

			if (rowsAffected == 1) {
				return user;
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String now() {
		return Calendar.getInstance().getTime().toString();
	}

	private static User mapUsers(ResultSet set) throws SQLException {

		return new User(

		set.getString("NAME"), set.getString("PASSWORD"), User.Type.valueOf(set
				.getString("TYPE")), Boolean.parseBoolean(set
				.getString("ISACTIVE")), set.getString("CREATED"));

	}
}
