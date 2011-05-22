package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import shared.*;
import client.*;

/**
 * Class to be run to start the application
 * 
 * @author Eduardo Nava
 * 
 */
public class Driver {
	public static ForumClient forumClient;
	public static ChatView chat;

	/**
	 * 
	 * @param args
	 *            To specify the server and port
	 */
	public static void main(String[] args) {
		try {
			if (args.length > 1) {
				forumClient = new ForumClient(args[0], args[1]);
			} else {
				forumClient = new ForumClient();
			}
			forumClient.connect();
			chat = new ChatView();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MainWindow.open();
	}

	/**
	 * Shows the Chat Window
	 * 
	 * @param topic
	 *            The current Topic (Chat Room)
	 */
	public static void openChat(Topic topic) {
		chat.open(topic);
	}

	/**
	 * Changes the chat Room
	 * 
	 * @param topic
	 */
	public static void setChatTopic(Topic topic) {
		try {
			forumClient.forum.setChatTopic(Driver.forumClient.user, topic);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		chat.setTopic(topic);
	}

}
