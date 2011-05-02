public interface ForumInterface {

    // Returns user info: id, username, password, type, actice
    public HashMap<String,String> loginUser(String username, String password);
 
    public boolean logoutUser(userId);

    // Returns the list of threads each hashmap contains:
    //     id: string
    //     title: title 
    //     
    List<HashMap<String, String>> getGroupList();
    
    
    // returns a hashmap info of each thread
    //         id: string
    //         title: string
    //         content: string
    List<HashMap<String, String>> getThreadList(String groupId);


    // returns: 
    // id: string
    // title: string
    // content: string
    // thread: string
    List<HashMap<String, String>> getThread(String threadId);


    // returns a list of groups
    // id: string
    // title: title
    List<HashMap<String, String>> getGroupRequestList();
    
}