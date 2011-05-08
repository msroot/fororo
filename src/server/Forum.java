package server;

import shared.*;
import server.db.*;
import java.rmi.*;
import java.rmi.server.*;
import java.net.MalformedURLException;
import java.util.*;

public class Forum extends UnicastRemoteObject implements ForumInterface {
    static final long serialVersionUID = 1; // to keep the compiler happy;
    private String host = "localhost";
    private String port = "1099";

    public HashMap<String, User> users = null;
    public HashMap<String, ForumClientInterface> clients = null;

    public Forum() throws RemoteException {
        clients = new HashMap<String, ForumClientInterface>();
        users = new HashMap<String, User>();
    }

    public Forum(String host, String port) throws RemoteException {
        this();
        this.host = host;
        this.port = port;
    }
    
    public void init() {
        System.setSecurityManager(new RMISecurityManager());
        String rmiStr = "rmi://"+host+":"+port+"/Forum";
        try {
            Naming.rebind(rmiStr, this);
            System.out.println("Created and registered Forum object on port: "+port);
            System.out.println("Now waiting for remote invocations");
        } catch (RemoteException re) {
            System.out.println("Unable to add remote object to registry");
        } catch (MalformedURLException me) {
            System.out.println("Incorrect address for registry");
        }
    }
    
    /******************** USERS ********************/

    public User loginUser(String username, String password, ForumClientInterface client) throws ForumException {

        User user = DBUser.getByName(username);

        if (user == null) {
            throw new ForumException("Invalid username or password");
        }
        
        if (!user.isActive()){
            throw new ForumException("User is inactive, can't login");
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

    public User registerUser(String userName, String password) throws RemoteException, ForumException{
        User user = DBUser.getByName(userName);
        if(user != null){
            throw new ForumException("User: "+userName+" already exists");
        }
        user = new User(userName, password, User.Type.NORMAL, true, "");
        return DBUser.create(user);
    }

    public User updateUser(User adminUser, User userToUpdate) throws RemoteException {
        requireAdmin(adminUser);
        return DBUser.update(userToUpdate);
    }
    
    public User deleteUser(User adminUser, User userToDelete) throws RemoteException{
        requireAdmin(adminUser);
        return DBUser.delete(userToDelete);
    }
    
    public List<User> getUsers() throws RemoteException{
        return DBUser.getAll();
    }
    
    private boolean userIsLoggedIn(String username) {
        return users.containsKey(username);
    }

    private void requireLogin(User user) throws ForumException {
        if (!userIsLoggedIn(user.name())) {
            throw new ForumException("User is not logged in");
        }
    }

    private void requireAdmin(User user) throws ForumException {
        requireLogin(user);
        User loggedUser = users.get(user.name());
        if (!loggedUser.type().equals(User.Type.ADMIN)) {
            throw new ForumException("User must be ADMIN");
        }
    }

    /******************** TOPICS ********************/

    public List<Topic> getTopics() throws RemoteException {
        return DBTopic.getAll();
    }

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

    public Topic approveTopic(User user, Topic topic) throws RemoteException, ForumException {

        requireAdmin(user);

        if (DBTopic.getById(topic.id()) == null) {
            throw new ForumException("Topic not found");
        }

        topic.isActive(true);
        return DBTopic.update(topic);
    }

    public Topic deleteTopic(User user, Topic topic) throws ForumException { 
        requireAdmin(user);
        return DBTopic.delete(topic);
    }

    /******************** THREADS ********************/

    public ForumThread getThreadById(String threadId) throws ForumException {
        return DBForumThread.getById(threadId);
    }

    public List<ForumThread> getThreadsByTopic(String topicId) throws ForumException {
        return DBForumThread.getAllByTopic(topicId);
    }

    public ForumThread createThread(User user, ForumThread thread) throws ForumException {

        User loggedUser = users.get(user.name());

        if (loggedUser != null && loggedUser.isActive()) {
            return DBForumThread.create(thread);
        } else {
            throw new ForumException("User:" + user.name() + "is not logged in or is inactive");
        }
    }
    
    public ForumThread deleteThread(User adminUser, ForumThread thread) throws RemoteException {
        requireAdmin(adminUser);
        return DBForumThread.delete(thread);
    }

    /******************** CONFIG ********************/
    
    public String getWelcomeMessage() throws RemoteException{
        return DBConfig.get().message();
    }


    /******************** TEST ********************/

    public void throwForumException() throws ForumException {
        throw new ForumException("giveMeAForumException()");
    }

    public String ping() throws RemoteException {
        return "pong";
    }


    /******************** RUNNER ********************/
    
    public static void main(String args[]) {
        Forum server = null;
        try {
            if(args.length > 1){
                server = new Forum(args[0], args[1]);
            }else{
                server = new Forum();
            }
            server.init();
        } catch (Exception e) {
            e.printStackTrace();
        }    	
    }
}