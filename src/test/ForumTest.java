package test;

import org.junit.*;
import static org.junit.Assert.*;
import server.*;
import client.*;
import shared.*;

public class ForumTest {
	
	@Test
    public void accepts_connections() {
		try{
			ForumClient client = new ForumClient();
			assertTrue("It should connect", client.connect());
		} catch (Exception e){
			fail("Should not throw exception: " + e.getStackTrace());
		}
	}
	
	@Test
    public void user_can_login() {
		try{
			ForumClient client = new ForumClient();
			
			assertTrue("It should connect", client.connect());
			
			User user = client.forum.loginUser("testuser", "abcd1234", client); 
			
			assertTrue("it shuold login => testuser:abcd1234", user instanceof User );
			assertTrue("username shuold be: testuser but is: " +user.name(), "testuser".equals(user.name()));
			assertTrue("password shuold be: abcd1234 but is: " +user.password(), "abcd1234".equals(user.password()));
		} catch (Exception e){
			fail("Should not throw exception: " + e.getStackTrace());
		}
	}

	@Test
    public void user_can_logout() {
		try{
			ForumClient client = new ForumClient();
			assertTrue("It should connect", client.connect());
			User user = client.forum.loginUser("testuser", "abcd1234", client);
			assertTrue(client.forum.logoutUser(user.name()));
		} catch (Exception e){
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
    public void can_get_topic_list() {
		try{
			ForumClient client = new ForumClient();
			assertTrue("It should connect", client.connect());
			client.forum.getThreadTopics();
		} catch (Exception e){
			e.printStackTrace();
			fail("Should not throw exception: " + e.getMessage());
		}
	}
	
	@Test
    public void can_get_threads_by_topic() {
		try{
			ForumClient client = new ForumClient();
			assertTrue("It should connect", client.connect());
			//TODO check threads are correct
			client.forum.getThreadsByTopic("0");
		} catch (Exception e){
			e.printStackTrace();
			fail("Should not throw exception: " + e.getMessage());
		}
	}
	
	@Test
    public void can_get_thread_by_id() {
		try{
			ForumClient client = new ForumClient();
			assertTrue("It should connect", client.connect());
			//TODO check if thread exists in db			
			client.forum.getThreadById("0");
		} catch (Exception e){
			e.printStackTrace();
			fail("Should not throw exception: " + e.getMessage());
		}
	}
	
	@Test
    public void can_create_thread() {
		try{
			ForumClient client = new ForumClient();
			assertTrue("It should connect", client.connect());
			client.forum.createThread("the title", "the description", "the_topic_id");
		} catch (Exception e){
			e.printStackTrace();
			fail("Should not throw exception: " + e.getMessage());
		}
	}
}