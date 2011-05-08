package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import client.*;
public class Driver{
	public static ForumClient forumClient;
	public static void main(String[] args) {
		try {
		    if(args.length > 1){
            	forumClient = new ForumClient(args[0], args[1]);
            }else{
                forumClient = new ForumClient();
            }
			forumClient.connect();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainWindow.main(null);
//		FrameTest.main(null);
	}
	public static void openGroup(String[] args){
//		MainWindow.window.close();
//		GroupView.main(args);
		GroupView groupView = new GroupView(args);
	}
	public static void close(){

	}

}
