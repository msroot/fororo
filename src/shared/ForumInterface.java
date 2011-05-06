package shared;

import java.rmi.*;
import java.util.*;

public interface ForumInterface extends java.rmi.Remote{

    public User loginUser(String userName, String password, ForumClientInterface client) throws RemoteException;
    
    public boolean logoutUser(String userName) throws RemoteException;
    
    // public
   
    // Topics
    public List<Topic> getTopics() throws RemoteException;
    
    public Topic approveTopic(User user, Topic topic) throws RemoteException;
    
    public Topic createTopic(User user, Topic topic) throws RemoteException;
    
    // Threads
    public List<ForumThread> getThreadsByTopic(String topicId) throws RemoteException;
    
    public ForumThread getThreadById(String threadId) throws RemoteException;
    
    public ForumThread createThread(User user, ForumThread thread) throws RemoteException;
    
    // Exception test
    public void throwForumException() throws RemoteException;
    
    public String ping() throws RemoteException;
    
    // public String getWelcomeMessage
}