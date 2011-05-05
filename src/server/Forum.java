package server;

import shared.*;
// import test.DBUser;
import server.db.DBUser;
import server.db.DBTopic;
import server.db.DBForumThread;

import java.rmi.*;
import java.rmi.server.*;
import java.net.MalformedURLException;
import java.util.*;

public class Forum extends UnicastRemoteObject implements ForumInterface {
	static final long serialVersionUID = 1; //to keep the compiler happy;
	
	HashMap<String, User> users = null;
	HashMap<String, ForumClientInterface> clients = null;

    public Forum() throws RemoteException {
        clients = new HashMap<String, ForumClientInterface>();
        users = new HashMap<String, User>();
    }

    public User loginUser(String username, String password, ForumClientInterface client) throws RemoteException, ForumException {
    	
    	User user = DBUser.getByName(username);
    	    	
    	if (user == null){ throw new ForumException("Invalid username or password"); }
    	
    	if (user.name().equals(username) && user.password().equals(password)){
    	    users.put(user.name(), user);
    	    clients.put(user.name(), client);
            return user;
    	} else{
    	    throw new ForumException("Invalid username or password");
    	}
    }
    
    public boolean logoutUser(String username) throws RemoteException {
        // TODO replace with userIsLoggedIn()
    	if (!users.containsKey(username)){ return false; }
        users.remove(username);
        clients.remove(username);
        return true;
    }
    
    // TOPICS
    public List<Topic> getTopics() throws RemoteException { 	    	
    	return DBTopic.getAll();
    }
    
    // public ThreadTopic activateThreadTopic(userId, topicId) throws RemoteException{
    //        if(!userIsLoggedIn()){
    //            throw new RemoteException("User is not logged in");
    //        }
    // 
    //            // FIXME get from DB
    //            ThreadTopic topic = new ThreadTopic(topicId, "", "");
    // 
    //            if(topic == null){
    //                // FIXME throw ForumExeption
    //                throw new RemoteException("Topic not found")
    //                }        
    // 
    //                User user         = users.get(userId);
    // 
    //                if(user.type == User.type.ADMIN){
    //                    topic.
    //                    }
    //                }
    //    }
    
    // THREADS
    public ForumThread getThreadById(String threadId) throws RemoteException{
        return DBForumThread.getById(threadId);
    }
   
    public List<ForumThread> getThreadsByTopic(String topicId) throws RemoteException {
        return DBForumThread.getAllByTopic(topicId);
    }
    
    public ForumThread createThread(String userId, String title, String content, String topicId) throws RemoteException{
        
        return DBForumThread.create(threadId);
       ForumThread thread = new ForumThread("thread_id", title, content, topicId);
        return thread;
    }
    
    public void throwForumException() throws ForumException{
    	throw new ForumException("giveMeAForumException()");
    }
    
    private boolean userIsLoggedIn(String username){
        return users.containsKey(username);
    }

    public static void main(String args[]){
        System.setSecurityManager(new RMISecurityManager());
        try {
            Forum server = new Forum();
            Naming.rebind("rmi://localhost:1099/Forum", server);
            System.out.println("Created and registered Forum object");
        }
        catch (RemoteException re){ 
            System.out.println("Unable to add remote object to registry");
        }
        catch (MalformedURLException me){          
            System.out.println("Incorrect address for registry");
        }
        finally{
            System.out.println("Now waiting for remote invocations");
        }
    }
}