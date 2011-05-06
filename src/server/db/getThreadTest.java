package server.db;

import java.util.List;

import shared.ForumThread;

public class getThreadTest {
	public static void main(String[] args) {

		/* getByName get only the Thread with the id=28 */

		ForumThread t = DBForumThread.getById("28");
		System.out.print("Content: " + t.content() + " id:" + t.id()
				+ " topicId:" + t.topicId() + " title:" + t.title());
		System.out.print("\n getByName -------------------END\n");

		/*
		 * getAllByTopic: get all threads where topicID=1
		 */

		List<ForumThread> treads = DBForumThread.getAllByTopic("1");
		for (ForumThread th : treads) {
			System.out.print("\nContent:" + th.content() + " id:" + th.id()
					+ " topicId:" + th.topicId() + " title:" + th.title());
		}
		System.out.print("\n getAllByTopic -------------------END");

		/* create a  new topic and get the current id*/
		ForumThread tr = new ForumThread("", "yes its work", "i get the id",
				"1", null, null);

		ForumThread th2 = DBForumThread.create(tr);
		System.out.print("\nContent:" + th2.content() + " ID:" + th2.id()
				+ " topicId:" + th2.topicId() + " title:" + th2.title());

		System.out.print("\n create -------------------END");

	}
}