package server;

import java.rmi.RemoteException;
import java.util.HashMap;

import shared.ForumClientInterface;

/**
 * Chat Class accepts client objects and broadcast messages back. The track of
 * "clients in current topic" are holding in forum class. Each topic have a chat
 * object in <Forum> and chat object hold all the clients must broadcast the
 * message
 */
public class Chat {

	public HashMap<String, ForumClientInterface> clients = null;

	/**
	 * Chat contractor creates a new {@link HashMap} with clients
	 */
	public Chat() {
		clients = new HashMap<String, ForumClientInterface>();
	}

	/**
	 * Add a new client object in the clients list
	 * 
	 * @param user
	 * @param client
	 */
	public void addClient(String user, ForumClientInterface client) {
		clients.put(user, client);
		broadcast(null, user + " has join");

	}

	/**
	 * Removes a client from the list
	 * 
	 * @param user
	 */
	public void removeClient(String user) {
		clients.remove(user);
		broadcast(null, user + " has left");

	}

	/**
	 * BroadCast messages from user to the current client list
	 * 
	 * @param user
	 * @param message
	 */
	public void broadcast(String user, String message) {

		if (user != null) {
			message = user.concat(" says:" + message);
		}

		for (String userId : clients.keySet()) {
			try {
				clients.get(userId).updateChat(message);

				System.out.println("from:" + user + " to " + userId + ""
						+ message);

			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

}
