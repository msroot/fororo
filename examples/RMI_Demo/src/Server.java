import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.util.*;

/**
 * A simple Server implementation that will handle Client registrations,
 * and alert all clients of any new connections via Callbacks.
 * Also demonstrates some degree of multi-threaded handling.
 * 
 * @author Daryl Teo 2010
 */
public class Server extends UnicastRemoteObject implements ServerInterface{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		new Server();
	}

	private List<ClientInterface> clients = new LinkedList<ClientInterface>();
	
	public Server() throws RemoteException{
		/* Create the Registry. 
		 * At the moment, I am simply creating it with no checks.
		 * With some checks, I can connect to the registry if a registry
		 * already exists */
		Registry reg = LocateRegistry.createRegistry(12345);
		
		/* Bind the Server to the RMI Registry */
		reg.rebind("Server", this);
		
		System.out.println("Server Bound to RMIRegistry");
	}

	@Override
	public void register(ClientInterface client) throws RemoteException {
		System.out.println("Server has registered new Client");
		
		synchronized(clients){
			/* Remember, all RMI calls should be assumed to be separate threads. */
			clients.add(client);
		}
		
		(new Thread(){
			public void run(){
				synchronized (clients){
					/*
					 * If ever dealing with a shared resource, ESPECIALLY iteration
					 * you MUST do this. Fail to do this at your peril. 
					 * */
					for (ClientInterface client : clients){
						try {
							/* What happens if one of the clients disconnects? You will likely
							 * get a ConnectException error. At the moment it is easy to handle just 
							 * this one call... but what if your Client Interface is too complex? 
							 * You certainly want to have a single class that will handle all 
							 * RMI calls.
							 * */
							client.alertNewUser("Client " + clients.size());
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}
}
