package shared;

import java.rmi.*;
import java.util.*;

public interface ForumInterface extends java.rmi.Remote{

    // Users
    public User loginUser(String userName, String password, ForumClientInterface client) throws RemoteException;
    
    public boolean logoutUser(User user) throws RemoteException;
    
    public User registerUser(String userName, String password) throws RemoteException;
    
    public User updateUser(User adminUser, User userToUpdate) throws RemoteException;
    
    public User deleteUser(User adminUser, User userToDelete) throws RemoteException;
    
    public List<User> getUsers() throws RemoteException;
   
    // Topics
    public List<Topic> getTopics() throws RemoteException;
    
    public Topic approveTopic(User user, Topic topic) throws RemoteException;
    
    public Topic createTopic(User user, Topic topic) throws RemoteException;
    
    public Topic deleteTopic(User user, Topic topic) throws RemoteException;
    
    // Threads
    public List<ForumThread> getThreadsByTopic(String topicId) throws RemoteException;
    
    public ForumThread getThreadById(String threadId) throws RemoteException;
    
    public ForumThread createThread(User user, ForumThread thread) throws RemoteException;
    
    public ForumThread deleteThread(User adminUser, ForumThread threadToDelete) throws RemoteException;
    
    // Config
    public String getWelcomeMessage() throws RemoteException;
    
    
    // CHAT
    public void sendChatMessage(User user, String message) throws RemoteException;
    public void setChatTopic(User user, Topic topic) throws RemoteException;
    
    // Exception test
    public void throwForumException() throws RemoteException;
    
    public String ping() throws RemoteException;
    
}