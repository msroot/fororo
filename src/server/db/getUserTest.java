package server.db;

import java.util.List;

import shared.User;

public class getUserTest {
	public static void main(String[] args) {

		/* getByName */
		//		
		// User dbUser = DBUser.getByName("John");
		// System.out.print(dbUser.name() + dbUser.password() +
		// dbUser.type().toString() + dbUser.isActive());
		// System.out.print("\n getByName -------------------");

		//		
		// List<User> dbUserList = DBUser.getAll();
		//
		// for (User u : dbUserList) {
		//
		// System.out.print(u.name() + u.password() + u.type().toString()
		// + u.isActive() + "\n");
		// }
		// System.out.print("\n getAll -------------------");

		/* create */
		User u = new User("yannis2", "pass", User.Type.NORMAL, true);
		User user = DBUser.create(u);

		if (user != null) {
			System.out.print(user.name() + user.password()
					+ user.type().toString() + user.isActive());
		}

		else if (user == null) {
			System.out.print("null");
		}

		System.out.print("\n create -------------------");

	}
}