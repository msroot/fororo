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

public class ChatTest {

	@Test
	public void loged_in_user_can_chat() {
		try {

			ForumClient c1 = new ForumClient();
			ForumClient c2 = new ForumClient();
			
			c1.connect();

			User u = c1.forum.loginUser("vic", "abcd1234", c1);
			c1.forum.sendChatMessage(u, "hello everybody");
			
			c2.connect();
			User u2 = c2.forum.loginUser("admin", "abcd1234", c2);
			c2.forum.sendChatMessage(u2, "offcourse re malaka!");
			
			c2.forum.logoutUser(u2.name());
			

		} catch (RemoteException e) {
			fail("it shuoldn't throw exception: " + e.getMessage());
		}
	}
}
