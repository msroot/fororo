package test;

import java.util.List;

import server.db.DBForumThread;
import shared.ForumThread;

public class ThreadManualTest {
	public static void main(String[] args) {

		/* getByName get only the Thread with the id=28 */

		ForumThread t = DBForumThread.getById("101");

		System.out.println(t);
		/*
		 * getAllByTopic: get all threads where topicID=1
		 */

		List<ForumThread> treads = DBForumThread.getAllByTopic("1");
		for (ForumThread th : treads) {

			System.out.println(th);
		}
		System.out.print("\n getAllByTopic -------------------END\n ");

		/* create a new topic and get the current id */
		ForumThread tr = new ForumThread("",
				"yes yes yes  yes yes its work hot hot baby", "i get the id",
				"1", null, null);

		ForumThread th2 = DBForumThread.create(tr);

		System.out.println(th2);
		System.out.print("\n create -------------------END");

	}
}