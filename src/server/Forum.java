package server;

import shared.*;
import server.db.DBUser;
import server.db.DBTopic;
import server.db.DBForumThread;

import java.rmi.*;
import java.rmi.server.*;
import java.net.MalformedURLException;
import java.util.*;

public class Forum extends UnicastRemoteObject implements ForumInterface {
	static final long serialVersionUID = 1; // to keep the compiler happy;

	public HashMap<String, User> users = null;
	public HashMap<String, ForumClientInterface> clients = null;

	public Forum() throws RemoteException {
		clients = new HashMap<String, ForumClientInterface>();
		users = new HashMap<String, User>();
	}

	public User loginUser(String username, String password,
			ForumClientInterface client) throws RemoteException, ForumException {

		User user = DBUser.getByName(username);

		if (user == null) {
			throw new ForumException("Invalid username or password");
		}

		if (user.name().equals(username) && user.password().equals(password)) {
			users.put(user.name(), user);
			clients.put(user.name(), client);
			return user;
		} else {
			throw new ForumException("Invalid username or password");
		}
	}
    
	public boolean logoutUser(String username) throws RemoteException {
		if (!userIsLoggedIn(username)) {
			return false;
		}
		users.remove(username);
		clients.remove(username);
		return true;
	}

	// TOPICS
	public List<Topic> getTopics() throws RemoteException {
		return DBTopic.getAll();
	}

	public Topic createTopic(User user, Topic topic) throws RemoteException {
		if (!userIsLoggedIn(user.name())) {
			throw new ForumException("User is not logged in");
		}

		User loggedUser = users.get(user.name());

		if (loggedUser.type().equals(User.Type.ADMIN)) {
			topic.isActive(true);
		} else {
			topic.isActive(false);
		}

		topic.userName(user.name());

		return DBTopic.create(topic);
	}

	public Topic approveTopic(User user, Topic topic) throws RemoteException {

		if (!userIsLoggedIn(user.name())) {
			throw new ForumException("User is not logged in");
		}

		User loggedUser = users.get(user.name());

		if (!loggedUser.type().equals(User.Type.ADMIN)) {
			throw new ForumException("User must be ADMIN");
		}

		if (DBTopic.getById(topic.id()) == null) {
			throw new ForumException("Topic not found");
		}

		topic.isActive(true);

		return DBTopic.update(topic);
	}

	// THREADS
    // TODO test
	public ForumThread getThreadById(String threadId) throws RemoteException {
		return DBForumThread.getById(threadId);
	}
    
    // TODO test
	public List<ForumThread> getThreadsByTopic(String topicId)
			throws RemoteException {
		return DBForumThread.getAllByTopic(topicId);
	}

	// TODO Test
	public ForumThread createThread(User user, ForumThread thread)
			throws RemoteException {

		User loggedUser = users.get(user.name());

		if (loggedUser != null && loggedUser.isActive()) {
			return DBForumThread.create(thread);
		} else {
			throw new ForumException("User:" + user.name()
					+ "is not logged in or is inactive");
		}
	}

	public void throwForumException() throws ForumException {
		throw new ForumException("giveMeAForumException()");
	}

	private boolean userIsLoggedIn(String username) {
		return users.containsKey(username);
	}

	public void init() {
		System.setSecurityManager(new RMISecurityManager());
		try {
			Naming.rebind("rmi://localhost:1099/Forum", this);
			System.out.println("Created and registered Forum object");
		} catch (RemoteException re) {
			System.out.println("Unable to add remote object to registry");
		} catch (MalformedURLException me) {
			System.out.println("Incorrect address for registry");
		} finally {
			System.out.println("Now waiting for remote invocations");
		}
	}

	public static void main(String args[]) {
        System.setSecurityManager(new RMISecurityManager());
        try {
            Forum server = new Forum();
            server.init();
        } catch (Exception e) {
            e.printStackTrace();
        }    	
	}
}