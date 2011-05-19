package server.db;

import java.util.List;

import shared.Comment;
import shared.Topic;

public class getCommentTest {
	public static void main(String[] args) {

		/* create comment */
		System.out.print(" create comment -------------------\n");
		Comment comm = new Comment(null, "vic Generated comment", "1", "1",
				null);
		Comment nc = DBComment.create(comm);

		System.out.println(nc);
		System.out.print("create-------------------END");

		/* getAll */
		List<Comment> com = DBComment.getAll("1");
		System.out.print("\n getAll -------------------START\n");
		System.out.print("total: " + com.size());

		for (Comment c : com) {

			System.out.println(c);

		}

		// update Comment
		Comment uc = new Comment("43", "must update commnent", "JohnJohn", "1",
				null);

		Comment updated = DBComment.update(uc);

		System.out.println(updated);
		System.out.print("\n -------------------");

	}
}