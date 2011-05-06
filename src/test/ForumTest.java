package test;

import org.junit.*;
import static org.junit.Assert.*;
import java.rmi.RemoteException;
import java.util.*;
import server.*;
import server.db.*;
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
	//
	// @Test
	// public void user_can_login() {
	// try{
	// ForumClient client = new ForumClient();
	//
	// assertTrue("It should connect", client.connect());
	//
	// User user = client.forum.loginUser("testuser", "abcd1234", client);
	//
	// assertTrue("it shuold login => testuser:abcd1234", user instanceof User
	// );
	// assertTrue("username shuold be: testuser but is: " +user.name(),
	// "testuser".equals(user.name()));
	// assertTrue("password shuold be: abcd1234 but is: " +user.password(),
	// "abcd1234".equals(user.password()));
	// } catch (Exception e){
	// fail("Should not throw exception: " + e.getStackTrace());
	// }
	// }
	//
	// @Test
	// public void user_can_logout() {
	// try{
	// ForumClient client = new ForumClient();
	// assertTrue("It should connect", client.connect());
	// User user = client.forum.loginUser("testuser", "abcd1234", client);
	// assertTrue(client.forum.logoutUser(user.name()));
	// } catch (Exception e){
	// fail("Should not throw exception: " + e.getMessage());
	// }
	// }
	//
	// @Test
	// public void can_get_topic_list() {
	// try{
	// ForumClient client = new ForumClient();
	// assertTrue("It should connect", client.connect());
	// client.forum.getTopics();
	// } catch (Exception e){
	// e.printStackTrace();
	// fail("Should not throw exception: " + e.getMessage());
	// }
	// }
	//
	// @Test
	// public void can_get_threads_by_topic() {
	// try{
	// ForumClient client = new ForumClient();
	// assertTrue("It should connect", client.connect());
	// //TODO check threads are correct
	// client.forum.getThreadsByTopic("0");
	// } catch (Exception e){
	// e.printStackTrace();
	// fail("Should not throw exception: " + e.getMessage());
	// }
	// }
	//
	// @Test
	// public void can_get_thread_by_id() {
	// try{
	// ForumClient client = new ForumClient();
	// assertTrue("It should connect", client.connect());
	// //TODO check if thread exists in db
	// client.forum.getThreadById("0");
	// } catch (Exception e){
	// e.printStackTrace();
	// fail("Should not throw exception: " + e.getMessage());
	// }
	// }
	//
	// @Test
	// public void can_create_thread() {
	// try{
	// ForumClient client = new ForumClient();
	// assertTrue("It should connect", client.connect());
	// client.forum.createThread("0", "the title", "the description",
	// "the_topic_id");
	// } catch (Exception e){
	// e.printStackTrace();
	// fail("Should not throw exception: " + e.getMessage());
	// }
	// }
	//
	// @Test
	// public void forum_exception_test() {
	// try{
	// ForumClient client = new ForumClient();
	// assertTrue("It should connect", client.connect());
	// client.forum.throwForumException();
	// fail("it should throw a ForumException");
	// } catch (RemoteException e){
	// System.out.println("Catched RemoteException");
	// System.out.println(e.getMessage());
	// }
	// }

	@Test
	public void can_get_topics() {
		try {
			Forum forum = new Forum();
			List<Topic> topics = forum.getTopics();
			for (Topic topic : topics) {
				// System.out.println(topic);
			}
		} catch (RemoteException e) {
			fail("it shuoldn't throw exception: " + e.getMessage());
		}
	}

	@Test
	public void can_create_topic() {
		try {
			Forum forum = new Forum();
			User user = new User("vic", "abcd1234", User.Type.NORMAL, true, "");
			forum.users.put(user.name(), user); // fake login
			Topic topic = new Topic("", "test topic", "the description", true,
					user.name(), "");
			Topic newTopic = forum.createTopic(user, topic);
			assertNotNull("it should not be null", newTopic);
			assertTrue("new Topic name shuold be: " + topic.name() + ", but is: " + topic.name(),
					topic.name().equals(newTopic.name()));
		} catch (RemoteException e) {
			fail("it shuoldn't throw exception: " + e.getMessage());
		}
	}
	
	@Test
	public void admin_user_can_approve_topic() {
		try {
			Forum forum = new Forum();
			User user = new User("john", "abcd1234", User.Type.ADMIN, true, "");
			forum.users.put(user.name(), user); // fake login
			Topic topic = forum.createTopic(user, new Topic("", "approve me", "to be approved", false, user.name(), ""));
			Topic aprovedTopic = forum.approveTopic(user, topic);
			assertNotNull("topic shouldn't be null", aprovedTopic);
			assertTrue("topic.isActive() should be: true, but is: "+aprovedTopic.isActive(),
					aprovedTopic.isActive()); 
		} catch (RemoteException e) {
			fail("it shuoldn't throw exception: " + e.getMessage());
		}
	}
	
	@Test
	public void normal_user_can_not_approve_topic() {
		try {
			Forum forum = new Forum();
			User user = new User("vic", "abcd1234", User.Type.NORMAL, true, "");
			forum.users.put(user.name(), user); // fake login
			Topic topic = forum.createTopic(user, new Topic("", "approve me", "to be approved", false, user.name(), ""));
			forum.approveTopic(user, topic);
			fail("it should throw a RemoteException");
		} catch (Exception e){
			assertTrue("Exception shuold be: ForumException but is: " + e.getClass(),
					e instanceof ForumException);
			assertTrue(e.getMessage().matches("User must be ADMIN"));
		}
	}

    @Test
    public void valid_user_can_login() {
        try {
            Forum forum = new Forum();
            User user = forum.loginUser("vic", "abcd1234", new ForumClient());

            assertNotNull("it should login => vic:abcd1234", user);
            assertTrue("username shuold be: vic but is: " + user.name(), user.name().equals("vic"));

        } catch (Exception e) {
            fail("Should not throw exception: " + e.getStackTrace());
        }
    }
    
    @Test
    public void invalid_user_can_not_login() {
        try {
            (new Forum()).loginUser("idontexist", "zzzzzzzz", new ForumClient());
            fail("it should throw a ForumException");
        } catch (Exception e) {
            assertTrue("Exception shuold be: ForumException but is: " + e.getClass(),
					e instanceof ForumException);
			assertTrue(e.getMessage().matches("Invalid username or password"));
        }
    }

    // There should be at a topic with Id 1 in db
    @Test
    public void can_get_thread_by_id() {
        try {
            ForumThread thread = (new Forum()).getThreadById("1");
            assertNotNull("it should not be null", thread);
            assertTrue("the title should be: test_thread_1, but is: "+ thread.title(), 
                thread.title().matches("test_thread_1"));
        } catch (Exception e) {
            fail("Should not throw exception: " + e.getStackTrace());
        }
    }    
    
    // there should be at least 1 thread for topicId 1 in db
    @Test
    public void can_get_threads_by_topic() {
        String topicId = "1";
        try {
            List<ForumThread> threads = (new Forum()).getThreadsByTopic(topicId);
            assertTrue("there should be at least 1 thread", threads.size() > 0);
            for(ForumThread thread : threads){
                assertTrue("topicId shuold be: "+topicId+ ", but is: "+thread.topicId(), 
                		thread.topicId().equals(topicId));
            }
        } catch (Exception e) {
            fail("Should not throw exception: " + e.getStackTrace());
        }
    }
    
    @Test
    public void logged_user_can_create_threads() {
        String topicId = "1";
        try {
            Forum forum = new Forum();
            User user = new User("vic", "abcd1234", User.Type.NORMAL, true, "");
            forum.users.put(user.name(), user); // fake login
            ForumThread newThread = new ForumThread("", "new_thread_test", "content", topicId, user.name(), "");
            ForumThread createdThread = forum.createThread(user, newThread);
            assertNotNull("The new thread should be created", createdThread);
            assertNotNull("The new thread should exist", forum.getThreadById(createdThread.id()));
        } catch (Exception e) {
            fail("Should not throw exception: " + e.getStackTrace());
        }
    }
    
    @Test
	public void users_can_register() {
	    User user = new User("register_test", "abcd1234", User.Type.NORMAL, true, "");
		try {
			Forum forum = new Forum();
            DBUser.delete(user);
			assertNull(user.name() +" should not exist in DB", DBUser.getByName(user.name()));
			assertNotNull(forum.registerUser(user.name(), user.password()));
			assertNotNull(DBUser.getByName(user.name()));		
		} catch (RemoteException e) {
			fail("it shuoldn't throw exception: " + e.getMessage());
		}
	}
	
	@Test
	public void can_get_welcome_message() {
		try {
			Forum forum = new Forum();
            assertNotNull(forum.getWelcomeMessage());
		} catch (RemoteException e) {
			fail("it shuoldn't throw exception: " + e.getMessage());
		}
	}
}