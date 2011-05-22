package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import shared.*;
import client.*;
public class Driver{
	public static ForumClient forumClient;
	public static ChatView chat;
	public static void main(String[] args) {
		try {
		    if(args.length > 1){
            	forumClient = new ForumClient(args[0], args[1]);
            }else{
                forumClient = new ForumClient();
            }
			forumClient.connect();
			chat = new ChatView();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//FIXME Just For Testing Delete this lines
//		try {
//			forumClient.user = forumClient.forum.loginUser("edd", "abcd1234", forumClient);
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		MainWindow.open();
//		MainWindow.main(null);
//		FrameTest.main(null);
	}
	public static void openGroup(String[] args){
//		MainWindow.window.close();
//		GroupView.main(args);
//		GroupView groupView = new GroupView(args);
	}
	public static void close(){

	}
	public static void openChat(Topic topic){
		chat.open(topic);
	}
	public static void setChatTopic(Topic topic){
		try {
			forumClient.forum.setChatTopic(Driver.forumClient.user, topic);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		chat.setTopic(topic);
	}

}
