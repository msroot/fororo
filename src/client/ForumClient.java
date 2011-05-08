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
	private String host = "localhost";
	private String port = "1099";
	
	public ForumClient() throws RemoteException {};
	
	public ForumClient(String host, String port) throws RemoteException {
	    this.host = host;
	    this.port = port;
	};
	
	public boolean connect(){
		String rmiStr = "rmi://"+host+":"+port+"/Forum";
		try{
			forum  = (ForumInterface) Naming.lookup(rmiStr);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
    
    public String test() throws RemoteException {
		return "it works";
	}
	
	public void updateChat(String message){
	    System.out.println(message);
	}

    public static void main(String args[]){
        ForumClient client = null;
        try {
            if(args.length > 1){
            	client = new ForumClient(args[0], args[1]);
            }else{
                client = new ForumClient();
            }
            client.connect();
            System.out.println(client.forum.ping());
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