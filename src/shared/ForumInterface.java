package shared;

import java.rmi.*;
import java.util.*;

public interface ForumInterface extends java.rmi.Remote{

    public User loginUser(String username, String password, ForumClientInterface client) throws RemoteException;
    
    public boolean logoutUser(String username) throws RemoteException;
   
    public List<Topic> getTopics() throws RemoteException;
    
    public Topic approveTopic(String userId, String topicId) throws RemoteException;
    
    public List<ForumThread> getThreadsByTopic(String topicId) throws RemoteException;
    
    public ForumThread getThreadById(String threadId) throws RemoteException;
    
    public ForumThread createThread(String userId, String title, String content, String topicId) throws RemoteException;
    
    public void throwForumException() throws RemoteException;
    
    // public String getWelcomeMessage   
}