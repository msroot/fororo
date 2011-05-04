package gui;
import java.rmi.RemoteException;

import client.*;
public class Driver {
	public static ForumClient forumClient;
	public static void main(String[] args) {
		try {
			forumClient = new ForumClient();
			forumClient.connect();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainWindow.main(null);
	}
}
