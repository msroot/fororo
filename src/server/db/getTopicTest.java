package server.db;

import java.util.List;

import shared.Topic;

public class getTopicTest {
	public static void main(String[] args) {

		/* getByName */
		Topic t = DBTopic.getById("1");

		System.out.println(t);

		/* getAllByTopic */
		List<Topic> treads = DBTopic.getAll();
		System.out.print("\n getAllByTopic -------------------START\n");
		for (Topic th : treads) {

			System.out.println(th);
		}

		// /* create Topic */
		Topic top = new Topic("146", "works???", "topic description", true,
				"vic", "now");
		Topic newTopic = DBTopic.create(top);
		System.out.print("\n create -------------------START\n");

		System.out.println(newTopic);

		System.out.print("\n create -------------------\n");

		/* update Topic */
		Topic top1 = new Topic("146", "work", "topic description", true, "vic",
				"now");
		System.out.print("\n update -------------------START\n");
		Topic newTopic1 = DBTopic.update(top1);

		System.out.println(newTopic1);
		System.out.print("\n update -------------------\n");

	}
}