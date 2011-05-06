package client;

import shared.ForumClientInterface;
import shared.ForumInterface;
import shared.User;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.net.MalformedURLException;

public class ForumClient extends UnicastRemoteObject implements ForumClientInterface{
	
    public User user = null;
    public ForumInterface forum = null;
	
	public ForumClient() throws RemoteException {};
	
	public boolean connect(){
		try{
			forum  = (ForumInterface) Naming.lookup("rmi://localhost:1099/Forum");
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
    
    public String test() throws RemoteException {
		return "it works";
	}

    public static void main(String args[]){
        try {
        	ForumClient client = new ForumClient();
            client.connect();
            System.out.println(client.forum.ping());
            ;
            // client.user = client.forum.loginUser("adamjones", "abcd1234", client);
//            System.out.println(client.user.name());
//            System.out.println(client.user.password());
//            System.out.println(client.user.type());
        }
        catch (Exception re)
        { 
            System.out.println("Failed to retrieve reference to remote object:");
            System.out.println(re);
        }
    }
}