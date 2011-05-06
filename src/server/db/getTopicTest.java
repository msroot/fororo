package server.db;

import java.util.List;

import shared.Topic;

public class getTopicTest {
	public static void main(String[] args) {

		/* getByName */
		Topic t = DBTopic.getById("1");
		System.out.print("\n getByName -------------------START\n");
		System.out
				.print("id: " + t.id() + "\n" + " desc:" + t.description()
						+ "\n" + " active:" + t.isActive() + "\n" + " name:"
						+ t.name());
		

		/* getAllByTopic */
		List<Topic> treads = DBTopic.getAll();
		System.out.print("\n getAllByTopic -------------------START\n");
		for (Topic th : treads) {
			System.out.print("\nid: " + th.id() + "\n" + " desc:"
					+ th.description() + "\n" + " active:" + th.isActive()
					+ "\n" + " name:" + th.name());
		}
		

		/* create Topic */
		Topic top = new Topic(null, "works???", "topic description", true);
		Topic newTopic = DBTopic.create(top);
		System.out.print("\n create -------------------START\n");
		System.out.print("id: " + newTopic.id() + "\n" + " desc:"
				+ newTopic.description() + "\n" + " active:"
				+ newTopic.isActive() + "\n" + " name:" + newTopic.name());
		System.out.print("\n create -------------------\n");

		/* update Topic */
		 Topic top1 = new Topic("18", "this was topic 18", "topic description",
		 true);
		 System.out.print("\n update -------------------START\n");
		 Topic newTopic1 = DBTopic.update(top1);
		 System.out
		 .print("id: " + newTopic1.id() + "\n" + " desc:" +
		 newTopic1.description()
		 + "\n" + " active:" + newTopic1.isActive() + "\n" + " name:"
		 + newTopic1.name());
		 System.out.print("\n update -------------------\n");
				

	}
}