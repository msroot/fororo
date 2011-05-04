package server.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import shared.User;

public class DBUser {
	static DBManager db = DBManager.getInstance();

	public DBUser() {
	}

	public static User getByName(String findName) {
		String name = null;
		String pass = null;
		User.Type type = null;
		Boolean active = null;

		try {

			ResultSet set = db.getSet("SELECT * FROM FUSER WHERE NAME='"
					+ findName + "'");

			while (set.next()) {
				type = (set.getString("TYPE") == "NORMAL") ? User.Type.NORMAL
						: User.Type.ADMIN;

				name = set.getString("NAME");
				pass = set.getString("PASSWORD");
				active = Boolean.parseBoolean(set.getString("ISACTIVE"));

			}

			return new User(name, pass, type, active);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<User> getAll() {
		List<User> userList = new ArrayList<User>();

		try {

			ResultSet set = db.getSet("SELECT * FROM FUSER");

			while (set.next()) {

				User user = new User(set.getString("NAME"), set
						.getString("PASSWORD"), User.Type.valueOf(set
						.getString("TYPE")), Boolean.parseBoolean(set
						.getString("ISACTIVE")));

				userList.add(user);

			}

			return userList;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static User create(User user) {

		String name = user.name();
		String pass = user.password();

		int status = db.updateSet("INSERT INTO FUSER VALUES ('" + name + "', '"
				+ pass + "','NORMAL','true')");

		if (status == 1) {
			System.out.print(status);
			return new User(name, pass, User.Type.NORMAL, true);
		}

		return null;

	}

	/**
	 * prints all the information in the rows from a SesultSet usefull for
	 * testing a debugging
	 * 
	 * @param rset
	 */
	public static void dumpResultSet(ResultSet rSet) {
		try {
			// rSet.beforeFirst();
			while (rSet.next()) {
				for (int i = 1; i <= rSet.getMetaData().getColumnCount(); i++) {
					System.out.print(rSet.getString(i) + ", ");
				}
				System.out.println();
			}
			// rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
