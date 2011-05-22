package server;

import shared.*;
import server.db.*;
import java.rmi.*;
import java.rmi.server.*;
import java.net.MalformedURLException;
import java.util.*;

/**
 * Main server class. Responsible to accept and hold clients requests and
 * objects via RMI. Assign {@link Chat} to clients and with current thread,
 * create and manage {@link User}, {@link ForumThread},{@link Topic} etc
 * 
 * @author Victor Nava
 * 
 */
public class Forum extends UnicastRemoteObject implements ForumInterface {
	static final long serialVersionUID = 1; // to keep the compiler happy;
	private String host = "localhost";
	private String port = "1099";
	public HashMap<String, User> users = null;
	public HashMap<String, Chat> chats = null;
	public HashMap<String, ForumClientInterface> clients = null;
	public HashMap<String, String> userInTopic = null;

	/**
	 * Forum Contractor
	 * 
	 * @throws RemoteException
	 */
	public Forum() throws RemoteException {
		users = new HashMap<String, User>();
		chats = new HashMap<String, Chat>();
		userInTopic = new HashMap<String, String>();
		clients = new HashMap<String, ForumClientInterface>();
	}

	/**
	 * Forum Contractor
	 * 
	 * @param host
	 * @param port
	 * @throws RemoteException
	 */
	public Forum(String host, String port) throws RemoteException {
		this();
		this.host = host;
		this.port = port;
	}

	/**
	 * Initialise rmi server
	 */
	public void init() {
		System.setSecurityManager(new RMISecurityManager());
		String rmiStr = "rmi://" + host + ":" + port + "/Forum";
		try {
			Naming.rebind(rmiStr, this);
			System.out.println("Created and registered Forum object on port: "
					+ port);
			System.out.println("Now waiting for remote invocations");
		} catch (RemoteException re) {
			System.out.println("Unable to add remote object to registry");
		} catch (MalformedURLException me) {
			System.out.println("Incorrect address for registry");
		}
	}

	/******************** USERS FUNCTIONALITY ********************/

	/**
	 * Login Current User
	 * 
	 * @param username
	 * @param password
	 * @param client
	 * @return user
	 */
	public User loginUser(String username, String password,
			ForumClientInterface client) throws ForumException {
		User user = DBUser.getByName(username);

		if (user == null) {
			throw new ForumException("Invalid username or password");
		}

		if (!user.isActive()) {
			throw new ForumException("Your account has been suspended.");
		}

		if (user.name().equals(username) && user.password().equals(password)) {
			users.put(user.name(), user);
			clients.put(user.name(), client);
			return user;
		} else {
			throw new ForumException("Invalid username or password");
		}
	}

	/**
	 * Logout a user
	 * 
	 * @param {@link User}
	 * @return {@link User}
	 */
	public boolean logoutUser(User user) throws RemoteException {
		if (!userIsLoggedIn(user.name())) {
			return false;
		}
		users.remove(user.name());
		clients.remove(user.name());

		try {
			chats.get(userInTopic.get(user.name())).removeClient(user.name());
		} catch (Exception e) {
		}
		return true;
	}

	/**
	 * Register a new user
	 * 
	 * @param userName
	 * @param password
	 * @return {@link User}
	 */
	public User registerUser(String userName, String password)
			throws RemoteException, ForumException {
		
        if (userName.length() < 3) {
            throw new ForumException("The username must be at least 3 characters long");
        }
        
        if (password.length() < 4 ) {
            throw new ForumException("The password must be at least 4 characters");
        }
		
		User user = DBUser.getByName(userName);
		if (user != null) {
			throw new ForumException("User: " + userName + " already exists");
		}
		user = new User(userName, password, User.Type.NORMAL, true, "");
		return DBUser.create(user);
	}

	/**
	 * Register a new user
	 * 
	 * @param userToUpdate
	 * @param admin
	 * @return {@link User}
	 */
	public User updateUser(User adminUser, User userToUpdate)
			throws RemoteException {
		requireAdmin(adminUser);
		return DBUser.update(userToUpdate);
	}

	/**
	 * Delete a user
	 * 
	 * @param userToDelete
	 * @param admin
	 * @return {@link User}
	 */
	public User deleteUser(User adminUser, User userToDelete)
			throws RemoteException {
		requireAdmin(adminUser);
		return DBUser.delete(userToDelete);
	}

	/**
	 * @return all users from db
	 */
	public List<User> getUsers() throws RemoteException {
		return DBUser.getAll();
	}

	/**
	 * Check if user login
	 * 
	 * @param username
	 * @return
	 */
	private boolean userIsLoggedIn(String username) {
		return users.containsKey(username);
	}

	/**
	 * require to login
	 * 
	 * @param user
	 * @throws ForumException
	 */
	private void requireLogin(User user) throws ForumException {
		if (!userIsLoggedIn(user.name())) {
			throw new ForumException("User is not logged in");
		}
	}

	/**
	 * require an Admin user
	 * 
	 * @param user
	 * @throws ForumException
	 */
	private void requireAdmin(User user) throws ForumException {
		requireLogin(user);
		User loggedUser = users.get(user.name());
		if (!loggedUser.type().equals(User.Type.ADMIN)) {
			throw new ForumException("User must be ADMIN");
		}
	}

