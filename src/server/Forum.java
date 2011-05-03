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
        //FIXME push client to list if valid user
    	//FIXME get user from DB
//    	User user dbUser.getByName();
    	User user = new User(username, password, User.Type.NORMAL);
    	    	
    	
    	if (user == null){ return null; }
    	
	    users.put(user.name(), user);
	    clients.put(user.name(), client);
        
	    return user;
    }
    
    public boolean logoutUser(String username) throws RemoteException {
    	System.out.println("tologout:" +username);
    	for (String u : users.keySet()){
    		System.out.println(u);
    	}
    	if (!users.containsKey(username)){
            return false;
        }
        users.remove(username);
        clients.remove(username);
        return true;
    }
    
    public ThreadTopic[] getThreadTopics() throws RemoteException {
    	//FIXME get from database
    	ThreadTopic[] groups = { new ThreadTopic(), new ThreadTopic(), new ThreadTopic() };
        return groups;
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