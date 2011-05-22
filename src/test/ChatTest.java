package test;

import oracle.net.aso.C11;

import org.junit.*;
import static org.junit.Assert.*;
import java.rmi.RemoteException;
import java.util.*;
import server.*;
import server.db.*;
import client.*;
import shared.*;
import shared.User.Type;

public class ChatTest {

	@Test
	public void two_users_in_the_same_category() {
		try {

			ForumClient c1 = new ForumClient();
			ForumClient c2 = new ForumClient();
			
			c1.connect();

			User u = c1.forum.loginUser("vic", "abcd1234", c1);
			c1.forum.setChatTopic(u, new Topic("id1", "", "", true, "", ""));
			
			c1.forum.sendChatMessage(u, "hello everybody");
			
			DBUser.create(new User("edd", "abcd1234", Type.ADMIN, true, ""));
			c2.connect();
			User u2 = c2.forum.loginUser("edd", "abcd1234", c2);
			
			c2.forum.setChatTopic(u2, new Topic("id1", "", "", true, "", ""));
			c2.forum.sendChatMessage(u2, "offcourse re malaka!");
			
			c2.forum.logoutUser(u2);
			

		} catch (RemoteException e) {
			fail("it shuoldn't throw exception: " + e.getMessage());
		}
	}





	@Test
	public void users_in_diffrent_cat() {
		try {

			ForumClient c1 = new ForumClient();
			ForumClient c2 = new ForumClient();
			ForumClient c3 = new ForumClient();

			DBUser.create(new User("john", "pass", Type.ADMIN, true, ""));
			DBUser.create(new User("eduardo", "eduardo", Type.ADMIN, true, ""));
			
			
			c1.connect();
			c2.connect();
			c3.connect();
			
			User u = c1.forum.loginUser("vic", "abcd1234", c1);
			User u3 = c3.forum.loginUser("eduardo", "eduardo", c2);
			User u2 = c2.forum.loginUser("john", "pass", c2);
			
			c1.forum.setChatTopic(u, new Topic("bahamas", "", "", true, "", ""));
			c2.forum.setChatTopic(u2, new Topic("Venezuela", "", "", true, "", ""));
			c3.forum.setChatTopic(u3, new Topic("Venezuela", "", "", true, "", ""));

			
			c1.forum.sendChatMessage(u, "i am in category bahamas");
			c2.forum.sendChatMessage(u2, "yeh i am in category Venezuela!");
			c3.forum.sendChatMessage(u3, "i am also in Venezuela!");
			
			 
			
			c1.forum.setChatTopic(u, new Topic("Venezuela", "", "", true, "", ""));

			c1.forum.sendChatMessage(u, "i also come now in Venezuela...its cool");
			

		} catch (RemoteException e) {
			fail("it shuoldn't throw exception: " + e.getMessage());
		}
	}
}
