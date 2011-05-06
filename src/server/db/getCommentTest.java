package server.db;

import java.util.List;

import shared.Comment;
import shared.Topic;

public class getCommentTest {
	public static void main(String[] args) {

		 

		 /*create comment */
		System.out.print("\n create comment -------------------");
		Comment comm = new Comment(null, "vic Generated comment", "1", "1", null);
		Comment nc = DBComment.create(comm);

//		System.out.print("\nid: " + nc.id() + "\n" + " content:" + nc.content()
//				+ "\n" + " user:" + nc.userName() + "\n" + " thread:"
//				+ nc.threadId());
		System.out.println(nc);
		System.out.print("\n create-------------------END");

		
		/* getAll */
		List<Comment> com = DBComment.getAll("1");
		System.out.print("\n getAll -------------------START\n");
		System.out.print("total: "+com.size());
		
		for (Comment c : com) {
//			System.out.print("\nid: " + c.id() + "\n" + " content:"
//					+ c.content() + "\n" + " user:" + c.userName() + "\n"
//					+ " thread:" + c.threadId());
			
			System.out.println(c);
			System.out.print("\n -------------------");
		}
		
		
//		 update Comment 
		Comment uc = new Comment("6", "must update commnent", "JohnJohn",
				"1", null);
		
		Comment updated = DBComment.update(uc);
		System.out.print("\n update -------------------START\n");
		System.out.print("\nid: " + updated.id() + "\n" + " content:" + updated.content()
				+ "\n" + " user:" + updated.userName() + "\n" + " thread:"
				+ updated.threadId());
		System.out.print("\n -------------------");


	}
}