	/******************** TOPICS FUNCTIONALITY ********************/

	/**
	 * @return all Topics from db
	 */
	public List<Topic> getTopics() throws RemoteException {
		return DBTopic.getAll();
	}

	/**
	 * Create a new topic
	 * 
	 * @param Topic
	 * @param User
	 * @return {@link Topic}
	 */
	public Topic createTopic(User user, Topic topic) throws RemoteException {
		requireLogin(user);

		User loggedUser = users.get(user.name());

		if (loggedUser.type().equals(User.Type.ADMIN)) {
			topic.isActive(true);
		} else {
			topic.isActive(false);
		}

		topic.userName(user.name());

		return DBTopic.create(topic);
	}

	/**
	 * Update the given topic
	 * 
	 * @param Topic
	 * @param User
	 * @return {@link Topic}
	 */
	public Topic approveTopic(User user, Topic topic) throws RemoteException,
			ForumException {

		requireAdmin(user);

		if (DBTopic.getById(topic.id()) == null) {
			throw new ForumException("Topic not found");
		}

		topic.isActive(true);
		return DBTopic.update(topic);
	}

	/**
	 * Delete the given topic
	 * 
	 * @param User
	 * @param Topic
	 * @throws ForumException
	 * @return {@link Topic}
	 */
	public Topic deleteTopic(User user, Topic topic) throws ForumException {
		requireAdmin(user);
		return DBTopic.delete(topic);
	}

	/******************** THREADS FUNCTIONALITY ********************/

	/**
	 * Get the thread from db with the given id
	 * 
	 * @param threadId
	 * @throws {@link ForumException}
	 * @return {@link ForumThread}
	 */
	public ForumThread getThreadById(String threadId) throws ForumException {
		return DBForumThread.getById(threadId);
	}

	/**
	 * Gets all the threads from db with the given topicId
	 * 
	 * @param topicId
	 * @return List
	 */
	public List<ForumThread> getThreadsByTopic(String topicId)
			throws ForumException {
		return DBForumThread.getAllByTopic(topicId);
	}

	/**
	 * Create a new Thread
	 * 
	 * @param User
	 * @param ForumThread
	 * @return {@link ForumThread}
	 */
	public ForumThread createThread(User user, ForumThread thread)
			throws ForumException {

		User loggedUser = users.get(user.name());

		if (loggedUser != null && loggedUser.isActive()) {
			return DBForumThread.create(thread);
		} else {
			throw new ForumException("User:" + user.name()
					+ "is not logged in or is inactive");
		}
	}

	/**
	 * Delete the given Thread
	 * 
	 * @param User
	 * @param ForumThread
	 * @return {@link ForumThread}
	 */
	public ForumThread deleteThread(User adminUser, ForumThread thread)
			throws RemoteException {
		requireAdmin(adminUser);
		return DBForumThread.delete(thread);
	}
	
	
	public ForumThread attachDescendantsToThread(ForumThread thread) throws RemoteException {
		return attachDesendants(thread);
	}
	
	private ForumThread attachDesendants(ForumThread thread){
	    List<ForumThread> descendants = DBForumThread.getAllByParent(thread.id());
		if (descendants.size() == 0){
		    return thread;
		}
		for(ForumThread child : descendants){    
		    child = attachDesendants(child);
		    thread.children.add(child);
		}
		return thread;
	}

	/******************** CONFIG FUNCTIONALITY ********************/

	/**
	 * Gets the welcome message from db
	 */
	public String getWelcomeMessage() throws RemoteException {
		return DBConfig.get().message();
	}

	/******************** CHAT FUNCTIONALITY ********************/

	/**
	 * Receive a message from the user and creates or adds to the currents chat
	 * list and broadcast the message
	 * 
	 * @param User
	 * @param message
	 */
	public void sendChatMessage(User user, String message)
			throws ForumException {
		requireLogin(user);
		try {
			chats.get(userInTopic.get(user.name())).broadcast(user.name(),
					message);
		} catch (Exception e) {
		}
	}

	/**
	 * Assign the user to the current topic
	 * 
	 * @param user
	 * @param topic
	 */
	public void setChatTopic(User user, Topic topic) throws RemoteException {

		String currentTopic = userInTopic.get(user.name());
		if (currentTopic != null) {
			chats.get(currentTopic).removeClient(user.name());
		}

		userInTopic.put(user.name(), topic.id());

		if (chats.get(topic.id()) == null) {
			chats.put(topic.id(), new Chat());
		}

		chats.get(topic.id()).addClient(user.name(), clients.get(user.name()));

	}

	/******************** TEST FUNCTIONALITY ********************/

	public void throwForumException() throws ForumException {
		throw new ForumException("giveMeAForumException()");
	}

	public String ping() throws RemoteException {
		return "pong";
	}

	/******************** RUNNER FUNCTIONALITY ********************/

	/**
	 * Main Forum Server function
	 */
	public static void main(String args[]) {
		Forum server = null;
		try {
			if (args.length > 1) {
				server = new Forum(args[0], args[1]);
			} else {
				server = new Forum();
			}
			server.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}