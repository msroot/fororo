package server.db;

import java.util.List;

import shared.*;

public class getUserTest {
	public static void main(String[] args) {

		/* create ********************************************** */
		User cu = new User("adam2", "pass", User.Type.NORMAL, true, null);
		User user = DBUser.create(cu);

		System.out.println(cu);
		System.out.print("\n create -------------------\n ");

		/* update ********************************************** */
		User up = new User("NewUser", "12345", User.Type.ADMIN, false, null);
		User updatedUser = DBUser.update(up);

		if (updatedUser != null) {

			System.out.println(up);
		}

		else if (user == null) {
			System.out.print("null");
		}

		System.out.print("\n update -------------------\n ");

		/* getByName********************************************** */

		User dbUser = DBUser.getByName("John");

		System.out.println(dbUser);
		System.out.print("\n getByName -------------------\n");

		/* getAll********************************************** */
		List<User> dbUserList = DBUser.getAll();

		for (User u : dbUserList) {

			System.out.println(u);
		}
		System.out.print("\n getAll -------------------\n");

	}
}