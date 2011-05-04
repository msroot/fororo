package server.db;

import java.util.List;

import shared.ThreadTopic;

public class getThreadTopicTest {
	public static void main(String[] args) {

		/* getByName get only the Thread with the id=1 */

		ThreadTopic t = DBThreadTopic.getById("1");
		System.out
				.print("id: " + t.id() + "\n" + " desc:" + t.description()
						+ "\n" + " active:" + t.isActive() + "\n" + " name:"
						+ t.name());
		System.out.print("\n getByName -------------------\n");

		/* getAllByTopic get all threads where topicID=1 */
		List<ThreadTopic> treads = DBThreadTopic.getAll();
		for (ThreadTopic th : treads) {
			System.out.print("\nid: " + th.id() + "\n" + " desc:"
					+ th.description() + "\n" + " active:" + th.isActive()
					+ "\n" + " name:" + th.name());
		}
		System.out.print("\n getAllByTopic -------------------\n");

	}
}