package server;

import shared.*;

import java.rmi.*;
import java.rmi.server.*;
import java.net.MalformedURLException;
import java.util.*;

public class Forum extends UnicastRemoteObject implements ForumInterface {
    
	HashMap<String, User> users = null;
	HashMap<String, ForumClientInterface> clients = null;

    public Forum() throws RemoteException {
        clients = new HashMap<String, ForumClientInterface>();
        users = new HashMap<String, User>();
    }

    public User loginUser(String username, String password, ForumClientInterface client) throws RemoteException {
    	//FIXME get user from DB
//    	User user dbUser.getByName();
    	
    	User user = new User(username, password, User.Type.NORMAL);
    	    	
    	if (user == null){ throw new RemoteException("Invalid username or password"); }
    	
    	if (user.name().equals(username) && user.password().equals(password)){
    	    users.put(user.name(), user);
    	    clients.put(user.name(), client);
            return user;
    	} else{
    	    throw new RemoteException("Invalid username or password");
    	}
    }
    
    public boolean logoutUser(String username) throws RemoteException {
    	if (!users.containsKey(username)){ return false; }
        users.remove(username);
        clients.remove(username);
        return true;
    }
    
    public ThreadTopic[] getThreadTopics() throws RemoteException {
    	//FIXME ThreadTopic[] topics = DBThreadTopic.getAll();    	
    	ThreadTopic[] topics = { new ThreadTopic(), new ThreadTopic(), new ThreadTopic() };
        return topics;
    }
    
    public ForumThread[] getThreadsByTopic(String topicId) throws RemoteException {
        //FIXME get from database
    	ForumThread[] threads = { new ForumThread(), new ForumThread(), new ForumThread() };
        return threads;
    }
    
    public ForumThread getThreadById(String threadId) throws RemoteException{
        return new ForumThread();
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