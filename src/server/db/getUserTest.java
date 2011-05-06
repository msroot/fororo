package server.db;

import java.util.List;

import shared.*;

 
public class getUserTest {
	public static void main(String[] args) {

		/* create ********************************************** */
		User cu = new User("adam2", "pass", User.Type.NORMAL, true);
		User user = DBUser.create(cu);

		if (user != null) {
			System.out.print(user.name() + user.password()
					+ user.type().toString() + user.isActive());
		}

		else if (user == null) {
			System.out.print("null");
		}

		System.out.print("\n create -------------------");

		/* update ********************************************** */
		User up = new User("NewUser", "12345", User.Type.ADMIN, false);
		User updatedUser = DBUser.update(up);

		if (updatedUser != null) {
			System.out.print(updatedUser.name() + updatedUser.password()
					+ updatedUser.type().toString() + updatedUser.isActive());

		}

		else if (user == null) {
			System.out.print("null");
		}

		System.out.print("\n update -------------------");

		/* getByName********************************************** */

		 User dbUser = DBUser.getByName("John");
		 System.out.print(dbUser.name() + dbUser.password() +
		 dbUser.type().toString() + dbUser.isActive());
		 System.out.print("\n getByName -------------------\n");
		
						
				 
				 
		 /* getAll***********************************************/
		 List<User> dbUserList = DBUser.getAll();
				
		 for (User u : dbUserList) {
				
		 System.out.print(u.name() + u.password() + u.type().toString()
		 + u.isActive() + "\n");
		 }
		 System.out.print("\n getAll -------------------\n");

	}
}