package shared;

import java.util.HashMap;

import server.Chat;

/**
 * Main <{@link Comment} Class. Server/Client common class for RMI
 * 
 * @author Victor Nava
 * 
 */
public class Comment implements java.io.Serializable {
	private String id = null;
	private String content = null;
	private String userName = null;
	private String threadId = null;
	private String created = null;

	public Comment() {
	};

	/**
	 * Comment contractor
	 * 
	 * @param id
	 * @param content
	 * @param userName
	 * @param threadId
	 * @param created
	 */
	public Comment(String id, String content, String userName, String threadId,
			String created) {
		this.id = id;
		this.content = content;
		this.userName = userName;
		this.threadId = threadId;
		this.created = created;
	}

	public String id() {
		return id;
	}

	public String content() {
		return content;
	}

	public String userName() {
		return userName;
	}

	public String threadId() {
		return threadId;
	}

	public String created() {
		return created;
	}

	public String toString() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("content", content);
		map.put("userName", userName);
		map.put("threadId", threadId);
		map.put("created", created);
		return map.toString();
	}

}