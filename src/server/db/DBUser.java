package server.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import shared.ForumThread;
import shared.User;

public class DBUser {
	static DBManager db = DBManager.getInstance();
	static Calendar calendar = Calendar.getInstance();
	static String now = calendar.getTime().toString();

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
//
//				type = (set.getString("TYPE").equalsIgnoreCase("NORMAL")) ? User.Type.NORMAL
//						: User.Type.ADMIN;
//				name = set.getString("NAME");
//				pass = set.getString("PASSWORD");
//				active = Boolean.parseBoolean(set.getString("ISACTIVE"));
			return mapUsers(set);
			}
			//return new User(name, pass, type, active);
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

//				User user = new User(set.getString("NAME"), set
//						.getString("PASSWORD"), User.Type.valueOf(set
//						.getString("TYPE")), Boolean.parseBoolean(set
//						.getString("ISACTIVE")));
//				userList.add(user);
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
						+ name + "', '" + pass + "','NORMAL','true','"+now+"')");
		if (status == 1) {
			//new User(name, password, type, isActive, created)
			return new User(name, pass, User.Type.NORMAL, true, now());
			
		}

		return null;

	}

	/**
	 * Updates user's information based on user name(ID) if user is dublicate in
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
			//return new User(name, pass, type, isActive);
			return user;
		}

		return null;

	}




	private static String now(){
		return Calendar.getInstance().getTime().toString();
	}
	 
	private static User mapUsers(ResultSet set) throws SQLException{
		
		
 		return new User(
				set.getString("NAME"),
				set.getString("PASSWORD"),
				User.Type.valueOf(set.getString("TYPE")),
				Boolean.parseBoolean(set.getString("ISACTIVE")),
				set.getString("CREATED")
				);


}}
