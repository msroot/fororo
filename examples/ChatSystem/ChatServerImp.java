import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ChatServerImp extends UnicastRemoteObject
        implements ChatServer 
{
    public final static int SUCCESS = 0;
    public final static int ERR_USER_NAME = 1;
    public final static int QUIT = 1;
    public final static int MSG = 2;
    public final static int JOIN = 3;

    public final static String SERVER = "";

    Hashtable chatListeners = new Hashtable();

    public ChatServerImp() throws RemoteException {
    }

    public int addChatListener(CallBack cl, String userName)
                throws java.rmi.RemoteException {

	// Check for collision
	if (chatListeners.get(userName) != null) {
	    System.out.println("Duplicate user! (" + userName + ")");
	    return ERR_USER_NAME;
	} else if (userName.equals(SERVER)) {
	    System.out.println("Bad username.");
	    return ERR_USER_NAME;
	} else {
	    chatListeners.put(userName, cl);
	    sendChatEvent(JOIN, userName, SERVER);
	    System.out.println("New user: " + userName);
	    return SUCCESS;
	}
    }

    public void removeChatListener(String userName, String reason)
	throws java.rmi.RemoteException {        
	sendChatEvent(QUIT, userName, reason);
      chatListeners.remove(userName);
	System.out.println("User quit: " + userName);
    }
    public void sendChatMessage(String sender, String message)
	throws java.rmi.RemoteException {
	sendChatEvent(MSG, sender, message);
    }

    /* sendChatEvent is called by clients indirectly through other 
     * methods for 
     * communication purposes.   
     */
    private void sendChatEvent(int type, String sender, String message) 
	 throws java.rmi.RemoteException {
	
       Hashtable h;
      
       synchronized(this) {h = (Hashtable)chatListeners.clone();}

	/* Disallow further communication from someone who has been
	 * removed from the table.
	 */
	 if (sender.equals(SERVER) || chatListeners.get(sender) != null) {
	    
	    try {
		 for (Enumeration e = h.elements(); e.hasMoreElements();) 
             {
		    CallBack cl = (CallBack)e.nextElement();

                switch (type)
                {
                   case MSG :  cl.addMessage(sender,message);
                     break;
                   case JOIN : cl.joinedMessage(sender);
                     break;
                   case QUIT :  cl.quitMessage(sender); 
		    }
	       } 
          } 
          catch(Exception e) {
		e.printStackTrace();
	    }
	 }
	
    }

    public static void main(String argv[]) {
       try {
          ChatServerImp thisExample = new ChatServerImp();
            Naming.rebind("//localhost/ChatServer", thisExample);
       } catch (Exception e) {
           System.out.println("ChatServer err: " + e.getMessage());
           e.printStackTrace();
       }
    }
}
