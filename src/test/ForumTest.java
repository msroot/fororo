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

    /******************** TOPICS ********************/

    @Test
    public void can_get_topics() {
        try {
            Forum forum = new Forum();
            List<Topic> topics = forum.getTopics();
            for (Topic topic : topics) {
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
    public void admins_can_approve_topic() {
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
    public void normal_users_can_not_approve_topic() {
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
    public void admins_can_delete_topics() {
        try {
            Forum forum = new Forum();
            User user = new User("john", "abcd1234", User.Type.ADMIN, true, "");
            forum.users.put(user.name(), user); // fake login
            Topic topic = new Topic("", "delete_topic_test", "the description", true, user.name(), "");
            Topic newTopic = forum.createTopic(user, topic);
            assertNotNull(forum.deleteTopic(user, newTopic));
        } catch (RemoteException e) {
            fail("it shuoldn't throw exception: " + e.getMessage());
        }
    }

    @Test
    public void normal_users_can_not_delete_topics() {
        try {
            Forum forum = new Forum();
            User user = new User("vic", "abcd1234", User.Type.NORMAL, true, "");
            forum.users.put(user.name(), user); // fake login
            Topic topic = new Topic("", "delete_topic_test", "the description", true, user.name(), "");
            Topic newTopic = forum.createTopic(user, topic);
            forum.deleteTopic(user, newTopic);
            fail("it shuold throw exception");
        } catch (RemoteException e) {
            assertTrue("Exception shuold be: ForumException but is: " + e.getClass(),
                e instanceof ForumException);
            assertTrue(e.getMessage().matches("User must be ADMIN"));
        }
    }

    /******************** USERS ********************/

    @Test
    public void valid_user_can_login() {
        try {
            Forum forum = new Forum();
            User user = forum.loginUser("vic", "abcd1234", new ForumClient());

            assertNotNull("it should login => vic:abcd1234", user);
            assertTrue("username shuold be: vic but is: " + user.name(), user.name().equals("vic"));

        } catch (Exception e) {
            e.printStackTrace();
            fail("Should not throw exception: ");
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

    @Test
    public void inactive_users_can_not_login() {
        try {
            User inactiveUser = new User("inactive_user", "abcd1234", User.Type.ADMIN, false, "");
            DBUser.delete(inactiveUser);
            DBUser.create(inactiveUser);
            (new Forum()).loginUser(inactiveUser.name(), inactiveUser.password(), new ForumClient());
            fail("it should throw a ForumException");
        } catch (Exception e) {
            assertTrue("Exception shuold be: ForumException but is: " + e.getClass(),
                e instanceof ForumException);
            assertTrue(e.getMessage().matches("Your account has been suspended."));
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
    public void admins_can_update_users() {
        try {
            Forum forum = new Forum();
            User admin = new User("john", "abcd1234", User.Type.ADMIN, true, "");
            User userToUpdate = new User("user_to_update", "abcd1234", User.Type.NORMAL, true, "");
            forum.users.put(admin.name(), admin); // fake login
            DBUser.delete(userToUpdate); //make sure it doesn't exist
            assertNotNull(DBUser.create(userToUpdate));
            userToUpdate.isActive(false);
            userToUpdate = DBUser.update(userToUpdate);
            assertFalse("isActive() should have changed to false", userToUpdate.isActive());
        } catch (RemoteException e) {
            fail("it shuoldn't throw exception: " + e.getMessage());
        }
    }

    @Test
    public void admins_can_delete_users() {
        try {
            Forum forum = new Forum();
            User admin = new User("john", "abcd1234", User.Type.ADMIN, true, "");
            User userToDelete = new User("user_to_delete", "abcd1234", User.Type.NORMAL, true, "");
            forum.users.put(admin.name(), admin); // fake login
            DBUser.create(userToDelete); //make sure it exist
            assertNotNull(DBUser.getByName(userToDelete.name()));
            forum.deleteUser(admin, userToDelete);
            assertNull("User shuold be null now", DBUser.getByName(userToDelete.name()));
        } catch (RemoteException e) {
            fail("it shuoldn't throw exception: " + e.getMessage());
        }
    }

    @Test
    public void normal_users_can_not_delete_users() {
        try {
            Forum forum = new Forum();
            User user = new User("vic", "abcd1234", User.Type.NORMAL, true, "");
            User userToDelete = new User("user_to_delete", "abcd1234", User.Type.NORMAL, true, "");
            forum.users.put(user.name(), user); // fake login
            DBUser.create(userToDelete); //make sure it exist
            assertNotNull(DBUser.getByName(userToDelete.name()));
            forum.deleteUser(user, userToDelete);
            fail("it shuold throw exception");
        } catch (RemoteException e) {
            assertTrue("Exception shuold be: ForumException but is: " + e.getClass(),
                e instanceof ForumException);
            assertTrue(e.getMessage().matches("User must be ADMIN"));
        }
    }

    @Test
    public void can_get_users() {
        try {
            Forum forum = new Forum();
            assertTrue(forum.getUsers().size() == DBUser.getAll().size());
        } catch (RemoteException e) {
            fail("it shuoldn't throw exception: " + e.getMessage());
        }
    }

    /******************** THREADS ********************/

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
    public void admins_can_delete_threads() {
        String topicId = "1";
        User admin = new User("john", "abcd1234", User.Type.ADMIN, true, "");
        ForumThread thread = new ForumThread("", "delete_thread_test", "content", topicId, admin.name(), "");
        try {
            thread = DBForumThread.create(thread);
            Forum forum = new Forum();
            forum.users.put(admin.name(), admin); // fake login
            forum.deleteThread(admin, thread);
            assertNull("It should delete the thread", DBForumThread.getById(thread.id()));
        } catch (Exception e) {
            fail("Should not throw exception: " + e.getStackTrace());
        }
    }


    @Test
    public void can_attach_descendants_to_thread() {
        String topicId = "1";
        User admin = new User("john", "abcd1234", User.Type.ADMIN, true, "");
        ForumThread thread = new ForumThread("", "root", "content", topicId, admin.name(), "");
        ForumThread child = null;
        try {
            thread = DBForumThread.create(thread);
            child = new ForumThread("", "child of: "+thread.id(), "content", topicId, admin.name(), "", thread.id());

            // add descendants to root thread
            DBForumThread.create(child);
            DBForumThread.create(child);
            DBForumThread.create(child);

            Forum forum = new Forum();

            assertTrue("it shuold not have childs", thread.children.size() == 0);
            thread = forum.attachDescendantsToThread(thread);
            assertTrue("now it shuold have 3 childs", thread.children.size() == 3);

        } catch (Exception e) {
            e.printStackTrace();
            fail("Should not throw exception: " + e.getMessage());
        }
    }
    
    @Test
    public void can_attach_descendants_deep() {
        String topicId = "1";
        User admin = new User("john", "abcd1234", User.Type.ADMIN, true, "");
        ForumThread thread = new ForumThread("", "root", "content", topicId, admin.name(), "");
        ForumThread child = null;
        ForumThread grandChild = null;
        
        try {
            thread = DBForumThread.create(thread);
            child = new ForumThread("", "child of: "+thread.id(), "content", topicId, admin.name(), "", thread.id());
            
            // add a childs to root thread
            child = DBForumThread.create(child);
            
            grandChild = new ForumThread("", "child of: "+child.id(), "content", topicId, admin.name(), "", child.id());
            
            // add two grand childs thru last child
            DBForumThread.create(grandChild);
            DBForumThread.create(grandChild);

            Forum forum = new Forum();

            assertTrue("it shuold not have childs", thread.children.size() == 0);
            thread = forum.attachDescendantsToThread(thread);
            assertTrue("it now it shuold have 1 child", thread.children.size() == 1);
            assertTrue("root shuold have 2 grandchilds thru: "+child.id() ,
            		thread.children.get(0).children.size() == 2);
            
        } catch (Exception e) {
            e.printStackTrace();
            fail("Should not throw exception: " + e.getMessage());
        }
    }



    /******************** CONFIG ********************/

    @Test
    public void can_get_welcome_message() {
        String msg = "Welcome message test";
        try {
            Forum forum = new Forum();
            DBConfig.update(new Config(msg));
            assertTrue(forum.getWelcomeMessage().equals(msg));
        } catch (RemoteException e) {
            fail("it shuoldn't throw exception: " + e.getMessage());
        }
    }
}