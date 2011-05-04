package shared;

import java.rmi.*;

public interface ForumInterface extends java.rmi.Remote{

    public User loginUser(String username, String password, ForumClientInterface client) throws RemoteException;
    
    public boolean logoutUser(String username) throws RemoteException;
    
    public ThreadTopic[] getThreadTopics() throws RemoteException;
    
    public ForumThread[] getThreadsByTopic(String topicId) throws RemoteException;
    
    public ForumThread getThreadById(String threadId) throws RemoteException;
    
//    public ThreadTopic getThreadTopics() throws RemoteException;
    
//    public boolean logoutUser(String userId);

    // Returns the list of threads each hashmap contains:
    //     id: string
    //     title: title 
    //     
    // List<HashMap<String, String>> getGroupList();
    
    
    // returns a hashmap info of each thread
    //         id: string
    //         title: string
    //         content: string
    // List<HashMap<String, String>> getThreadList(String groupId);


    // returns: 
    // id: string
    // title: string
    // content: string
    // thread: string
    // List<HashMap<String, String>> getThread(String threadId);


    // returns a list of groups
    // id: string
    // title: title
    // List<HashMap<String, String>> getGroupRequestList();
    
}