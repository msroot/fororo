package server;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import shared.ForumClientInterface;

public class Chat {

	public HashMap<String, ForumClientInterface> clients = null;

	// public Map clients = new HashMap();

	public Chat() {
		clients = new HashMap<String, ForumClientInterface>();
	}

	public void addClient(String user, ForumClientInterface client) {
		clients.put(user, client);
		broadcast(null, user + " has join");

	}

	public void removeClient(String user) {
		clients.remove(user);
		broadcast(null, user + " has left");

	}

	public void broadcast(String user, String message) {

		if (user != null) {
			message = user.concat(" says:" + message);
		}

		
		for (String userId : clients.keySet()) {
			try {
				clients.get(userId).updateChat(message);
				
				System.out.println("from:" + user + " to " + userId + "" +message);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
			
		/*for (ForumClientInterface client : clients.values()) {
			try {
				client.updateChat(message);
				System.out.println("from:" + user + " to " + client);
			} catch (RemoteException e) {
				e.printStackTrace();
			}*/

		}
	}

}
