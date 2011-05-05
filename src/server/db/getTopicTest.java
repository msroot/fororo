package server.db;

import java.util.List;

import shared.Topic;

public class getTopicTest {
	public static void main(String[] args) {

		/* getByName  */
//		Topic t = DBTopic.getById("1");
//		System.out
//				.print("id: " + t.id() + "\n" + " desc:" + t.description()
//						+ "\n" + " active:" + t.isActive() + "\n" + " name:"
//						+ t.name());
//		System.out.print("\n getByName -------------------\n");
//
//		/* getAllByTopic  */
//		List<Topic> treads = DBTopic.getAll();
//		for (Topic th : treads) {
//			System.out.print("\nid: " + th.id() + "\n" + " desc:"
//					+ th.description() + "\n" + " active:" + th.isActive()
//					+ "\n" + " name:" + th.name());
//		}
//		System.out.print("\n getAllByTopic -------------------\n");
//		
		/*create Topic*/
		Topic top =   new Topic(null, "works???", "topic description", true);
		Topic newTopic = DBTopic.create(top);
		System.out
				.print("id: " + newTopic.id() + "\n" + " desc:" + newTopic.description()
						+ "\n" + " active:" + newTopic.isActive() + "\n" + " name:"
						+ newTopic.name());
		System.out.print("\n create -------------------\n");
		
		
		/*update Topic*/
//		Topic top =   new Topic("41", "this is topic 41", "topic description", true);
//		Topic newTopic = DBTopic.update(top);
//		System.out
//				.print("id: " + newTopic.id() + "\n" + " desc:" + newTopic.description()
//						+ "\n" + " active:" + newTopic.isActive() + "\n" + " name:"
//						+ newTopic.name());
//		System.out.print("\n update -------------------\n");
//		
//		
//		
		
		

	}
}