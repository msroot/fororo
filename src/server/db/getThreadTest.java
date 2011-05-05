package server.db;

import java.util.List;

import shared.ForumThread;
import shared.User;

public class getThreadTest {
	public static void main(String[] args) {

		/* getByName get only the Thread with the id=1*/
				
		ForumThread t = DBForumThread.getById("28");
		 System.out.print( 
				 "Content: "+ t.content()+
				 " id:"+ t.id() +
				 " topicId:"+ t.topicId() +
				 " title:" +t.title());
		 System.out.print("\n getByName -------------------\n");

				
		 /* getAllByTopic get all threads where topicID=1*/
		 List<ForumThread> treads = DBForumThread.getAllByTopic("1");
		 for (ForumThread th : treads) {
			 System.out.print( 
					 "\nContent:"+ th.content()+
					 " id:"+ th.id() +
					 " topicId:"+ th.topicId() +
					 " title:" +th.title());
		 }
		 System.out.print("\n getAllByTopic -------------------");

	}
}