import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

/**
 * Pulls the Server from the RMIRegistry and registers itself with
 * the Server.
 * Upon the server's notifcation, it will then alert the user with
 * any new connections.
 * 
 * @author Daryl Teo 2010
 */
public class Client extends UnicastRemoteObject implements ClientInterface{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		new Client();
	}
	
	public Client() throws Exception{
		/* Get the Registry! */
		Registry reg = LocateRegistry.getRegistry("localhost",12345);
		
		/* Get the Server! */
		ServerInterface server = (ServerInterface)reg.lookup("Server");
		
		/* Register this client as Callback */
		server.register(this);
	}

	@Override
	public void alertNewUser(String name) throws RemoteException {
		/* This will be called by the Server via Callbacks. */
		System.out.printf("A new client %s has connected to the server\n",name);
	}

	
}