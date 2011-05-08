package server;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import shared.ForumClientInterface;

public class Chat {

	 public HashMap<String, ForumClientInterface> clients = null;

	//public Map clients = new HashMap();

	public Chat() {
		 clients = new HashMap<String, ForumClientInterface>();
	}

	public void addClient(String user, ForumClientInterface client) {
		clients.put(user, client);
	}

	public void removeClient(String user) {
		clients.remove(user);
	}

	public void broadCast(String user, String message) {
		String broadMessage = user.concat(" says:" + message);

		
		for (ForumClientInterface client :clients.values()){
			client.updateChat(broadMessage);
			
		}		/*Iterator iterator = clients.values().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			System.out.println(key);
		}*/

	}

}
